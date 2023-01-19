import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseStuff implements MouseListener, MouseMotionListener{
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked");
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse entered");
	}
	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exited");	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed");
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse released");
	}
	
	//Mouse Motion Stuff
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged");
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("Mouse moved");
	}
}
