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
	
	public void setRotation(int r){
		this.rotation=r;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		incrementRotation();
		repaint();
		
		
	}
	public TileGUI(){
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
		
		
		drawHex(g2, 3, 1, -1);
		
		drawHex(g2, 5, COORDINATES[rotation][0], COORDINATES[rotation][1]);
	}


	public static final int MESSAGE_X = 300;
	public static final int MESSAGE_Y = 100;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				SizedFrame frame = new SizedFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			}
		});

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


class SizedFrame extends JFrame{
	private int colorOne;
	private int colorTwo;
	
	private TileGUI comp;
	public SizedFrame() {
		
		// get screen dimensions

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		
//	      addMouseListener( this );
//
//	      addMouseMotionListener( this);
	      // set frame width and height and let platform pick location of screen
		setSize(screenWidth / 2, screenHeight / 2);
		setLocationByPlatform(true);
		// can set frame icon and title here, currently no icon
		setTitle("Rotator");
		
		


	    
	    

		comp = new TileGUI();
		add(comp);
		
	}







}

