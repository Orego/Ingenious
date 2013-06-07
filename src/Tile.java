/** A tile with two colors. */
public class Tile {

	/** One of the two colors on the tile. */
	private final int a;

	/** The other color on the tile. */
	private final int b;

	/**
	 * @param a Color of the first hex.
	 * @param b Color of the second hex.
	 */
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

	@Override
	public String toString() {
		return "<" + a + ", " + b + ">";
	}

}
