
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
    public DrawPanel() {                      // set up graphics window
        super();                               // this can be omitted
        //Color darkBlue = new Color(0, 0, 150); 
        //setBackground(Color.white);
     }
	
	
	// draws an X from the corners of the panel
    public void paintComponent(Graphics g) {

		//call paintComponent
		super.paintComponent(g);
		
		int width=getWidth();
		int height=getHeight();
		
		int steps=15;
		int horizontalUnit=width/steps;
		int verticalUnit=height/steps;
//		
//		for (int i=0;i<steps;i++) {
//			g.drawLine(0,0,i*horizontalUnit,height-i*verticalUnit);
//			g.drawLine(width,height,i*horizontalUnit,height-i*verticalUnit);
//			g.drawLine(0,height,width-i*horizontalUnit,height-i*verticalUnit);
//			g.drawLine(width,0,width-i*horizontalUnit,height-i*verticalUnit);
//		}
		
		for (int i=0;i<steps;i++) {
			g.drawLine(0,i*verticalUnit,(i+1)*horizontalUnit,height);
			g.drawLine(i*horizontalUnit,0,width,(i+1)*verticalUnit);
			g.drawLine(0,height-i*verticalUnit,(i+1)*horizontalUnit,0);
			g.drawLine(i*horizontalUnit,height,width,height-(i+1)*verticalUnit);
		}
		
		
		//g.drawLine(0,height,width,0);
	}
}
