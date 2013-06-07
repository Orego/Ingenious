import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class BoardFrame extends JFrame {
	private Game game;
	private JButton swapButton;

	public BoardFrame(final Game game) {
		setTitle("Ingenious");
		this.game = game;
//		setSize((int) (40 + 11 * BoardComponent.HEX_WIDTH),
//				(int) (60 + BoardComponent.HEX_HEIGHT + 0.75 * BoardComponent.HEX_HEIGHT * 10));
		final JPanel panel = new JPanel();
		PlayerGui[] playerGui = new PlayerGui[game.getNumberOfPlayers()];
		for(int i = 0; i < game.getNumberOfPlayers(); i++) {			
			playerGui[i] = new PlayerGui(game, panel, i);
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
				game.getPlayer(game.getCurrentPlayerIndex()).swapTiles();
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
				game.getPlayer(game.getCurrentPlayerIndex()).refreshHand();
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
		game.incrementSelectedTileRotation();
	}
	public Tile getSelectedTile() {
		return game.getSelectedTile();
	}
	public int getSelectedTileRotation() {
		return game.getSelectedTileRotation();
	}
	public Board getBoard() {
		return game.getBoard();
	}
	public boolean isGameOver() {
		return game.isOver();
	}
	public int getWinner() {
		return game.getWinner();
	}
	public boolean isValidHex(int row, int column) {
		return game.getBoard().isValidHex(row, column);
	}
	public boolean isValidTilePlacement(int row, int col,
			int selectedTileRotation) {
		return game.isValidTilePlacement(row, col, selectedTileRotation);
	}
	public Player getCurrentPlayer() {
		return game.getPlayer(game.getCurrentPlayerIndex());
	}
	public int getCurrentPlayerIndex() {
		return game.getCurrentPlayerIndex();
	}
	public boolean play(int currentPlayerIndex, Tile selectedTile, int row,
			int col, int selectedTileRotation) {
		return game.play(currentPlayerIndex, selectedTile, row, col, selectedTileRotation);
	}
	
}
