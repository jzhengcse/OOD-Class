import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Shapes extends JPanel{

	/**
	 * @param args
	 */
	private int choice; //user's choice of which shape to draw
	
	public Shapes(int userChoice) {
		choice=userChoice;
	}
	
	// draws a cascade of shapes starting from the top-left corner
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int steps=10;
		int xUnit=(getWidth()-50)/steps;
		int yUnit=(getHeight()-50)/steps;
		for (int i=0; i<steps; i++) {
			//pick the shape based on the user's choice
			switch (choice) {
			case 1:
				g.drawRect(getWidth()/2-25-i*xUnit/2,getHeight()/2-25-i*yUnit/2,50+i*xUnit,50+i*yUnit);
				break;
			case 2:
				g.drawOval(getWidth()/2-25-i*xUnit/2,getHeight()/2-25-i*yUnit/2,50+i*xUnit,50+i*yUnit);
				break;
			}
			
		}
	}
	
	
	
	public static void main(String[] args) {
		String input=JOptionPane.showInputDialog("Enter 1 to draw rectangles\n"+"Enter 2 to draw ovals");
		int choice=Integer.parseInt(input);
		
		Shapes panel=new Shapes(choice);
		JFrame application=new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(300,300);
		application.setVisible(true);

	}

}
