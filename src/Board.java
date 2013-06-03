/** An Ingenious (hexagonal) board. */
public class Board {
	
	/** Color for an unoccupied hex. */
	public static final int VACANT = -1;

	/** The diameter of the board (distance in hexagons from one corner to opposite corner). */
	private static final int DIAMETER = 11; // this must be odd!
	
	/** The length of one edge of the board, in hexagons. */
	private static final int SIDE_LENGTH = DIAMETER/2 + 1;
	
	/** A 2D array representation of the board. */
	private Hex[][] board = new Hex[DIAMETER][DIAMETER];
	
	/** Make an empty board [and later, wire its hexagons together]. */
	public Board() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				board[r][c] = new Hex(VACANT, r, c);
			}
		}
		board[0][0].setColor(0);
		board[0][5].setColor(1);
		board[5][0].setColor(2);
		board[5][10].setColor(3);
		board[10][5].setColor(4);
		board[10][10].setColor(5);		
	}

	/** Returns the color at row r, column c. */
	public int getColor(int r, int c) {
		return board[r][c].getColor();
	}

	public static void main(String[] args) {
		Board b = new Board();
		System.out.println("Non-vacant hexes on initial board:");
		for (int r = 0; r < b.board.length; r++) {
			for (int c = 0; c < b.board[r].length; c++) {
				if (b.board[r][c].getColor() != VACANT) {
					System.out.println(r + ", " + c + ": " + b.board[r][c].getColor());
				}
			}
		}
	}
}
