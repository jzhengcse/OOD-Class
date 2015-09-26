import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JApplet;

public class HelloApplet extends JApplet {
	public HelloApplet() {
		
	}
    @Override
    public void init() {
        setLayout(new FlowLayout()); 
     
        // Code to set up the GUI
    }
    @Override
    public void paint(Graphics g) {
       super.paint(g);
       g.drawString("Hello, Applet World!", 10, 20);
    }
    
//	public static void main(String[] args) {
//		HelloApplet testOjbect1=new HelloApplet();
//	}
}