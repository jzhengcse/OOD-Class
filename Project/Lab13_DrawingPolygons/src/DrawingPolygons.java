import java.applet.Applet;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;


public class DrawingPolygons extends Applet implements MouseListener,ActionListener{

	private JButton colorButton;  //color picker
	private JButton clearButton;  //reset button
	private Color polygonColor;   //color of polygon
	private Boolean allowAddPoint;  //use to test whether user is done drawing
	private Vector<Integer> X; //temp container for Xarray
	private Vector<Integer> Y; //temp container for Yarray
	private int [] Xarray; //X coordinate for polygon
	private int [] Yarray; //Y coordinate for polygon

	

	
	public void init() {
		polygonColor=Color.BLACK;   //default color	
		allowAddPoint=true;   //set default to true
		setLayout(new FlowLayout(FlowLayout.LEFT));  //set layout
		
		colorButton=new JButton("Color Picker"); //set label of colorButton
		colorButton.addActionListener(this); //add action listener
		add(colorButton); //add to program
		
		clearButton=new JButton("Reset"); //set label of clearButton
		clearButton.addActionListener(this); //add action listener
		add(clearButton); //add to program
		

		X=new Vector<Integer>(); //allocate X vector
		Y=new Vector<Integer>(); //allocate Y vector
		addMouseListener(this); //add Mouse Listener to the program
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);  //call super paint class
		
		g.setColor(Color.BLACK); //color for outline to drawing area
		g.drawRect(5, 45, (int)(this.getSize().getWidth()-10), (int)(this.getSize().getHeight()-45-5)); //create the drawing area
		g.setColor(polygonColor); //set the color
		//g.fill3DRect(0, 0, 50, 50, true);
                
		if (X.size()>0) {
			if(allowAddPoint) { //if not done draw the polyline
				g.drawPolyline(Xarray, Yarray, X.size());
			} else { //if done fill the polygon
				g.fillPolygon(Xarray,Yarray,X.size());
			}
		}

		
		
	}
	
    public void mousePressed(MouseEvent e) {        
    	
    }

    public void mouseClicked(MouseEvent e) {         
    	if(SwingUtilities.isLeftMouseButton(e)){ //it's left mouse click
    		if(allowAddPoint==true && e.getY()>45) { //if not done
    			
    			X.add(e.getX()); //add x coordinate
    			Y.add(e.getY()); //add y coordinate
    			
    			Xarray=new int[X.size()]; //allocate Xarray
    			Yarray=new int[Y.size()]; //allocate Yarray
    			
    			for (int i=0; i<X.size();i++) { //copy from vector array
    				Xarray[i]=(int)X.get(i);
    				Yarray[i]=(int)Y.get(i);
    			}
    			repaint(); //draw the polygon in paint()
    		}

    		
    	} 
    	if (SwingUtilities.isRightMouseButton(e)) { //it's right mouse click
    		if (allowAddPoint==true) { // if not done
    			allowAddPoint=false; // set to done
    			repaint(); //fill the polygon in paint()
    		}

    	}
    }

    public void mouseReleased(MouseEvent e) {      

    }

    public void mouseEntered(MouseEvent e) {       

    }

    public void mouseExited(MouseEvent e) {        

   }
    
    public void actionPerformed(ActionEvent e) {                
        if(e.getSource() == colorButton) { 
            // allow user to choose a color
        	polygonColor = JColorChooser.showDialog(this, "Choose a color", polygonColor); 
        }
        if(e.getSource() == clearButton) {
           //set the allowAddpoint to true and reallocate memory for myPolygon
        	allowAddPoint=true;
    		X=new Vector<Integer>(); //reallocate X vector
    		Y=new Vector<Integer>(); //reallocate Y vector
        }

        repaint();                                       
    }
    
    
    

}
