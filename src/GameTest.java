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
	
	@Test
	public void testPlaceTile() {
		Tile tile = new Tile(0, 1);
		assertEquals(Board.VACANT, game.getBoard().getHex(2, 4).getColor());
		assertEquals(Board.VACANT, game.getBoard().getHex(2, 5).getColor());
		game.placeTile(tile, 2, 4);
		assertEquals(0, game.getBoard().getHex(2, 4).getColor());
		assertEquals(1, game.getBoard().getHex(2, 5).getColor());
	}

	@Test
	public void testLegalMove() {
		Tile tile = new Tile(0,0);
		assertTrue(game.placeTile(tile, 4, 4));
		assertFalse(game.placeTile(tile, 0, 5));
		assertFalse(game.placeTile(tile, 0, 6));
	}
}
