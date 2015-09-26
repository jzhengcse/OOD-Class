import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Color; 
import javax.swing.*;


public class EventDemo4 extends JApplet implements ItemListener {
    private JRadioButton radioBlack;                       // radio button for black
    private JRadioButton radioRed;                         // radio button for red
    private JRadioButton radioGreen;                       // radio button for green
    private JRadioButton radioBlue;                        // radio button for blue
    private Color textColor;                               // color to display message

    @Override
    public void init() {                                  // set up GUI
        ButtonGroup  radioColors;                        // logical linkage for color buttons
		
        setLayout(new FlowLayout()); 

        textColor = Color.BLACK;                         // initially write in black
     
        add(new JLabel("Choose a color: "));             // set up prompt

        radioBlack = new JRadioButton("Black", true);    // create radio buttons
        radioRed = new JRadioButton("Red", false);
        radioGreen = new JRadioButton("Green", false);
        radioBlue = new JRadioButton("Blue", false);

        radioBlack.addItemListener(this);                // add listeners to them
        radioRed.addItemListener(this); 
        radioGreen.addItemListener(this); 
        radioBlue.addItemListener(this); 

        add(radioBlack);                                 // add them to GUI
        add(radioRed);
        add(radioGreen);
        add(radioBlue);

        radioColors = new ButtonGroup();                 // logically link buttons
        radioColors.add(radioBlack);
        radioColors.add(radioRed);
        radioColors.add(radioGreen);
        radioColors.add(radioBlue);
    }

    @Override
    public void paint(Graphics g) {                        // draw text to applet in color     
        super.paint(g);

        g.setColor(textColor);                             // change color as chosen
        g.drawString("Welcome to event handling", 10, 50);
    }

    public void itemStateChanged(ItemEvent e) {             // handle radio button changes
        if(e.getSource() == radioBlack && e.getStateChange() == ItemEvent.SELECTED) {  // black chosen
            textColor = Color.BLACK;
        }
        if(e.getSource() == radioRed && e.getStateChange() == ItemEvent.SELECTED) {    // red chosen
            textColor = Color.RED;
        }
        if(e.getSource() == radioGreen && e.getStateChange() == ItemEvent.SELECTED) {  // green chosen
            textColor = Color.GREEN;
        }
        if(e.getSource() == radioBlue && e.getStateChange() == ItemEvent.SELECTED) {   // blue chosen
            textColor = Color.BLUE;
        }

        repaint();                                       
    }
}
 