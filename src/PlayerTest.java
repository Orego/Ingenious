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

	@Test
	public void testRefreshHand() {
		Hand oldHand = new Hand();
		oldHand.addAll(p0.getHand());
		p0.placeTile(5, 0, 1, 0);
		assertEquals(6, p0.getHand().size());
		assertTrue(oldHand.get(5) != p0.getHand().get(5));
	}
	
	@Test
	public void testPlaysLeft() {
		assertEquals(0, p0.getPlaysLeft());
		p0.startTurn();
		assertEquals(1, p0.getPlaysLeft());
		
	}

	@Test
	public void testCanSwapTiles() {
		assertFalse(p0.canSwapTiles());
		p0.addScore(1, 1);
		assertFalse(p0.canSwapTiles());
		p0.addScore(2, 1);
		assertFalse(p0.canSwapTiles());
		p0.addScore(3, 1);
		assertFalse(p0.canSwapTiles());
		p0.addScore(4, 1);
		assertFalse(p0.canSwapTiles());
		p0.addScore(5, 1);
		assertTrue(p0.canSwapTiles());
	}
	
	@Test
	public void testSwapTiles() {
		Tile originalFirstTile = p0.getHand().get(0);
		
		g.placeTile(p0.getHand().get(5), 0, 1, 0);
		p0.getHand().remove(5);
		p0.swapTiles();
		
		// the player should have 6 tiles
		assertEquals(6, p0.getHand().size());		
		
		// it's player 1's turn now
		assertEquals(1, g.getCurrentPlayerIndex());
		
		// the player's tiles should not be the same as they started: make sure the first one is different than it was
		Tile newFirstTile = p0.getHand().get(0);
		assertFalse(originalFirstTile == newFirstTile);
	}
	
}
