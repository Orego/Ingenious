import java.util.Scanner;
import java.lang.Math;

/** The main class to run the game. */
public class Game {

	private int successfulMoves;
	private int numberOfPlayers;
	private Player[] players;
	boolean bonusMove = false;

	public static void main(String[] args) {
		Game g = new Game();
		Scanner in = new Scanner(System.in);
		int row;
		int column;
		int rotation;
		Tile tile = new Tile((int) (Math.random() * 6),
				(int) (Math.random() * 6));
		while (true) {
			System.out
					.println(g.board
							+ "\n\n Current Tile is "
							+ tile
							+ "\nPlayer "
							+ g.currentPlayerIndex
							+ " give rotation of tile:\n"
							+ "(Color 2 rotates around Color 1. E is 0, NE is 1, NW is 2, etc..)");
			rotation = in.nextInt();
			System.out.println("Give row to place tile:");
			row = in.nextInt();
			System.out.println("Give column to place tile:");
			column = in.nextInt();
			if (g.placeTile(tile, row, column, rotation)) {
				tile = new Tile((int) (Math.random() * 6),
						(int) (Math.random() * 6));
				System.out.println("Player who scored");
				System.out.println(g.players);
				if (g.getPlayer(g.getCurrentPlayerIndex()).getPlaysLeft() == 0) {
					g.switchPlayers();
				}
			} else {
				System.out.println("Invalid move: try again");
			}
		}
	}

	public Game() {
		bag = new Bag();
		numberOfPlayers = 2;
		players = new Player[2];
		players[0] = new Player(this);
		players[1] = new Player(this);

		board = new Board();
		successfulMoves = 0;
		players[0].startTurn();
	}

	/** Zero-based index of the current player. */
	private int currentPlayerIndex;

	/** The game's board. */
	private Board board;

	/** Returns the current board. */
	public Board getBoard() {
		return board;
	}
	
	/** The game's bag of tiles. */
	private Bag bag;
	
	/** Returns the bag. */
	public Bag getBag() {
		return bag;
	}
	
	/** Returns the number of players in the game. */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/** Returns the zero-based index of the current player. */
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	/** Advance to the next player. */
	public void switchPlayers() {
		successfulMoves++;
		currentPlayerIndex = (currentPlayerIndex + 1) % 2;
		players[currentPlayerIndex].startTurn();
	}
	

	public boolean isValidTilePlacement(int row, int column, int rotation) {
		int row2 = board.getAdjacentRow(rotation, row);
		int column2 = board.getAdjacentColumn(rotation, column);
		if (!board.isValidHex(row, column)
				|| board.getHex(row, column).getColor() != Board.VACANT
				|| !board.isValidHex(row2, column2)
				|| board.getHex(row2, column2).getColor() != Board.VACANT) {
			return false;
		}
		if (successfulMoves < 2) {
			// initial placement: verify that this move is adjacent to a corner
			// hex with no neighbors
			// does row, column have any corner hex in its neighbors
			Hex adjacentCornerHex1 = board.getHex(row, column)
					.adjacentCornerHex();
			Hex adjacentCornerHex2 = board.getHex(row2, column2)
					.adjacentCornerHex();
			if ((adjacentCornerHex1 == null || adjacentCornerHex1
					.hasAnyNeighbors())
					&& (adjacentCornerHex2 == null || adjacentCornerHex2
							.hasAnyNeighbors())) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Tells board where to place the tile based on the player's given
	 * coordinates.
	 */
	public boolean placeTile(Tile tile, int row, int column, int rotation) {
		int row2 = board.getAdjacentRow(rotation, row);
		int column2 = board.getAdjacentColumn(rotation, column);
				
		if (isValidTilePlacement(row, column, rotation)) {
			board.placeTile(tile, row, column, row2, column2);
			switchPlayers();
			return true;
		} else {
			return false;
		}
	}

	public Player getPlayer(int i) {
		//

		return players[i];
	}

	public boolean play(int playerIndex, Tile tile, int row, int column,
			int rotation) {
		if (!placeTile(tile, row, column, rotation)) {
			return false;
		}
		Hex front = board.getHex(row, column);
		Hex back = front.getNeighbor(rotation);
		scoreFrom(playerIndex, front, back);
		scoreFrom(playerIndex, back, front);
		players[playerIndex].incrementPlaysLeft(-1);
		return true;
	}

	/**
	 * Adds points scored by player playerIndex, starting from the front hex of
	 * the tile and looking out in every direction except through the back hex.
	 */
	protected void scoreFrom(int playerIndex, Hex front, Hex back) {
		int color = front.getColor();
		for (int direction = 0; direction < 6; direction++) {
			Hex neighbor = front.getNeighbor(direction);
			if (neighbor != null) {
				if (neighbor != back) {
					if (neighbor.getColor() == color) {
						Hex tempNeighbor = neighbor;
						while (tempNeighbor != null
								&& tempNeighbor.getColor() == color) {
							players[playerIndex].addScore(color, 1);
							tempNeighbor = tempNeighbor.getNeighbor(direction);
						}
					}

				}
			}
		}
	}

	/** Returns true if the game is over. */
	public boolean isOver() {
		for(int i=0; i<2; i++){
			boolean instantVictory=true;
			for(int j=0; j<6; j++){
				if(getPlayer(i).getScore(j)!=18){
					instantVictory=false;
				}
				
			}
			if(instantVictory){
				return true;
			}
		}
		
		for(int r=0; r<11; r++){
			for(int c=0; c<11; c++){
				if(board.isValidHex(r, c)){
					Hex temp = board.getHex(r, c);
					if(temp.getColor()==-1){
						for(int i=0; i<6; i++){
							if(temp.getNeighbor(i)!=null){
								if(temp.getNeighbor(i).getColor()==-1){
									return false;
								}
							}
						}
					}
				}
			}
		}
		
		return true;
	}

	/** Returns the number of the player currently winning. */
	public int getWinner() {
		int[] s0 = new int[6];
		int[] s1 = new int[6];
		System.arraycopy(players[0].getScores(), 0, s0, 0, 6);
		System.arraycopy(players[1].getScores(), 0, s1, 0, 6);
		java.util.Arrays.sort(s0);
		java.util.Arrays.sort(s1);
		for (int i = 0; i < s0.length; i++) {
			if (s0[i] > s1[i]) {
				return 0;
			} else if (s1[i] > s0[i]) {
				return 1;
			}
		}
		return -1;
	}

}
