import java.util.*;
import javax.swing.JLabel;

public class ChessPiece {
	public static final Boolean White = true;
	public static final Boolean Black = false;
	private final int designationnumber;

	// note to self: not needed but useful
	protected int[] position = new int[2];

	private boolean player;
	private boolean alive = true;
	private String piecetype;
	public int score = 0;
	JLabel Image = new JLabel();

	ChessPiece(boolean whichplayer, int[] startingcoordinates, int numbergiven, String piecetype) {
		player = whichplayer;
		position[0] = startingcoordinates[0];
		position[1] = startingcoordinates[1];
		designationnumber = numbergiven;
		this.piecetype = piecetype;
		if (piecetype.equals("pawn")) {
			score = 1;
		} else if (piecetype.equals("bishop") || piecetype.equals("knight")) {
			score = 3;
		} else if (piecetype.equals("rook")) {
			score = 5;
		} else if (piecetype.equals("queen")) {
			score = 9;
		}
	}

	public String gettype() {
		return piecetype;
	}

	public int[] getposition() {
		return position;
	}

	public void setposition(int x, int y) {
		position[0] = x;
		position[1] = y;
	}

	public int getdesignationnumber() {
		return designationnumber;
	}

	public Boolean getplayer() {
		return player;
	}

	public ArrayList<int[]> possiblemoves(ChessPiece[][] board) {
		return remove(simplifiedpossiblemoves(board), board);
		// to be specialized
	}

	public ArrayList<int[]> simplifiedpossiblemoves(ChessPiece[][] board) {
		return new ArrayList<>();
	}

	public Boolean getalive() {
		return alive;
	}

	public String toString() {
		return "----------\nPiece: " + piecetype + "\nSide: " + player + "\nPlace: (" + position[0] + ", " + position[1]
				+ ")\nAliveness: " + alive + "\n----------";
	}

	public String getPieceType() {
		return piecetype;
	}

	public void animations(final int startingcoordsx, final int startingcoordsy, final int endingcoordsx,
			final int endingcoordsy) {
		final Timer foranimations = new Timer();
		TimerTask task = new TimerTask() {
			double x = startingcoordsx * 75;
			double y = startingcoordsy * 75;
			boolean forward = true;

			public void run() {
				ChessBoard.freezegame = true;
				// ChessBoard.AlivePieces.setVisible(false);
				x += (endingcoordsx - startingcoordsx);
				y += (endingcoordsy - startingcoordsy);
				Image.setBounds((int) x, (int) y, Image.getWidth(), Image.getHeight());
				if (x == endingcoordsx * 75 && y == endingcoordsy * 75) {
					forward = false;
				}
				if (!forward) {
					ChessBoard.freezegame=false;
					ChessBoard.board[endingcoordsx][endingcoordsy] = ChessBoard.board[startingcoordsx][startingcoordsy];
					ChessBoard.board[endingcoordsx][endingcoordsy].setposition(endingcoordsx, endingcoordsy);
					ChessBoard.board[startingcoordsx][startingcoordsy] = new EmptySpace(
							new int[] { startingcoordsx, startingcoordsy });
					ChessBoard.refreshpieces();
					if (ChessBoard.board[endingcoordsx][endingcoordsy].getPieceType().equals("pawn")) {
						Pawn replacement = new Pawn(ChessBoard.board[endingcoordsx][endingcoordsy].getplayer(),
								ChessBoard.board[endingcoordsx][endingcoordsy].getposition(),
								ChessBoard.board[endingcoordsx][endingcoordsy].getdesignationnumber(), Pawn.ElPassant);
						replacement.FirstMove = false;
						ChessBoard.board[endingcoordsx][endingcoordsy] = replacement;
					} else if (ChessBoard.board[startingcoordsx][startingcoordsy].getPieceType().equals("rook")) {
						Rook replacement = new Rook(ChessBoard.board[endingcoordsx][endingcoordsy].getplayer(),
								ChessBoard.board[endingcoordsx][endingcoordsy].getposition(),
								ChessBoard.board[endingcoordsx][endingcoordsy].getdesignationnumber());
						replacement.haveNotMoved = false;
						ChessBoard.board[endingcoordsx][endingcoordsy] = replacement;
					} else if (ChessBoard.board[startingcoordsx][startingcoordsy].getPieceType().equals("king")) {
						King replacement = new King(ChessBoard.board[endingcoordsx][endingcoordsy].getplayer(),
								ChessBoard.board[endingcoordsx][endingcoordsy].getposition(),
								ChessBoard.board[endingcoordsx][endingcoordsy].getdesignationnumber());
						replacement.haveNotMoved = false;
						ChessBoard.board[endingcoordsx][endingcoordsy] = replacement;
					}
					if (ChessBoard.Player == White) {
						ChessBoard.Player = Black;
					} else {
						ChessBoard.Player = White;
					}
					foranimations.cancel();
					ChessBoard.freezegame = false;

					if (ChessBoard.Player == White) {
						ChessBoard.chessClock.switchPlayer();
						ChessBoard.scores.switchPlayer();
					} else {
						ChessBoard.chessClock.switchPlayer();
						ChessBoard.scores.switchPlayer();
					}
					if(ChessBoard.checkForCheckmate()) {
						ChessBoard.freezegame = true;
						if(ChessBoard.Player == ChessBoard.White) {
							ChessBoard.forother.BlackCheckmateVisible(true);
							ChessBoard.forother.WelcomeVisible(false);
							ChessClock.mode = ChessClock.PAUSED;
						}else {
							ChessBoard.forother.WhiteCheckmateVisible(true);
							ChessBoard.forother.WelcomeVisible(false);
							ChessClock.mode = ChessClock.PAUSED;
						}
					}
					// ChessBoard.checkForCheck();
					// foranimations.purge();
				}
				// ChessBoard.refreshpieces();
				// ChessBoard.AlivePieces.setVisible(true);
			}
		};
		foranimations.scheduleAtFixedRate(task, 0, 10);
	}

