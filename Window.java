import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Window extends JPanel {
	private static JFrame frame = new JFrame(); // creates Gui object
	private static int H = 500, W = 500; // height and width of the graphics window
	
	protected static JFrame getFrame() { return frame; }
	protected static int getWindowWidth() { return W; }
	protected static int getWindowHeight() { return H; }
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui();
			}
		});
	}
	
	private static void createAndShowGui() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // enables close window operation
		frame.setLocation((1920-getWindowWidth())/2, (1080-getWindowHeight())/2);
		frame.setResizable(true);
		frame.add(new Gui());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(getWindowWidth(), getWindowHeight());
	}
}