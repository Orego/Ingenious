import javax.swing.*;
import java.awt.*;
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
		setSize((int) (40 + 11 * BoardComponent.HEX_WIDTH),
				(int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
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
		Polygon[][] hex = new Polygon[11][11];
		double angle;
		double centerx;
		double centery;
		Board board = game.getBoard();
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				hex[i][j] = new Polygon();
				if(board.isValidHex(i, j)) {
					centerx = 40 + 0.5 * HEX_WIDTH * (5 - i) + HEX_WIDTH * j;
					centery = 40 + 0.75*HEX_HEIGHT*i;
					for(int k = 0; k < 6; k++) {			
						angle = 2 * Math.PI/6 * (k);
						hex[i][j].addPoint((int)(centerx + (HEX_HEIGHT)/2 * Math.sin(angle)), 
								(int)(centery + (HEX_HEIGHT)/2 * Math.cos(angle)));
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
}
