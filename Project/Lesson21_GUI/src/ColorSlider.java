
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ColorSlider extends JApplet implements ChangeListener {
    private int redVal;                     // how much red is in the color map
    private FlowLayout layout;              // applet layout
    private JSlider redSlider;              // slider to change the amount of red
    private JLabel  redPrompt;              // prompt to label the red input slider
    private JLabel  redReading;             // display slider value

    @Override
    public void init() {
         layout = new FlowLayout();             // set up layout
         layout.setAlignment(FlowLayout.LEFT);
         setLayout(layout);

         redVal = 0;                            // no red initially

         redPrompt = new JLabel("Drag slider to vary 'redness': ");    // set up prompt
         add(redPrompt);

         redSlider = new JSlider(SwingConstants.HORIZONTAL,      // set up slider for red
                                 0, 255, 0);                     //  from 0-255, initially 0
         redSlider.setMajorTickSpacing(16);                      // break up ticks by 16
                                                                 // since 16^2 = 256
         redSlider.setMinorTickSpacing(8);
         redSlider.setPaintTicks(true);
         redSlider.addChangeListener(this);
         add(redSlider);

         redReading = new JLabel( Integer.toString( redVal ) );  // create a label based on redVal
         add(redReading);
    }

    // paint function omitted
    @Override
    public void paint(Graphics g) {
       super.paint(g);
       
    }

    public void stateChanged(ChangeEvent e) {      // handle red slider changing
        redVal = redSlider.getValue();
        redReading.setText(Integer.toString( redVal ));// save the red value
//redReading(Integer.toString( redVal ));  // create a label based on redVal
        //System.out.println(redVal);
        repaint();
    }
}