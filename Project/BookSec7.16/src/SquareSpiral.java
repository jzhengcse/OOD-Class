

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SquareSpiral extends JPanel{
    public SquareSpiral() {                      // set up graphics window
        super();                               // this can be omitted
        //Color darkBlue = new Color(0, 0, 150); 
        //setBackground(Color.white);
     }
	
	
	// draws an X from the corners of the panel
    public void paintComponent(Graphics g) {

		//call paintComponent
		super.paintComponent(g);
		

		int unit=10;
		
		int radio=5;
		int centerX=getWidth()/2;
		int centerY=getHeight()/2;
		
		
		int finishAngel=180;
		
		
		for(int i=0;radio*2<getWidth()&radio*2<getHeight();i++) {
//			if(i%2==0 && i!=0) {
//				unit+=10;
//			}
			if(i%2==0) {

				g.drawArc(centerX-radio+5, centerY-radio, radio*2, radio*2, 0, finishAngel);

			} else {
				g.drawArc(centerX-radio, centerY-radio, radio*2, radio*2, 0, finishAngel);
			}
			
//			centerY=
			finishAngel=-finishAngel;
			radio+=5;
			
//			if(i%2==0) {
//				centerX+=5;
//			} else {
//				centerX-=5;
//			}
			

			
			
//			if(i%4==0) {
//				g.drawLine(centerX, centerY, centerX, centerY+unit);
//				centerY+=unit;
//				
//			} else if(i%4==1) {
//				g.drawLine(centerX, centerY, centerX-unit, centerY);
//				centerX-=unit;
//				
//			} else if(i%4==2) {
//				g.drawLine(centerX, centerY, centerX, centerY-unit);
//				centerY-=unit;
//				
//			} else {
//				g.drawLine(centerX, centerY, centerX+unit, centerY);
//				centerX+=unit;
//			}
		}
		
		
//		int width=getWidth();
//		int height=getHeight();
//		
//		int steps=15;
//		int horizontalUnit=width/steps;
//		int verticalUnit=height/steps;
//		
//		for (int i=0;i<steps;i++) {
//			g.drawLine(0,0,i*horizontalUnit,height-i*verticalUnit);
//			g.drawLine(width,height,i*horizontalUnit,height-i*verticalUnit);
//			g.drawLine(0,height,width-i*horizontalUnit,height-i*verticalUnit);
//			g.drawLine(width,0,width-i*horizontalUnit,height-i*verticalUnit);
//		}
		
//		for (int i=0;i<steps;i++) {
//			g.drawLine(0,i*verticalUnit,(i+1)*horizontalUnit,height);
//			g.drawLine(i*horizontalUnit,0,width,(i+1)*verticalUnit);
//			g.drawLine(0,height-i*verticalUnit,(i+1)*horizontalUnit,0);
//			g.drawLine(i*horizontalUnit,height,width,height-(i+1)*verticalUnit);
//		}
		
		
		//g.drawLine(0,height,width,0);
	}
    
    

	public static void main(String[] args) {
		
		// create a new frame to hold the panel
		JFrame application = new JFrame();
		
		//Create a panel that contains our drawing
		SquareSpiral panel = new SquareSpiral();
		
		//add the panel to the frame
		application.add(panel); 
		
		// set the frame to exit when it is closed
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		application.setSize(250,250); //set the size of the frame
		application.setVisible(true); //make the frame visible
	}
    
    
}
