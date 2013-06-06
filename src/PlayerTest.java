import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Player p0;
	Game game;
	
	@Before
	public void setUp() throws Exception {
		game = new Game();
		p0 = game.getPlayer(0);
//		p0 = new Player(new Game());
//		p0.startTurn();
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
		assertEquals(1, p0.getPlaysLeft());
	}

	@Test
	public void testCanSwapTiles() {
		// add points to every color except for ORANGE
		p0.addScore(0, 1);
		p0.addScore(2, 1);
		p0.addScore(3, 1);
		p0.addScore(4, 1);
		p0.addScore(5, 1);
		assertTrue(p0.getHand().size() == 6);
		// remove a tile from the hand
		p0.getHand().remove(3);
		// make sure that worked
		assertTrue(p0.getHand().size() == 5);
		// add an ORANGE tile to the hand
		p0.getHand().add(new Tile(1,2));
		// make sure that worked
		assertTrue(p0.getHand().size() == 6);
		// make sure that player can't swap tiles
		assertFalse(p0.canSwapTiles());
		// clear the hand and make sure it worked
		p0.getHand().clear();
		assertTrue(p0.getHand().size() == 0);
		// add a bunch of non-ORANGE tiles and make sure it worked
		p0.getHand().add(new Tile(0,2));
		p0.getHand().add(new Tile(0,2));
		p0.getHand().add(new Tile(0,2));
		p0.getHand().add(new Tile(0,2));
		p0.getHand().add(new Tile(0,2));
		assertTrue(p0.getHand().size() == 5);
		// make sure that user can swap tiles
		assertTrue(p0.canSwapTiles());
	}
	
	@Test
	public void testSwapTiles() {
		Tile originalFirstTile = p0.getHand().get(0);
		
		game.play(0, p0.getHand().get(5), 0, 1, 0);
		p0.getHand().remove(5);
		p0.swapTiles();
		
		// the player should have 6 tiles
		assertEquals(6, p0.getHand().size());		
		
		// it's not player 0's turn anymore
		assertEquals(0, p0.getPlaysLeft());
		
		// the player's tiles should not be the same as they started: make sure the first one is different than it was
		Tile newFirstTile = p0.getHand().get(0);
		assertFalse(originalFirstTile == newFirstTile);
	}
	
	@Test
	public void testBonus1() {
		p0.addScore(3, 18);
		assertEquals(2, p0.getPlaysLeft());
	}
	
	@Test
	public void testBonus2() {
		p0.addScore(2, 10);
		p0.addScore(5, 18);
		p0.addScore(2, 10);
		assertEquals(3, p0.getPlaysLeft());
		assertEquals(18, p0.getScore(2));
	}

	@Test
	public void testBonus3() {
		p0.addScore(1, 18);
		p0.addScore(1, 18);
		assertEquals(2, p0.getPlaysLeft());		
	}
}
