import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.*;  

public class Gui extends JPanel {
	protected static final int H = 500, W = 500; // height and width of the graphics window
	protected static double myGraphX = 0.25, myGraphY = 0.25; // x and y coords of the graph viewing angle
	protected static boolean zIsTop = true; // viewing angle is on top of the sphere (true)
	public static JFrame f = new JFrame(); // creates Gui object
	public static int count = 0;
	
	public static void main(String[] args) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // enables close window operation
		f.add(new Gui());
		f.setSize(W, H);
		f.setLocation((1920-W)/2, (1080-H)/2);
		f.setResizable(false);
		f.setVisible(true);
		initKeyBinds();
	}
	
	/* EXAMPLE OF A NEW COMPONENT
	public void drawRectangle(Rectangle newR, Stroke stroke, Color c){
	    recs.add(newR);
	    strokes.add(stroke);
	    Color.add(c);
	}
	protected void paintComponent(Graphics g){
    	super.paintComponent(g);
    	for (int i = 0; i < recs.size(); i ++) {
        	g.setColor(colors.get(i));
        	g.setStroke(strokes.get(i));
        	g.drawRectangle(recs);
    	}
	}
	*/
	public void drawAxes() {

	}
	
	protected void paintComponent(Graphics g) {
		System.out.println("paintComponent has been called!");
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(255, 255, 255));
		g2d.fillRect(0, 0, W, H); // creates background
		
		/* draw axes - OLD
		g2d.setColor(new Color(0, 0, 0));
		g2d.draw(new Line2D.Double(W/2, H/2, W, H/2)); // +y
		g2d.draw(new Line2D.Double(W/2, H/2, 0, H)); // +x
		g2d.draw(new Line2D.Double(W/2, H/2, W/2, 0)); // +z
		g2d.setColor(new Color(225, 225, 225));
		g2d.draw(new Line2D.Double(W/2, H/2, 0, H/2)); // -y
		g2d.draw(new Line2D.Double(W/2, H/2, W, 0)); // -x
		g2d.draw(new Line2D.Double(W/2, H/2, W/2, H)); // -z
		*/
		
			
		boolean[][] pixelState = new boolean[W][H]; // which pixels on the screen are on/off
		Graph myGraph = new Graph();
		Line[][] myLines = myGraph.genScreen(myGraphX, myGraphY, zIsTop); // generates which pixels are on/off
		
		/* draws axes
		 * y: red
		 * x: green
		 * z: blue
		 */
		for (int x = 0; x < W; ++x) {
			for (int y = 0; y < W; ++y) {
				if (myGraph.placeY(myLines[x][y])) {
					g2d.setColor(new Color(200, 0, 0));
					g2d.draw(new Line2D.Double(x, y, x, y));
				} else if (myGraph.placeX(myLines[x][y])) {
					g2d.setColor(new Color(0, 200, 0));
					g2d.draw(new Line2D.Double(x, y, x, y));
				} else if (myGraph.placeZ(myLines[x][y])) {
					g2d.setColor(new Color(0, 0, 200));
					g2d.draw(new Line2D.Double(x, y, x, y));
				}
			}
		}
	}
	
	private static void initKeyBinds() {
		boolean onKeyRelease = false;
		// binds ESC to "TEST!" as an example 
		KeyStroke escKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, onKeyRelease);
		Action escAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				System.out.println("TEST!");
			}
		};
		// maps the input of the keystroke to an action string "ESC"
		// then maps the action string of "ESC" to perform the defined action "escAction"
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escKeyStr, "ESC");
		f.getRootPane().getActionMap().put("ESC", escAction);
		
		
		// binds the spacebar to change the z viewing angle (on top or below the sphere)
		KeyStroke spaceKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, onKeyRelease);
		Action spaceAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				zIsTop = !zIsTop;
				System.out.println("z side switched");
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(spaceKeyStr, "SPACE");
		f.getRootPane().getActionMap().put("SPACE", spaceAction);
		
		// binds the 'up arrow' to increment y viewing angle values.
		KeyStroke upKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, onKeyRelease);
		Action upAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				myGraphY += 0.05;
				System.out.println("y: " + myGraphY);
				System.out.println("y incremented");
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upKeyStr, "UP");
		f.getRootPane().getActionMap().put("UP", upAction);
		
		// binds the 'down arrow' to decrement y viewing angle values.
		KeyStroke downKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, onKeyRelease);
		Action downAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				myGraphY -= 0.05;
				System.out.println("y: " + myGraphY);
				System.out.println("y decremented");
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downKeyStr, "DOWN");
		f.getRootPane().getActionMap().put("DOWN", downAction);
		
		// binds the 'right arrow' to decrement x viewing angle values.
		KeyStroke rightKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, onKeyRelease);
		Action rightAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				myGraphX += 0.05;
				System.out.println("x: " + myGraphX);
				System.out.println("x incremented");
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightKeyStr, "RIGHT");
		f.getRootPane().getActionMap().put("RIGHT", rightAction);
		
		// binds the 'left arrow' to decrement x viewing angle values.
		KeyStroke leftKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, onKeyRelease);
		Action leftAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				myGraphX -= 0.05;
				System.out.println("x: " + myGraphX);
				System.out.println("x decremented");
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftKeyStr, "LEFT");
		f.getRootPane().getActionMap().put("LEFT", leftAction);
		
		// redraws the Gui upon keypress
		f.invalidate();
		f.revalidate();
		f.repaint();
	}
	
}