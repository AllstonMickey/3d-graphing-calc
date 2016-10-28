import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.*;  

public class Gui extends Window {
	private static double myGraphX = 0.225, myGraphY = 0.225; // x and y coords of the graph viewing angle
	private static boolean zIsTop = true; // viewing angle is on top of the sphere (true)
	
	public Gui() {
		initKeyBinds(getFrame());
	}
	
	protected void paintComponent(Graphics g) {
		System.out.println("paintComponent has been called!");
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(255, 255, 255));
		g2d.fillRect(0, 0, getWindowWidth(), getWindowHeight()); // creates background
		
		boolean[][] pixelState = new boolean[getWindowWidth()][getWindowHeight()]; // which pixels on the screen are on/off
		Graph myGraph = new Graph();
		Line[][] myLines = myGraph.genScreen(myGraphX, myGraphY, zIsTop); // generates which pixels are on/off
        
		/* draws axes
		 * y: red
		 * x: green
		 * z: blue
		 */
        for (int x = 0; x < getWindowWidth(); ++x) {
			for (int y = 0; y < getWindowHeight(); ++y) {
				double paraNum = myGraph.placePara(myLines[x][y]);
				double xNum = myGraph.placeAxis(myLines[x][y],1,2);
				double yNum = myGraph.placeAxis(myLines[x][y],0,2);
				double zNum = myGraph.placeAxis(myLines[x][y],0,1);
				if (paraNum>0 && paraNum<Math.abs(xNum) && paraNum<Math.abs(yNum) && paraNum<Math.abs(zNum)){
					int numColor = (int)Math.round(paraNum*127);
					g2d.setColor(new Color(numColor,numColor,numColor));
					g2d.draw(new Line2D.Double(x, y, x, y));
				} else if (yNum>0) {
					g2d.setColor(new Color(200, 0, 0));
					g2d.draw(new Line2D.Double(x, y, x, y));
				} else if (xNum>0) {
					g2d.setColor(new Color(0, 200, 0));
					g2d.draw(new Line2D.Double(x, y, x, y));
				} else if (zNum>0) {
					g2d.setColor(new Color(0, 0, 200));
					g2d.draw(new Line2D.Double(x, y, x, y));
				}
			}
		}
	}
	
	private static void initKeyBinds(JFrame f) {
		double t = 0.05; // tolerance/delta value for myGraphX and myGraphY
		boolean onKeyRelease = false; // execute the key input while the key is held down
		
		/** binds a key to some action
		 * space changes the side of the sphere (top or bottom)
		 * up, down, left, and right change the viewing angle by incr/decr myGraphX & myGraphY
		 *     also forces said viewing angle to be in the specified sphere
		 * redraws the screen (f.repaint()) with the updated values after each keypress
		 */
		
		KeyStroke spaceKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, onKeyRelease);
		Action spaceAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				zIsTop = !zIsTop;
				System.out.println("z side switched");
				f.repaint(); // redraw the Gui
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(spaceKeyStr, "SPACE");
		f.getRootPane().getActionMap().put("SPACE", spaceAction);
		
		KeyStroke upKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, onKeyRelease);
		Action upAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				if (myGraphY < 1 - t && Math.sqrt(myGraphX*myGraphX + (myGraphY+t)*(myGraphY+t)) < 1 - t) {
					myGraphY += 0.05;
					f.repaint();
				}
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upKeyStr, "UP");
		f.getRootPane().getActionMap().put("UP", upAction);
		
		KeyStroke downKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, onKeyRelease);
		Action downAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				if (myGraphY > -1 + 0.05 && Math.sqrt(myGraphX*myGraphX + (myGraphY-t)*(myGraphY-t)) < 1 - t) {
					myGraphY -= 0.05;
					f.repaint();
				}
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downKeyStr, "DOWN");
		f.getRootPane().getActionMap().put("DOWN", downAction);
		
		KeyStroke rightKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, onKeyRelease);
		Action rightAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				if (myGraphX < 1 - 0.05 && Math.sqrt((myGraphX+t)*(myGraphX+t) + myGraphY*myGraphY) < 1 - t) {
					myGraphX += 0.05;
					f.repaint();
				}
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightKeyStr, "RIGHT");
		f.getRootPane().getActionMap().put("RIGHT", rightAction);
		
		KeyStroke leftKeyStr = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, onKeyRelease);
		Action leftAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override public void actionPerformed(ActionEvent e) {
				if (myGraphX > -1 + 0.05 && Math.sqrt((myGraphX-t)*(myGraphX-t) + myGraphY*myGraphY) < 1 - t) {
					myGraphX -= 0.05;
					f.repaint();
				}
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftKeyStr, "LEFT");
		f.getRootPane().getActionMap().put("LEFT", leftAction);
		
	}
}
