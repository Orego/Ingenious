import java.util.Arrays;

public class Player {
	private int[] score;
	private Hand hand;
	private boolean hasPlayed;
	private Game gameBeingPlayed;
	
	public Player(Game game) {
		score = new int[6];
		hand = new Hand(game.getBag());
		hasPlayed = false;
		gameBeingPlayed = game;
	}
	
	

	public static void main(String[] args) {
		Game g = new Game();
		Player p = g.getPlayer(0);
		System.out.println("Your initial scores are:\n"
				+ Arrays.toString(p.score));
		p.addScore(2, 4);
		p.addScore(5, 1);
		System.out
				.println("After adding 4 points to color 2 and adding 1 point to color 5, your scores are:\n"
						+ Arrays.toString(p.score));
		System.out.println("\nA random hand:\n" + p.getHand());
		p.placeTile(5, 0, 1, 0);
		System.out.println("After playing the 6th tile in their hand, their new hand is:\n" + p.getHand());
		
	}

	/** Gets the hand of the player. */
	public Hand getHand() {
		return hand;
	}
	
	/** Gets the score of given color */
	public int getScore(int color) {
		return score[color];
	}

	/** Adds the amount to the score of color. */
	public void addScore(int color, int amount) {
		if(score[color]<18){
		score[color] += amount;
			if(score[color]>=18){
				gameBeingPlayed.switchPlayers();
			}
		}
		
	}
	
	/** Returns true if this player has played their first turn. */
	public boolean hasPlayed() {
		return hasPlayed;
	}
	
	/** Places a tile from the hand and removes that tile from their hand */
	public void placeTile(int tileIndex, int row, int column, int rotation) {
		Tile tile = hand.get(tileIndex);
		gameBeingPlayed.placeTile(tile, row, column, rotation);
		hand.remove(tileIndex);
		refreshHand();
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


	/** Fills the hand to six tiles. */
	public void refreshHand() {
		while(hand.size() < 6) {
			hand.add(gameBeingPlayed.getBag().draw());
		}
	}

}
