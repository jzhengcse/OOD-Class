import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;


public class FrontEndBackEnd extends JApplet implements  ItemListener,ActionListener{
    private int upLeftX;                     // top left point x-coordinate of square to draw
    private int upLeftY;                     // top left pint y-coordinate of square to draw
    private final int Length=30;             // square edge length
	private JPanel panel1;
	private JPanel panel2;                       
	private JRadioButton drawSqureRadioButton;     //RationButton for Drawing Square
	private JRadioButton writeMessageRadioButton;  //RatioButton for Drawing Message
	private boolean ifDrawSquare;			  //Test to draw square or message
	private ButtonGroup buttongroup;          //ButtonGroup for RadioButtons
	private Color textColor;	             //Color of the object to be draw
	private JTextField textField;            //Text Field for entering the message to be draw
	private JComboBox dropDownList;           //Drop down list
	private String[] colorArray={"","Pick randomly","top left"}; //Stirng[] for drop down
	private JCheckBox drawInColorCheckButton;                   //Check box to decide color or black
	private JButton drawItButton;                               //draw button that addActionListener

	
	
	public void init() {
		setLayout(new FlowLayout());
		
		
		// ************************************
		//Panel 1
		// ************************************
		panel1=new JPanel();	
		
		// Add DrawSquare RadioButton
		drawSqureRadioButton=new JRadioButton("Draw Square",false);
		drawSqureRadioButton.addItemListener(this);
		panel1.add(drawSqureRadioButton);
		
		
		// Add WriteMessage RadioButton
		writeMessageRadioButton=new JRadioButton("Write message: ",true);
		writeMessageRadioButton.addItemListener(this);
		panel1.add(writeMessageRadioButton);
		
		// Add  DrawSquare and WriteMessage RadioButton to ButtonGroup
		buttongroup=new ButtonGroup();
		buttongroup.add(drawSqureRadioButton);
		buttongroup.add(writeMessageRadioButton);
		
		// Add TextArea
		textField=new JTextField(20);
		panel1.add(textField);

		
		// ************************************
		//Panel 2
		// ************************************
		panel2=new JPanel();
		
		// Add a JLabel
		panel2.add(new JLabel("Select where to draw: "));

		// Add a dropdown list
		dropDownList=new JComboBox(colorArray);
		dropDownList.setMaximumRowCount(3);
		dropDownList.addItemListener(this);
		panel2.add(dropDownList);
		
		// Add a DrawInColor JCheckBox
		drawInColorCheckButton=new JCheckBox("Draw in color");
		drawInColorCheckButton.addItemListener(this);
		panel2.add(drawInColorCheckButton);
		
		// Add DrawIt JButton
		drawItButton=new JButton("Draw It!");
		drawItButton.addActionListener(this);
		panel2.add(drawItButton);

		// Add Pane1 and Pane2 to JFrame
		add(panel1);
		add(panel2);
	}
	public void paint(Graphics g) {
	       super.paint(g);  //call super method
	       g.setColor(textColor);  //set color 
	       if(ifDrawSquare) {  //draw square
	    	   g.drawRect(upLeftX, upLeftY, Length, Length);
	       } else { //draw message
	    	   g.drawString(textField.getText(), upLeftX,upLeftY);
	       }
	}
	
	@Override
	public void itemStateChanged(ItemEvent ie) {

		if(ie.getSource()==drawSqureRadioButton && drawSqureRadioButton.isSelected()) {
			ifDrawSquare=true; //draw square 
		}
		if(ie.getSource()==writeMessageRadioButton && writeMessageRadioButton.isSelected()) {
			ifDrawSquare=false; //draw message
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		// set parameter and call repaint when drawItButton is selected and dropDownList is not empty string
        if(ae.getSource() == drawItButton && dropDownList.getSelectedItem()!="") {
			 if (dropDownList.getSelectedItem()=="Pick randomly") {
				upLeftX=(int)(getWidth()*1.0/10.0+Math.random()*(getWidth()*8.0/10.0));  // random X location with appropriate padding
				upLeftY=(int)(getHeight()*2.0/10.0+Math.random()*(getHeight()*7.0/10.0)); //random Y location appropriate padding

			} else {
				upLeftX=(int)(getWidth()*1/10.0); //X location with appropriate padding
				upLeftY=(int)(getHeight()*2/10.0); //Y location with appropriate padding
			}
		
			if(drawInColorCheckButton.isSelected()) {
				textColor=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)); //random color
		
			} else {
				textColor=Color.black; //default color
			}
			
			repaint();
        	
        }
        
		
	}
	

}
