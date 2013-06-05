import java.util.Arrays;

public class Player {
	private int[] score;
	private boolean hasPlayed;

	public Player() {
		score = new int[6];
		hasPlayed = false;
	}

	public static void main(String[] args) {
		Player p = new Player();
		System.out.println("Your initial scores are:\n"
				+ Arrays.toString(p.score));
		p.addScore(2, 4);
		p.addScore(5, 1);
		System.out
				.println("After adding 4 points to color 2 and adding 1 point to color 5, your scores are:\n"
						+ Arrays.toString(p.score));
	}

	/** Gets the score of given color */
	public int getScore(int color) {
		return score[color];
	}

	/** Adds the amount to the score of color. */
	public void addScore(int color, int amount) {
		score[color] += amount;
	}
	
	/** Returns true if this player has played their first turn. */
	public boolean hasPlayed() {
		return hasPlayed;
	}
	
	/** This should be called to indicate that this player has played their first turn already. */
	public void played() {
		hasPlayed = true;
	}
	
	public String toString() {
		String result = "[";
		result = result + score[0];
		for (int i = 1; i < score.length; i++) {			
			result = result + ", " + score[i];
		}
		return result + "]";
	}

}
