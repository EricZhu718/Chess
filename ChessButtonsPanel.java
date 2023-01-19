import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ChessButtonsPanel extends JPanel {
	private JButton draw;
	private JButton resign;
	
	private final ChessClock clock;
	
	public ChessButtonsPanel(final ChessClock clock) {
		this.clock = clock;
		draw = new JButton("Draw");
		draw.setFont(new Font("Consolas", Font.PLAIN, 32));
		
		resign = new JButton("Resign");
		resign.setFont(new Font("Consolas", Font.PLAIN, 32));
		
		
		this.add(draw, BorderLayout.EAST);
		this.add(resign, BorderLayout.WEST);
		
		draw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clock.drawClicked();
				ChessBoard.forother.WelcomeVisible(false);
				ChessBoard.forother.DrawVisible(true);
				ChessBoard.freezegame = true;
			}
		});
		
		resign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clock.resignClicked();
				if(ChessBoard.forother.Player == ChessPiece.White) {
					ChessBoard.forother.BlackWinsResignVisible(true);
					ChessBoard.forother.WelcomeVisible(false);
					ChessBoard.freezegame = true;
				}else {
					ChessBoard.forother.WhiteWinsResignVisible(true);
					ChessBoard.forother.WelcomeVisible(false);
					ChessBoard.freezegame = true;
				}
			}
		});
	}
}

