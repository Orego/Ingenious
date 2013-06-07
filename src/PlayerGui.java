import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class PlayerGui extends HexGui implements MouseListener, MouseMotionListener{
	
	private Game game;
	private int selectedTile;
	private JPanel panel;
	private int playerIndex;
	
	public PlayerGui(Game game, JPanel panel, int playerIndex) {
		selectedTile = -1;
		this.game = game;
		this.panel = panel;
		addMouseListener(this);
		addMouseMotionListener(this);
		this.playerIndex = playerIndex;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if((x >= 30) && (x <= 30 + 2 * BoardComponent.HEX_WIDTH)) {
			for(int i = 0; i < 6; i++) {
				if((y >= (30 + i*(BoardComponent.HEX_HEIGHT + 30))) 
				&& (y <= (30 + BoardComponent.HEX_HEIGHT + i*(BoardComponent.HEX_HEIGHT + 30)))) {
					if (i < game.getPlayer(playerIndex).getHand().size()) {
						selectedTile = i;
					}
				}
			}
		}
		panel.repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Game getGame() {
		return game;
	}
	
	public int getSelectedTile() {
		return selectedTile;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int centerx;
		int centery;
		// Draw the six tiles of a player's hand
		for (int i = 0; i < game.getPlayer(playerIndex).getHand().size(); i++) {
			centerx = (int)(30 + HEX_WIDTH / 2);
			centery = 30 + HEX_HEIGHT / 2 + 80 * i;
			drawHex(g, game.getPlayer(playerIndex).getHand().get(i).getA(), centerx, centery, 0, 0, false, ((i == selectedTile) && (playerIndex == game.getCurrentPlayerIndex())));
			centerx += HEX_WIDTH;
			drawHex(g, game.getPlayer(playerIndex).getHand().get(i).getB(), centerx, centery, 0, 0, false, ((i == selectedTile) && (playerIndex == game.getCurrentPlayerIndex())));
			centerx -= HEX_WIDTH;
		}

		// Display the scores of a player's hand
		for (int i = 0; i < 6; i++) {
			if (i == 0) {
				g2.setPaint(Color.RED);
			} else if (i == 1) {
				g2.setPaint(Color.ORANGE);
			} else if (i == 2) {
				g2.setPaint(Color.YELLOW);
			} else if (i == 3) {
				g2.setPaint(Color.GREEN);
			} else if (i == 4) {
				g2.setPaint(Color.CYAN);
			} else {
				g2.setPaint(Color.MAGENTA);
			}

			g2.fill(new Rectangle2D.Double(160, 180 + i * 50 , 50, 50));
			g2.setPaint(Color.BLACK);
			int score = game.getPlayer(playerIndex).getScore(i);
			String s = score + "";
			g2.drawString(s, 180, 210 + i * 50);
		}
	}

	public void setSelectedTile(int i) {
		selectedTile = i;
	}

}
