import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;




public class BagTest {	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shuffleTest() {
		Bag bag = new Bag();
		Tile testTile1 = bag.get(3);
		Tile testTile2 = bag.get(12);
		Tile testTile3 = bag.get(45);
		bag.shuffle();
		assertFalse(testTile1 == bag.get(3));
		assertFalse(testTile2 == bag.get(12));
		assertFalse(testTile3 == bag.get(45));
	}

}
