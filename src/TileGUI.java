import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Scanner;

import javax.swing.*;

public class TileGUI extends HexGui implements MouseListener{

	private Polygon hex1;
	private Polygon hex2;
	private int rotation;
	private int[] xPoly2 = { 236, 193, 193, 236, 279, 279 };
	private int[] yPoly2 = { 200, 175, 125, 100, 125, 175 };
	private PlayerGUI playerGui;
	
	public void setRotation(int r){
		this.rotation=r;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		incrementRotation();
		repaint();
		
		
	}
	public TileGUI(PlayerGUI playerGui){
		this.playerGui = playerGui;
		addMouseListener( this );
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/** Coordinates of second hex in rotated tile, indexed by rotation. */
	public static final int[][] COORDINATES = {{1, 0}, {0, -1}, {0, -2}, {1, -2}, {2, -1}, {2, 0}};
	
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		
		if(playerGui.getSelectedTile() != -1) {			
			drawHex(g2, playerGui.getGame().getPlayer(playerGui.getGame().getCurrentPlayerIndex()).getHand().get(playerGui.getSelectedTile()).getA(), 1, -1);
			
			drawHex(g2, playerGui.getGame().getPlayer(playerGui.getGame().getCurrentPlayerIndex()).getHand().get(playerGui.getSelectedTile()).getB(), COORDINATES[rotation][0], COORDINATES[rotation][1]);
		}
	}


	public void incrementRotation() {
		rotation = (rotation + 1) % 6;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


