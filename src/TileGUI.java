import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

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
	public void paintComponent(Graphics g){
		
		Graphics2D g2= (Graphics2D) g;
		
		// drawing a rectangle
		
		double leftX=200;
		double topY=150;
		double width=400;
		double height=150;
		
		Rectangle2D rect= new Rectangle2D.Double(leftX, topY, width, height);
		
		g2.draw(rect);
		g2.fill(rect);
		g2.setPaint(Color.RED);
		g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);
		
		//Drawing an ellipse bounded by the rectangle
		g2.setPaint(Color.BLUE);
		Ellipse2D ellipse = new Ellipse2D.Double();
		ellipse.setFrame(rect);
		g2.draw(ellipse);
		g2.fill(ellipse);
		g2.setPaint(Color.MAGENTA);
		// Drawing a circle
		double centerX= rect.getCenterX();
		double centerY= rect.getCenterY();
		double radius = 150;
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX, centerY, centerX+radius, centerY + radius);
		g2.draw(circle);
		g2.fill(circle);
	}
	
	
	
	public static final int MESSAGE_X = 300;
	public static final int MESSAGE_Y = 100;
}
