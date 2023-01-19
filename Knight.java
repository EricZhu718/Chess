import java.util.*;

public class Knight extends ChessPiece {
	Knight(boolean whichplayer, int[] startingcoordinates, int numbergiven) {
		super(whichplayer, startingcoordinates, numbergiven, "knight");
	}

	public ArrayList<int[]> simplifiedpossiblemoves(ChessPiece[][] board) {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		int x = getposition()[0];
		int y = getposition()[1];
		if (getalive()) {
			if (x + 2 < 8 && y + 1 < 8 && board[x + 2][y + 1].getdesignationnumber() == -1) {
				moves.add(new int[] { x + 2, y + 1 });
			} else if (x + 2 < 8 && y + 1 < 8 && board[x + 2][y + 1].getplayer() != getplayer()) {
				moves.add(new int[] { x + 2, y + 1, board[x + 2][y + 1].getdesignationnumber() });
			}
			if (x + 1 < 8 && y + 2 < 8 && board[x + 1][y + 2].getdesignationnumber() == -1) {
				moves.add(new int[] { x + 1, y + 2 });
			} else if (x + 1 < 8 && y + 2 < 8 && board[x + 1][y + 2].getplayer() != getplayer()) {
				moves.add(new int[] { x + 1, y + 2, board[x + 1][y + 2].getdesignationnumber() });
			}
			if (x - 2 >= 0 && y + 1 < 8 && board[x - 2][y + 1].getdesignationnumber() == -1) {
				moves.add(new int[] { x - 2, y + 1 });
			} else if (x - 2 >= 0 && y + 1 < 8 && board[x - 2][y + 1].getplayer() != getplayer()) {
				moves.add(new int[] { x - 2, y + 1, board[x - 2][y + 1].getdesignationnumber() });
			}
			if (x - 1 >= 0 && y + 2 < 8 && board[x - 1][y + 2].getdesignationnumber() == -1) {
				moves.add(new int[] { x - 1, y + 2 });
			} else if (x - 1 >= 0 && y + 2 < 8 && board[x - 1][y + 2].getplayer() != getplayer()) {
				moves.add(new int[] { x - 1, y + 2, board[x - 1][y + 2].getdesignationnumber() });
			}
			if (x + 2 < 8 && y - 1 >= 0 && board[x + 2][y - 1].getdesignationnumber() == -1) {
				moves.add(new int[] { x + 2, y - 1 });
			} else if (x + 2 < 8 && y - 1 >= 0 && board[x + 2][y - 1].getplayer() != getplayer()) {
				moves.add(new int[] { x + 2, y - 1, board[x + 2][y - 1].getdesignationnumber() });
			}
			if (x + 1 < 8 && y - 2 >= 0 && board[x + 1][y - 2].getdesignationnumber() == -1) {
				moves.add(new int[] { x + 1, y - 2 });
			} else if (x + 1 < 8 && y - 2 >= 0 && board[x + 1][y - 2].getplayer() != getplayer()) {
				moves.add(new int[] { x + 1, y - 2, board[x + 1][y - 2].getdesignationnumber() });
			}
			if (x - 2 >= 0 && y - 1 >= 0 && board[x - 2][y - 1].getdesignationnumber() == -1) {
				moves.add(new int[] { x - 2, y - 1 });
			} else if (x - 2 >= 0 && y - 1 >= 0 && board[x - 2][y - 1].getplayer() != getplayer()) {
				moves.add(new int[] { x - 2, y - 1, board[x - 2][y - 1].getdesignationnumber() });
			}
			if (x - 1 >= 0 && y - 2 >= 0 && board[x - 1][y - 2].getdesignationnumber() == -1) {
				moves.add(new int[] { x - 1, y - 2 });
			} else if (x - 1 >= 0 && y - 2 >= 0 && board[x - 1][y - 2].getplayer() != getplayer()) {
				moves.add(new int[] { x - 1, y - 2, board[x - 1][y - 2].getdesignationnumber() });
			}
		}
		return moves;
	}

}
