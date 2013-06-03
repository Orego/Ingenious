/** The main class to run the game. */
public class Game {

	/** Zero-based index of the current player. */
	private int currentPlayerIndex;
	
	/** Returns the zero-based index of the current player. */
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}
	
	/** Advance to the next player. */
	public void switchPlayers() {
		currentPlayerIndex = (currentPlayerIndex + 1) % 2;
	}
	
}
