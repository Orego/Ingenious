import java.util.ArrayList;

/** A hexagon on the board with its coordinates, occupied with a certain color and linked to its neighbors. */
public class Hex {
	
	/**  Specifying the six possible directions of this hex. */
	public static final int RIGHT = 0;
	
	public static final int UP_RIGHT = 1;
	
	public static final int UP_LEFT = 2;
	
	public static final int LEFT = 3;
	
	public static final int DOWN_LEFT = 4;
	
	public static final int DOWN_RIGHT = 5;
	
	/** The color of this hexagon. */
	private int color;
	
	/** The neighbors of this hexagon (null if no neighbor exists). */
	private ArrayList<Hex> neighbors;
	
	/** This hexagon's row in the 2D array. */
	private int row;
	
	/** This hexagon's column in the 2D array. */
	private int column;
	
	/** Make a hexagon with the given color, and row and column coordinates. */
	public Hex(int color, int row, int column) {
		this.color = color;
		this.row = row;
		this.column = column;
		neighbors = new ArrayList<Hex>();
		for (int i = 0; i < 6; i++)
			neighbors.add(null);
	}
	
	/** Set the given neighbor to the Hex h. */
	public void setNeighbor(int direction, Hex h) {
		neighbors.set(direction, h);
	}
	
	/** Return this hexagon's color. */
	public int getColor() {
		return color;
	}
	
	/** Set this hexagon's color. */
	public void setColor(int color) {
		this.color = color;
	}
	
	/** Return this hexagon's row coordinate. */
	public int getRow() {
		return row;
	}
	
	/** Set this hexagon's row coordinate. */
	public void setRow(int r) {
		row = r;
	}
	
	/** Return this hexagon's column coordinate. */
	public int getColumn() {
		return column;
	}
	
	/** Set this hexagon's column coordinate. */
	public void setColumn(int c) {
		column = c;
	}
	
	/** Return the neighbor in a certain direction of this hex. */
	public Hex getNeighbor(int direction) {
		return neighbors.get(direction);
	}
	
	
}
