import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ChessTimePanel extends JPanel {

	private JLabel time;
	private JLabel playerName;

	public ChessTimePanel(String name) {
		// top left bottom right
		Border b = BorderFactory.createEmptyBorder(20, 40, 20, 40);
		time = new JLabel("00:00");
		time.setFont(new Font("Consolas", Font.PLAIN, 64));
		time.setBorder(b);
		time.setVerticalAlignment(SwingConstants.CENTER);
		
		playerName = new JLabel(name);
		playerName.setFont(new Font("Verdana", Font.PLAIN, 32));
		playerName.setBorder(b);
		playerName.setVerticalAlignment(SwingConstants.CENTER);
		
		// add other crap
		this.add(time, BorderLayout.EAST);
		this.add(playerName, BorderLayout.WEST);
	}
	
	public void setTime(int minutes, int seconds) {
		time.setText(String.format("%d:%02d", minutes, seconds));
	}
	
	public void setActive(boolean isActive) {
		if(isActive)
			playerName.setFont(new Font("Verdana", Font.BOLD, 32));
		else {
			playerName.setFont(new Font("Verdana", Font.PLAIN, 32));
		}
	}

}
