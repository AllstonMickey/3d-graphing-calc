import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.*;  

public class Gui extends JPanel {
	protected static final int H = 500;
	protected static final int W = 500;
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Gui());
		f.setSize(W, H);
		f.setLocation((1920-W)/2, (1080-H)/2);
		f.setResizable(false);
		f.setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(255, 255, 255));
		g2d.fillRect(0, 0, W, H);		
		// draw axes
		g2d.setColor(new Color(0, 0, 0));
		g2d.draw(new Line2D.Double(W/2, H/2, W, H/2)); // +y
		g2d.draw(new Line2D.Double(W/2, H/2, 0, H)); // +x
		g2d.draw(new Line2D.Double(W/2, H/2, W/2, 0)); // +z
		g2d.setColor(new Color(225, 225, 225));
		g2d.draw(new Line2D.Double(W/2, H/2, 0, H/2)); // -y
		g2d.draw(new Line2D.Double(W/2, H/2, W, 0)); // -x
		g2d.draw(new Line2D.Double(W/2, H/2, W/2, H)); // -z
	
		/*
		g2d.setColor(new Color(255, 255, 255));
		g2d.fillRect(0, 0, W, H);		
		
		boolean[][] pixelState = new boolean[W][H];
		for (int x = 0; x < W; ++x) {
			for (int y = 0; y < W; ++y) {
				if (pixelState[x][y]) {
					g2d.setColor(new Color(200, 0, 0));
					g2d.draw(new Line2D.Double(x, y, x, y));
				}
			}
		}
		*/
	}
}