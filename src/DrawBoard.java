import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Scanner;

public class DrawBoard {
	
	public static class GameInfo {
		public Game game = new Game();
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int row;
		int column;
		int rotation;
		Tile tile = new Tile((int)(Math.random()*6), (int)(Math.random()*6));
		
		final Game game = new Game();
		final BoardFrame bf = new BoardFrame(game);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BoardFrame frame = bf;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
		while (true) {
			System.out.println(game.getBoard() + "\n\n Current Tile is " + tile
					+ "\nPlayer " + game.getCurrentPlayerIndex() + " give rotation of tile:\n" +
							"(Color 2 rotates around Color 1. E is 0, NE is 1, NW is 2, etc..)");
			rotation = in.nextInt();
			System.out.println("Give row to place tile:");
			row = in.nextInt();
			System.out.println("Give column to place tile:");
			column = in.nextInt();
			if(game.play(game.getCurrentPlayerIndex(), tile, row, column, rotation)) {
				tile = new Tile((int)(Math.random()*6), (int)(Math.random()*6));
				System.out.println("Player 0: " + game.getPlayer(0));
				System.out.println("Player 1: " + game.getPlayer(1));
			} else {
				System.out.println("Invalid move: try again");
			}
			bf.repaint();
		}

	}	
}

class BoardFrame extends JFrame {
	public BoardFrame(Game game) {
		setTitle("Ingenious");
		BoardComponent comp = new BoardComponent(game);
		setSize((int) (271 + 11 * BoardComponent.HEX_WIDTH),
				(int) (100 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
		add(comp);
	}
}

class BoardComponent extends JComponent {
	Game game;
	public static final int HEX_HEIGHT = 50;
	public static final double HEX_WIDTH = HEX_HEIGHT * Math.sqrt(3)/2;
	public BoardComponent(Game game) {
		this.game = game;
	}
	public void paintComponent(Graphics g) {
		
		double angle;
		double centerx;
		double centery;
		Board board = game.getBoard();
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				drawHex(g, board, i, j);
			}
		}
		drawHand(g);
	}
	
	/** Draws a hexagon around the given center coordinates, with the given color. */
	public void drawHandHex(Graphics g, int centerx, int centery, int color) {
		Polygon hex = new Polygon();
		double angle;
		for(int k = 0; k < 6; k++) {			
			angle = 2 * Math.PI/6 * (k);
			hex.addPoint((int)(centerx + (HEX_HEIGHT)/2 * Math.sin(angle)), 
					(int)(centery + (HEX_HEIGHT)/2 * Math.cos(angle)));
			g.setColor(Color.BLACK);
			g.fillPolygon(hex);
		}
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
	
	protected void drawHand(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int centerx;
		int centery;
		// Draw the six tiles of a player's hand
		for (int i = 0; i < 6; i++) {
			centerx = (int)(30 + HEX_WIDTH / 2);
			centery = 30 + HEX_HEIGHT / 2 + 80 * i;
			drawHandHex(g, centerx, centery, game.getPlayer(game.getCurrentPlayerIndex()).getHand().get(i).getA());
			centerx += HEX_WIDTH;
			drawHandHex(g, centerx, centery, game.getPlayer(game.getCurrentPlayerIndex()).getHand().get(i).getB());
			centerx -= HEX_WIDTH;
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
				g2.setPaint(Color.CYAN);
			} else {
				g2.setPaint(Color.MAGENTA);
			}

			g2.fill(new Rectangle2D.Double(160, 30 + i * 80, 50, 50));
			g2.setPaint(Color.BLACK);
			int score = game.getPlayer(game.getCurrentPlayerIndex()).getScore(i);
			String s = score + "";
			g2.drawString(s, 180, 60 + i * 80);
		}
		String playerString = "Player " + (game.getCurrentPlayerIndex() + 1) + "'s turn.";
		g2.drawString(playerString, (int)(170 + 0.5 * (70 + 11 * HEX_WIDTH)), 475);
	}
	
	protected void drawHex(Graphics g, Board board, int i, int j) {
		double angle;
		double centerx;
		double centery;
		Polygon hex = new Polygon();
		if(board.isValidHex(i, j)) {
			centerx = 271 + 0.5 * HEX_WIDTH * (5 - i) + HEX_WIDTH * j;
			centery = 40 + 0.75*HEX_HEIGHT*i;
			for(int k = 0; k < 6; k++) {			
				angle = 2 * Math.PI/6 * (k);
				hex.addPoint((int)(centerx + (HEX_HEIGHT)/2 * Math.sin(angle)), 
						(int)(centery + (HEX_HEIGHT)/2 * Math.cos(angle)));
			}
			if (board.getHex(i, j).getColor() < 0) {
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
			switch (board.getHex(i, j).getColor()) {
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
}
