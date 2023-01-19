import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class ScorePanel extends JPanel{
	private JLabel score;
	private JLabel playerName;
	public ScorePanel panel = this;
	public ScorePanel(String name) {
		Border b = BorderFactory.createEmptyBorder(40, 40, 40, 40);
		score = new JLabel("0");
		score.setFont(new Font("Consolas", Font.PLAIN, 8));
		score.setBorder(b);
		score.setVerticalAlignment(SwingConstants.CENTER);
		
		playerName = new JLabel(name + " MaterialVal");
		playerName.setFont(new Font("Verdana", Font.PLAIN, 8));
		playerName.setBorder(b);
		playerName.setVerticalAlignment(SwingConstants.CENTER);
		
		// add other crap
		this.add(score, BorderLayout.NORTH);
		this.add(playerName, BorderLayout.SOUTH);
	}
	
	public void setScore(int newnum) {
		String hold = "" + newnum;
		score.setText(String.format(hold));
	}
	
	public void setActive(boolean isActive) {
		if(isActive)
			playerName.setFont(new Font("Verdana", Font.BOLD, 8));
		else {
			playerName.setFont(new Font("Verdana", Font.PLAIN, 8));
		}
	}
}


