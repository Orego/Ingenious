import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class GameTest {

	private Game game;
	
	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@Test
	public void testSwitchPlayers() {
		assertEquals(0, game.getCurrentPlayerIndex());
		game.switchPlayers();
		assertEquals(1, game.getCurrentPlayerIndex());
		game.switchPlayers();
		assertEquals(0, game.getCurrentPlayerIndex());
	}

}
