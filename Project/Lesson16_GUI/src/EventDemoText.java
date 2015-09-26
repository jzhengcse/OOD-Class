import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class EventDemoText extends JApplet implements ActionListener {
    private JTextField nameField;                          // field to enter name
    private String message;                                // message to read back to user
    
    @Override
    public void init() {                                   // set up GUI
        setLayout(new FlowLayout()); 

        message = "";                                      // initially, draw nothing
         
        add(new JLabel("Enter your name here:"));          // create GUI for entering name
        nameField = new JTextField(10);
        nameField.addActionListener(this);                 // register name field w/ event handler
        add(nameField);
    }

    @Override
    public void paint(Graphics g) {                        // display message string                        
       super.paint(g);

       g.drawString(message, 10, 50);                      // print out entered name
    }

    public void actionPerformed(ActionEvent e) {           // process "Enter" in nameField
        message = "Name entered was: " + nameField.getText();

        repaint();                                       
    }
}
  