
import java.awt.*;
import javax.swing.*;

public class GUIDemoGridBagVarySize extends JApplet {
    private JButton button1;                      // button #1 of 7 to demo layout
    private JButton button2;                      // button #2 of 7 to demo layout
    private JButton button3;                      // button #3 of 7 to demo layout
    private JButton button4;                      // button #4 of 7 to demo layout
    private JButton button5;                      // button #5 of 7 to demo layout
    private JButton button6;                      // button #6 of 7 to demo layout
    private JButton button7;                      // button #7 of 7 to demo layout   

    private GridBagLayout layout;                 // layout of applet
    private GridBagConstraints constraints;       // helper for the layout

    @Override
    public void init() {
        layout = new GridBagLayout();             // set up layout
        setLayout(layout); 
        constraints = new GridBagConstraints();   

        button1 = new JButton("1");               // initialize the buttons
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");

        constraints.fill = GridBagConstraints.BOTH; 
        constraints.weightx = 1.0;
        constraints.weighty = 1.0; 

        constraints.gridx = 0;                   // define constraints for button
        constraints.gridy = 0;
        constraints.gridwidth = 6;
        constraints.gridheight = 2; 
        layout.setConstraints(button1, constraints); 
        add(button1);  

//        constraints.gridx = 0;                   // define constraints for button
//        constraints.gridy = 1;
//        constraints.gridwidth = 2;
//        constraints.gridheight = 2; 
//        layout.setConstraints(button2, constraints); 
//        add(button2);
//
//        constraints.gridx = 3;                   // define constraints for button
//        constraints.gridy = 0;
//        constraints.gridwidth = 1;
//        constraints.gridheight = 3;  
//        layout.setConstraints(button3, constraints); 
//        add(button3);
        constraints.weightx = 0;
        constraints.weighty = 0; 
        constraints.gridx = 6;                   // define constraints for button
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;  
        layout.setConstraints(button4, constraints); 
        add(button4);
        
//        constraints.gridx = 2;                   // define constraints for button
//        constraints.gridy = 0;
//        constraints.gridwidth = 1;
//        constraints.gridheight = 1;  
//        layout.setConstraints(button5, constraints); 
//        add(button5);

//        constraints.gridx = 2;                   // define constraints for button
//        constraints.gridy = 2;
//        constraints.gridwidth = 1;
//        constraints.gridheight = 1;  
//        layout.setConstraints(button6, constraints); 
//        add(button6);
//
//        constraints.gridx = 2;                   // define constraints for button
//        constraints.gridy = 1;
//        constraints.gridwidth = 1;
//        constraints.gridheight = 1;  
//        layout.setConstraints(button7, constraints); 
//        add(button7);
    }

    @Override
    public void paint(Graphics g) {
       super.paint(g);
    }
}