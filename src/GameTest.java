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
	
	public void playTwoValidInitialMoves() {
		Tile tile = new Tile(0, 1);
		game.placeTile(tile, 5, 9, Hex.UP_RIGHT);
		game.placeTile(tile, 10, 9, Hex.LEFT);
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
		// play next to the bottom right corner
		assertTrue(game.placeTile(tile, 10, 9, Hex.LEFT));
		// play away from corners
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
	
	@Test
	public void testNoOverlap() {
		playTwoValidInitialMoves();
		Tile tile = new Tile(0, 1);
		// move once
		assertTrue(game.placeTile(tile, 5, 5, 0));
		// try to play an overlapping tile
		assertFalse(game.placeTile(tile, 6, 6, 2));
		// try to play a tile over a corner hex
		assertFalse(game.placeTile(tile, 5, 0, 0));
	}
	
	@Test
	public void testScore1(){
		assertEquals(0,game.getPlayer(0).getScore(0));
	}
	
	@Test
	public void testScore2() {
		// Place a tile next to a corner hex
		game.play(0, new Tile(0, 1), 0, 1, 0);
		assertEquals(1, game.getPlayer(0).getScore(0));
	}
	
	@Test
	public void testScore3() {
		// Find score from both hexes of tile
		game.play(1, new Tile(0, 1), 0, 1, 0);
		game.play(0, new Tile(3, 3), 5, 1, 1);
		game.play(1, new Tile(3, 0), 1, 0, 0);
		assertEquals(3, game.getPlayer(1).getScore(0));
	}

	@Test
	public void testScore4() {
		// Don't find score through other hex in tile
		game.play(1, new Tile(0, 1), 0, 1, 0);
		game.play(0, new Tile(3, 3), 5, 1, 1);
		game.play(1, new Tile(0, 0), 1, 0, 0);
		assertEquals(4, game.getPlayer(1).getScore(0));
		
		
	}
	@Test
	public void testScore5() {
		// Don't find score through other hex in tile
		game.play(1, new Tile(0, 1), 0, 1, 0);
		game.play(0, new Tile(3, 3), 5, 1, 1);
		game.play(1, new Tile(0, 0), 1, 0, 0);
		game.play(1, new Tile(0, 5), 2, 2, 0);
		System.out.println(game.getBoard());
		assertEquals(6, game.getPlayer(1).getScore(0));
		
		

}}
