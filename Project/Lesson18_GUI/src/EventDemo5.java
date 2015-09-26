import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.JApplet;

public class EventDemo5 extends JApplet implements MouseListener {
    private int x;              // x coordinate of mouse event
    private int y;              // y coordinate of mouse event
    private String event;       // description of mouse event 

    @Override
    public void init() {                              // set up GUI         
        setLayout(new FlowLayout()); 

        addMouseListener(this);                      // listen for mouse events

        x = -1;                                      // set x negative for no initial message
    }

    @Override
    public void paint(Graphics g) {                   // draw message to screen
       super.paint(g);

       g.drawRect(0, 0, getWidth(), getHeight());   // show bounds of applet

       if(x != - 1) {                               // display event during repainting only 
           g.drawString("Mouse event " + event + " at (" + x + ", " + y + ")", 10,50);
       }
    }

    public void mousePressed(MouseEvent e) {        // save coordinates of presses
        x = e.getX();
        y = e.getY();
        event = "press"; 

        repaint();
    }

    public void mouseClicked(MouseEvent e) {         // save coordinates of clicks
        x = e.getX();
        y = e.getY();
        event = "click"; 

        repaint();
    }

    public void mouseReleased(MouseEvent e) {        // save coordinates of releases
        x = e.getX();
        y = e.getY();
        event = "release"; 

        repaint();
    }

    public void mouseEntered(MouseEvent e) {        // save coordinates when mouse enters applet
        x = e.getX();
        y = e.getY();
        event = "enter"; 

        repaint();
    }

    public void mouseExited(MouseEvent e) {        // save coordinates when mouse leaves applet
        x = e.getX();
        y = e.getY();
        event = "exit"; 

        repaint();
   }
}