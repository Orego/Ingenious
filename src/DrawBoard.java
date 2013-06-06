import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.*;
public class DrawBoard {
	
	private static final UIState uiState = new UIState();
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int row;
		int column;
		int rotation;
		Tile tile = new Tile((int)(Math.random()*6), (int)(Math.random()*6));
		
		final BoardFrame bf = new BoardFrame(uiState);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BoardFrame frame = bf;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		

//		while (true) {
//			System.out.println(uiState.game.getBoard() + "\n\n Current Tile is " + tile
//					+ "\nPlayer " + uiState.game.getCurrentPlayerIndex() + " give rotation of tile:\n" +
//							"(Color 2 rotates around Color 1. E is 0, NE is 1, NW is 2, etc..)");
//			rotation = in.nextInt();
//			System.out.println("Give row to place tile:");
//			row = in.nextInt();
//			System.out.println("Give column to place tile:");
//			column = in.nextInt();
//			if(uiState.game.placeTile(tile, row, column, rotation)) {
//				tile = new Tile((int)(Math.random()*6), (int)(Math.random()*6));
//			} else {
//				System.out.println("Invalid move: try again");
//			}
		bf.repaint();
//		}
	}	
}

class BoardFrame extends JFrame {
	UIState uiState;
	public BoardFrame(UIState uiState) {
		setTitle("Ingenious");
		this.uiState = uiState;
//		setSize((int) (40 + 11 * BoardComponent.HEX_WIDTH),
//				(int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
		JPanel panel = new JPanel();
		BoardComponent comp = new BoardComponent(uiState, panel);
		PlayerGui[] playerGui = new PlayerGui[uiState.game.getNumberOfPlayers()];
		for(int i = 0; i < uiState.game.getNumberOfPlayers(); i++) {			
			playerGui[i] = new PlayerGui(uiState.game, panel, i);
			playerGui[i].setPreferredSize(new Dimension((int) (145 + BoardComponent.HEX_WIDTH * 2), (int) (210 + BoardComponent.HEX_HEIGHT * 6)));
			if(i == 0) {				
				panel.add(playerGui[i]);
			}
		}
		comp.setPreferredSize(new Dimension((int) (40 + 11 * BoardComponent.HEX_WIDTH), (int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10)));
		panel.add(comp);
		TileGUI tileGui = new TileGUI(playerGui, uiState.game);
		tileGui.setPreferredSize(new Dimension((int) (4 * BoardComponent.HEX_WIDTH), (int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10)));
		panel.add(tileGui);
		panel.add(playerGui[1]);
		System.out.println(comp);
		System.out.println(tileGui);
		System.out.println(playerGui);
		add(panel);
		pack();
	}
}

