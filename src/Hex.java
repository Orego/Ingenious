import java.util.ArrayList;

/** A hexagon on the board with its coordinates, occupied with a certain color and linked to its neighbors. */
public class Hex {
	
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
		neighbors = new ArrayList<Hex>(6);
	}
	
	/** Set the given neighbor to the Hex h. */
	public void setNeighbor(int index, Hex h) {
		neighbors.set(index, h);
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
	
	/** Return this hexagon's column coordinate. */
	public int getColumn() {
		return column;
	}
}
