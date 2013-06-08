import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JComponent;
import static java.awt.Color.*;

/** Component capable of drawing hexes. */
public abstract class HexGui extends JComponent {

	/** Shades for checkerboard background. */
	public static final Color[] CHECKERBOARD_SHADING = {new Color(220, 220, 220), new Color(190, 190, 190), WHITE};

	/** Height of a hex (in pixels). */
	public static final int HEX_HEIGHT = 50;

	/** Width of a hex (in pixels). */
	public static final double HEX_WIDTH = HEX_HEIGHT * Math.sqrt(3) / 2;

	private static final long serialVersionUID = 1L;
	
	/** Draws a hex of the given color at the given board row and column. */
	protected void drawHex(Graphics g, int color, int row, int column) {
		Color shading = BLACK;
		if (color < 0) {
			shading = CHECKERBOARD_SHADING[(row + column) % 3];
		}
		drawHex(g, color, row, column, shading);
	}

	/** Draws a hex of the given color and shading at the given board row and column. */
	protected void drawHex(Graphics g, int color, int row, int column, Color shading) {
		double centerx = 40 + 0.5 * HEX_WIDTH * (5 - row) + HEX_WIDTH * column;
		double centery = 40 + 0.75 * HEX_HEIGHT * row;
		drawHex(g, color, centerx, centery, shading);
	}
	
	public void drawHex(Graphics g, int color, double centerx, double centery, Color shading) {
		Polygon hex = new Polygon();
		for (int k = 0; k < 6; k++) {
			double angle = 2 * Math.PI / 6 * (k);
			hex.addPoint((int) (centerx + (HEX_HEIGHT) / 2 * Math.sin(angle)),
					(int) (centery + (HEX_HEIGHT) / 2 * Math.cos(angle)));
		}
		g.setColor(shading);
		g.fillPolygon(hex);
		g.drawPolygon(hex);
		switch (color) {
		case 0:
			g.setColor(Color.RED);
			g.fillOval((int) (centerx - HEX_HEIGHT / 4.0),
					(int) (centery - HEX_HEIGHT / 4.0), (int) (2 * HEX_HEIGHT / 4.0),
					(int) (2 * HEX_HEIGHT / 4.0));
			break;
		case 1:
			g.setColor(Color.ORANGE);
			g.fillRect((int) (centerx - HEX_HEIGHT / 4.0),
					(int) (centery - HEX_HEIGHT / 4.0), (int) (2 * HEX_HEIGHT / 4.0),
					(int) (2 * HEX_HEIGHT / 4.0));
			break;
		case 2:
			g.setColor(Color.YELLOW);
			g.fillRect((int) (centerx - HEX_HEIGHT / 4.0),
					(int) (centery - HEX_HEIGHT / 8.0), (int) (2 * HEX_HEIGHT / 4.0),
					(int) (2 * HEX_HEIGHT / 8.0));
			g.fillRect((int) (centerx - HEX_HEIGHT / 8.0),
					(int) (centery - HEX_HEIGHT / 4.0), (int) (2 * HEX_HEIGHT / 8.0),
					(int) (2 * HEX_HEIGHT / 4.0));
			break;
		case 3:
			g.setColor(Color.GREEN);
			double d = HEX_HEIGHT / 4.0;
			double h = (int) (d * Math.sqrt(3) / 2.0);
			Polygon triangle = new Polygon(new int[] { (int) (centerx - d),
					(int) (centerx), (int) (centerx + d) }, new int[] {
					(int) (centery + h), (int) (centery - h),
					(int) (centery + h) }, 3);
			g.fillPolygon(triangle);
			break;
		case 4:
			g.setColor(Color.CYAN);
			h = HEX_HEIGHT / 4.0;
			g.fillRect((int) (centerx - h), (int) (centery - h), (int) (2 * h),
					(int) (2 * h / 3));
			g.fillRect((int) (centerx - h), (int) (centery + h / 3),
					(int) (2 * h), (int) (2 * h / 3));
			break;
		case 5:
			g.setColor(Color.MAGENTA);
			h = HEX_HEIGHT / 4.0;
			Polygon diamond = new Polygon(new int[] { (int) (centerx - h),
					(int) (centerx), (int) (centerx + h), (int) (centerx) },
					new int[] { (int) (centery), (int) (centery - h),
							(int) (centery), (int) (centery + h) }, 4);
			g.fillPolygon(diamond);
			break;
		default: // vacant
		}
	}
}