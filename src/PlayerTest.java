import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Game g;
	Player p0;
	
	@Before
	public void setUp() throws Exception {
		g = new Game();
		p0 = g.getPlayer(0);
	}
	
	@Test
	public void testGetScore() {
		assertEquals(0, p0.getScore(2));
		p0.addScore(2, 2);
		assertEquals(2, p0.getScore(2));
		p0.addScore(2, 3);
		assertEquals(5, p0.getScore(2));
		assertEquals(0, p0.getScore(3));
	}

	@Test

	public void testToString() {
		Player p = new Player(new Game());
		assertEquals("[0, 0, 0, 0, 0, 0]", p.toString());
		p.addScore(3, 7);
		assertEquals("[0, 0, 0, 7, 0, 0]", p.toString());
	}


	public void testRefreshHand() {
		Hand oldHand = new Hand();
		oldHand.addAll(p0.getHand());
		p0.placeTile(5, 0, 1, 0);
		assertEquals(6, p0.getHand().size());
		assertTrue(oldHand.get(5) != p0.getHand().get(5));
	}

}
