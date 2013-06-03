import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TileTest {

	private Tile tile;
	
	@Before
	public void setUp() throws Exception {
		tile = new Tile(5, 2);
	}

	@Test
	public void testGetters() {
		assertEquals(5, tile.getA());
		assertEquals(2, tile.getB());
	}

}
