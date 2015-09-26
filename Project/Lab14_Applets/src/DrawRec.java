import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class DrawRec extends JApplet implements MouseListener,ItemListener  {
	private JRadioButton RedButton;
	private JRadioButton GreenButton;
	private JRadioButton BlueButton;
	
	private int x;
	private int y;
	
	private Color RecColor=Color.RED;

	
	public void init() {
		
		setLayout(new FlowLayout());
		RedButton=new JRadioButton("Red",true);
		GreenButton=new JRadioButton("Green",false);
		BlueButton=new JRadioButton("Blue",false);
		
		RedButton.addItemListener(this);
		GreenButton.addItemListener(this);
		BlueButton.addItemListener(this);
		
		ButtonGroup buttongroup1= new ButtonGroup();
		buttongroup1.add(RedButton);
		buttongroup1.add(GreenButton);
		buttongroup1.add(BlueButton);
		
		add(RedButton);
		add(GreenButton);
		add(BlueButton);
		
		addMouseListener(this);

	}
	
    @Override
    public void paint(Graphics g) {
       super.paint(g);
       g.setColor(RecColor);
       if(y>=50) {
    	   g.fillRect(x, y, 20, 20); 
       }
    }
	
    
    public void mousePressed(MouseEvent e) {        // save coordinates of presses
        x = e.getX();
        y = e.getY();
       
        repaint();


    }

    public void mouseClicked(MouseEvent e) {         // save coordinates of clicks
//        x = e.getX();
//        y = e.getY();
//        repaint();
    }

    public void mouseReleased(MouseEvent e) {        // save coordinates of releases

    }

    public void mouseEntered(MouseEvent e) {        // save coordinates when mouse enters applet

    }

    public void mouseExited(MouseEvent e) {        // save coordinates when mouse leaves applet

   }
    
    public void itemStateChanged(ItemEvent e) {             // handle radio button changes
        if(e.getSource() == RedButton && RedButton.isSelected()) {  // black chosen
        	RecColor = Color.RED;
        }
        if(e.getSource() == GreenButton && GreenButton.isSelected()) {    // red chosen
        	RecColor = Color.GREEN;
        }
        if(e.getSource() == BlueButton && BlueButton.isSelected()) {  // green chosen
        	RecColor = Color.BLUE;
        }
                                     
    }
    
}
