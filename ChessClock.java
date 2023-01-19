import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ChessClock extends JPanel implements ActionListener {

	private Timer timer;
	private int whiteSec, whiteMin, blackSec, blackMin;
	public static int mode;
	public static final int PAUSED = 0, WHITE = 1, BLACK = 2;
	
	private ChessTimePanel blackTimer, whiteTimer;

	public ChessClock() {
		super();
		blackTimer = new ChessTimePanel("Black");
		whiteTimer = new ChessTimePanel("White");
		JPanel options = new ChessButtonsPanel(this);

		add(blackTimer, BorderLayout.NORTH);
		add(options, BorderLayout.CENTER);
		add(whiteTimer, BorderLayout.SOUTH);
		add(blackTimer, BorderLayout.NORTH);
		add(options, BorderLayout.CENTER);
		add(whiteTimer, BorderLayout.SOUTH);

//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		pack();
		setVisible(true);

		mode = WHITE;
		whiteSec = 0;
		whiteMin = 5;
		blackSec = 0;
		blackMin = 5;

		timer = new Timer(1000, this);
		timer.start();
		
		actionPerformed(null);
	}
	public JPanel getCheckClockwhite() {
		return whiteTimer;
	}
	public JPanel getCheckClockblack() {
		return 	blackTimer;
	}
	public static void main(String[] args) {
		new ChessClock();
	}
	public void actionPerformed(ActionEvent e) {
		if (mode == WHITE) {
			whiteSec--;
			whiteTimer.setActive(true);
			if (whiteSec < 0) {
				whiteSec += 60;
				whiteMin -= 1;
			}
			if (whiteMin < 0) {
				whiteMin = 0;
				whiteSec = 0;
				mode = PAUSED;
				ChessBoard.forother.BlackWinsTimeVisible(true);
				ChessBoard.forother.WelcomeVisible(false);
				ChessBoard.freezegame=true;
			}
		}
		if (mode == BLACK) {
			blackTimer.setActive(true);
			blackSec--;
			if (blackSec < 0) {
				blackSec += 60;
				blackMin -= 1;
			}
			if (blackMin < 0) {
				blackMin = 0;
				blackSec = 0;
				mode = PAUSED;
				ChessBoard.forother.WhiteWinsTimeVisible(true);
				ChessBoard.forother.WelcomeVisible(false);
				ChessBoard.freezegame=true;
			}
		}

		//System.out.println("W " + whiteMin + ":" + whiteSec);
		//System.out.println("B " + blackMin + ":" + blackSec);
		
		whiteTimer.setTime(whiteMin, whiteSec);
		blackTimer.setTime(blackMin, blackSec);
		
		if (mode == WHITE){
			whiteTimer.setActive(true);
			blackTimer.setActive(false);
		} else if (mode == BLACK) {
			blackTimer.setActive(true);
			whiteTimer.setActive(false);
		}
	}

	public void drawClicked() {
		mode = PAUSED;
		
		// wire this into the rest of the program
	}

	public void resignClicked() {
		mode = PAUSED;
		// wire this into the rest of the program
	}

	public void switchPlayer() {
		if (mode == WHITE){
			whiteTimer.setActive(false);
			mode = BLACK;
		} else if (mode == BLACK) {
			blackTimer.setActive(false);
			mode = WHITE;
		}
	}

	// clock.setMode(ChessClock.PAUSED, WHITE, BLACK)
	public void setMode(int m) {
		mode = m;
	}
}
