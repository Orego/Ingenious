import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Scanner;

import javax.swing.*;

public class TileGUI {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable(){
			
			public void run(){
				

				SizedFrame frame= new SizedFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
			}
		});
		

	}
	
	
}	
class SimpleFrame extends JFrame{
	public SimpleFrame(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("hello");
	}
	
	public static final int DEFAULT_WIDTH=600;
	public static final int DEFAULT_HEIGHT=500;


}

class SizedFrame extends JFrame{
	private int colorOne;
	private int colorTwo;
	public SizedFrame(){

		//get screen dimensions
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		// set frame width and height and let platform pick location of screen
		
		setSize(screenWidth /2, screenHeight /2);
		setLocationByPlatform(true);
		 //can set frame icon and title here, currently no icon
		setTitle("Rectangle");
		NotHelloWorldComponent comp = new NotHelloWorldComponent();
		add(comp);
	}
	

	
}

class NotHelloWorldComponent extends JComponent{
	private Polygon hex1;
	private Polygon hex2;
	public void paintComponent(Graphics g){
		
		Graphics2D g2= (Graphics2D) g;
		
		
		
		// drawing a rectangle
		
		double leftX=200;
		double topY=150;
		double width=150;
		double height=150;
		
		
		//y coordinates for the first hex : 1, .5, -.5, -1, -.5, .5
		//x coordinates for the first hex : 0, -.866025, -0.866025, 0, .866025, .866025
		// The above coordinates are for a hexagon with radius(?) of 1. To get a larger hexagon, multiply 
		//the coordinates by the radius of the desired hexagon
		// These coordinates should yield a hexagon that has flat edges on its left and right sides, so as to allow
		// us to place two hexagons next to each other cleanly.
		
        int xPoly[] = {150, 20, 20, 150, 280, 280};
        int yPoly[] = {300, 225, 75, 0, 75, 225};
        
        
        // coordinates for hex 2
        
        int xPoly2[] = {410, 280, 280, 410, 540, 540};
        int yPoly2[] = {300, 225, 75, 0, 75, 225};

        hex1 = new Polygon(xPoly, yPoly, 6);
        hex2= new Polygon(xPoly2, yPoly2, 6);
		
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the color for the first hex (in integer form)");
		int a = in.nextInt();
		System.out.println("Please enter the color for the second hex (in integer form)");
		int b = in.nextInt();
		
		if(a==0){
		g2.setPaint(Color.RED);	
		}else if(a==1){
			g2.setPaint(Color.ORANGE);
		}else if(a==2){
			g2.setPaint(Color.YELLOW);
		}else if(a==3){
			g2.setPaint(Color.GREEN);
		}else if(a==4){
			g2.setPaint(Color.BLUE);
		}else if(a==5){
			g2.setPaint(Color.MAGENTA);
		}
		
        g.drawPolygon(hex1);
        g2.fill(hex1);
        

		
			if(b==0){
			g2.setPaint(Color.RED);	
			}else if(b==1){
				g2.setPaint(Color.ORANGE);
			}else if(b==2){
				g2.setPaint(Color.YELLOW);
			}else if(b==3){
				g2.setPaint(Color.GREEN);
			}else if(b==4){
				g2.setPaint(Color.BLUE);
			}else if(b==5){
				g2.setPaint(Color.MAGENTA);
			}
	        g.drawPolygon(hex2);
	        g2.fill(hex2);

		
		
		
//		g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);
		
		//Drawing an ellipse bounded by the rectangle
//		g2.setPaint(Color.BLUE);
//		Ellipse2D ellipse = new Ellipse2D.Double();
//		ellipse.setFrame(rect);
//		g2.draw(ellipse);
//		g2.fill(ellipse);
//		g2.setPaint(Color.MAGENTA);
//		// Drawing a circle
//		double centerX= rect.getCenterX();
//		double centerY= rect.getCenterY();
//		double radius = 150;
//		Ellipse2D circle = new Ellipse2D.Double();
//		circle.setFrameFromCenter(centerX, centerY, centerX+radius, centerY + radius);
//		g2.draw(circle);
//		g2.fill(circle);
	}
	
	
	
	public static final int MESSAGE_X = 300;
	public static final int MESSAGE_Y = 100;
}
