import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/** GUI for the board. */
public class BoardComponent extends HexGui implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;

	/** Board column of current mouse position. */
	private int col;

	private BoardFrame frame;

	public Color MEDIUM_GRAY = new Color(75, 75, 75);

	/** Board row of current mouse position. */
	private int row;

	/** True if the mouse is over a valid tile placement position. */
	private boolean validTilePosition;

	public BoardComponent(BoardFrame frame) {
		this.frame = frame;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void drawBoard(Graphics g) {
		Board board = frame.getBoard();
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (board.isValidHex(i, j)) {
					drawHex(g, board, i, j);
				}
			}
		}
		if (frame.isGameOver()) {
			int winner = frame.getWinner();
			g.setColor(Color.BLACK);
			String result = "There was a tie!";
			if (winner != -1) {
				result = "The winner is player: " + winner;
			}
			g.drawString(
					result,
					(int) ((40 + 11 * BoardComponent.HEX_WIDTH) / 3),
					(int) (40 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
		}
	}

	protected void drawHex(Graphics g, Board board, int i, int j) {
		if (frame.isValidHex(i, j)) {
			drawHex(g, board.getHex(i, j).getColor(), i, j);
		}
	}

	public void drawTile(Graphics g) {
		if ((!validTilePosition || (frame.getSelectedTile() == null))
				&& !frame.isGameOver()) {
			return;
		}
		drawHex(g, frame.getSelectedTile().getA(), row, col, MEDIUM_GRAY);
		drawHex(g,
				frame.getSelectedTile().getB(),
				frame.getBoard().getAdjacentRow(
						frame.getSelectedTileRotation(), row),
				frame.getBoard().getAdjacentColumn(
						frame.getSelectedTileRotation(), col), MEDIUM_GRAY);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (frame.getCurrentPlayer().getPlaysLeft() != 0 && !frame.isGameOver()) {
			int row = (int) Math.round(4.0 / 3.0 * (event.getY() - 40.0)
					/ HEX_HEIGHT);
			int col = (int) Math.round((event.getX() - 40 - 0.5 * HEX_WIDTH
					* (5 - row))
					/ HEX_WIDTH);
			Tile t = frame.getSelectedTile();
			Player player = frame.getCurrentPlayer();
			if (t != null) {
				if (frame.play(t, row, col)) {
					frame.setSelectedTile(null);
				}
				if (player.getPlaysLeft() == 0) {
					if (!player.canSwapTiles()) {
						player.refreshHand();
					} else {
						frame.setButtonEnabledness(true);
					}
					frame.setSelectedTile(null);
				}
			}
		}
		frame.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// Does nothing
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
	public void mouseMoved(MouseEvent event) {
		// find which hexagon the mouse is in (which one's center it is closest
		// to)
		row = (int) Math.round(4.0 / 3.0 * (event.getY() - 40.0) / HEX_HEIGHT);
		col = (int) Math
				.round((event.getX() - 40 - 0.5 * HEX_WIDTH * (5 - row))
						/ HEX_WIDTH);
		validTilePosition = frame.isValidTilePlacement(row, col,
				frame.getSelectedTileRotation());
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Does nothing
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Does nothing
	}

	public void paintComponent(Graphics g) {
		drawBoard(g);
		if (!frame.isGameOver()) {
			drawTile(g);
		}
	}

}
