import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

class BoardComponent extends HexGui implements MouseListener, MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	
	private BoardFrame frame;
	private PlayerGui[] playerGui;
	public BoardComponent(BoardFrame frame, PlayerGui[] playerGui, TileGui tileGui) {
		this.frame = frame;
		this.playerGui = playerGui;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void drawBoard(Graphics g) {
		Polygon[][] hex = new Polygon[11][11];
		Board board = frame.getBoard();
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				if(board.isValidHex(i, j)){
					drawHex(g, board, i, j);
				}
			}
		}
		if(frame.isGameOver()) {
			int winner = frame.getWinner();
			g.setColor(Color.BLACK);
			if(winner == -1) {
				g.drawString("There was a tie!", (int) ((40 + 11 * BoardComponent.HEX_WIDTH)/3), (int) (40 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
			} else {
				g.drawString("The winner is player: " + (winner), (int) ((40 + 11 * BoardComponent.HEX_WIDTH)/3), (int) (40 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
			}
		}
	}
	
	protected void drawHex(Graphics g, Board board, int i, int j) {
		if(frame.isValidHex(i, j)) {			
			drawHex(g, board.getHex(i, j).getColor(), i, j);
		}
	}
	
	public Color MEDIUM_GRAY = new Color(75, 75 ,75);

	public void drawTile(Graphics g) {
		if ((!validTilePosition || (playerGui[frame.getCurrentPlayerIndex()].getSelectedTile() == -1)) && (!frame.isGameOver())) {
			return;
		}
		drawHex(g, frame.getCurrentPlayer().getHand()
				.get(playerGui[frame.getCurrentPlayerIndex()].getSelectedTile())
				.getA(), row, col, MEDIUM_GRAY);
		drawHex(g, frame.getSelectedTile().getB(), 
				frame.getBoard().getAdjacentRow(frame.getSelectedTileRotation(), row), frame.getBoard().getAdjacentColumn(frame.getSelectedTileRotation(), col), MEDIUM_GRAY);
	}

	
	public void paintComponent(Graphics g) {
		drawBoard(g);
		if(!frame.isGameOver()) {
			drawTile(g);			
		}
			// accept mouseclicks and movements
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	// For drag and drop
	private int row;
	private int col;
	private boolean validTilePosition;
	
	@Override
	public void mouseMoved(MouseEvent event) {
		// find which hexagon the mouse is in (which one's center it is closest to)
		
		row = (int) Math.round(4.0/3.0 * (event.getY() - 40.0) / HEX_HEIGHT);
		col = (int) Math.round((event.getX() - 40 - 0.5 * HEX_WIDTH * (5 - row)) / HEX_WIDTH);
		
		if (frame.isValidTilePlacement(row, col, frame.getSelectedTileRotation())) {
			validTilePosition = true;

		} else {
			validTilePosition = false;
		}
		frame.repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if(frame.getCurrentPlayer().getPlaysLeft()!=0){
		if(!frame.isGameOver()) {			
			((JPanel)(frame.getContentPane().getComponent(0))).getComponent(3).setEnabled(true);
			int row = (int) Math.round(4.0/3.0 * (event.getY() - 40.0) / HEX_HEIGHT);
			int col = (int) Math.round((event.getX() - 40 - 0.5 * HEX_WIDTH * (5 - row)) / HEX_WIDTH);
			
			if ((frame.getSelectedTile() != null) && frame.isValidTilePlacement(row, col, frame.getSelectedTileRotation())) {
				if (frame.play(frame.getCurrentPlayerIndex(), frame.getSelectedTile(), 
						row, col, frame.getSelectedTileRotation())) {
					frame.getCurrentPlayer().getHand().remove(playerGui[frame.getCurrentPlayerIndex()].getSelectedTile());
					playerGui[frame.getCurrentPlayerIndex()].setSelectedTile(-1);
				}
				if (frame.getCurrentPlayer().getPlaysLeft() == 0) {
					if(!frame.getCurrentPlayer().canSwapTiles()){
						frame.getCurrentPlayer().refreshHand();
					}else{
						
						frame.getComponent(5).setEnabled(true);
						frame.getComponent(4).setEnabled(true);
					}
					playerGui[frame.getCurrentPlayerIndex()].setSelectedTile(0);
				}
			}
		}
	}
		}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
