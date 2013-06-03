import static org.junit.Assert.*;

import org.junit.Test;


public class HandTest {

	@Test
	public void test() {
		Hand h = new Hand();
		h.add(new Tile(1, 2));
		h.add(new Tile(3, 4));
		assertEquals("[<1, 2>, <3, 4>]", h.toString());
	}

}
