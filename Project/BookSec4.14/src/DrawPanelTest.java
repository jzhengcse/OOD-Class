import javax.swing.JFrame;

public class DrawPanelTest {
	public static void main(String[] args) {
		
		// create a new frame to hold the panel
		JFrame application = new JFrame();
		
		//Create a panel that contains our drawing
		DrawPanel panel = new DrawPanel();
		
		//add the panel to the frame
		application.add(panel); 
		
		// set the frame to exit when it is closed
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		application.setSize(250,250); //set the size of the frame
		application.setVisible(true); //make the frame visible
	}
}
