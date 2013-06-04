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
		
		// initial placements:
		// try to not play next to a corner hex
		assertFalse(game.placeTile(tile, 5, 5, Hex.RIGHT));
		assertFalse(game.placeTile(tile, 5, 5, Hex.RIGHT));
		assertFalse(game.placeTile(tile, 5, 5, Hex.RIGHT));
		// play next to the right corner
		assertTrue(game.placeTile(tile, 5, 9, Hex.UP_RIGHT));
		// try to play next to the same corner (as the other player)
		assertFalse(game.placeTile(tile, 6, 9, Hex.RIGHT));
		// play away from corners
		assertTrue(game.placeTile(tile, 10, 9, Hex.LEFT));
		assertTrue(game.placeTile(tile, 5, 3, Hex.UP_LEFT));
		
		// subsequent placements
		assertEquals(Board.VACANT, game.getBoard().getHex(2, 4).getColor());
		assertEquals(Board.VACANT, game.getBoard().getHex(2, 5).getColor());
		game.placeTile(tile, 2, 4, 0);
		assertEquals(0, game.getBoard().getHex(2, 4).getColor());
		assertEquals(1, game.getBoard().getHex(2, 5).getColor());
		
		// test some legal and illegal moves
		assertTrue(game.placeTile(tile, 4, 4, 0));
		assertFalse(game.placeTile(tile, 0, 5, 0));
		assertFalse(game.placeTile(tile, 0, 6, 0));
		
		// test rotation
		game.placeTile(tile, 0, 1, 0);
		assertEquals(0, game.getBoard().getHex(0, 1).getColor());
		assertEquals(1, game.getBoard().getHex(0, 2).getColor());
		game.placeTile(tile, 1, 2, 4);
		assertEquals(0, game.getBoard().getHex(1, 2).getColor());
		assertEquals(1, game.getBoard().getHex(2, 2).getColor());
	}
}
