import java.util.Scanner;

/** A tile with two colors. */
public class Tile {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a number for the first color: ");
		int a = in.nextInt();
		System.out.print("Enter a number for the second color: ");
		int b = in.nextInt();
		Tile t = new Tile(a, b);
		System.out.println("The first number was: " + t.getA());
		System.out.println("The second number was: " + t.getB());
	}
	
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

	@Override
	public String toString() {
		return "<" + a + ", " + b + ">";
	}
}
