import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Lab12Main extends JApplet{
	
	private GridBagLayout layout2;    // layout of applet
	private GridBagConstraints constraints; //constraints for the layout
	private JTextField screen;                      //screeen of the calculator
	private JButton zero;                      // button #1 of 16 of the calculator
	private JButton one;                      // button #2 of 16 of the calculator
	private JButton two;                      // button #3 of 16 of the calculator
	private JButton three;                      // button #4 of 16 of the calculator
	private JButton four;                      // button #5 of 16 of the calculator
	private JButton five;                      // button #6 of 16 of the calculator
	private JButton six;                      // button #7 of 16 of the calculator
	private JButton seven;                      // button #8 of 16 of the calculator
	private JButton eight;                      // button #9 of 16 of the calculator
	private JButton nine;                      // button #10 of 16 of the calculator
	private JButton dot;                      // button #11 of 16 of the calculator
	private JButton equal;                      // button #12 of 16 of the calculator
	private JButton plus;                      // button #13 of 16 of the calculator
	private JButton minus;                      // button #14 of 16 of the calculator
	private JButton multiply;                      // button #15 of 16 of the calculator
	private JButton divide;                      // button #16 of 16 of the calculator


	
	public void init() {
		
		layout2=new GridBagLayout(); // set up layout
		constraints=new GridBagConstraints(); 
		
		setLayout(layout2);
		
		screen=new JTextField("0."); // initialize the screen
		screen.setEditable(false);
		screen.setHorizontalAlignment(JLabel.RIGHT);
		//screen.setAlignmentX(RIGHT_ALIGNMENT);
		zero=new JButton("0");  // initialize the buttons
		one=new JButton("1");
		two=new JButton("2");
		three=new JButton("3");
		four=new JButton("4");
		five=new JButton("5");
		six=new JButton("6");
		seven=new JButton("7");
		eight=new JButton("8");
		nine=new JButton("9");
		
		dot=new JButton(".");
		equal=new JButton("=");
		plus=new JButton("+");
		minus=new JButton("-");
		multiply=new JButton("*");
		divide=new JButton("/");
		
		
		
        constraints.fill = GridBagConstraints.BOTH; // define constraints for button
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 4;
        constraints.gridheight = 1; 
        constraints.insets = new Insets(6,6,6,6);
        

        constraints.gridx = 0;                   // define constraints for button
        constraints.gridy = 0;
        layout2.setConstraints(screen, constraints); 
        add(screen); 
        
        constraints.gridwidth = 1;
        constraints.insets = new Insets(6,6,6,6);
        
        constraints.gridx = 0;                   // define constraints for button
        constraints.gridy = 1;
        layout2.setConstraints(seven, constraints); 
        add(seven); 

        constraints.gridx = 1;                   // define constraints for button
        constraints.gridy = 1;
        layout2.setConstraints(eight, constraints); 
        add(eight); 
        
        
        constraints.gridx = 2;                   // define constraints for button
        constraints.gridy = 1;
        layout2.setConstraints(nine, constraints); 
        add(nine); 
        
        constraints.gridx = 3;                   // define constraints for button
        constraints.gridy = 1;
        layout2.setConstraints(divide, constraints); 
        add(divide); 
        
        
        constraints.gridx = 0;                   // define constraints for button
        constraints.gridy = 2;
        layout2.setConstraints(four, constraints); 
        add(four); 
        
        
        constraints.gridx = 1;                   // define constraints for button
        constraints.gridy = 2;
        layout2.setConstraints(five, constraints); 
        add(five); 
        
        constraints.gridx = 2;                   // define constraints for button
        constraints.gridy = 2;
        layout2.setConstraints(six, constraints); 
        add(six); 
        
        
        constraints.gridx = 3;                   // define constraints for button
        constraints.gridy = 2;
        layout2.setConstraints(multiply, constraints); 
        add(multiply); 
        
        
        constraints.gridx = 0;                   // define constraints for button
        constraints.gridy = 3;
        layout2.setConstraints(one, constraints); 
        add(one); 
        
        
        constraints.gridx = 1;                   // define constraints for button
        constraints.gridy = 3;
        layout2.setConstraints(two, constraints); 
        add(two); 
        
        constraints.gridx = 2;                   // define constraints for button
        constraints.gridy = 3;
        layout2.setConstraints(three, constraints); 
        add(three); 
        
        
        constraints.gridx = 3;                   // define constraints for button
        constraints.gridy = 3;
        layout2.setConstraints(minus, constraints); 
        add(minus); 
        
        
        
        constraints.gridx = 0;                   // define constraints for button
        constraints.gridy = 4;
        layout2.setConstraints(zero, constraints); 
        add(zero); 
        
        
        constraints.gridx = 1;                   // define constraints for button
        constraints.gridy = 4;
        layout2.setConstraints(dot, constraints); 
        add(dot); 
        
        constraints.gridx = 2;                   // define constraints for button
        constraints.gridy = 4;
        layout2.setConstraints(equal, constraints); 
        add(equal); 
        
        
        constraints.gridx = 3;                   // define constraints for button
        constraints.gridy = 4;
        layout2.setConstraints(plus, constraints); 
        add(plus); 
        
		


	}
	
    @Override
    public void paint(Graphics g) {
       super.paint(g);
    }
	
}


//public static void main(String[] args) {
//
//JFrame application = new JFrame();                               // the program itself
//Lab12Main applet1 = new Lab12Main();                            // applet1 of the calculator
//application.add(applet1);                                      // add applet1 to application
//application.setTitle("Application");                             // set title
//application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set frame to exit when it is closed
//application.setSize(400, 300);                                // window is 500 pixels wide, 400 high
//application.setVisible(true);          
//}