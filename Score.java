import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

// TODO update all every time

public class Score extends JPanel {
	public static final int WHITE = 1, BLACK = 2;
	private ScorePanel blackScorePanel, whiteScorePanel;

	public Score() {
		super();
		blackScorePanel = new ScorePanel("Black");
		whiteScorePanel = new ScorePanel("White");
		add(blackScorePanel, BorderLayout.NORTH);
		add(whiteScorePanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	public void switchPlayer() {
		int newscore = ChessBoard.scoreMethod(true);
		whiteScorePanel.setScore(newscore);
		newscore = ChessBoard.scoreMethod(false);
		blackScorePanel.setScore(newscore);
	}
	
}
