
public class Player {
	
private int[] score = new int[6];

/** Gets the score of given color */
public int getScore(int color) {
	return score[color];
}

/** Adds the amount to the score of color. */
public void addScore(int color, int amount) {
	score[color] += amount;
}


}
