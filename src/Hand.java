import java.util.ArrayList;

/** A player's hand of 6 tiles */
public class Hand extends ArrayList<Tile> {

	public static void main(String[] args) {
		Hand h = new Hand(new Bag());
		System.out.println("Here is a hand: " + h);
	}
	
	public Hand() {
	}
	
	public Hand(Bag bag) {
		for(int i = 0; i < 6; i++) {
			add(bag.draw());
		}
	}
}
