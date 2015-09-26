

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;


public class DrawBullseye  extends JPanel {
	//PRE:
	//POST: Class name DrawBullsevye that extends JPanel has been created 
    public DrawBullseye () {                      // set up graphics window
       super();                               // this can be omitted
       //Color darkBlue = new Color(0, 0, 150); 
       setBackground(Color.white);
    }
    
    @Override
    public void paintComponent(Graphics g) { 
    	//
    	// POST: Graphics g has been draw 
        int width = getWidth();            // width of window in pixels
        int height = getHeight();            // height of window in pixels
        super.paintComponent(g);              // call superclass to make panel display correctly       
        int numberOfCircles=10;  //set number of circle
        
        int diameter;
        
        // This condition statement is for the circles to to fully contained within the window
        if (width>=height) { 
        	diameter=height;
        } else {
        	diameter=width;
        }
        
        int increment=diameter/numberOfCircles;

        Color color1=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)); //random choose color1
        Color color2=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)); //random choose color2 that is different from color1
        while (color2==color1) {
        	color2=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        }
        for ( int i=0; i<numberOfCircles;i++) {
        	if(i%2==0) {
	        	g.setColor(color1);
	            g.fillOval(width/2-diameter/2, height/2-diameter/2, diameter, diameter); //stay in center
        	} else {
            
        	
		    	g.setColor(color2);
		        g.fillOval(width/2-diameter/2, height/2-diameter/2, diameter, diameter); //stay in center
        	}
            diameter-=increment;
            
        }
//        g.drawLine(0, height, width, 0); 
//        g.drawRect(width/2-100, height/2-100, 200, 200); 
//        g.drawOval(width/2-100, height/2-100, 200, 200); 
//        g.drawRect(width/2-5, height/2-5, 10, 10); 
//        g.drawArc(width/2-100, height/2-50, 200, 100, 0, 180); 
        
        // set color3 that is different from color1 and color2
        Color color3=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        while(color3==color1||color3==color2) {
        	color3=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        }
        
  
        g.setColor(color3);	//set name color
        
        Font font1 = new Font("SansSerif", Font.BOLD, 20); //set font
        g.setFont(font1);
        String s1="Jie Zheng";
        g.drawString(s1, (width-getFontMetrics(font1).stringWidth(s1))/2,getFontMetrics(font1).getHeight()); 
    }

    public static void main(String[] args) {
    	DrawBullseye  panel = new DrawBullseye ();                            // window for drawing
        JFrame application = new JFrame();                            // the program itself
        
        application.setTitle("APPlication");
        //application.setBackground( Color.BLACK );
        application.getContentPane().setBackground(Color.BLACK);
        
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set frame to exit
                                                                      // when it is closed
        application.add(panel);           

        application.setSize(500, 500);         // window is 500 pixels wide, 400 high
        application.setVisible(true);          
    }

}