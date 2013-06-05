import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class PlayerGui {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SimpleFrame frame = new SimpleFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			}
		});

	}

}

class SimpleFrame extends JFrame {
	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		add(new PlayerComponent());
	}

	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 800;
}

class PlayerComponent extends JComponent {

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// Draw the six tiles of a player's hand
		for (int i = 0; i < 6; i++) {
			g2.fill(new Rectangle2D.Double(40, 40 + i * 125, 360, 85));
		}
		
		// Display the scores of a player's hand
		for (int i = 0; i < 6; i++) {
			if (i == 0) {
				g2.setPaint(Color.RED);
			} else if (i == 1) {
				g2.setPaint(Color.ORANGE);
			} else if (i == 2) {
				g2.setPaint(Color.YELLOW);
			} else if (i == 3) {
				g2.setPaint(Color.GREEN);
			} else if (i == 4) {
				g2.setPaint(Color.BLUE);
			} else {
				g2.setPaint(Color.MAGENTA);
			}
			
			g2.fill(new Rectangle2D.Double(440, 40 + i * 125, 120, 85));
		}
	}
}