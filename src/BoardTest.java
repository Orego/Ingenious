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

}
