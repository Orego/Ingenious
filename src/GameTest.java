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
	
	protected void playTwoValidInitialMoves() {
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
		assertEquals(6, game.getPlayer(1).getScore(0));
	}
	
	@Test
	public void testScore6() {
		boolean legal= game.play(1, new Tile(0, 1),-1, 1, 0);
		assertFalse(legal);
		
	}
	
	@Test
	public void testBonus() {
		game.play(0, new Tile(0, 0), 0, 1, 0);
		game.play(1, new Tile(3, 3), 5, 1, 1);
		game.play(0, new Tile(0, 0), 1, 0, 0);
		game.play(1, new Tile(4, 4), 5, 3, 1);
		game.play(0, new Tile(0, 0), 2, 0, 0);
		game.play(1, new Tile(5, 5), 5, 4, 1);
		game.play(0, new Tile(0, 0), 3, 0, 0);
		game.play(1, new Tile(3, 3), 6, 2, 1);
		game.play(0, new Tile(0, 0), 4, 2, 1);
		
		// Player 0 gets over 18 points from this last move and should play again
		assertTrue(game.getCurrentPlayerIndex()==0);
		
	}
	@Test
	public void testSuccessfulPlay() {
		game.getPlayer(game.getCurrentPlayerIndex()).startTurn();
		game.play(0, new Tile(0, 0), 0, 1, 0);
		assertEquals(0, game.getPlayer(game.getCurrentPlayerIndex()).getPlaysLeft());
	}
	
	@Test
	public void testIllegalMove() {
		game.getPlayer(game.getCurrentPlayerIndex()).startTurn();
		assertFalse(game.play(0, new Tile(0, 0), 0, 0, 0));
		assertEquals(1, game.getPlayer(game.getCurrentPlayerIndex()).getPlaysLeft());		
	}
	
	@Test
	public void testGameOver() {
		for (int r = 0; r < 11; r++) {
			for (int c = 0; c < 11; c++) {
				if (game.getBoard().isValidHex(r, c)) {
					game.getBoard().setColor(r, c, 0);
				}
			}
		}
		game.getBoard().setColor(8, 3, -1);
		game.getBoard().setColor(8, 4, -1);
		game.getBoard().setColor(8, 5, -1);
		assertFalse(game.isOver());
		game.getBoard().setColor(8, 4, 4);
		assertTrue(game.isOver());
	}
	@Test
	public void testVictory() { 
		for(int i=0; i<6; i++){
			game.getPlayer(0).addScore(i, 14);
		}
		for(int i=0; i<5; i++){
			game.getPlayer(1).addScore(i, 17);
		}
		
		assertEquals(0, game.getWinner());
		game.getPlayer(1).addScore(5, 17);
		assertEquals(1, game.getWinner());
	}
	
	@Test
	public void testVictory2() {
		game.getPlayer(0).addScore(0, 4);
		game.getPlayer(0).addScore(1, 5);
		game.getPlayer(0).addScore(2, 6);
		game.getPlayer(0).addScore(3, 8);
		game.getPlayer(0).addScore(4, 10);
		game.getPlayer(0).addScore(5, 11);
		
		game.getPlayer(1).addScore(3, 4);
		game.getPlayer(1).addScore(1, 5);
		game.getPlayer(1).addScore(5, 6);
		game.getPlayer(1).addScore(0, 8);
		game.getPlayer(1).addScore(4, 11);
		game.getPlayer(1).addScore(2, 11);
		
		assertEquals(1, game.getWinner());
		game.getPlayer(0).addScore(3, 1);
		assertEquals(0, game.getWinner());
	}
	
	@Test
	public void testVictory3() {
		game.getPlayer(0).addScore(0, 4);
		game.getPlayer(0).addScore(1, 5);
		game.getPlayer(0).addScore(2, 6);
		game.getPlayer(0).addScore(3, 8);
		game.getPlayer(0).addScore(4, 10);
		game.getPlayer(0).addScore(5, 11);
		
		game.getPlayer(1).addScore(3, 4);
		game.getPlayer(1).addScore(1, 5);
		game.getPlayer(1).addScore(5, 6);
		game.getPlayer(1).addScore(0, 8);
		game.getPlayer(1).addScore(4, 10);
		game.getPlayer(1).addScore(2, 11);
		
		assertEquals(-1, game.getWinner());
	}

}
