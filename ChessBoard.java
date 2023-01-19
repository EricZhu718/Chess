import java.util.*;
import java.util.Timer;
import javax.swing.*;
//import java.awt.Color;
import java.awt.event.*;

public class ChessBoard implements MouseListener, MouseMotionListener {
	static JFrame GUI = new JFrame();
	static ChessBoard forother;
	static JPanel Board = new JPanel();
	static JPanel AlivePieces = new JPanel();
	static JPanel DeadPieces = new JPanel();
	static ArrayList<ChessPiece> deadpieces = new ArrayList<ChessPiece>();
	static JPanel Score = new JPanel();
	static JPanel StartScreen = new JPanel();
	static String Screen;
	static ArrayList<int[]> squareslitup = new ArrayList<int[]>();
	static ChessPiece[][] board = new ChessPiece[8][8];
	final static boolean White = ChessPiece.White;
	final static boolean Black = ChessPiece.Black;
	static int selectedxcoordinate;
	static int selectedycoordinate;
	static boolean ElPassant = false;
	static boolean Player = White;
	static boolean pieceselected = false;
	static boolean whitecheck = false;
	static boolean blackcheck = false;
	static boolean freezegame = false;
	static Timer delay = new Timer();
	static JLabel WhiteScore = new JLabel();
	static JLabel BlackScore = new JLabel();
	static Score scores = new Score();
	static ChessClock chessClock = new ChessClock();
	static Screens welcome = new Screens("src/newwelcome.png");
	static Screens draw = new Screens("src/drawscreen.png");
	static Screens blackwinsresign = new Screens("src/blackwinsresign.png");
	static Screens whitewinsresign = new Screens("src/whitewinsresign.png");
	static Screens blacktimeout = new Screens("src/blackwinstime.png");
	static Screens whitetimeout = new Screens("src/whitetimeout.png");
	static Screens whitecheckmate = new Screens("src/whitewcheckmate.png");
	static Screens blackcheckmate = new Screens("src/blackwcheckmate.png");
	static boolean check = false;
	static JButton back = new JButton("Back");
	static JButton forward = new JButton("Forward");
	static ArrayList<ChessPiece[][]> pastmoves = new ArrayList<ChessPiece[][]>();
	static ArrayList<ChessPiece[][]> nextmoves = new ArrayList<ChessPiece[][]>();

	public static void setCheck(boolean yes) {
		check = yes;
	}

	public static boolean getCheck() {
		return check;
	}

