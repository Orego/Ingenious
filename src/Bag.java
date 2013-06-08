import java.util.ArrayList;

/** The bag of tiles. */
public class Bag extends ArrayList<Tile> {

	private static final long serialVersionUID = 1L;

	public Bag() {
		for (int x = 0; x < 6; x++) {
			for (int i = 0; i < 5; i++) {
				add(new Tile(x, x));
			}
		}
		for (int x = 0; x < 6; x++) {
			for (int y = x + 1; y < 6; y++) {
				for (int i = 0; i < 6; i++) {
					add(new Tile(x, y));
				}
			}
		}
		shuffle();
	}

	public Tile draw() {
		return remove(size() - 1);
	}

	public void shuffle() {
		java.util.Collections.shuffle(this);
	}

}
