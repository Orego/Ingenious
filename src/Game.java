import java.util.Scanner;
import java.lang.Math;

/** The main class to run the game. */
public class Game {
	
	private int successfulMoves;
	
	public static void main(String[] args) {
		Game g = new Game();
		Scanner in = new Scanner(System.in);
		int row;
		int column;
		int rotation;
		Tile tile = new Tile((int)(Math.random()*6), (int)(Math.random()*6));
		while (true) {
			System.out.println(g.board + "\n\n Current Tile is " + tile
					+ "\nPlayer " + g.currentPlayerIndex + " give rotation of tile:\n" +
							"(Color 2 rotates around Color 1. E is 0, NE is 1, NW is 2, etc..)");
			rotation = in.nextInt();
			System.out.println("Give row to place tile:");
			row = in.nextInt();
			System.out.println("Give column to place tile:");
			column = in.nextInt();
			if(g.placeTile(tile, row, column, rotation)) {				
				tile = new Tile((int)(Math.random()*6), (int)(Math.random()*6));
			} else {
				System.out.println("Invalid move: try again");
			}
		}
	}
	
	public Game() {
		board = new Board();
		successfulMoves = 0;
	}

	/** Zero-based index of the current player. */
	private int currentPlayerIndex;
	
	/** The game's board. */
	private Board board;
	
	/** Returns the current board. */
	public Board getBoard() {
		return board;
	}
	
	/** Returns the zero-based index of the current player. */
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}
	
	/** Advance to the next player. */
	public void switchPlayers() {
		currentPlayerIndex = (currentPlayerIndex + 1) % 2;
		successfulMoves++;
	}
	
	/** Tells board where to place the tile based on the player's given coordinates. */
	public boolean placeTile(Tile tile, int row, int column, int rotation) {
		int row2 = board.getAdjacentRow(rotation, row);
		int column2 = board.getAdjacentColumn(rotation, column);
		if (!board.isValidHex(row, column) || !board.isValidHex(row2, column2)) {			
			return false;
		}
		if (successfulMoves < 2) {
			// initial placement: verify that this move is adjacent to a corner hex with no neighbors
			// does row, column have any corner hex in its neighbors
			Hex adjacentCornerHex1 = board.getHex(row, column).adjacentCornerHex();
			Hex adjacentCornerHex2 = board.getHex(row2, column2).adjacentCornerHex();
			if ((adjacentCornerHex1 == null || adjacentCornerHex1.hasAnyNeighbors()) &&
				(adjacentCornerHex2 == null || adjacentCornerHex2.hasAnyNeighbors())) {
				return false;
			}
		}
		
		board.placeTile(tile, row, column, row2, column2);
		switchPlayers();
		return true;
	}
	
}
