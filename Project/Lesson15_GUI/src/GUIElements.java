import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;


public class GUIElements extends JApplet {
	private JLabel label1;
	private JTextField textField1;
	private JPasswordField passwordField1;

	
	private JButton button1;
	
	private JTextArea textArea1;
	
	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	ButtonGroup buttonGroup2;
	
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	ButtonGroup buttonGroup1;
	
	private JComboBox dropDown1;
	private static final String[] COLORS = {"Black", "Red", "Green", "Blue"};
	
	private JList list1;
	
//    private JLabel description;              // Describes program's functionality
//    private JLabel promptCenterX;            // Prompt for center x-coordinate
//    private JLabel promptCenterY;            // Prompt for center y-coordinate
//    private JLabel promptRadius;             // Prompt for radius
//    private JTextField inCenterX;            // Field to input center x-coordinate
//    private JTextField inCenterY;            // Field to input center y-coordinate
//    private JTextField inRadius;             // Field to input radius
//    private int centerX;                     // Center x-coordinate of circle to draw
//    private int centerY;                     // Center y-coordinate of circle to draw
//    private int radius;                      // Radius of circle to draw


    @Override
    @SuppressWarnings("unchecked") //???
    public void init() {                          // Set up GUI, initialize variables
       setLayout(new FlowLayout());
       label1=new JLabel("Hello World");
       label1.setToolTipText("S");
//       label1.setText("fdf");
//       label1.setIcon(icon);
       add(label1);
       
       textField1=new JTextField(20);
//       passwordField1=new passwordField1(10);
//       textField1.setEditable(false);
       add(textField1);
       
       passwordField1=new JPasswordField(10);
       add(passwordField1);
       
       button1=new JButton("Submit");
       add(button1);
       
       textArea1=new JTextArea("Type your common", 5,10);
       add(textArea1);
       add(new JScrollPane(textArea1));
       
       checkBox1=new JCheckBox("PA");
       checkBox2=new JCheckBox("NY");
       buttonGroup2=new ButtonGroup();
//       buttonGroup2.add(checkBox1);
//       buttonGroup2.add(checkBox2);
//       buttonGroup2.setSelected(m, true)
       add(checkBox1);
       add(checkBox2);
       
       
       
       radioButton1=new JRadioButton("A");
       radioButton2=new JRadioButton("B");
       
      
       buttonGroup1=new ButtonGroup();
       buttonGroup1.add(radioButton1);
       buttonGroup1.add(radioButton2);
       add(radioButton1);
       add(radioButton2);
       
       dropDown1=new JComboBox(COLORS);
       dropDown1.setMaximumRowCount(5);
       add(dropDown1);
       
       list1=new JList(COLORS);
       list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       add(list1);
//       description = new JLabel("This program will draw a circle.");
//       add(description);
//
//       promptCenterX = new JLabel("Enter center x-coordinate: "); // Set up input
//       add(promptCenterX);                                        // for x-coord.
//       inCenterX = new JTextField(5);
//       add(inCenterX);
//
//       promptCenterY = new JLabel("Enter center y-coordinate: "); // Set up input
//       add(promptCenterY);                                        // for y-coord.
//       inCenterY = new JTextField(5);
//       add(inCenterY);
//
//       promptRadius = new JLabel("Enter radius and hit Enter:"); // Set up input
//       add(promptRadius);                                        // for radius
//       inRadius = new JTextField(5);
//       inRadius.addActionListener(this);                         // Process upon enter
//       add(inRadius);
   }

    @Override
    public void paint(Graphics g) {                      // Display results
//       int startX;                                       // Upper left x of bounding rect.
//       int startY;                                       // Upper left y of bounding rect.
//	   
       super.paint(g);
//
//       startX = centerX - radius;                        // Move by radius to get top corner
//       startY = centerY - radius;
//
//       g.drawOval(startX, startY, radius*2, radius*2);   // Draw circle
//    }

//    public void actionPerformed(ActionEvent e) {          // Process user actions
//        centerX = Integer.parseInt(inCenterX.getText()); // Store user inputs
//        centerY = Integer.parseInt(inCenterY.getText());
//        radius  = Integer.parseInt(inRadius.getText());
//
//        repaint();                                       // Redraw circle

    }
}
