/** An Ingenious (hexagonal) board. */
public class Board {
	
	/** The diameter of the board (distance in hexagons from one corner to opposite corner). */
	private static final int DIAMETER = 11; // this must be odd!
	
	/** The length of one edge of the board, in hexagons. */
	private static final int SIDE_LENGTH = DIAMETER/2 + 1;
	
	/** A 2D array representation of the board. */
	private Hex[][] board = new Hex[DIAMETER][DIAMETER];
	
	/** Make an empty board [and later, wire its hexagons together]. */
	public Board() {
	}
}
