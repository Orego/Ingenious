public class Board {

	/** Color for an unoccupied hex. */
	public static final int VACANT = -1;
	
	private int[][] grid = new int[11][11];
	
	/** Returns the color at row r, column c. */
	public int getColor(int r, int c) {
		return grid[r][c];
	}
	
	public Board() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				grid[r][c] = VACANT;
			}
		}
		grid[0][0] = 0;
		grid[0][5] = 1;
		grid[5][0] = 2;
		grid[5][10] = 3;
		grid[10][5] = 4;
		grid[10][10] = 5;		
	}

	public static void main(String[] args) {
		Board b = new Board();
		System.out.println("Non-vacant hexes on initial board:");
		for (int r = 0; r < b.grid.length; r++) {
			for (int c = 0; c < b.grid[r].length; c++) {
				if (b.grid[r][c] != VACANT) {
					System.out.println(r + ", " + c + ": " + b.grid[r][c]);
				}
			}
		}
	}

}
