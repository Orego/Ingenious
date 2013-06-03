import java.util.ArrayList;

/** A player's hand of 6 tiles */
public class Hand extends ArrayList<Tile>{
	public static void main(String[] args) {
	Hand h = new Hand();
	h.add(new Tile(1, 2));
	h.add(new Tile(3, 4));
	System.out.println("Here is a hand: " + h);
	}
}
