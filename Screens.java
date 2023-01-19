import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screens extends JPanel{

	private BufferedImage img;
	private JLabel label;
	
	public Screens(String imagePath) {
		super();
		
		try {
			img = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		label = new JLabel(new ImageIcon(img));
		add(label);
	}
	
}
