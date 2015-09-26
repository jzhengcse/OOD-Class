
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jiz5118
 */
public class LabelFrame extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public LabelFrame() {
        super("Testing JLabel");
        setLayout(new FlowLayout());

        label1 = new JLabel("Label with text");

        label1.setToolTipText("This is label1");

        add(label1);

        Icon bug = new ImageIcon("bug.png");
        label2 = new JLabel("Label with text and icon", bug, SwingConstants.LEFT);
        label2.setToolTipText("This is label2");
       //label2.setVerticalTextPosition(SwingConstants.BOTTOM);
        //label2.setHorizontalTextPosition(PROPERTIES);

        add(label2);

        label3 = new JLabel();
        label3.setText("Label with icon and text at bottom");
        label3.setIcon(bug);
        label3.setHorizontalTextPosition(SwingConstants.CENTER);
        label3.setVerticalTextPosition(SwingConstants.BOTTOM);
        label3.setToolTipText("This is label3");
        add(label3);
        
        Icon bug2=new ImageIcon("1.png");
        JButton helloButton=new JButton("hello",bug);
        helloButton.setRolloverIcon(bug2);
        add(helloButton);
    }
    
    public static void main(String[] args) {
        LabelFrame labelFrame=new LabelFrame();
        labelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labelFrame.setSize(500,400);
        labelFrame.setVisible(true);
    }
    
}
