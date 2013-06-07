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
		
		final BoardFrame bf = new BoardFrame(uiState);
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

class BoardFrame extends JFrame {
	final UIState uiState;
	private JButton swapButton;
	public BoardFrame(final UIState uiState) {
		setTitle("Ingenious");
		this.uiState = uiState;
//		setSize((int) (40 + 11 * BoardComponent.HEX_WIDTH),
//				(int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
		final JPanel panel = new JPanel();
		PlayerGui[] playerGui = new PlayerGui[uiState.game.getNumberOfPlayers()];
		for(int i = 0; i < uiState.game.getNumberOfPlayers(); i++) {			
			playerGui[i] = new PlayerGui(uiState.game, panel, i);
			playerGui[i].setPreferredSize(new Dimension((int) (145 + BoardComponent.HEX_WIDTH * 2), (int) (210 + BoardComponent.HEX_HEIGHT * 6)));
			if(i == 0) {				
				panel.add(playerGui[i]);
			}
		}
		TileGui tileGui = new TileGui(this);
		tileGui.setPreferredSize(new Dimension((int) (4 * BoardComponent.HEX_WIDTH), (int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10)));
		BoardComponent comp = new BoardComponent(this, playerGui, tileGui);
		comp.setPreferredSize(new Dimension((int) (40 + 11 * BoardComponent.HEX_WIDTH), (int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10)));
		panel.add(comp);
		panel.add(tileGui);
		panel.add(playerGui[1]);
		System.out.println(comp);
		System.out.println(tileGui);
		System.out.println(playerGui);
		add(panel);
		
		
		// These two buttons should be disabled until we are able to have a choice between
		// swapping and refreshing.
		swapButton = new JButton("Swap");
		JButton refreshButton = new JButton("Refresh");
		panel.add(swapButton);
		swapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				uiState.game.getPlayer(uiState.game.getCurrentPlayerIndex()).swapTiles();
				panel.getComponent(5).setEnabled(false);
				panel.getComponent(4).setEnabled(false);
				panel.repaint();
			}
		});
		swapButton.setEnabled(false);
		panel.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				uiState.game.getPlayer(uiState.game.getCurrentPlayerIndex()).refreshHand();
				panel.getComponent(5).setEnabled(false);
				panel.getComponent(4).setEnabled(false);
				panel.repaint();
			}
		});
		refreshButton.setEnabled(false);
		pack();
	}
	public JButton getSwapButton(){
		return swapButton;
	}
	public void incrementSelectedTileRotation() {
		uiState.game.incrementSelectedTileRotation();
	}
	public Tile getSelectedTile() {
		return uiState.game.getSelectedTile();
	}
	public int getSelectedTileRotation() {
		return uiState.game.getSelectedTileRotation();
	}
	public Board getBoard() {
		return uiState.game.getBoard();
	}
	public boolean isGameOver() {
		return uiState.game.isOver();
	}
	public int getWinner() {
		return uiState.game.getWinner();
	}
	public boolean isValidHex(int row, int column) {
		return uiState.game.getBoard().isValidHex(row, column);
	}
	public boolean isValidTilePlacement(int row, int col,
			int selectedTileRotation) {
		return uiState.game.isValidTilePlacement(row, col, selectedTileRotation);
	}
	public Player getCurrentPlayer() {
		return uiState.game.getPlayer(uiState.game.getCurrentPlayerIndex());
	}
	public int getCurrentPlayerIndex() {
		return uiState.game.getCurrentPlayerIndex();
	}
	public boolean play(int currentPlayerIndex, Tile selectedTile, int row,
			int col, int selectedTileRotation) {
		return uiState.game.play(currentPlayerIndex, selectedTile, row, col, selectedTileRotation);
	}
	
}


