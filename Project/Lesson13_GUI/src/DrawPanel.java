import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;


public class DrawPanel extends JPanel
{
    public DrawPanel() {                      // set up graphics window
       super();                               // this can be omitted
       //Color darkBlue = new Color(0, 0, 150); 
       setBackground(Color.white);
    }
    
    
    public void init() {                      // set up graphics window
        //super();                               // this can be omitted
        //Color darkBlue = new Color(0, 0, 150); 
        setBackground(Color.white);
     }
    
    @Override
    public void paintComponent(Graphics g) { 
        int width = getWidth();            // width of window in pixels
        int height = getHeight();            // height of window in pixels
        super.paintComponent(g);              // call superclass to make panel display correctly
        g.setColor(new Color(181, 0, 181));
        this.setBackground(Color.white);
       
        g.drawLine(0, height, width, 0); 
        g.drawRect(width/2-100, height/2-100, 200, 200); 
        g.drawOval(width/2-200, height/2-100, 400, 200); 
        g.drawRect(width/2-5, height/2-5, 10, 10); 
        g.drawArc(width/2-100, height/2-50, 200, 100, 0, 180); 
        g.drawString("Hello, World", width/2, height/2); 
        g.fill3DRect(50, 50, 50, 50, true);
        g.fillRoundRect(100, 100, 50, 50, 50, 50);
        g.fillArc(100, 200, 50, 50, -90, -90);
        // Drawing code goes here
        // Notice that you should call super.paintComponent method before you start drawing
        // Otherwise, what's been done in the super will cover your drawing
    }

    public static void main(String[] args) {
    	
    	JFrame application = new JFrame();                            // the program itself
        DrawPanel panel = new DrawPanel();                            // window for drawing
        application.add(panel);  
        
        application.setTitle("Application");
        //application.setBackground( Color.BLACK );
        //application.getContentPane().setBackground(Color.black);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set frame to exit
                                                                      // when it is closed
                 

        application.setSize(500, 400);         // window is 500 pixels wide, 400 high
        application.setVisible(true);          
    }

}