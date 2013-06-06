/** An Ingenious (hexagonal) board. */
public class Board {
	
	/** Color for an unoccupied hex. */
	public static final int VACANT = -1;

	/** The diameter of the board (distance in hexagons from one corner to opposite corner). */
	public static final int DIAMETER = 11; // this must be odd!
	
	/** The length of one edge of the board, in hexagons. */
	public static final int SIDE_LENGTH = DIAMETER/2 + 1;
	
	/** A 2D array representation of the board. */
	private Hex[][] board = new Hex[DIAMETER][DIAMETER];
	
	/** Make an empty board [and later, wire its hexagons together]. */
	public Board() {
		// create all the valid hexes		
		for (int r = 0; r < DIAMETER; r++) {
			for (int c = 0; c < DIAMETER; c++) {
				if (isValidHex(r, c)) {
					board[r][c] = new Hex(Board.VACANT, r, c);
				}
			}
		}	
		wireNeighbors();
		// set the corners
		board[0][0].makeCornerHex(0);
		board[0][5].makeCornerHex(1);
		board[5][0].makeCornerHex(2);
		board[5][10].makeCornerHex(3);
		board[10][5].makeCornerHex(4);
		board[10][10].makeCornerHex(5);
	}

	/** Returns the row of the adjacent hex according to your given rotation. */
	public int getAdjacentRow(int rotation, int row) {
		switch(rotation) {
		case Hex.RIGHT:
		case Hex.LEFT:
			return row;
		case Hex.UP_LEFT:
		case Hex.UP_RIGHT:
			return row - 1;
		case Hex.DOWN_RIGHT:
		case Hex.DOWN_LEFT:
			return row + 1;
		default:
			return -1;
		}
	}
	
	/** Returns the column of the adjacent hex according to your given rotation. */
	public int getAdjacentColumn(int rotation, int column) {
		switch(rotation) {
			case Hex.UP_RIGHT:
			case Hex.DOWN_LEFT:
				return column;
			case Hex.UP_LEFT:
			case Hex.LEFT:
				return column - 1;
			case Hex.DOWN_RIGHT:
			case Hex.RIGHT:
				return column + 1;
			default:
				return -1;
		}
	}
	
	/** Returns the color at row r, column c. */
	public int getColor(int r, int c) {
		return board[r][c].getColor();
	}
	
	public void setColor(int r, int c, int color) {
		board[r][c].setColor(color);
	}
	
	/** Get a hex at coordinate (r, c) from the board. */
	public Hex getHex(int r, int c) {
		return board[r][c];
	}
	
	/** Checks if given position has a non-null hex. */
	public boolean isValidHex(int row, int column) {
		if((row < 0) || (row >= DIAMETER)) {			
			return false;
		}
		if(isBeyondRightBorder(row, column) || isBeyondLeftBorder(row, column)) {
			return false;
		}
		return true;
	}
	
	/** Places given tile at the given coordinates. 
	 * (r1 and c1 are the coordinates for tile color a, r2 and c2 are for color b) */
	public void placeTile(Tile tile, int r1, int c1, int r2, int c2) {
		getHex(r1, c1).setColor(tile.getA());
		getHex(r2, c2).setColor(tile.getB());
	}
	
	/** Check whether the given coordinates are at the right border or beyound. */
	protected boolean isAtRightBorderOrBeyond(int row, int column) {
		if (isAboveMiddleRow(row, column)) {
			return column >= row + SIDE_LENGTH - 1;
		} else {
			return column >= DIAMETER - 1;
		}
	}
	
	/** Check whether the given coordinates are beyond the right border. */
	protected boolean isBeyondRightBorder(int row, int column) {
		if (isAboveMiddleRow(row, column)) {
			return column > row + SIDE_LENGTH - 1;
		} else {
			return column > DIAMETER - 1;
		}
	}
	
	/** Check whether the given coordinates are at the left border or beyound. */
	protected boolean isAtLeftBorderOrBeyond(int row, int column) {
		if (isAboveMiddleRow(row, column)) {
			return column <= 0;
		} else {
			return column <= row - SIDE_LENGTH + 1;
		}
	}
	
	/** Check whether the given coordinates are beyond the left border. */
	protected boolean isBeyondLeftBorder(int row, int column) {
		if (isAboveMiddleRow(row, column)) {
			return column < 0;
		} else {
			return column < row - SIDE_LENGTH + 1;
		}
	}

	/** Check whether the given coordinates are above the middle row. */
	protected boolean isAboveMiddleRow(int row, int column) {
		return row < SIDE_LENGTH - 1;			
	}

	/** Check whether the given coordinates are on the bottom row. */
	protected boolean isOnBottomRow(int row, int column) {
		return row == DIAMETER - 1;
	}
	
	/** Wiring the neighbors of each hex. */
	protected void wireNeighbors() {
		// wires every valid hex to its neighbors, handling the edge cases properly
		for (int row = 0; row < DIAMETER; row++) {
			for (int column = 0; column < DIAMETER; column++) {
				if (isBeyondLeftBorder(row, column) || isBeyondRightBorder(row, column))
					continue;
				// wire nodes to their left and right neighbors:
				if (!isAtRightBorderOrBeyond(row, column)) {
					getHex(row, column).setNeighbor(Hex.RIGHT, getHex(row, column + 1));
					getHex(row, column + 1).setNeighbor(Hex.LEFT, getHex(row, column));
				}
				// wire nodes to their down-right and up-left neighbors:
				if (isAboveMiddleRow(row, column) || (!isAtRightBorderOrBeyond(row, column) && !isOnBottomRow(row, column))) {
					getHex(row, column).setNeighbor(Hex.DOWN_RIGHT, getHex(row + 1, column + 1));
					getHex(row + 1, column + 1).setNeighbor(Hex.UP_LEFT, getHex(row, column));
				}
				// wire nodes to their down-left and up-right neighbors:
				if (isAboveMiddleRow(row, column) || (!isAtLeftBorderOrBeyond(row, column) && !isOnBottomRow(row, column))) {
					getHex(row, column).setNeighbor(Hex.DOWN_LEFT, getHex(row + 1, column));
					getHex(row + 1, column).setNeighbor(Hex.UP_RIGHT, getHex(row, column));
				}
			}
		}	
	}

	@Override
	public String toString() {
		String result = "";
		int prefixLength = SIDE_LENGTH - 1;
		boolean reachedMiddleLine = false;
		for (int r = 0; r < DIAMETER; r++) {
			for (int i = 0; i < prefixLength; i++) {
				result += " ";
			}
			if (!reachedMiddleLine) {
				prefixLength--;
				if (prefixLength == 0) {
					reachedMiddleLine = true;
				}
			} else {
				prefixLength++;
			}
			for (int c = 0; c < DIAMETER; c++) {
				if (getHex(r, c) != null) {
					result += getHex(r, c) + " ";
				}
			}
			result += "\n";
		}
		return result;
	}
	
	public static void main(String[] args) {
		Board b = new Board();
		System.out.println(b);
	}
}
