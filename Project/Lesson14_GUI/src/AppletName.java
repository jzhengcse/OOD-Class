import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;


public class AppletName extends JApplet implements ActionListener {
    // Declarations of class-wide variables

    @Override
    public void init() {
        setLayout(new FlowLayout()); 
     
        // Code to set up the GUI
    }

    @Override
    public void paint(Graphics g) {
       super.paint(g);

       // Code for drawing objects on the applet
    }

    public void actionPerformed(ActionEvent e) {
        // Code for processing user actions

        repaint();                                       
    }
}