import java.awt.Color;

public class UIState {
	public static final int PLACE_TILE_OR_RESELECT = 1;
	public int currentState = 1; 
	public Game game = new Game();
	public double centerx;
	public double centery;
	public static final int HEX_HEIGHT = 50;
	public static final double HEX_WIDTH = HEX_HEIGHT * Math.sqrt(3)/2;
	public boolean validTilePosition;
}
