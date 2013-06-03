import static org.junit.Assert.*;

import org.junit.Test;


public class HandTest {

	@Test
	public void testAdd() {
		Hand h = new Hand();
		h.add(new Tile(1, 2));
		h.add(new Tile(3, 4));
		assertEquals("[<1, 2>, <3, 4>]", h.toString());
	}
	
	@Test
	public void testInitialHand() {
		Bag bag = new Bag();
		assertEquals(120, bag.size());
		Hand h = new Hand(bag);
		assertEquals(114, bag.size());
		assertEquals(6, h.size());
	}

}
