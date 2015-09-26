import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class EventDemo3 extends JApplet implements ActionListener {
    private JTextField nameField;                          // field to enter name
    private JButton okButton;                              // button to enter name
    private JButton clearButton;                           // button to clear name
    private String message;                                // message to read back to user
    
    @Override
    public void init() {                                   // set up GUI           
        setLayout(new FlowLayout());     

        add(new JLabel("Enter your name here:"));         // set up input for name text field
        nameField = new JTextField(10);
        nameField.addActionListener(this);
        add(nameField);

        okButton = new JButton("OK");                      // set up OK button
        okButton.addActionListener(this);
        add(okButton); 

        clearButton = new JButton("Clear");                // set up Clear button
        clearButton.addActionListener(this);
        add(clearButton); 
    }

    @Override
    public void paint(Graphics g) {                        // draw message to applet                    
       super.paint(g);

       g.drawString(message, 10, 50);
    }

    public void actionPerformed(ActionEvent e) {          // process nameField, okButton, clearButton      
        if(e.getSource() == nameField) {
           // process "Enter" in nameField
           message = "Hit enter in text field; Name entered was: " 
                   + nameField.getText();
        }
        if(e.getSource() == okButton) {                   // process clicking "OK" button
           message = "Clicked " + e.getActionCommand() + "; Name entered was: " 
                   + nameField.getText();       
        }
        if(e.getSource() == clearButton) {                // process clicking "Clear" button
           nameField.setText("");                        // clear the text field itself
           message = "Clicked " + e.getActionCommand();
        }

        repaint();                                       
    }
}
  