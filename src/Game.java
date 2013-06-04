import java.util.Scanner;

/** The main class to run the game. */
public class Game {

	public static void main(String[] args) {
		Game g = new Game();
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("Player " + g.currentPlayerIndex + ", hit return.");
			in.nextLine();
			g.switchPlayers();
		}
	}

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
