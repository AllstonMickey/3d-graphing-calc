import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Window extends JPanel {
	private static final long serialVersionUID = -4845827189025752911L;
	private static int H = 500, W = 500; // height and width of the graphics window
	private static JFrame frame = new JFrame(); // creates Gui JFrame object
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
		frame.setLocation((1920 - getWindowWidth())/2, (1080 - getWindowHeight())/2);
		frame.setResizable(false);
		frame.setSize(getWindowWidth(), getWindowHeight());
		frame.add(new Gui());
		frame.setVisible(true);
	}
}