import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TileGUI extends HexGui implements MouseListener {

	private int rotation;

	private Game game;

	/**
	 * Returns the rotation of this tile (in 60-degree increments
	 * counterclockwise from right).
	 */
	public int getRotation() {
		return rotation;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		incrementRotation();
		repaint();
	}

	public TileGUI( Game game) {
		this.game = game;
		rotation = 0;
		addMouseListener(this);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Does nothing
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Does nothing
	}

	/** Coordinates of second hex in rotated tile, indexed by rotation. */
	public static final int[][] COORDINATES = { { 1, 0 }, { 0, -1 }, { 0, -2 },
			{ 1, -2 }, { 2, -1 }, { 2, 0 } };

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Tile tile = game.getSelectedTile();
		if (tile != null) {
			drawHex(g2, tile.getA(), 1, -1);
			drawHex(g2, tile.getB(), COORDINATES[rotation][0],
					COORDINATES[rotation][1]);
		}
	}

	public void incrementRotation() {
		rotation = (rotation + 1) % 6;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
