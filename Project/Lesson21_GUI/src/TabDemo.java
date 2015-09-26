import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TabDemo extends JFrame implements ActionListener {
    private int tabCt;                       // number of tabs
    private JTabbedPane tabs;                // tabbed window
    private JButton addTabButton;            // button to add a tab

    public TabDemo() {                       // set up GUI
       super("Tab Demo");

       JPanel tab0;                         // default tab GUI
       JPanel tab1;                         // second tab GUI

       tabs = new JTabbedPane();

       addTabButton = new JButton("Add a tab");  // button for user to spawn tabs
       addTabButton.addActionListener(this);

       tab0 = new JPanel();               // set up first tab
       tab0.add(new JLabel("Default tab"));
       tab0.add(addTabButton);
       tabs.addTab("Main Tab", null, tab0,
                   "The default tab");           // tool tip

       tab1 = new JPanel();               // set up tab 1
       tab1.add(new JLabel("Tab 1"));
       tabs.addTab("Tab 1", null, tab1,
                   "Provided demo tab");         // tool tip

       tabCt = 2;

       add(tabs);                               // put tab system in application

    }

    public void actionPerformed(ActionEvent e) {  // handle button
       JPanel cur;                                // new tab to add

       if(e.getSource() == addTabButton) {        // user wants new tab
          cur = new JPanel();              // set up new tab
          cur.add(new JLabel("Tab " + tabCt));
          tabs.addTab("Tab " + tabCt, null, cur,
                      "A tab you made" );         // tool tip

          tabCt++;                                // keep track of tabs
       }
    }

    public static void main(String[] args) {
       TabDemo prog = new TabDemo();
       prog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       prog.setSize(500, 400);
       prog.setVisible(true);
    }
}
