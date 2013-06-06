import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JComponent;

public abstract class HexGui extends JComponent {

	public static final int HEX_HEIGHT = 50;
	public static final double HEX_WIDTH = HEX_HEIGHT * Math.sqrt(3)/2;

	public HexGui() {
		super();
	}

	protected void drawHex(Graphics g, int color, int i, int j) {
		double angle;
		double centerx;
		double centery;
		Polygon hex = new Polygon();
		
			centerx = 40 + 0.5 * HEX_WIDTH * (5 - i) + HEX_WIDTH * j;
			centery = 40 + 0.75*HEX_HEIGHT*i;
			for(int k = 0; k < 6; k++) {			
				angle = 2 * Math.PI/6 * (k);
				hex.addPoint((int)(centerx + (HEX_HEIGHT)/2 * Math.sin(angle)), 
						(int)(centery + (HEX_HEIGHT)/2 * Math.cos(angle)));
			}
			if (color < 0) {
				switch ((i + j) % 3) {
				case 0:
					g.setColor(Color.LIGHT_GRAY);
					break;
				case 1:
					g.setColor(Color.GRAY);
					break;
				case 2:
					g.setColor(Color.WHITE);
					break;
				}
			} else {
				g.setColor(Color.BLACK);				
			}
			
			g.fillPolygon(hex);
			g.drawPolygon(hex);
			switch (color) {
			case 0:
				g.setColor(Color.RED);
				g.fillOval( (int)(centerx-HEX_HEIGHT/4), (int)(centery-HEX_HEIGHT/4), 2*HEX_HEIGHT/4, 2*HEX_HEIGHT/4);
				
				break;
			case 1:
				g.setColor(Color.ORANGE);
				g.fillRect( (int)(centerx-HEX_HEIGHT/4), (int)(centery-HEX_HEIGHT/4), 2*HEX_HEIGHT/4, 2*HEX_HEIGHT/4);
				break;
			case 2:
				
				g.setColor(Color.YELLOW);
				g.fillRect( (int)(centerx-HEX_HEIGHT/4), (int)(centery-HEX_HEIGHT/8), 2*HEX_HEIGHT/4, 2*HEX_HEIGHT/8);
				g.fillRect( (int)(centerx-HEX_HEIGHT/8), (int)(centery-HEX_HEIGHT/4), 2*HEX_HEIGHT/8, 2*HEX_HEIGHT/4);
				
				break;
			case 3:
				g.setColor(Color.GREEN);
				double d = HEX_HEIGHT / 4;
				double h = (int)(d * Math.sqrt(3) / 2);
				Polygon triangle = new Polygon(new int[] {(int)(centerx - d), (int)(centerx), (int)(centerx + d)}, new int[] {(int)(centery + h), (int)(centery - h), (int)(centery + h)}, 3);
				g.fillPolygon(triangle);
				break;
			case 4:
				g.setColor(Color.CYAN);
			    h= HEX_HEIGHT/4;
				g.fillRect( (int)(centerx-h), (int)(centery-h), (int)(2*h), (int)(2*h/3));
				g.fillRect( (int)(centerx-h), (int)(centery+h/3), (int)(2*h), (int)(2*h/3));
				
				
				break;
			case 5:
				g.setColor(Color.MAGENTA);
				h = HEX_HEIGHT / 4;
				Polygon diamond = new Polygon(new int[] {(int)(centerx - h), (int)(centerx), (int)(centerx + h), (int)(centerx)}, new int[] {(int)(centery), (int)(centery - h), (int)(centery), (int)(centery + h)}, 4);
				g.fillPolygon(diamond);
				
				break;
			default: // vacant
				
			
		}
	}

}