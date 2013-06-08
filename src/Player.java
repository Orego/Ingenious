import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	private int[] score;
	private Hand hand;
	private boolean hasPlayed;
	private Game gameBeingPlayed;
	private int playsLeft;
	

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
					score[color]=18;
					incrementPlaysLeft(1);
				}
			}
		
	}
	
	/** Returns true if this player has played their first turn. */
	public boolean hasPlayed() {
		return hasPlayed;
	}
	
	/** Places a tile from the hand and removes that tile from their hand. */
	public void placeTile(int tileIndex, int row, int column, int rotation) {
		// get the tile
		Tile tile = hand.get(tileIndex);
		// tell the game to place it
		gameBeingPlayed.placeTile(tile, row, column, rotation);
		// remove it from my hand
		hand.remove(tileIndex);
		// refill my hand: or if I have no Tile corresponding to the lowest
		// score color then I get to swap all of my tiles
		// basically, the player can 
		refreshHand();
	}
	
	/** This should be called to indicate that this player has played their first turn already. */
	public void played() {
		hasPlayed = true;
	}
	
	@Override
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
		gameBeingPlayed.switchPlayers();
	}

	/** Return true if the player is allowed to swap all their tiles. */
	public boolean canSwapTiles() {
		// find the minimum score and minimum-scored color
		int minColor = 0;
		int min = getScore(minColor);
		for (int color = 0; color < 6; color++) {
			if (getScore(color) < min) {
				minColor = color;
				min = getScore(color);
			}
		}
		// we have the minimum, now.
		// count how many colors have that same score
		int count = 0;
		for (int color = 0; color < 6; color++) {
			if (getScore(color) == min) {
				count++;
			}
		}
		
		if (count != 1) {
			return false;
		}
		for (int i = 0; i < getHand().size(); i++) {
			Tile tile = getHand().get(i);
			if (tile.getA() == minColor || tile.getB() == minColor) {
				return false;
			}
		}
		return true;
	}

	/** Swap all the player's tiles with new ones from the bag. */
	public void swapTiles() {
		hand.clear();
		ArrayList<Tile> aside = new ArrayList<Tile>();
		aside.addAll(hand);
		Bag bag = gameBeingPlayed.getBag();
		while (hand.size() < 6) {
			hand.add(bag.draw());
		}
		bag.addAll(aside);
		bag.shuffle();
		gameBeingPlayed.switchPlayers();
	}
	
	public int getPlaysLeft() {
		return playsLeft;
	}
	
	public void incrementPlaysLeft(int i){
		playsLeft+=i;
	}



	public void startTurn() {
		playsLeft=1;
		
	}

	public int[] getScores() {
		return score;
	}
}
