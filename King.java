import java.util.ArrayList;

public class King extends ChessPiece {
	public boolean haveNotMoved = true;
	static int whitex;
	static int originalwhitex;
	static int whitey;
	static int blackx;
	static int blacky;
	static int originalblackx;

	King(boolean whichplayer, int[] startingcoordinates, int numbergiven) {
		super(whichplayer, startingcoordinates, numbergiven, "king");
		if (whichplayer == White) {
			whitex = startingcoordinates[0];
			whitey = startingcoordinates[1];
			originalwhitex = startingcoordinates[0];
		} else {
			blackx = startingcoordinates[0];
			blacky = startingcoordinates[1];
			originalblackx = startingcoordinates[0];
		}
	}

	public ArrayList<int[]> simplifiedpossiblemoves(ChessPiece[][] board) {
		ArrayList<int[]> result = new ArrayList<int[]>();
		if (getalive()) {
			if (getplayer() == White) {
				if (whitey + 1 < 8 && (board[whitex][whitey + 1].getdesignationnumber() == -1)) {
					result.add(new int[] { whitex, whitey + 1 });
				}
				if (whitey - 1 >= 0 && (board[whitex][whitey - 1].getdesignationnumber() == -1)) {
					result.add(new int[] { whitex, whitey - 1 });
				}
				if (whitex - 1 >= 0 && (board[whitex - 1][whitey].getdesignationnumber() == -1)) {
					result.add(new int[] { whitex - 1, whitey });
				}
				if (whitex + 1 < 8 && (board[whitex + 1][whitey].getdesignationnumber() == -1)) {
					result.add(new int[] { whitex + 1, whitey });
				}
				if (whitex + 1 < 8 && whitey - 1 >= 0 && (board[whitex + 1][whitey - 1].getdesignationnumber() == -1)) {
					result.add(new int[] { whitex + 1, whitey - 1 });
				}
				if (whitex + 1 < 8 && whitey + 1 < 8 && (board[whitex + 1][whitey + 1].getdesignationnumber() == -1)) {
					result.add(new int[] { whitex + 1, whitey + 1 });
				}
				if (whitex - 1 >= 0 && whitey - 1 >= 0
						&& (board[whitex - 1][whitey - 1].getdesignationnumber() == -1)) {
					result.add(new int[] { whitex - 1, whitey - 1 });
				}
				if (whitex - 1 >= 0 && whitey + 1 < 8 && (board[whitex - 1][whitey + 1].getdesignationnumber() == -1)) {
					result.add(new int[] { whitex - 1, whitey + 1 });
				}

			} else {
				if (blacky + 1 < 8 && (board[blackx][blacky + 1].getdesignationnumber() == -1)) {
					result.add(new int[] { blackx, blacky + 1 });
				}
				if (blacky - 1 >= 0 && (board[blackx][blacky - 1].getdesignationnumber() == -1)) {
					result.add(new int[] { blackx, blacky - 1 });
				}
				if (blackx - 1 < 8 && (board[blackx - 1][blacky].getdesignationnumber() == -1)) {
					result.add(new int[] { blackx - 1, blacky });
				}
				if (blackx + 1 < 8 && (board[blackx + 1][blacky].getdesignationnumber() == -1)) {
					result.add(new int[] { blackx + 1, blacky });
				}
				if (blackx + 1 < 8 && blacky - 1 >= 0 && (board[blackx + 1][blacky - 1].getdesignationnumber() == -1)) {
					result.add(new int[] { blackx + 1, blacky - 1 });
				}
				if (blackx + 1 < 8 && blacky + 1 < 8 && (board[blackx + 1][blacky + 1].getdesignationnumber() == -1)) {
					result.add(new int[] { blackx + 1, blacky + 1 });
				}
				if (blackx - 1 < 8 && blacky - 1 >= 0 && (board[blackx - 1][blacky - 1].getdesignationnumber() == -1)) {
					result.add(new int[] { blackx - 1, blacky - 1 });
				}
				if (blackx - 1 >= 0 && blacky + 1 < 8 && (board[blackx - 1][blacky + 1].getdesignationnumber() == -1)) {
					result.add(new int[] { blackx - 1, blacky + 1 });
				}
			}
			if (getplayer() == White) {
				if (whitey + 1 < 8 && (board[whitex][whitey + 1].getdesignationnumber() != -1
						&& board[whitex][whitey + 1].getplayer() != White)) {
					result.add(new int[] { whitex, whitey + 1, -1 });
				}
				if (whitey - 1 >= 0 && (board[whitex][whitey - 1].getdesignationnumber() != -1
						&& board[whitex][whitey - 1].getplayer() != White)) {
					result.add(new int[] { whitex, whitey - 1, -1 });
				}
				if (whitex - 1 >= 0 && (board[whitex - 1][whitey].getdesignationnumber() != -1
						&& board[whitex - 1][whitey].getplayer() != White)) {
					result.add(new int[] { whitex - 1, whitey, -1 });
				}
				if (whitex + 1 < 8 && (board[whitex + 1][whitey].getdesignationnumber() != -1
						&& board[whitex + 1][whitey].getplayer() != White)) {
					result.add(new int[] { whitex + 1, whitey, -1 });
				}
				if (whitex + 1 < 8 && whitey - 1 >= 0 && (board[whitex + 1][whitey - 1].getdesignationnumber() != -1
						&& board[whitex + 1][whitey - 1].getplayer() != White)) {
					result.add(new int[] { whitex + 1, whitey - 1, -1 });
				}
				if (whitex + 1 < 8 && whitey + 1 < 8 && (board[whitex + 1][whitey + 1].getdesignationnumber() != -1
						&& board[whitex + 1][whitey + 1].getplayer() != White)) {
					result.add(new int[] { whitex + 1, whitey + 1, -1 });
				}
				if (whitex - 1 >= 0 && whitey - 1 >= 0 && (board[whitex - 1][whitey - 1].getdesignationnumber() != -1
						&& board[whitex - 1][whitey - 1].getplayer() != White)) {
					result.add(new int[] { whitex - 1, whitey - 1, -1 });
				}
				if (whitex - 1 >= 0 && whitey + 1 < 8 && (board[whitex - 1][whitey + 1].getdesignationnumber() != -1
						&& board[whitex - 1][whitey + 1].getplayer() != White)) {
					result.add(new int[] { whitex - 1, whitey + 1, -1 });
				}

			} else {
				if (blacky + 1 < 8 && (board[blackx][blacky + 1].getdesignationnumber() != -1
						&& board[blackx][blacky + 1].getplayer() != Black)) {
					result.add(new int[] { blackx, blacky + 1, -1 });
				}
				if (blacky - 1 >= 0 && (board[blackx][blacky - 1].getdesignationnumber() != -1
						&& board[blackx][blacky - 1].getplayer() != Black)) {
					result.add(new int[] { blackx, blacky - 1, -1 });
				}
				if (blackx - 1 < 8 && (board[blackx - 1][blacky].getdesignationnumber() != -1
						&& board[blackx - 1][blacky].getplayer() != Black)) {
					result.add(new int[] { blackx - 1, blacky, -1 });
				}
				if (blackx + 1 < 8 && (board[blackx + 1][blacky].getdesignationnumber() != -1
						&& board[blackx + 1][blacky].getplayer() != Black)) {
					result.add(new int[] { blackx + 1, blacky, -1 });
				}
				if (blackx + 1 < 8 && blacky - 1 >= 0 && (board[blackx + 1][blacky - 1].getdesignationnumber() != -1
						&& board[blackx + 1][blacky - 1].getplayer() != Black)) {
					result.add(new int[] { blackx + 1, blacky - 1, -1 });
				}
				if (blackx + 1 < 8 && blacky + 1 < 8 && (board[blackx + 1][blacky + 1].getdesignationnumber() != -1
						&& board[blackx + 1][blacky + 1].getplayer() != Black)) {
					result.add(new int[] { blackx + 1, blacky + 1, -1 });
				}
				if (blackx - 1 < 8 && blacky - 1 >= 0 && (board[blackx - 1][blacky - 1].getdesignationnumber() != -1
						&& board[blackx - 1][blacky - 1].getplayer() != Black)) {
					result.add(new int[] { blackx - 1, blacky - 1, -1 });
				}
				if (blackx - 1 >= 0 && blacky + 1 < 8 && (board[blackx - 1][blacky + 1].getdesignationnumber() != -1
						&& board[blackx - 1][blacky + 1].getplayer() != Black)) {
					result.add(new int[] { blackx - 1, blacky + 1, -1 });
				}
			}
		}
		return result;
	}

	public void setposition(int x, int y) {
		super.setposition(x, y);
		if (getplayer() == White) {
			whitex = x;
			whitey = y;
		} else {
			blackx = x;
			blacky = y;
		}
	}
}