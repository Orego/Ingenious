import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputAdapter;
import java.util.Scanner;

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
		BoardComponent comp = new BoardComponent(uiState);
		setSize((int) (40 + 11 * UIState.HEX_WIDTH) + 300,
				(int) (60 + UIState.HEX_HEIGHT + 0.75 * UIState.HEX_HEIGHT * 10));
		add(comp);
	}
}

class BoardComponent extends JComponent implements MouseListener, MouseMotionListener {
	UIState uiState;
	public BoardComponent(UIState uiState) {
		this.uiState = uiState;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void drawBoard(Graphics g) {
		Polygon[][] hex = new Polygon[11][11];
		double angle;
		double centerx;
		double centery;
		Board board = uiState.game.getBoard();
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				hex[i][j] = new Polygon();
				if(board.isValidHex(i, j)) {
					centerx = 40 + 0.5 * UIState.HEX_WIDTH * (5 - i) + UIState.HEX_WIDTH * j;
					centery = 40 + 0.75*UIState.HEX_HEIGHT*i;
					for(int k = 0; k < 6; k++) {			
						angle = 2 * Math.PI/6 * (k);
						hex[i][j].addPoint((int)(centerx + (UIState.HEX_HEIGHT)/2 * Math.sin(angle)), 
								(int)(centery + (UIState.HEX_HEIGHT)/2 * Math.cos(angle)));
					}
					switch (board.getHex(i, j).getColor()) {
					case 0:
						g.setColor(Color.RED);
						break;
					case 1:
						g.setColor(Color.ORANGE);
						break;
					case 2:
						g.setColor(Color.YELLOW);
						break;
					case 3:
						g.setColor(Color.GREEN);
						break;
					case 4:
						g.setColor(Color.BLUE);
						break;
					case 5:
						g.setColor(Color.MAGENTA);
						break;
					default: // vacant
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
					}
					
					g.fillPolygon(hex[i][j]);
					g.setColor(Color.BLACK);
					g.drawPolygon(hex[i][j]);
				}
			}
		}
	}
	
	public void drawTile(Graphics g) {
		
		if (!uiState.validTilePosition) {
			return;
		}
		
		Polygon[] hexes = { new Polygon(), new Polygon() };

		// if rotation, change angle to 60deg * rotation
		
		double x = uiState.centerx;
		double y = uiState.centery;
		
		for (int i = 0; i < 2; i++) {
			x = x + i * UIState.HEX_WIDTH * Math.cos(Math.PI / 3 * 2);
			y = y - i * UIState.HEX_WIDTH * Math.sin(Math.PI / 3 * 2);
			
			for(int j = 0; j < 6; j++) {			
				double angle = 2 * Math.PI/6 * j;
				hexes[i].addPoint((int)(x + UIState.HEX_HEIGHT/2.0 * Math.sin(angle)), 
						(int)(y + UIState.HEX_HEIGHT/2.0 * Math.cos(angle)));
			}
			if(i < 1) {
				g.setColor(Color.RED);
			} else{				
				g.setColor(Color.CYAN);
			}
			g.fillPolygon(hexes[i]);
			g.setColor(Color.BLACK);
			g.drawPolygon(hexes[i]);
		}
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
		
		int row = (int) Math.round(4.0/3.0 * (event.getY() - 40.0) / UIState.HEX_HEIGHT);
		int col = (int) Math.round((event.getX() - 40 - 0.5 * UIState.HEX_WIDTH * (5 - row)) / UIState.HEX_WIDTH);
		
		if (uiState.game.isValidTilePlacement(row, col, 2)) {
			uiState.validTilePosition = true;
			uiState.centerx = 40 + 0.5 * UIState.HEX_WIDTH * (5 - row) + UIState.HEX_WIDTH * col;
			uiState.centery = 40 + 0.75 * UIState.HEX_HEIGHT * row;
		} else {
			uiState.validTilePosition = false;
		}
		repaint();
		
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
