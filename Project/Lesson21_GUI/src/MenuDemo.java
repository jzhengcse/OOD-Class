import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class MenuDemo extends JFrame implements ActionListener {
    private JMenuBar menuBar;                 // program's main menu bar

    private JMenu fileMenu;                   // file menu
    private JMenu formatMenu;                 // format menu
    private JMenu helpMenu;                   // help menu

    private JMenuItem file_exit;              // file menu, exit item
    private JMenuItem format_font;            // format menu, font item
    private JMenuItem format_color;           // format menu, color item
    private JMenuItem help_about;             // help menu, about item

    public MenuDemo() {                       // set up GUI
       super("Menu Demo");

       menuBar = new JMenuBar();              // construct menu bar

       fileMenu = new JMenu("File");          // define file menu
       fileMenu.setMnemonic('F');             // shortcut

       file_exit = new JMenuItem("Exit");     // define file menu options
       file_exit.setMnemonic('E');
       file_exit.addActionListener(this);
       fileMenu.add(file_exit);

       menuBar.add(fileMenu);                 // add file menu to menu bar

       formatMenu = new JMenu("Format");      // define format menu
       formatMenu.setMnemonic('m');           // shortcut

       format_font = new JMenuItem("Font");   // define format menu options
       format_font.setMnemonic('F');
       format_font.addActionListener(this);
       formatMenu.add(format_font);
       format_color = new JMenuItem("Color"); // define format menu options
       format_color.setMnemonic('C');
       format_color.addActionListener(this);
       formatMenu.add(format_color);

       menuBar.add(formatMenu);               // add format menu to menu bar

       helpMenu = new JMenu("Help");          // define help menu
       helpMenu.setMnemonic('H');             // shortcut

       help_about = new JMenuItem("About");   // define help menu options
       help_about.setMnemonic('A');
       help_about.addActionListener(this);
       helpMenu.add(help_about);

       menuBar.add(helpMenu);                  // add help menu to menu bar

       setJMenuBar(menuBar);                   // put menu bar on application
    }

    public void actionPerformed(ActionEvent e) {  // process menu items
       Color color;                               // color chosen

       if (e.getSource() == file_exit) {           // process File > Exit
           System.exit(0);
       }

       if (e.getSource() == format_font) {    // process Format > Font
           // Implement a font choice here
           JOptionPane.showMessageDialog(null,
                       "Font choice goes here");
       }

       if (e.getSource() == format_color) {    // process Format > Color
           // Implement a color choice here
           // Would ordinarily do something with color classwide
           color = Color.GREEN;
           color = JColorChooser.showDialog(this,
                         "Choose a color", color);
       }

       if (e.getSource() == help_about) {      // process Help > About
           JOptionPane.showMessageDialog(null,
              "This program just shows menu setup.");
       }
    }

    public static void main(String[] args) {
       MenuDemo prog = new MenuDemo();
       prog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       prog.setSize(500, 400);
       prog.setVisible(true);
    }
}