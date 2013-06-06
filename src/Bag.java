import java.util.ArrayList;
import java.lang.Math;

public class Bag extends ArrayList<Tile> {

	public static void main(String[] args) {
	}

	public Bag() {
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 5; y++) {
				add(new Tile(x, x));
			}
		}

		for (int x = 0; x < 6; x++) {
			for (int y = x + 1; y < 6; y++) {
				for (int z = 0; z < 6; z++) {
					add(new Tile(x, y));

				}
			}

		}
		shuffle();
	}

	public Tile draw() {
		shuffle();
		return remove(size() - 1);
	}

	public void shuffle() {
		int currentSize = size();
		ArrayList<Tile> temp = new ArrayList(currentSize);
		for (int x = 0; x < currentSize; x++) {
			int tempSize = size();
			temp.add(remove((int) (Math.random() * tempSize)));
		}
		addAll(temp);
	}
}
