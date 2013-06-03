/** A tile with two colors. */
public class Tile {

	/** One of the two colors on the tile. */
	private int a;
	
	/** The other color on the tile. */
	private int b;
	
	public Tile(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	/** Returns one of the colors on this tile. */
	public int getA() {
		return a;
	}
	
	/** Returns the other color on this tile. */
	public int getB() {
		return b;
	}

}
