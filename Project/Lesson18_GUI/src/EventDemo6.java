import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JApplet;

public class EventDemo6 extends JApplet implements KeyListener {
    private String event="";                         // description of keyboard event 

    @Override
    public void init() {                             // set up UI                    
       this.setLayout(new FlowLayout()); 
       this.addKeyListener(this);                        // listen for keyboard events
       this.setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {                  // draw message to applet                
       super.paint(g);

       g.drawRect(0, 0, getWidth(), getHeight());    // show bounds of applet

       g.drawString(event, 10, 50);
    }

    public void keyPressed(KeyEvent e) {             // handle key presses
       event = e.getKeyChar() + " pressed"; 
       repaint();
    }

    public void keyReleased(KeyEvent e) {             // handle key releases
       event = e.getKeyChar() + " released"; 
                 
       repaint();
    }

    public void keyTyped(KeyEvent e) {               // handle typing on applet
       event = e.getKeyChar() + " typed";

       repaint();
    }
}