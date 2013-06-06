import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Scanner;

public class DrawBoard {
	
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
//		setSize((int) (40 + 11 * BoardComponent.HEX_WIDTH),
//				(int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
		JPanel panel = new JPanel();
		PlayerGui[] playerGui = new PlayerGui[game.getNumberOfPlayers()];
		for(int i = 0; i < game.getNumberOfPlayers(); i++) {			
			playerGui[i] = new PlayerGui(game, panel, i);
			playerGui[i].setPreferredSize(new Dimension((int) (145 + BoardComponent.HEX_WIDTH * 2), (int) (210 + BoardComponent.HEX_HEIGHT * 6)));
			if(i == 0) {				
				panel.add(playerGui[i]);
			}
		}
		comp.setPreferredSize(new Dimension((int) (40 + 11 * BoardComponent.HEX_WIDTH), (int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10)));
		panel.add(comp);
		TileGUI tileGui = new TileGUI(playerGui, game);
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

class BoardComponent extends HexGui {
	Game game;
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
				if(board.isValidHex(i, j)){
				drawHex(g, board.getHex(i, j).getColor(), i, j);
				}
			}
		}
	}
}
