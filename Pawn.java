import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Pawn extends ChessPiece {
	static Boolean ElPassant = false;
	Boolean FirstMove = true;

	Pawn(Boolean whichplayer, int[] startingcoordinates, int numbergiven, Boolean ElPassant) {
		super(whichplayer, startingcoordinates, numbergiven, "pawn");
	}

	public ArrayList<int[]> simplifiedpossiblemoves(ChessPiece[][] board) {
		int x = getposition()[0];
		int y = getposition()[1];
		// might cause a bug
		ArrayList<int[]> possiblemoves = new ArrayList<int[]>();
		// if designation number is -1, space is empty
		if (getalive()) {
			if (getplayer() == Black) {
				if (y + 1 < 8 && board[x][y + 1].getdesignationnumber() == -1) {
					possiblemoves.add(new int[] { x, y + 1 });
					if (FirstMove && y + 2 < 8 && board[x][y + 2].getdesignationnumber() == -1) {
						possiblemoves.add(new int[] { x, y + 2 });
					}
					if (FirstMove && y + 2 < 8 && ElPassant && board[x][y + 2].getplayer() == White && x + 1 < 8
							&& board[x + 1][y + 2].getdesignationnumber() == -1) {
						possiblemoves.add(new int[] { x + 1, y + 2, board[x][y + 2].getdesignationnumber() });
					}
					if (FirstMove && y + 2 < 8 && ElPassant && board[x][y + 2].getplayer() == White && x - 1 >= 0
							&& board[x - 1][y + 2].getdesignationnumber() == -1) {
						possiblemoves.add(new int[] { x - 1, y + 2, board[x][y + 2].getdesignationnumber() });
					}
				}
				if (x + 1 < 8 && y + 1 < 8 && board[x + 1][y + 1].getplayer() == White) {
					possiblemoves.add(new int[] { x + 1, y + 1, board[x + 1][y + 1].getdesignationnumber() });
				}
				if (x - 1 >= 0 && y + 1 < 8 && board[x - 1][y + 1].getplayer() == White) {
					possiblemoves.add(new int[] { x - 1, y + 1, board[x - 1][y + 1].getdesignationnumber() });
				}
			} else {
				// pawn is White
				if (y - 1 >= 0 && board[x][y - 1].getdesignationnumber() == -1) {
					possiblemoves.add(new int[] { x, y - 1 });
					if (FirstMove && y - 2 >= 0 && board[x][y - 2].getdesignationnumber() == -1) {
						possiblemoves.add(new int[] { x, y - 2 });
					}
					if (FirstMove && y - 2 >= 0 && ElPassant && board[x][y - 2].getplayer() == Black && x + 1 < 8
							&& board[x + 1][y - 2].getdesignationnumber() == -1) {
						possiblemoves.add(new int[] { x + 1, y - 2, board[x][y - 2].getdesignationnumber() });
					}
					if (FirstMove && y - 2 >= 0 && ElPassant && board[x][y - 2].getplayer() == Black && x - 1 >= 0
							&& board[x - 1][y - 2].getdesignationnumber() == -1) {
						possiblemoves.add(new int[] { x - 1, y - 2, board[x][y - 2].getdesignationnumber() });
					}
				}
				if (x - 1 >= 0 && y - 1 >= 0 && board[x - 1][y - 1].getplayer() == Black
						&& board[x - 1][y - 1].getdesignationnumber() != -1) {
					possiblemoves.add(new int[] { x - 1, y - 1, board[x - 1][y - 1].getdesignationnumber() });
				}
				if (x + 1 < 8 && y - 1 >= 0 && board[x + 1][y - 1].getplayer() == Black
						&& board[x + 1][y - 1].getdesignationnumber() != -1) {
					possiblemoves.add(new int[] { x + 1, y - 1, board[x + 1][y - 1].getdesignationnumber() });
				}
			}
		}
		return possiblemoves;
	}

	public static void changeElPassant(Boolean ElPassantyesorno) {
		ElPassant = ElPassantyesorno;
	}

	public void setposition(final int x, final int y) {
		position[0] = x;
		position[1] = y;
		if (!ChessBoard.freezegame&&(getplayer() == White && y == 0) || (getplayer() == Black && y == 7)) {
			final JFrame Prompt = new JFrame();
			JButton Queen = new JButton("Queen");
			Queen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Prompt.dispose();
					ChessBoard.board[x][y] = new Queen(getplayer(), new int[] { x, y }, getdesignationnumber());
					ChessBoard.freezegame = false;
				}
			});
			Queen.setBounds(0, 200, 100, 100);
			JButton Knight = new JButton("Knight");
			Knight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Prompt.dispose();
					ChessBoard.board[x][y] = new Knight(getplayer(), new int[] { x, y }, getdesignationnumber());
					ChessBoard.freezegame = false;
				}
			});
			Knight.setBounds(100, 200, 100, 100);
			JButton Bishop = new JButton("Bishop");
			Bishop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Prompt.dispose();
					ChessBoard.board[x][y] = new Bishop(getplayer(), new int[] { x, y }, getdesignationnumber());
					ChessBoard.freezegame = false;
				}
			});
			Bishop.setBounds(200, 200, 100, 100);
			JButton Rook = new JButton("Rook");
			Rook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Prompt.dispose();
					ChessBoard.board[x][y] = new Rook(getplayer(), new int[] { x, y }, getdesignationnumber());
					ChessBoard.freezegame = false;
				}
			});
			Rook.setBounds(300, 200, 100, 100);
			Prompt.setBounds(400, 400, 422, 400);
			Prompt.setLayout(null);
			Prompt.add(Queen);
			Prompt.add(Knight);
			Prompt.add(Bishop);
			Prompt.add(Rook);
			// Prompt.pack();
			Prompt.setVisible(true);
			ChessBoard.freezegame = true;
		}

	}
}
