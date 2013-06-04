import java.awt.*;
import javax.swing.*;

public class TileGUI {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				SimpleFrame frame= new SimpleFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
			}
		});
		

	}
}	
class SimpleFrame extends JFrame{
	public SimpleFrame(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
	}
	
	public static final int DEFAULT_WIDTH=600;
	public static final int DEFAULT_HEIGHT=500;


}
