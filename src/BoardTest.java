import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@Test
	public void testConstructor() {
		assertEquals(0, board.getColor(0, 0));
		assertEquals(1, board.getColor(0, 5));
		assertEquals(2, board.getColor(5, 0));
		assertEquals(3, board.getColor(5, 10));
		assertEquals(4, board.getColor(10, 5));
		assertEquals(5, board.getColor(10, 10));
		assertEquals(Board.VACANT, board.getColor(2, 3));
	}

	@Test
	public void testNeighbors() {
		// verify that neighbors have the right coordinates
		Hex h = board.getHex(0, 0);
		Hex rightNeighbor = h.getNeighbor(Hex.RIGHT);
		assertEquals(rightNeighbor.getRow(), 0);
		assertEquals(rightNeighbor.getColumn(), 1);
		
		// verify that invalid neighbors are null
		assertNull(h.getNeighbor(Hex.LEFT));
		assertNull(h.getNeighbor(Hex.UP_LEFT));
		assertNull(board.getHex(1, Board.SIDE_LENGTH).getNeighbor(Hex.RIGHT));
		
		// verify that some invalid hexes don't exist
		assertEquals(null, board.getHex(0, Board.SIDE_LENGTH + 1));
	}
}
