import javax.swing.*;
import java.awt.*;

public class DrawBoard {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BoardFrame frame = new BoardFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}	
}

class BoardFrame extends JFrame {
	public BoardFrame() {
		setTitle("Ingenious");
		setSize(1300, 600);
		BoardComponent comp = new BoardComponent();
		add(comp);
	}
}

class BoardComponent extends JComponent {
	public void paintComponent(Graphics g) {
		Polygon[][] hex = new Polygon[11][11];
		int height = 50;
		double width = height * (Math.sqrt(3)/2);
		
		double angle;
		double centerx;
		double centery;
		Board board = new Board();
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				hex[i][j] = new Polygon();
				if(board.isValidHex(i, j)) {
					centerx = 40 + 0.5 * width * (5 - i) + width * j;
					centery = 40 + 0.75*height*i;
					for(int k = 0; k < 6; k++) {			
						angle = 2 * Math.PI/6 * (k);
						hex[i][j].addPoint((int)(centerx + height/2 * Math.sin(angle)), 
								(int)(centery + height/2 * Math.cos(angle)));
					}
					g.setColor(Color.RED);
					g.fillPolygon(hex[i][j]);
					g.setColor(Color.BLACK);
					g.drawPolygon(hex[i][j]);
				}
			}
		}
	}
}