class BoardComponent extends HexGui implements MouseListener, MouseMotionListener {
	private UIState uiState;
	private JPanel panel;
	public BoardComponent(UIState uiState, JPanel panel) {
		this.uiState = uiState;
		this.panel = panel;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void drawBoard(Graphics g) {
		Polygon[][] hex = new Polygon[11][11];
		Board board = uiState.game.getBoard();
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				if(board.isValidHex(i, j)){
					drawHex(g, board, i, j);
				}
			}
		}
	}
	
	protected void drawHex(Graphics g, Board board, int i, int j) {
		if(uiState.game.getBoard().isValidHex(i, j)) {			
			drawHex(g, board.getHex(i, j).getColor(), i, j, false);
		}
	}
	
	protected void drawHex(Graphics g, int color, int i, int j, boolean isBeingPlaced) {
		double angle;
		double centerx;
		double centery;
		Polygon hex = new Polygon();
		if(uiState.game.getBoard().isValidHex(i, j)) {
			centerx = 40 + 0.5 * UIState.HEX_WIDTH * (5 - i) + UIState.HEX_WIDTH * j;
			centery = 40 + 0.75*UIState.HEX_HEIGHT*i;
			for(int k = 0; k < 6; k++) {			
				angle = 2 * Math.PI/6 * (k);
				hex.addPoint((int)(centerx + (UIState.HEX_HEIGHT)/2 * Math.sin(angle)), 
						(int)(centery + (UIState.HEX_HEIGHT)/2 * Math.cos(angle)));
			}
			if (color < 0) {
				switch ((i + j) % 3) {
				case 0:
					g.setColor(uiState.BACKGROUND1);
					break;
				case 1:
					g.setColor(uiState.BACKGROUND2);
					break;
				case 2:
					g.setColor(Color.WHITE);
					break;
				}
			} else if(!isBeingPlaced){
				g.setColor(Color.BLACK);				
			} else {
				g.setColor(uiState.MEDIUM_GRAY);
			}
			
			g.fillPolygon(hex);
			g.drawPolygon(hex);
			switch (color) {
			case 0:
				g.setColor(Color.RED);
				g.fillOval( (int)(centerx-UIState.HEX_HEIGHT/4), (int)(centery-UIState.HEX_HEIGHT/4), 2*UIState.HEX_HEIGHT/4, 2*UIState.HEX_HEIGHT/4);
				break;
			case 1:
				g.setColor(Color.ORANGE);
				g.fillRect( (int)(centerx-UIState.HEX_HEIGHT/4), (int)(centery-UIState.HEX_HEIGHT/4), 2*UIState.HEX_HEIGHT/4, 2*UIState.HEX_HEIGHT/4);
				break;
			case 2:
				g.setColor(Color.YELLOW);
				g.fillRect( (int)(centerx-UIState.HEX_HEIGHT/4), (int)(centery-UIState.HEX_HEIGHT/8), 2*UIState.HEX_HEIGHT/4, 2*UIState.HEX_HEIGHT/8);
				g.fillRect( (int)(centerx-UIState.HEX_HEIGHT/8), (int)(centery-UIState.HEX_HEIGHT/4), 2*UIState.HEX_HEIGHT/8, 2*UIState.HEX_HEIGHT/4);
				break;
			case 3:
				g.setColor(Color.GREEN);
				double d = UIState.HEX_HEIGHT / 4;
				double h = (int)(d * Math.sqrt(3) / 2);
				Polygon triangle = new Polygon(new int[] {(int)(centerx - d), (int)(centerx), (int)(centerx + d)}, new int[] {(int)(centery + h), (int)(centery - h), (int)(centery + h)}, 3);
				g.fillPolygon(triangle);
				break;
			case 4:
				g.setColor(Color.CYAN);
			    h = UIState.HEX_HEIGHT/4;
				g.fillRect( (int)(centerx-h), (int)(centery-h), (int)(2*h), (int)(2*h/3));
				g.fillRect( (int)(centerx-h), (int)(centery+h/3), (int)(2*h), (int)(2*h/3));
				break;
			case 5:
				g.setColor(Color.MAGENTA);
				h = UIState.HEX_HEIGHT / 4;
				Polygon diamond = new Polygon(new int[] {(int)(centerx - h), (int)(centerx), (int)(centerx + h), (int)(centerx)}, new int[] {(int)(centery), (int)(centery - h), (int)(centery), (int)(centery + h)}, 4);
				g.fillPolygon(diamond);
				break;
			default: // vacant
				
			}
		}
	}
	
	public void drawTile(Graphics g) {
		
		if (!uiState.validTilePosition) {
			return;
		}
		uiState.game.getBoard().getAdjacentColumn(uiState.rotation, uiState.col);
		drawHex(g, 0, uiState.row, uiState.col, true);
		drawHex(g, 4, uiState.game.getBoard().getAdjacentRow(uiState.rotation, uiState.row), uiState.game.getBoard().getAdjacentColumn(uiState.rotation, uiState.col), true);
	}

	
	public void paintComponent(Graphics g) {
		if (uiState.currentState == UIState.PLACE_TILE_OR_RESELECT) {
			drawBoard(g);
			drawTile(g);
			// accept mouseclicks and movements
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		// find which hexagon the mouse is in (which one's center it is closest to)
		
		uiState.row = (int) Math.round(4.0/3.0 * (event.getY() - 40.0) / UIState.HEX_HEIGHT);
		uiState.col = (int) Math.round((event.getX() - 40 - 0.5 * UIState.HEX_WIDTH * (5 - uiState.row)) / UIState.HEX_WIDTH);
		
		if (uiState.game.isValidTilePlacement(uiState.row, uiState.col, 2)) {
			uiState.validTilePosition = true;

		} else {
			uiState.validTilePosition = false;
		}
		panel.repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		int row = (int) Math.round(4.0/3.0 * (event.getY() - 40.0) / UIState.HEX_HEIGHT);
		int col = (int) Math.round((event.getX() - 40 - 0.5 * UIState.HEX_WIDTH * (5 - row)) / UIState.HEX_WIDTH);
		
		if (uiState.game.isValidTilePlacement(row, col, 2)) {
			uiState.game.placeTile(new Tile(0, 4), row, col, 2);
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
