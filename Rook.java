import java.util.ArrayList;

public class Rook extends ChessPiece {
	public boolean haveNotMoved=true;
	Rook(boolean whichplayer, int[] startingcoordinates, int numbergiven) {
		super(whichplayer, startingcoordinates, numbergiven, "rook");
	}
	public void setposition(int x, int y) {
		super.setposition(x, y);
		haveNotMoved=false;
	}
	public ArrayList<int[]> simplifiedpossiblemoves(ChessPiece[][] Board) {
		ArrayList<int[]> output = new ArrayList<int[]>();
		boolean proceed = true;
		for (int i = 1; proceed && getposition()[0] + i < 8; i++) {
			if (Board[getposition()[0] + i][getposition()[1]].getdesignationnumber() == -1) {
				output.add(new int[] { getposition()[0] + i, getposition()[1] });
			} else if (Board[getposition()[0] + i][getposition()[1]].getplayer() != getplayer()) {
				output.add(new int[] { getposition()[0] + i, getposition()[1],
						Board[getposition()[0] + i][getposition()[1]].getdesignationnumber() });
				proceed = false;
			} else {
				// piece at Board[getposition()[0]+i][getposition()[1]+i] is same side as the
				// bishop
				proceed = false;
			}
		}
		proceed = true;
		for (int i = 1; proceed && getposition()[0] - i >= 0; i++) {
			if (Board[getposition()[0] - i][getposition()[1]].getdesignationnumber() == -1) {
				output.add(new int[] { getposition()[0] - i, getposition()[1] });
			} else if (Board[getposition()[0] - i][getposition()[1]].getplayer() != getplayer()) {
				output.add(new int[] { getposition()[0] - i, getposition()[1],
						Board[getposition()[0] - i][getposition()[1]].getdesignationnumber() });
				proceed = false;
			} else {
				// piece at Board[getposition()[0]+i][getposition()[1]+i] is same side as the
				// bishop
				proceed = false;
			}
		}
		proceed = true;
		for (int i = 1; proceed && getposition()[1] - i >= 0; i++) {
			if (Board[getposition()[0]][getposition()[1] - i].getdesignationnumber() == -1) {
				output.add(new int[] { getposition()[0], getposition()[1] - i });
			} else if (Board[getposition()[0]][getposition()[1] - i].getplayer() != getplayer()) {
				output.add(new int[] { getposition()[0], getposition()[1] - i,
						Board[getposition()[0]][getposition()[1] - i].getdesignationnumber() });
				proceed = false;
			} else {
				// piece at Board[getposition()[0]+i][getposition()[1]+i] is same side as the
				// bishop
				proceed = false;
			}
		}
		proceed = true;
		for (int i = 1; proceed && getposition()[1] + i < 8; i++) {
			if (Board[getposition()[0]][getposition()[1] + i].getdesignationnumber() == -1) {
				output.add(new int[] { getposition()[0], getposition()[1] + i });
			} else if (Board[getposition()[0]][getposition()[1] + i].getplayer() != getplayer()) {
				output.add(new int[] { getposition()[0], getposition()[1] + i,
						Board[getposition()[0]][getposition()[1] + i].getdesignationnumber() });
				proceed = false;
			} else {
				// piece at Board[getposition()[0]+i][getposition()[1]+i] is same side as the
				// bishop
				proceed = false;
			}
		}
		
		return output;
	}
}