	public static boolean isCapturableAt(ChessPiece[][] board, int col, int row) {
		/*
		 * board is addressed [col][row], or [x][y] if there is no piece on the board at
		 * [col, row] return false set player = the player at [col, row] for every chess
		 * piece on the board: if the piece is the other player's piece: let l = the
		 * list of possiblemoves the opponent could make from that location if(l
		 * contains [col, row]) return true return false
		 * 
		 */
		boolean whatPlayer;

		if (board[col][row] instanceof EmptySpace) {
			return false;
		}

		whatPlayer = board[col][row].getplayer();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (board[x][y].getplayer() != whatPlayer) {
					ArrayList<int[]> l = board[x][y].possiblemoves(board);
					for (int[] pos : l) {
						if (pos[0] == col && pos[1] == row) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	ChessBoard() {
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pastmoves.size() > 0) {
					backamove();
				}
			}
		});
		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nextmoves.size() > 0) {
					forwardamove();
				}
			}

		});
		back.setBounds(1300, 500, 100, 100);
		forward.setBounds(1300, 600, 100, 100);

		forother = this;
		Board.setLayout(null);
		Board.setBounds(400, 150, 600, 600);
		GUI.setSize(2100, 1200);
		GUI.setLayout(null);
		// displayStart();
		// System.out.println(AlivePieces.isDoubleBuffered());
		// System.out.println(AlivePieces.isOptimizedDrawingEnabled());

		displayGame();
		GUI.addMouseListener(this);
		GUI.addMouseMotionListener(this);
		GUI.setVisible(true);
		GUI.add(forward);
		GUI.add(back);
	}

	public static int scoreMethod(boolean player) {
		int answer = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getplayer() == player) {
					answer += board[i][j].score;
				}
			}
		}
		return answer;
	}

	private void backamove() {
		TurnOffLights();
		pieceselected = false;
		nextmoves.add(new ChessPiece[8][8]);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				nextmoves.get(nextmoves.size() - 1)[i][j] = board[i][j].clone();
			}
		}
		board = pastmoves.get(pastmoves.size() - 1);
		pastmoves.remove(pastmoves.size() - 1);
		refreshpieces();
		Player = !Player;
		chessClock.switchPlayer();
	}

	private void forwardamove() {
		TurnOffLights();
		pieceselected = false;
		pastmoves.add(new ChessPiece[8][8]);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pastmoves.get(pastmoves.size() - 1)[i][j] = board[i][j].clone();
			}
		}
		board = nextmoves.get(nextmoves.size() - 1);
		nextmoves.remove(nextmoves.size() - 1);
		refreshpieces();
		Player = !Player;
		chessClock.switchPlayer();
	}

	private void displayGame() {
		Screen = "Game";
		for (int i = 0; i < 8; i++) {
			for (int j = 2; j < 7; j++) {
				board[i][j] = new EmptySpace(new int[] { i, j });
			}
		}
		for (int i = 0; i < 8; i++) {
			board[i][1] = new Pawn(Black, new int[] { i, 1 }, 23 - i, ElPassant);
			board[i][6] = new Pawn(White, new int[] { i, 6 }, 8 + i, ElPassant);
		}
		board[1][0] = new Knight(Black, new int[] { 1, 0 }, 25);
		board[6][0] = new Knight(Black, new int[] { 6, 0 }, 30);
		board[1][7] = new Knight(White, new int[] { 1, 7 }, 1);
		board[6][7] = new Knight(White, new int[] { 6, 7 }, 6);
		board[0][0] = new Rook(Black, new int[] { 0, 0 }, 31);
		board[7][0] = new Rook(Black, new int[] { 7, 0 }, 24);
		board[0][7] = new Rook(White, new int[] { 0, 7 }, 0);
		board[7][7] = new Rook(White, new int[] { 7, 7 }, 7);
		board[2][0] = new Bishop(Black, new int[] { 2, 0 }, 29);
		board[5][0] = new Bishop(Black, new int[] { 5, 0 }, 26);
		board[2][7] = new Bishop(White, new int[] { 2, 7 }, 2);
		board[5][7] = new Bishop(White, new int[] { 5, 7 }, 4);
		board[4][0] = new King(Black, new int[] { 4, 0 }, 27);
		board[4][7] = new King(White, new int[] { 4, 7 }, 3);
		board[3][0] = new Queen(Black, new int[] { 3, 0 }, 28);
		board[3][7] = new Queen(White, new int[] { 3, 7 }, 4);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					JLabel Square = new JLabel(new ImageIcon("src/WhiteSquare.jpg"));
					Square.setBounds(75 * i, 75 * j, 75, 75);
					Board.add(Square);
				} else {
					JLabel Square = new JLabel(new ImageIcon("src/color-swatch.jpg"));
					Square.setBounds(75 * i, 75 * j, 75, 75);
					Board.add(Square);
				}
			}
		}
		AlivePieces.setLayout(null);
		AlivePieces.setOpaque(false);
		AlivePieces.setBounds(400, 150, 600, 600);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!board[i][j].getPieceType().equals("EmptySpace")) {
					String imagename = "src/";
					if (board[i][j].getplayer()) {
						imagename += "white";
					} else {
						imagename += "black";
					}
					imagename += board[i][j].getPieceType() + "_75x75.png";
					JLabel piece = new JLabel(new ImageIcon(imagename));
					piece.setBounds(i * 75, j * 75, 75, 75);
					AlivePieces.add(piece);
					board[i][j].Image = piece;
				}
			}
		}
		/*
		 * WhiteScore = new JLabel(""+score(White)); WhiteScore.setBackground(new
		 * Color(0,0,0)); WhiteScore.setOpaque(true); WhiteScore.setBounds(300, 300,
		 * 100, 100); BlackScore = new JLabel(""+score(Black));
		 * BlackScore.setBackground(new Color(0,0,0)); BlackScore.setOpaque(true);
		 * BlackScore.setBounds(1000, 300, 100, 100); GUI.add(WhiteScore);
		 * GUI.add(BlackScore);
		 */
		GUI.add(AlivePieces, 0);
		GUI.add(Board, 1);

		chessClock.setBounds(-50, 300, 500, 500);
		GUI.add(chessClock, 2);

		// JLabel l = new JLabel("asd");
		// l.setBounds(1050, 300, 30, 30);
		// GUI.add(l, 3);

		scores.setBounds(1050, 450, 180, 300);
		GUI.add(scores, 3);

		welcome.setBounds(50, 4, 300, 300);
		// setComponentZOrder(welcome, 1000);
		welcome.setVisible(true);
		GUI.add(welcome, 4);

		draw.setBounds(50, 4, 300, 300);
		draw.setVisible(false);
		GUI.add(draw, 5);

		blackwinsresign.setBounds(50, 4, 300, 300);
		blackwinsresign.setVisible(false);
		GUI.add(blackwinsresign, 6);

		whitewinsresign.setBounds(50, 4, 300, 300);
		whitewinsresign.setVisible(false);
		GUI.add(whitewinsresign, 7);

		blacktimeout.setBounds(50, 4, 300, 300);
		blacktimeout.setVisible(false);
		GUI.add(blacktimeout, 8);

		whitetimeout.setBounds(50, 4, 300, 300);
		whitetimeout.setVisible(false);
		GUI.add(whitetimeout, 9);
		
		whitecheckmate.setBounds(50, 4, 300, 300);
		whitecheckmate.setVisible(false);
		GUI.add(whitecheckmate, 9);
		
		blackcheckmate.setBounds(50, 4, 300, 300);
		blackcheckmate.setVisible(false);
		GUI.add(blackcheckmate, 9);
	}

	public void BlackCheckmateVisible(boolean change) {
		blackcheckmate.setVisible(change);
	}
	
	public void WhiteCheckmateVisible(boolean change) {
		whitecheckmate.setVisible(change);
	}
	public void WelcomeVisible(boolean change) {
		welcome.setVisible(change);
	}

	public void DrawVisible(boolean change) {
		draw.setVisible(change);
	}

	public void BlackWinsResignVisible(boolean change) {
		blackwinsresign.setVisible(change);
	}

	public void WhiteWinsResignVisible(boolean change) {
		whitewinsresign.setVisible(change);
	}

	public void BlackWinsTimeVisible(boolean change) {
		blacktimeout.setVisible(change);
	}

	public void WhiteWinsTimeVisible(boolean change) {
		whitetimeout.setVisible(change);
	}

	public static boolean lookforcheck(Boolean Player) {
		if (Player == White) {
			// checks if White King is in check
			King WhiteKing = null;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j].getplayer() == White && board[i][j].getPieceType() == "king") {
						WhiteKing = (King) board[i][j];
					}
				}
			}
			// ArrayList<int[]> possiblemovesofblackpieces = new ArrayList<int[]>();
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j].getplayer() == Black) {
						ArrayList<int[]> possiblemoves = board[i][j].possiblemoves(board);
						if (possiblemoves.contains(new int[] { WhiteKing.position[0], WhiteKing.position[1],
								WhiteKing.getdesignationnumber() })) {
							return true;
						}
					}
				}
			}
			return false;
		} else {
			// Checks if Black King is in check
			King BlackKing = null;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j].getplayer() == Black && board[i][j].getPieceType() == "king") {
						BlackKing = (King) board[i][j];
					}
				}
			}
			// ArrayList<int[]> possiblemovesofwhitepieces = new ArrayList<int[]>();
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j].getplayer() == Black) {
						ArrayList<int[]> possiblemoves = board[i][j].possiblemoves(board);
						if (possiblemoves.contains(new int[] { BlackKing.position[0], BlackKing.position[1],
								BlackKing.getdesignationnumber() })) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}

	public static void refreshpieces() {
		AlivePieces.setVisible(false);
		AlivePieces.removeAll();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!board[i][j].getPieceType().equals("EmptySpace")) {
					String imagename = "src/";
					if (board[i][j].getplayer()) {
						imagename += "white";
					} else {
						imagename += "black";
					}
					imagename += board[i][j].getPieceType() + "_75x75.png";
					board[i][j].Image = new JLabel(new ImageIcon(imagename));
					board[i][j].Image.setBounds(i * 75, j * 75, 75, 75);
					AlivePieces.add(board[i][j].Image);
				}
			}
		}
		AlivePieces.revalidate();
		AlivePieces.repaint();
		AlivePieces.setVisible(true);
	}

	public void mouseMoved(MouseEvent arg0) {
		if (!freezegame) {
			if (Screen.equals("Game")) {
				int x = arg0.getX() - 8;
				int y = arg0.getY() - 30;
				if (!pieceselected) {
					TurnOffLights();
				}
				if (!pieceselected && (x - 400) / 75 >= 0 && (x - 400) / 75 < 8 && (y - 150) / 75 >= 0
						&& (y - 150) / 75 < 8 && board[(x - 400) / 75][(y - 150) / 75].getplayer() == Player) {
					ArrayList<int[]> moves = board[(x - 400) / 75][(y - 150) / 75].possiblemoves(board);
					for (int k = 0; k < moves.size(); k++) {
						LightUpSquare(moves.get(k));
					}
				}
			}
		}
		// refreshpieces();
	}

	private static void LightUpSquare(int[] coordinates) {
		Board.setVisible(false);
		if (coordinates.length == 3) {
			JLabel square = (JLabel) Board.getComponentAt(75 * coordinates[0] + 50, 75 * coordinates[1] + 50);
			square.setIcon(new ImageIcon("src/YellowSquare.jpg"));
			squareslitup.add(new int[] { coordinates[0], coordinates[1] });
		} else {
			JLabel square = (JLabel) Board.getComponentAt(75 * coordinates[0] + 50, 75 * coordinates[1] + 50);
			square.setIcon(new ImageIcon("src/CyanSquare.png"));
			squareslitup.add(new int[] { coordinates[0], coordinates[1] });
		}
		// refreshpieces();
		Board.setVisible(true);
	}

	public static void ChangeElPassant(Boolean yesorno) {
		ElPassant = yesorno;
		Pawn.changeElPassant(yesorno);
	}

	public static void displaypieces() {
		AlivePieces.removeAll();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				AlivePieces.add(board[i][j].Image);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (!freezegame) {
			if (Screen.equals("Game")) {
				int x = e.getX() - 8;
				int y = e.getY() - 30;
				if (!pieceselected && (x - 400) / 75 >= 0 && (x - 400) / 75 < 8 && (y - 150) / 75 >= 0
						&& (y - 150) / 75 < 8 && board[(x - 400) / 75][(y - 150) / 75].getplayer() == Player) {
					TurnOffLights();
					ArrayList<int[]> moves = board[(x - 400) / 75][(y - 150) / 75].possiblemoves(board);
					for (int k = 0; k < moves.size(); k++) {
						LightUpSquare(moves.get(k));
					}
					pieceselected = true;
					selectedxcoordinate = (x - 400) / 75;
					selectedycoordinate = (y - 150) / 75;
				} else if (pieceselected && selectedxcoordinate == (x - 400) / 75
						&& selectedycoordinate == (y - 150) / 75
						&& board[(x - 400) / 75][(y - 150) / 75].getplayer() == Player) {
					TurnOffLights();
					pieceselected = false;
				} else if (pieceselected && (x - 400) / 75 >= 0 && (x - 400) / 75 < 8 && (y - 150) / 75 >= 0
						&& (y - 150) / 75 < 8 && board[(x - 400) / 75][(y - 150) / 75].getdesignationnumber() != -1
						&& board[(x - 400) / 75][(y - 150) / 75].getplayer() == Player) {
					TurnOffLights();
					ArrayList<int[]> moves = board[(x - 400) / 75][(y - 150) / 75].possiblemoves(board);
					for (int k = 0; k < moves.size(); k++) {
						LightUpSquare(moves.get(k));
					}
					pieceselected = true;
					selectedxcoordinate = (x - 400) / 75;
					selectedycoordinate = (y - 150) / 75;
				} else if (pieceselected && (x - 400) / 75 >= 0 && (x - 400) / 75 < 8 && (y - 150) / 75 >= 0
						&& (y - 150) / 75 < 8
						&& (contains(squareslitup,
								new int[] { (x - 400) / 75, (y - 150) / 75,
										board[(x - 400) / 75][(y - 150) / 75].getdesignationnumber() })
								|| contains(squareslitup, new int[] { (x - 400) / 75, (y - 150) / 75 }))) {
					Move(selectedxcoordinate, selectedycoordinate, (x - 400) / 75, (y - 150) / 75);
					pieceselected = false;
					TurnOffLights();
					refreshpieces();
					// refreshdeadpieces();
				}
			}
		}
	}

	public static boolean contains(ArrayList<int[]> coordinateslist, int[] coordinates) {
		for (int i = 0; i < coordinateslist.size(); i++) {
			boolean check = true;
			for (int j = 0; j < coordinateslist.get(i).length; j++) {
				if (coordinateslist.get(i)[j] != coordinates[j]) {
					check = false;
				}
			}
			if (check) {
				return true;
			}
		}
		return false;
	}

	private static void refreshdeadpieces() {
		int whitepieces = 0;
		int blackpieces = 0;
		for (int i = 0; i < deadpieces.size(); i++) {
			if (deadpieces.get(i).getplayer() == White) {
				deadpieces.get(i).Image.setBounds(275, 75 * whitepieces + 150, 75, 75);
				whitepieces++;
			} else {
				deadpieces.get(i).Image.setBounds(1050, 75 * blackpieces + 150, 75, 75);
				blackpieces++;
			}
		}
	}

	public static void Move(int startingcoordsx, int startingcoordsy, int endingcoordsx, int endingcoordsy) {
		pastmoves.add(new ChessPiece[8][8]);
		nextmoves = new ArrayList<ChessPiece[][]>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pastmoves.get(pastmoves.size() - 1)[i][j] = board[i][j].clone();
			}
		}
		if (!board[endingcoordsx][endingcoordsy].gettype().equals("EmptySpace")) {
			deadpieces.add(board[endingcoordsx][endingcoordsy]);
			refreshdeadpieces();
		}
		board[startingcoordsx][startingcoordsy].animations(startingcoordsx, startingcoordsy, endingcoordsx,
				endingcoordsy);

	}

	public static boolean checkForCheckmate() {
		
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					if (board[x][y] instanceof EmptySpace) {

					}else if(board[x][y].getplayer() == Player){
						ArrayList<int[]> possiblemoves = board[x][y].possiblemoves(board); 
						if(possiblemoves.size() != 0) {
							
							return false;

						}
					}
				}
		}
		System.out.println("CHECKMATE");
		
		return true;
	}
	public static void checkForCheck() {
		/*
		 * search thru board to find [col, row] for current player's king if(col,row is
		 * capturable) check = true else false
		 */
		int[] place = new int[2];

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (board[x][y].gettype().equals("king") && board[x][y].getplayer() == Player) {
					place[0] = x;
					place[1] = y;
				}
			}
		}
		// System.out.println("Found king at col " + place[0] + ", row " + place[1]);

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				// System.out.print(isCapturableAt(board, y, x) ? "Y " : ". ");
			}
			// System.out.println();
		}

		if (isCapturableAt(board, place[0], place[1])) {
			check = true;
		} else {
			check = false;
		}

		// System.out.println("CHECK: " + check);
	}

	private static void TurnOffLights() {
		while (squareslitup.size() > 0) {
			JLabel square = (JLabel) Board.getComponentAt(75 * squareslitup.get(0)[0], 75 * (squareslitup.get(0)[1]));
			if ((squareslitup.get(0)[0] + squareslitup.get(0)[1]) % 2 == 0) {
				square.setIcon(new ImageIcon("src/WhiteSquare.jpg"));
			} else {
				square.setIcon(new ImageIcon("src/color-swatch.jpg"));
			}
			squareslitup.remove(0);
		}
		refreshpieces();
	}

	public static void main(String[] args) {
		new ChessBoard();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
