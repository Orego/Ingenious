import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class PlayerGui extends HexGui implements MouseListener,
		MouseMotionListener {

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
		if (game.getPlayer(game.getCurrentPlayerIndex()).getPlaysLeft() > 0) {
			int x = e.getX();
			int y = e.getY();
			game.setSelectedTile(null);
			if ((x >= 30) && (x <= 30 + 2 * BoardComponent.HEX_WIDTH)) {
				for (int i = 0; i < 6; i++) {
					if ((y >= (30 + i * (BoardComponent.HEX_HEIGHT + 30)))
							&& (y <= (30 + BoardComponent.HEX_HEIGHT + i
									* (BoardComponent.HEX_HEIGHT + 30)))) {
						if (i < game.getPlayer(playerIndex).getHand().size()) {
							selectedTile = i;
							game.setSelectedTile(game.getPlayer(playerIndex)
									.getHand().get(i));
						}
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
		// Draw the six tiles of a player's hand
		for (int i = 0; i < game.getPlayer(playerIndex).getHand().size(); i++) {
			double centerx = (30 + HEX_WIDTH / 2);
			double centery = 30 + HEX_HEIGHT / 2 + 80 * i;
			Color shading = Color.BLACK;
			// if ((i == selectedTile) && (playerIndex ==
			// game.getCurrentPlayerIndex())) {
			// shading = new Color(130, 130, 130);
			// }
			drawHex(g, game.getPlayer(playerIndex).getHand().get(i).getA(),
					centerx, centery, shading);
			centerx += HEX_WIDTH;
			drawHex(g, game.getPlayer(playerIndex).getHand().get(i).getB(),
					centerx, centery, shading);
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

			g2.fill(new Rectangle2D.Double(160, 180 + i * 50, 50, 50));
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
