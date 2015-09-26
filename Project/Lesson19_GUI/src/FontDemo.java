import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class FontDemo extends JApplet implements ItemListener {
    // GUI Declarations omitted
    private Font font;                                    // current font
    private Color color=Color.black;
    private JRadioButton radioSerif;
    private JRadioButton radioSansSerif;
    private JRadioButton radioMono;

    // init() omitted

    
    @Override
    @SuppressWarnings("unchecked") //???
    public void init() {                          // Set up GUI, initialize variables
       setLayout(new FlowLayout());
//       label1=new JLabel("Hello World");
//       label1.setToolTipText("S");
//       label1.setText("fdf");
//       label1.setIcon(icon);
//       add(label1);
       
       radioSerif=new JRadioButton("Serif");
       radioSansSerif=new JRadioButton("SansSerif");
       radioMono=new JRadioButton("Monospaced");
       
       
       radioSerif.addItemListener(this);  
       radioSansSerif.addItemListener(this);  
       radioMono.addItemListener(this);  
       
       add(radioSerif);
       add(radioSansSerif);
       add(radioMono);
       
       ButtonGroup buttonGroup2=new ButtonGroup();
       buttonGroup2.add(radioSerif);
       buttonGroup2.add(radioSansSerif);
       buttonGroup2.add(radioMono);
       
       
       

       

   }
    
    
    
    @Override
    public void paint(Graphics g) {                       // draw message to screen        
       super.paint(g);
       
       
       int[] x = {50, 50, 200};
       int[] y = {100, 400, 400};
       

       //buttonGroup2.add(radioSerif);
       

       
       //g.fillPolygon(x, y, 3); 
       g.drawPolyline(x, y, 3);
       //color = JColorChooser.showDialog(this, "Choose a color", color); 
//       Polygon triangle;
//       triangle = new Polygon();
//       triangle.addPoint(50, 100);
//       triangle.addPoint(50, 400);
//       triangle.addPoint(200, 400);
//       
//       g.drawPolygon(triangle);
       //font=JFontChooser.getSelectedFont();
       g.setFont(font);                                  // change font as chosen
       g.drawString("Yay fonts!", 10, 50);
    }

    public void itemStateChanged(ItemEvent e) {          // respond to color radio buttons
        if(e.getSource() == radioSerif && e.getStateChange() == ItemEvent.SELECTED) {     // serif chosen
            font = new Font("Serif", Font.PLAIN, 10);
        }
        if(e.getSource() == radioSansSerif && e.getStateChange() == ItemEvent.SELECTED) { // sans serif chosen
            font = new Font("SansSerif", Font.PLAIN, 10);
        }
        if(e.getSource() == radioMono && e.getStateChange() == ItemEvent.SELECTED) {      // mono chosen
            font = new Font("Monospaced", Font.PLAIN, 10);
        }
        
        repaint();                                       
    }
}
