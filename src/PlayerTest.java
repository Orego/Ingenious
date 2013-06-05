import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testGetScore() {
		Player p = new Player();
		assertEquals(0, p.getScore(2));
		p.addScore(2, 2);
		assertEquals(2, p.getScore(2));
		p.addScore(2, 3);
		assertEquals(5, p.getScore(2));
		assertEquals(0, p.getScore(3));
	}

	@Test
	public void testToString() {
		Player p = new Player();
		assertEquals("[0, 0, 0, 0, 0, 0]", p.toString());
		p.addScore(3, 7);
		assertEquals("[0, 0, 0, 7, 0, 0]", p.toString());
	}

}
