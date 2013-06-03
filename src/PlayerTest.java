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

}
