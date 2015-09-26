import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;


public class CircleApplet2 extends JApplet implements ActionListener {
//	private JLabel label1;
	
    private JLabel description;              // Describes program's functionality
    private JLabel promptCenterX;            // Prompt for center x-coordinate
    private JLabel promptCenterY;            // Prompt for center y-coordinate
    private JLabel promptRadius;             // Prompt for radius
    private JTextField inCenterX;            // Field to input center x-coordinate
    private JTextField inCenterY;            // Field to input center y-coordinate
    private JTextField inRadius;             // Field to input radius
    private int centerX;                     // Center x-coordinate of circle to draw
    private int centerY;                     // Center y-coordinate of circle to draw
    private int radius;                      // Radius of circle to draw


    @Override
    public void init() {                          // Set up GUI, initialize variables
       setLayout(new FlowLayout());
//       label1=new JLabel("Hello World");
       description = new JLabel("This program will draw a circle.");
       add(description);

       promptCenterX = new JLabel("Enter center x-coordinate: "); // Set up input
       add(promptCenterX);                                        // for x-coord.
       inCenterX = new JTextField(5);
       add(inCenterX);

       promptCenterY = new JLabel("Enter center y-coordinate: "); // Set up input
       add(promptCenterY);                                        // for y-coord.
       inCenterY = new JTextField(5);
       add(inCenterY);

       promptRadius = new JLabel("Enter radius and hit Enter:"); // Set up input
       add(promptRadius);                                        // for radius
       inRadius = new JTextField(5);
       inRadius.addActionListener(this);                         // Process upon enter
       add(inRadius);
   }

    @Override
    public void paint(Graphics g) {                      // Display results
       int startX;                                       // Upper left x of bounding rect.
       int startY;                                       // Upper left y of bounding rect.
	   
       super.paint(g);

       centerX=getWidth()/2;
       centerY=getHeight()/2;
       startX = centerX - radius;                        // Move by radius to get top corner
       startY = centerY - radius;

       g.fillOval(startX, startY, radius*2, radius*2);   // Draw circle
    }

    public void actionPerformed(ActionEvent e) {          // Process user actions
        //centerX = Integer.parseInt(inCenterX.getText()); // Store user inputs
        //centerY = Integer.parseInt(inCenterY.getText());
        radius  = Integer.parseInt(inRadius.getText());

        repaint();                                       // Redraw circle

    }
}
