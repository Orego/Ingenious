import javax.swing.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.*;
public class DrawBoard {
	
	private static final UIState uiState = new UIState();
	
	public static void main(String[] args) {

		Game game = new Game();
		uiState.game = game;
		
		final BoardFrame bf = new BoardFrame(game);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BoardFrame frame = bf;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
//		while (!game.isOver()) {
//			System.out.println(game.getBoard()
//							+ "\n\n Current Tile is "
//							+ tile
//							+ "\nPlayer "
//							+ game.getCurrentPlayerIndex()
//							+ " give rotation of tile:\n"
//							+ "(Color 2 rotates around Color 1. E is 0, NE is 1, NW is 2, etc..)");
//			rotation = in.nextInt();
//			System.out.println("Give row to place tile:");
//			row = in.nextInt();
//			System.out.println("Give column to place tile:");
//			column = in.nextInt();
//			if (game.play(game.getCurrentPlayerIndex(), tile, row, column,
//					rotation)) {
//				tile = new Tile((int) (Math.random() * 6),
//						(int) (Math.random() * 6));
//				System.out.println("Player 0: " + game.getPlayer(0));
//				System.out.println("Player 1: " + game.getPlayer(1));
//				if (game.getPlayer(game.getCurrentPlayerIndex()).getPlaysLeft() == 0) {
//					game.switchPlayers();
//				}
//			} else {
//				System.out.println("Invalid move: try again");
//			}
			bf.repaint();
//		}
//		System.out.println("Game over!");
//		int winner = game.getWinner();
//		if (winner == -1) {
//			System.out.println("There was a tie");
//		} else {
//			System.out.println("The winner is player: " + winner);
//		}
	}	
}



