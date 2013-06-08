import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TileGui extends HexGui implements MouseListener {

	private static final long serialVersionUID = 1L;

	/** Coordinates of second hex in rotated tile, indexed by rotation. */
	public static final int[][] COORDINATES = { { 1, 0 }, { 0, -1 }, { 0, -2 },
			{ 1, -2 }, { 2, -1 }, { 2, 0 } };

	private BoardFrame gui;

	public TileGui(BoardFrame gui) {
		this.gui = gui;
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		gui.incrementSelectedTileRotation();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Does nothing
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Does nothing
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Does nothing
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Does nothing
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Tile tile = gui.getSelectedTile();
		int rotation = gui.getSelectedTileRotation();
		if (tile != null) {
			drawHex(g2, tile.getA(), 1, -1);
			drawHex(g2, tile.getB(), COORDINATES[rotation][0],
					COORDINATES[rotation][1]);
		}
	}

}
