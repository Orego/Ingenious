import java.util.ArrayList;
import static java.lang.Math.*;



public class Bag extends ArrayList<Tile>{

	public Bag() {
		for(int i = 0; i < 120; i++) {
			add(new Tile((int)(6 * random()),(int)(6 * random())));
		}
	}
}
