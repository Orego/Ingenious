
import java.awt.Color;


public class UIState {
	public Game game = new Game();
	public int rotation = 2;
	public int row;
	public int col;
	public static final int HEX_HEIGHT = 50;
	public static final double HEX_WIDTH = HEX_HEIGHT * Math.sqrt(3)/2;
	public boolean validTilePosition;
	public Color MEDIUM_GRAY = new Color(75, 75 ,75);
	public Color BACKGROUND1 = new Color(220, 220, 220);
	public Color BACKGROUND2 = new Color(190, 190, 190);
}