	public ChessPiece clone() {
		if (piecetype.equals("bishop")) {
			return new Bishop(player, position, designationnumber);
		} else if (piecetype.equals("pawn")) {
			Pawn result = new Pawn(player, position, designationnumber, alive);
			Pawn cloned = (Pawn) (ChessBoard.board[position[0]][position[1]]);
			result.FirstMove = cloned.FirstMove;
			return result;
		} else if (piecetype.equals("knight")) {
			return new Knight(player, position, designationnumber);
		} else if (piecetype.equals("rook")) {
			return new Rook(player, position, designationnumber);
		} else if (piecetype.equals("queen")) {
			return new Queen(player, position, designationnumber);
		} else if (piecetype.equals("king")) {
			return new King(player, position, designationnumber);
		} else {
			return new EmptySpace(position);
		}
	}

	public ArrayList<int[]> remove(ArrayList<int[]> input, ChessPiece[][] board) {
		ChessBoard.freezegame = true;
		int k = 0;
		while (k < input.size()) {
			ChessPiece end = board[input.get(k)[0]][input.get(k)[1]];
			ChessPiece start = board[position[0]][position[1]];
			int x = position[0];
			int y = position[1];
			int a = input.get(k)[0];
			int b = input.get(k)[1];
			board[a][b] = board[x][y];
			board[x][y] = new EmptySpace(new int[] { x, y });
			this.setposition(a, b);
			boolean remove = false;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (getplayer() == White) {
						if (ChessBoard.contains(board[i][j].simplifiedpossiblemoves(board), new int[] { King.whitex,
								King.whitey, board[King.whitex][King.whitey].getdesignationnumber() })) {
							remove = true;
							//System.out.print("yes");
						}
					} else {
						if (ChessBoard.contains(board[i][j].simplifiedpossiblemoves(board), new int[] { King.blackx,
								King.blacky, board[King.blackx][King.blacky].getdesignationnumber() })) {
							remove = true;
							//System.out.print("yes");
						}
					}
				}
			}
			board[a][b] = end;
			board[x][y] = start;
			start.setposition(x, y);
			if (remove) {
				input.remove(k);
			} else {
				k++;
			}
		}
		ChessBoard.freezegame = false;
		return input;
	}
}