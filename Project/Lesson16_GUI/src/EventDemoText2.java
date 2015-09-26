import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class EventDemoText2 extends JApplet implements ActionListener {
    private JTextField fNameField;                         // field to enter first name
    private JTextField lNameField;                         // field to enter last name
    private String message;                                // message to read back to user
    
    @Override
    public void init() {                                    // set up GUI      
        setLayout(new FlowLayout()); 
        message = "";                                      // initially, draw nothing
         
        add(new JLabel("Enter first name here:"));         // create GUI for entering first name
        fNameField = new JTextField(10);
        fNameField.addActionListener(this);                // register name field w/ event handler
        add(fNameField);

        add(new JLabel("Enter last name here:"));          // create GUI for entering last name
        lNameField = new JTextField(10);
        lNameField.addActionListener(this);                // register name field w/ event handler
        add(lNameField);
    }

    @Override
    public void paint(Graphics g) {                        // draw name to applet  
       super.paint(g);

       g.drawString(message, 10, 50);                      // print out entered name
    }

    public void actionPerformed(ActionEvent e) {            // process "Enter" in either text field     
        if(e.getSource() == fNameField) { 
           // process first name entry
           message = "Name entered was: " + fNameField.getText();
        }
        if(e.getSource() == lNameField) {
           // process last name entry
           message = "Name entered was: " + lNameField.getText();
        }

        repaint();                                       
    }
}