
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TopLevelGUI extends JFrame implements ActionListener, ChangeListener {
    //DataBase

    public Connection dbConnection;
    public Statement dbStatement;
    public ResultSet dbResultSet;
    String sql;
    //Primary data
    private MediaStore mediaStore1;
    // TopLevelGUI
    private JTabbedPane tabs;                // tabbed window
    private JPanel movieTab;
    private JPanel musicTab;
    private JPanel audioBookTab;
//    private JPanel accountPanel;
    private JPanel purchaseHistoryTab; //tab for customer Account only
    private JPanel userTab; //tab for Manager account only
    private JPanel accountPanel; //left account panel
    // Layout
    private GridBagLayout layout;    // layout of applet
    private GridBagConstraints constraints; //constraints for the layout
    // GUI in the logout status
//    private JTextField userName;
    private JPasswordField passWord;
    private JButton login;
    private JButton logout;
    // GUI for Customer Account
    private JButton depositeButton;
    private JButton addCustomerButton;
    private JTextField userID;
    private JTextField userName;
    private JTextField userAddress;
    private JTextField userCredit;
    private JButton addMediaButton;
    private JTextField mediaName;
    private JTextField mediaAuthor;
    private JTextField mediaTime;
    private JTextField mediaGenre;
    private JTextField mediaPrice;
    private JTextField mediaYearOfRelease;
    private JButton removeMediaButton;
    private JButton purchaseMediaButton;
    private JComboBox accountTypeJComboBox;
    // feedback GUI
    private JButton giveFeedbackButton;
    // used to initial the List
    // used to display the info in the top of the account tab
    private JLabel userInfoLabel;
    private JLabel managerInfoLabel;
    // get the uerIndex when login
    private int userIndex;
    private static final ArrayList<String> MovieCols = new ArrayList<>(Arrays.asList("Number", "Name", "Director", "Time", "Genre", "Ranking", "Price", "Num Sold", "Rating", "Year"));
    private static final ArrayList<String> MusicCols = new ArrayList<>(Arrays.asList("Number", "Name", "Artist", "Time", "Genre", "Ranking", "Price", "Num Sold", "Rating"));
    private static final ArrayList<String> AudioBookCols = new ArrayList<>(Arrays.asList("Number", "Name", "Author", "Time", "Genre", "Ranking", "Price", "Num Sold", "Rating"));
    private static final ArrayList<String> PurchaseHistoryCols = new ArrayList<>(Arrays.asList("Number", "Type", "Name", "Author", "Time", "Genre", "Ranking", "Price", "Num Sold", "Rating", "Year"));
    private static final ArrayList<String> UserCols = new ArrayList<>(Arrays.asList("Number", "UserID", "Name", "Credit", "Purchase#", "Address"));
    private static final Object[] feedbackChoices = {"1", "2", "3", "4", "5"};
    private String[] currentRow;
    private JTable movieTable;
    private JTable musicTable;
    private JTable audioBookTable;
    private JTable purchaseHistoryTable;
    private JTable userTable;
    private JPanel addCustomerPanel;
    private JPanel addMediaPanel;
    private int selectedRow;
    private JButton checkPurchaseHistoryButton;
    private JPanel checkPurchaseHistoryPanel;
    private JScrollPane purchaseHistoryScrollPane;
    private FontMetrics myFontMetrics;
    private Font myFont;
    DatabaseMetaData dbMeta;

//    public void setUpDB() {
//        try {
//            // start Derby engine
//            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
//            Class.forName(driver).newInstance();
//            String protocol = "jdbc:derby:";
//            String dbName = "MediaStoreDB";
//            dbConnection = DriverManager.getConnection("jdbc:derby:MediaStoreDB; create=true");
//            dbStatement = dbConnection.createStatement();
//
//            // test to see if we need to run the initial squl script
//            dbMeta = dbConnection.getMetaData();
//            String[] tableTypes = {"TABLE"};
//            dbResultSet = dbMeta.getTables(null, null, "%", tableTypes);
//            if (!dbResultSet.next()) {
//                // no table found, we need to execut the initial sql statments
//                // first read the sql script
//                InputStream is = TopLevelGUI.class.getResourceAsStream("init.sql");
//                InputStreamReader isr = new InputStreamReader(is);
//                BufferedReader br = new BufferedReader(isr);
//                StringBuilder sb = new StringBuilder();
//                String line;
//                line = br.readLine();
//                while (line != null) {
//                    sb.append(line);
//                    if (line.contains(";")) {
//                        sql = sb.toString();
//                        sb = new StringBuilder();
//                        // executeQuery cannot process ; so remove it from sql
//                        sql = sql.substring(0, sql.indexOf(';'));
//                        // run sql here
//                        if (sql.toUpperCase().contains("SELECT")) {
//                            dbStatement.executeQuery(sql);
//                        } else {
//                            dbStatement.execute(sql);
//                        }
//                    }
//                    line = br.readLine();
//                }
//            }
//
//
//            // test
//            sql = "select * from users";
//            dbResultSet = dbStatement.executeQuery(sql);
//            while (dbResultSet.next()) {
//                System.out.println(dbResultSet.getString("user_id"));
//            }
//            sql = "select * from medias";
//            dbResultSet = dbStatement.executeQuery(sql);
//            while (dbResultSet.next()) {
//                System.out.println(dbResultSet.getString("name"));
//            }
//
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            // could not start Derby engine
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            // could not connect to Derby
//            e.printStackTrace();
//        }
//
//    }

    public TopLevelGUI() {

        super("Media Store");

//        setUpDB();



        mediaStore1 = new MediaStore("jiz5118", "Jie", "College Ave, State College", "123"); //ID, userName , userAddress , password
        tabs = new JTabbedPane();

//        this.setResizable(false);

//        accountPanel = new JPanel(); // set up first tab
//        tabs.addTab("Account", null, accountPanel, "The Account Tab");


        movieTab = new JPanel();
        tabs.addTab("Movie", null, movieTab, "The Movie tab");

        musicTab = new JPanel();
        tabs.addTab("Music", null, musicTab, "The Music tab");

        audioBookTab = new JPanel();
        tabs.addTab("AudioBook", null, audioBookTab, "The AudioBook Tab");

        add(tabs, BorderLayout.CENTER);    // put tab system in application

        tabs.addChangeListener(this);
        accountPanel = new JPanel();
//        add(accountPanel,BorderLayout.WEST);
        accountPanel.setPreferredSize(new Dimension(300, 800));
        add(accountPanel, BorderLayout.WEST);

        myFont = getFont();
//        myFontMetrics=getFontMetrics(myFont);
        logout();


//        purchaseMediaButton = new JButton("Purchase"); //for the state change
//
//        giveFeedbackButton = new JButton("FeedBack");


    }

    public void logout() {

        accountPanel.removeAll();
        accountPanel.validate();
//        accountPanel.validate();
//        movieTab.removeAll();
//        musicTab.removeAll();
//        audioBookTab.removeAll();
//        accountPanel.validate();

//        movieTab.validate();
//        musicTab.validate();
//        audioBookTab.validate();


        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        accountPanel.setLayout(layout); // set up layout

        constraints.fill = GridBagConstraints.BOTH; // define constraints for button
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(40, 40, 0, 40);




        // panel 1
        JPanel accountLoginPanel = new JPanel();
        accountLoginPanel.setLayout(new GridLayout(4, 1, 0, 8));

        JPanel userNamePanel = new JPanel();
        userNamePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        userNamePanel.add(new JLabel("UserID:"));
        userID = new JTextField(10);
        userNamePanel.add(userID);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        passwordPanel.add(new JLabel("PassWord:"));
        passWord = new JPasswordField(10);
        passwordPanel.add(passWord);


//        panel 2
        JPanel accountTypePanel = new JPanel();
        accountTypePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        accountTypeJComboBox = new JComboBox(new String[]{"Customer", "Manager"});
        accountTypeJComboBox.setMaximumRowCount(2);

        accountTypeJComboBox.setPreferredSize(new Dimension(passWord.getPreferredSize().width, passWord.getPreferredSize().height));
        accountTypePanel.add(new JLabel("        Type:"));
        accountTypePanel.add(accountTypeJComboBox);

//        userAccount = new JRadioButton("User Account", true);
//        ManagerAccount = new JRadioButton("Manager Account", false);
//        ButtonGroup myButtonGroup = new ButtonGroup();
//        myButtonGroup.add(userAccount);
//        myButtonGroup.add(ManagerAccount);
//        accountTypePanel.add(userAccount);
//        accountTypePanel.add(ManagerAccount);


        login = new JButton("Login");
        login.addActionListener(this);
//        login.setPreferredSize(null);


        accountLoginPanel.add(userNamePanel);
        accountLoginPanel.add(passwordPanel);
        accountLoginPanel.add(accountTypePanel);
//        accountLoginPanel.add(ManagerAccount);
        accountLoginPanel.add(login);


        accountPanel.add(accountLoginPanel, constraints);




//        constraints.gridy = 1;
//        accountPanel.add(accountTypePanel, constraints);

        // add some empty panel to push the first two panels up 
        for (int i = 2; i < 8; i++) {
            constraints.gridy = i;
            accountPanel.add(new JPanel(), constraints);
        }



        if (tabs.getTabCount() == 4) {
            tabs.removeTabAt(tabs.getTabCount() - 1);
            tabs.validate();
        }

        repaint();

    }

    public void loginUserAccount() {

        accountPanel.removeAll();
        accountPanel.validate();


        constraints.fill = GridBagConstraints.BOTH; // define constraints for button
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(20, 40, 20, 40);


        // add row1
        userInfoLabel = new JLabel("<html><br>User Information: </br>" + mediaStore1.getCustomers().get(userIndex).toString() + "</html>");
        accountPanel.add(userInfoLabel, constraints);

        //add row2
        constraints.insets = new Insets(20, 40, 20, 40);
        constraints.gridy = 1;
        constraints.gridwidth = 1;



        //add row3
        constraints.gridy = 1;
        constraints.gridwidth = 1;

        depositeButton = new JButton("Deposit");
        depositeButton.addActionListener(this);
        accountPanel.add(depositeButton, constraints);


        constraints.gridy = 2;
//        purchaseMediaButton = new JButton("购买");
        purchaseMediaButton = new JButton("Purchase");
        purchaseMediaButton.addActionListener(this);
        accountPanel.add(purchaseMediaButton, constraints);

        //add row4
        constraints.gridy = 3;
        giveFeedbackButton = new JButton("FeedBack");
        giveFeedbackButton.addActionListener(this);
        giveFeedbackButton.setVisible(false);
//        giveFeedbackButton.setEnabled(false);
        accountPanel.add(giveFeedbackButton, constraints);



        //add row5
        constraints.gridy = 4;
        logout = new JButton("Logout");
        logout.addActionListener(this);
        accountPanel.add(logout, constraints);


        // add empty panel to push up the coontent
        for (int i = 5; i < 10; i++) {
            constraints.gridy = i;
            accountPanel.add(new JPanel(), constraints);
        }

        // add addition tab for customer account
        purchaseHistoryTab = new JPanel();
        tabs.addTab("Purchased", null, purchaseHistoryTab);


//        accountPanel.validate();

        // initialize the history tab
        updatePurchaseHistoryTab();
        repaint();
//        System.out.println(depositeButton.getHeight());
//        System.out.println(depositeButton.getWidth());
    }

    public void loginManagerAccount() {
        accountPanel.removeAll();
        accountPanel.validate();


        constraints.fill = GridBagConstraints.BOTH; // define constraints for button
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(20, 40, 20, 40);

        // add row1
        managerInfoLabel = new JLabel("<html><br>Manager Information: </br>" + mediaStore1.getManager().toString()
                + "<br>Store TotalSale:" + String.format("%.2f", mediaStore1.CheckTotalSale()) + "</br></html>");
        accountPanel.add(managerInfoLabel, constraints);




        // add row2
        constraints.insets = new Insets(20, 40, 20, 40);
        constraints.gridx = 0;
        constraints.gridy = 1;
        removeMediaButton = new JButton("Remove Media");
        removeMediaButton.addActionListener(this);
        accountPanel.add(removeMediaButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        checkPurchaseHistoryButton = new JButton("Purchase History");
        checkPurchaseHistoryButton.setVisible(false);
//        checkPurchaseHistoryButton.setEnabled(false);
        checkPurchaseHistoryButton.addActionListener(this);
        accountPanel.add(checkPurchaseHistoryButton, constraints);


        // add row4

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        addMediaButton = new JButton("Add Media");
        addMediaButton.addActionListener(this);


        accountPanel.add(addMediaButton, constraints);

        // add row5
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.gridy = 4;
        addCustomerButton = new JButton("Add Customer");
        addCustomerButton.addActionListener(this);
        accountPanel.add(addCustomerButton, constraints);




        // add row6
        constraints.gridx = 0;
        constraints.gridy = 5;
        logout = new JButton("Logout");
        logout.addActionListener(this);
        accountPanel.add(logout, constraints);



        // add empty panel to push up the coontent
        for (int i = 6; i < 10; i++) {
            constraints.gridy = i;
            accountPanel.add(new JPanel(), constraints);
        }


        // add addition tab for manager account
        userTab = new JPanel();
        tabs.addTab("User Account", null, userTab);

//        saleSummaryTab=new JPanel();


//        repaint();
        updateUserTab();
        repaint();
//        accountPanel.validate();





    }

    public void setUpPurchaseHistoryTable() {


        MyTableModel purchaseHistoryTableModel = new MyTableModel();
        purchaseHistoryTable = new JTable(purchaseHistoryTableModel);
        for (int i = 0; i < PurchaseHistoryCols.size(); i++) {
            purchaseHistoryTableModel.addColumn(PurchaseHistoryCols.get(i));
        }
        purchaseHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        purchaseHistoryTable.setRowSelectionAllowed(true);
        purchaseHistoryTable.setColumnSelectionAllowed(false);
        purchaseHistoryTable.setAutoCreateRowSorter(true);



        purchaseHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        purchaseHistoryTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        purchaseHistoryTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        purchaseHistoryTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        purchaseHistoryTable.getColumnModel().getColumn(6).setPreferredWidth(40);
        purchaseHistoryTable.getColumnModel().getColumn(8).setPreferredWidth(40);
        purchaseHistoryScrollPane = new JScrollPane(purchaseHistoryTable);
        purchaseHistoryTable.setFillsViewportHeight(true);



        for (int i = 0; i < mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().size(); i++) {



            String number = String.format("%d", i + 1);

            String type;
//            String year;
            if (mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i) instanceof Movie) {
                type = "Movie";
//                year="";
            } else if (mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i) instanceof Music) {
                type = "Music";
//                year="";
            } else {
                type = "AudioBook";
//                year="";
            }



            String name = mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).name;
            String author = mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).author;
            String time = mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).time;
            String genre = mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).genre;
            String averageRanking = String.format("%d", mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).rank);
            String price = String.format("%.2f", mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).price);
            String numberSold = String.format("%d", mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).numberSold);
            String rating = String.format("%.2f", mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).rate);
            String year = String.format("%d", mediaStore1.getCustomers().get(userIndex).getPurchaseHistory().get(i).getYear());
            String[] currentRow = {number, type, name, author, time, genre, averageRanking, price, numberSold, rating, year};
            purchaseHistoryTableModel.addRow(currentRow);

        }

        repaint();

    }

    public void updatePurchaseHistoryTab() {
        purchaseHistoryTab.removeAll();
//        purchaseHistoryTab.validate();

        purchaseHistoryTab.setLayout(new BorderLayout());
        setUpPurchaseHistoryTable();
        purchaseHistoryTab.add(purchaseHistoryScrollPane, BorderLayout.CENTER);
        purchaseHistoryTab.validate();
    }

    public void updateUserTab() {
        userTab.removeAll();
        userTab.validate();

        // set layout
        userTab.setLayout(new BorderLayout());



        MyTableModel userTableModel = new MyTableModel();
        userTable = new JTable(userTableModel);
        for (int i = 0; i < UserCols.size(); i++) {
            userTableModel.addColumn(UserCols.get(i));
        }

        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setRowSelectionAllowed(true);
        userTable.setColumnSelectionAllowed(false);
        userTable.setAutoCreateRowSorter(true);



        userTable.getColumnModel().getColumn(5).setPreferredWidth(300);
        JScrollPane userScrollPane = new JScrollPane(userTable);
        userTable.setFillsViewportHeight(true);
        userTab.add(userScrollPane, BorderLayout.CENTER);




        for (int i = 0; i < mediaStore1.getCustomers().size(); i++) {
            String number = String.format("%d", i + 1);
            String ID = mediaStore1.getCustomers().get(i).ID;
            String name = mediaStore1.getCustomers().get(i).name;
            String credit = String.format("%.2f", mediaStore1.getCustomers().get(i).credit);
            String numberPurchase = String.format("%d", mediaStore1.getCustomers().get(i).getPurchaseHistory().size());
            String address = mediaStore1.getCustomers().get(i).address;
            String[] currentRow = {number, ID, name, credit, numberPurchase, address};
            userTableModel.addRow(currentRow);


        }


        repaint();


    }

    public boolean isNoneNegetiveDouble(String string) {
        try {
            if (Double.parseDouble(string) >= 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "can't accept negative number", null, JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Number Format Error", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public void updateLibrary() {

        //resort the library
        mediaStore1.updateAverageRanking();

        movieTab.removeAll();
        musicTab.removeAll();
        audioBookTab.removeAll();

        movieTab.validate();
        musicTab.validate();
        audioBookTab.validate();



        movieTab.setLayout(new BorderLayout());

        MyTableModel movieTableModel = new MyTableModel();
        movieTable = new JTable(movieTableModel);

        //add cols
        for (int i = 0; i < MovieCols.size(); i++) {
            movieTableModel.addColumn(MovieCols.get(i));
        }


        movieTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movieTable.setRowSelectionAllowed(true);
        movieTable.setColumnSelectionAllowed(false);
        movieTable.setAutoCreateRowSorter(true);

        movieTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        movieTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        movieTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        movieTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        movieTable.getColumnModel().getColumn(5).setPreferredWidth(40);
        movieTable.getColumnModel().getColumn(7).setPreferredWidth(40);
        JScrollPane movieScrollPane = new JScrollPane(movieTable);
        movieTable.setFillsViewportHeight(true);
        movieTab.add(movieScrollPane, BorderLayout.CENTER);


        musicTab.setLayout(new BorderLayout());


        MyTableModel musicTableModel = new MyTableModel();
        musicTable = new JTable(musicTableModel);

        for (int i = 0; i < MusicCols.size(); i++) {
            musicTableModel.addColumn(MusicCols.get(i));
        }



        musicTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        musicTable.setRowSelectionAllowed(true);
        musicTable.setColumnSelectionAllowed(false);
        musicTable.setAutoCreateRowSorter(true);

        musicTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        musicTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        musicTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        musicTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        musicTable.getColumnModel().getColumn(5).setPreferredWidth(40);
        musicTable.getColumnModel().getColumn(7).setPreferredWidth(40);
        JScrollPane albumScrollPane = new JScrollPane(musicTable);
        musicTable.setFillsViewportHeight(true);
        musicTab.add(albumScrollPane, BorderLayout.CENTER);


        audioBookTab.setLayout(new BorderLayout());


        MyTableModel audioBookTableModel = new MyTableModel();
        audioBookTable = new JTable(audioBookTableModel);
        for (int i = 0; i < AudioBookCols.size(); i++) {
            audioBookTableModel.addColumn(AudioBookCols.get(i));
        }

        audioBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        audioBookTable.setRowSelectionAllowed(true);
        audioBookTable.setColumnSelectionAllowed(false);
        audioBookTable.setAutoCreateRowSorter(true);

        audioBookTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        audioBookTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        audioBookTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        audioBookTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        audioBookTable.getColumnModel().getColumn(5).setPreferredWidth(40);
        audioBookTable.getColumnModel().getColumn(7).setPreferredWidth(40);
        JScrollPane audioBookScrollPane = new JScrollPane(audioBookTable);
        audioBookTable.setFillsViewportHeight(true);
        audioBookTab.add(audioBookScrollPane, BorderLayout.CENTER);






        int movieIndex = 0;
        int musicIndex = 0;
        int audioBookIndex = 0;
        for (int i = 0; i < mediaStore1.getMedias().size(); i++) {
            if (mediaStore1.getMedias().get(i).avaliable == true) {
                if (mediaStore1.getMedias().get(i) instanceof Movie) {
//                    currentRow = new Vector<String>();
                    String number = String.format("%d", movieIndex + 1);
                    String name = mediaStore1.getMedias().get(i).name;
                    String author = mediaStore1.getMedias().get(i).author;
                    String time = mediaStore1.getMedias().get(i).time;
                    String genre = mediaStore1.getMedias().get(i).genre;
                    String averageRanking = String.format("%d", mediaStore1.getMedias().get(i).rank);
                    String price = String.format("%.2f", mediaStore1.getMedias().get(i).price);
                    String numberSold = String.format("%d", mediaStore1.getMedias().get(i).numberSold);
                    String rating = String.format("%.2f", mediaStore1.getMedias().get(i).rate);
                    String year = String.format("%d", mediaStore1.getMedias().get(i).getYear());
                    String[] currentRow = {number, name, author, time, genre, averageRanking, price, numberSold, rating, year};
                    movieTableModel.addRow(currentRow);
                    movieIndex++;


                } else if (mediaStore1.getMedias().get(i) instanceof Music) {


                    String number = String.format("%d", musicIndex + 1);
                    String name = mediaStore1.getMedias().get(i).name;
                    String author = mediaStore1.getMedias().get(i).author;
                    String time = mediaStore1.getMedias().get(i).time;
                    String genre = mediaStore1.getMedias().get(i).genre;
                    String averageRanking = String.format("%d", mediaStore1.getMedias().get(i).rank);
                    String price = String.format("%.2f", mediaStore1.getMedias().get(i).price);
                    String numberSold = String.format("%d", mediaStore1.getMedias().get(i).numberSold);
                    String rating = String.format("%.2f", mediaStore1.getMedias().get(i).rate);
//                    String year=String.format("%d", ((Movie) (mediaStore1.getMedias().get(i))).getYear());
                    String[] currentRow = {number, name, author, time, genre, averageRanking, price, numberSold, rating};
                    musicTableModel.addRow(currentRow);
                    musicIndex++;



                } else {

                    String number = String.format("%d", audioBookIndex + 1);
                    String name = mediaStore1.getMedias().get(i).name;
                    String author = mediaStore1.getMedias().get(i).author;
                    String time = mediaStore1.getMedias().get(i).time;
                    String genre = mediaStore1.getMedias().get(i).genre;
                    String averageRanking = String.format("%d", mediaStore1.getMedias().get(i).rank);
                    String price = String.format("%.2f", mediaStore1.getMedias().get(i).price);
                    String numberSold = String.format("%d", mediaStore1.getMedias().get(i).numberSold);
                    String rating = String.format("%.2f", mediaStore1.getMedias().get(i).rate);
//                    String year=String.format("%d", ((Movie) (mediaStore1.getMedias().get(i))).getYear());
                    String[] currentRow = {number, name, author, time, genre, averageRanking, price, numberSold, rating};
                    audioBookTableModel.addRow(currentRow);
                    audioBookIndex++;


                }
            }

        }


        repaint();

    }

    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e) {  // handle button


        if (e.getSource() == logout) {
            logout();
        }



        if (e.getSource() == login) {
            if (accountTypeJComboBox.getSelectedIndex() == 0) {
                userIndex = mediaStore1.foundCustomer(userID.getText());
                if (userIndex != -1) { // find it and login
                    loginUserAccount();
//                    updateLibrary();

                } else {
                    JOptionPane.showMessageDialog(null, "Can't find UserID \"" + userID.getText() + "\" in the database", null, JOptionPane.WARNING_MESSAGE);
                }

            } else { // manager account
                if (mediaStore1.getManager().ID.equals(userID.getText()) && Arrays.equals(mediaStore1.getManager().password.toCharArray(), passWord.getPassword())) {
                    loginManagerAccount();
//                    updateLibrary();

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Manager userID and password combination", null, JOptionPane.WARNING_MESSAGE);
                }

            }
        }



        if (e.getSource() == addCustomerButton) {
            addCustomerPanel = new JPanel();
            addCustomerPanel.setLayout(new GridLayout(4, 1));
            addCustomerPanel.setPreferredSize(new Dimension(80, 200));

            JPanel userIDPanel = new JPanel();
            userIDPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            userIDPanel.add(new JLabel("UserID:"));
            userID = new JTextField(10);
            userIDPanel.add(userID);
            addCustomerPanel.add(userIDPanel);


            JPanel namePanel = new JPanel();
            namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            namePanel.add(new JLabel("Name:"));
            userName = new JTextField(10);
            namePanel.add(userName);
            addCustomerPanel.add(namePanel);


            JPanel addressPanel = new JPanel();
            addressPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            addressPanel.add(new JLabel("Address:"));
            userAddress = new JTextField(10);
            addressPanel.add(userAddress);
            addCustomerPanel.add(addressPanel);

            JPanel creditPanel = new JPanel();
            creditPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            creditPanel.add(new JLabel("Credit:"));
            userCredit = new JTextField(10);
            creditPanel.add(userCredit);
            addCustomerPanel.add(creditPanel);



            int result = JOptionPane.showConfirmDialog(null, addCustomerPanel, "Add a Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
//                int index = mediaStore1.getManager().foundCustomer(mediaStore1.getCustomers(), userID.getText());
//                if (index == -1) {
                if (userID.getText().isEmpty() || userName.getText().isEmpty() || userAddress.getText().isEmpty() || userCredit.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty inputs is not allow", null, JOptionPane.WARNING_MESSAGE);

                    //                    JOptionPane.showMessageDialog(null, "Account with User ID \"" + userID.getText() + "\" has been added", null, JOptionPane.WARNING_MESSAGE);
                } else {
                    if (isNoneNegetiveDouble(userCredit.getText())) {
                        mediaStore1.addCustomer(new Customer(userID.getText(), userName.getText(), userAddress.getText(), Double.parseDouble(userCredit.getText())));
                        updateUserTab();

                    }

                }
            }





        }



        if (e.getSource() == addMediaButton) {

            addMediaPanel = new JPanel();
            addMediaPanel.setLayout(new GridLayout(7, 1));
            addMediaPanel.setPreferredSize(new Dimension(80, 300));


            JPanel mediaTypePanel = new JPanel();
            mediaTypePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            mediaTypePanel.add(new JLabel("Type:"));
            JComboBox mediaType = new JComboBox(new String[]{"Movie", "Music", "AudioBook"});
            mediaType.setPreferredSize(new Dimension(passWord.getPreferredSize().width, passWord.getPreferredSize().height));
            mediaTypePanel.add(mediaType);
            addMediaPanel.add(mediaTypePanel);



            JPanel mediaNamePanel = new JPanel();
            mediaNamePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            mediaNamePanel.add(new JLabel("Name:"));
            mediaName = new JTextField(10);
            mediaNamePanel.add(mediaName);
            addMediaPanel.add(mediaNamePanel);


            JPanel mediaAuthorPanel = new JPanel();
            mediaAuthorPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            mediaAuthorPanel.add(new JLabel("Author:"));
            mediaAuthor = new JTextField(10);
            mediaAuthorPanel.add(mediaAuthor);
            addMediaPanel.add(mediaAuthorPanel);



            JPanel mediaTimePanel = new JPanel();
            mediaTimePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            mediaTimePanel.add(new JLabel("Time:"));
            mediaTime = new JTextField(10);
            mediaTimePanel.add(mediaTime);
            addMediaPanel.add(mediaTimePanel);



            JPanel mediaGenrePanel = new JPanel();
            mediaGenrePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            mediaGenrePanel.add(new JLabel("Genre:"));
            mediaGenre = new JTextField(10);
            mediaGenrePanel.add(mediaGenre);
            addMediaPanel.add(mediaGenrePanel);



            JPanel mediaPricePanel = new JPanel();
            mediaPricePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            mediaPricePanel.add(new JLabel("Price:"));
            mediaPrice = new JTextField(10);
            mediaPricePanel.add(mediaPrice);
            addMediaPanel.add(mediaPricePanel);



            JPanel mediaYearOfReleasePanel = new JPanel();
            mediaYearOfReleasePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            mediaYearOfReleasePanel.add(new JLabel("Year:"));
            mediaYearOfRelease = new JTextField(10);
            mediaYearOfRelease.setText("Movie Only");
            mediaYearOfReleasePanel.add(mediaYearOfRelease);
            addMediaPanel.add(mediaYearOfReleasePanel);


            int result = JOptionPane.showConfirmDialog(null, addMediaPanel, "Add a media", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);


            if (result == JOptionPane.OK_OPTION) {
//                int index = mediaStore1.getManager().foundMedia(mediaStore1.getMedias(), mediaName.getText(),mediaType.getSelectedItem().toString());
//                if (index == -1) { // did not find it and add it
                if (mediaName.getText().isEmpty() || mediaAuthor.getText().isEmpty() || mediaTime.getText().isEmpty() || mediaGenre.getText().isEmpty() || mediaPrice.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty inputs is not allow", null, JOptionPane.WARNING_MESSAGE);

                } else {


                    if (mediaType.getSelectedIndex() == 0) {
                        if (mediaYearOfRelease.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Empty inputs is not allow", null, JOptionPane.WARNING_MESSAGE);

                        } else {
                            if (isNoneNegetiveDouble(mediaYearOfRelease.getText()) && isNoneNegetiveDouble(mediaPrice.getText())) {
                                mediaStore1.addMedia(new Movie(mediaName.getText(), mediaAuthor.getText(), mediaTime.getText(), mediaGenre.getText(),
                                        Integer.parseInt(mediaYearOfRelease.getText()), Double.parseDouble(mediaPrice.getText())));
                                updateLibrary();
                            }


                        }

                    } else if (mediaType.getSelectedIndex() == 1) {
                        if (isNoneNegetiveDouble(mediaPrice.getText())) {
                            mediaStore1.addMedia(new Music(mediaName.getText(), mediaAuthor.getText(), mediaTime.getText(), mediaGenre.getText(),
                                    Double.parseDouble(mediaPrice.getText())));
                            updateLibrary();
                        }

                    } else {
                        if (isNoneNegetiveDouble(mediaPrice.getText())) {
                            mediaStore1.addMedia(new AudioBook(mediaName.getText(), mediaAuthor.getText(), mediaTime.getText(), mediaGenre.getText(),
                                    Double.parseDouble(mediaPrice.getText())));
                            updateLibrary();
                        }

                    }
                }
            }


        }






        if (e.getSource() == removeMediaButton) {

            if (tabs.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(null, "Please select a media from Movie Music or AudioBook Tab", null, JOptionPane.WARNING_MESSAGE);
            } else if (tabs.getSelectedIndex() == 0) {
                selectedRow = movieTable.getSelectedRow();
                if (movieTable.isRowSelected(selectedRow)) {
                    mediaStore1.removeMedia(movieTable.getValueAt(selectedRow, 1).toString(), "movie");
//                    JOptionPane.showMessageDialog(null, movieTable.getValueAt(selectedRow, 1).toString() + " has been removed", null, JOptionPane.WARNING_MESSAGE);
                    updateLibrary();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media to be removed", null, JOptionPane.WARNING_MESSAGE);
                }

            } else if (tabs.getSelectedIndex() == 1) {
                selectedRow = musicTable.getSelectedRow();
                if (musicTable.isRowSelected(selectedRow)) {
                    mediaStore1.removeMedia(musicTable.getValueAt(selectedRow, 1).toString(), "music");
//                    JOptionPane.showMessageDialog(null, musicTable.getValueAt(selectedRow, 1).toString() + " has been removed", null, JOptionPane.WARNING_MESSAGE);
                    updateLibrary();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media to be removed", null, JOptionPane.WARNING_MESSAGE);
                }
            } else {
                selectedRow = audioBookTable.getSelectedRow();
                if (audioBookTable.isRowSelected(selectedRow)) {
                    mediaStore1.removeMedia(audioBookTable.getValueAt(selectedRow, 1).toString(), "audioBook");
//                    JOptionPane.showMessageDialog(null, audioBookTable.getValueAt(selectedRow, 1).toString() + " has been removed", null, JOptionPane.WARNING_MESSAGE);
                    updateLibrary();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media to be removed", null, JOptionPane.WARNING_MESSAGE);
                }
            }





        }



        if (e.getSource() == checkPurchaseHistoryButton) {
            selectedRow = userTable.getSelectedRow();
            if (userTable.isRowSelected(selectedRow)) {
                userIndex = mediaStore1.foundCustomer(userTable.getValueAt(selectedRow, 1).toString());
                checkPurchaseHistoryPanel = new JPanel();
                checkPurchaseHistoryPanel.setLayout(new BorderLayout());
                checkPurchaseHistoryPanel.setPreferredSize(new Dimension(900, 700));
                setUpPurchaseHistoryTable();
                checkPurchaseHistoryPanel.add(purchaseHistoryScrollPane, BorderLayout.CENTER);
//                JOptionPane.showConfirmDialog
                JOptionPane.showConfirmDialog(userTab, checkPurchaseHistoryPanel, userTable.getValueAt(selectedRow, 1).toString() + ": Purchase History", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
//                    mediaStore1.removeMediaButton(movieTable.getValueAt(selectedRow, 1).toString(), "movie");
//                    JOptionPane.showMessageDialog(null, movieTable.getValueAt(selectedRow, 1).toString() + " has been removed", null, JOptionPane.WARNING_MESSAGE);
//                    updateLibrary();
            } else {
                JOptionPane.showMessageDialog(null, "Please select a user", null, JOptionPane.WARNING_MESSAGE);
            }






        }


        if (e.getSource() == depositeButton) {
            String deposite = (String) JOptionPane.showInputDialog("How much to deposite", "100");
            if (deposite != null) {
                if (deposite.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty inputs is not allow", null, JOptionPane.WARNING_MESSAGE);
                } else {

                    if (isNoneNegetiveDouble(deposite)) {
                        mediaStore1.getCustomers().get(userIndex).deposit(Double.parseDouble(deposite));
                        userInfoLabel.setText("<html><br>User Information: </br>" + mediaStore1.getCustomers().get(userIndex).toString() + "</html>");
                    }


                }


            }

        }

        if (e.getSource() == purchaseMediaButton) {

            if (tabs.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(null, "Please select a media from Movie Music or AudioBook Tab", null, JOptionPane.WARNING_MESSAGE);
            } else if (tabs.getSelectedIndex() == 0) {
                selectedRow = movieTable.getSelectedRow();
                if (movieTable.isRowSelected(selectedRow)) {
                    mediaStore1.getCustomers().get(userIndex).purchase(mediaStore1.getMedias(), movieTable.getValueAt(selectedRow, 1).toString(), "movie");
//                    JOptionPane.showMessageDialog(null, movieTable.getValueAt(selectedRow, 1).toString() + " has been removed", null, JOptionPane.WARNING_MESSAGE);
                    updatePurchaseHistoryTab();
                    updateLibrary();
                    userInfoLabel.setText("<html><br>User Information: </br>" + mediaStore1.getCustomers().get(userIndex).toString() + "</html>");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);
                }

            } else if (tabs.getSelectedIndex() == 1) {
                selectedRow = musicTable.getSelectedRow();
                if (musicTable.isRowSelected(selectedRow)) {
                    mediaStore1.getCustomers().get(userIndex).purchase(mediaStore1.getMedias(), musicTable.getValueAt(selectedRow, 1).toString(), "music");
//                    mediaStore1.getManager().removeMediaButton(mediaStore1.getMedias(), musicTable.getValueAt(selectedRow, 1).toString());
//                    JOptionPane.showMessageDialog(null, musicTable.getValueAt(selectedRow, 1).toString() + " has been removed", null, JOptionPane.WARNING_MESSAGE);
                    updatePurchaseHistoryTab();
                    updateLibrary();
                    userInfoLabel.setText("<html><br>User Information: </br>" + mediaStore1.getCustomers().get(userIndex).toString() + "</html>");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);
                }
            } else {
                selectedRow = audioBookTable.getSelectedRow();
                if (audioBookTable.isRowSelected(selectedRow)) {
                    mediaStore1.getCustomers().get(userIndex).purchase(mediaStore1.getMedias(), audioBookTable.getValueAt(selectedRow, 1).toString(), "audioBook");

//                    mediaStore1.getManager().removeMediaButton(mediaStore1.getMedias(), audioBookTable.getValueAt(selectedRow, 1).toString());
//                    JOptionPane.showMessageDialog(null, audioBookTable.getValueAt(selectedRow, 1).toString() + " has been removed", null, JOptionPane.WARNING_MESSAGE);
                    updatePurchaseHistoryTab();
                    updateLibrary();
                    userInfoLabel.setText("<html><br>User Information: </br>" + mediaStore1.getCustomers().get(userIndex).toString() + "</html>");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);
                }
            }
        }


        if (e.getSource() == giveFeedbackButton) {
            if (tabs.getSelectedIndex() == 3) { // if one item is selected 

//                System.out.println(feedBack);
                selectedRow = purchaseHistoryTable.getSelectedRow();
                if (purchaseHistoryTable.isRowSelected(selectedRow)) {
//                    int index = mediaStore1.foundMedia(purchaseHistoryTable.getValueAt(selectedRow, 2).toString(), purchaseHistoryTable.getValueAt(selectedRow, 1).toString());
//                    System.out.println(index);
//                    if (index != -1)
//                    { // found it in the library
//                        if (mediaStore1.getCustomers().get(userIndex).ifRated(mediaStore1.getMedias().get(index).feedBackUserID)) { //multiplyrate
//                            JOptionPane.showMessageDialog(null, "You already rate it before", null, JOptionPane.WARNING_MESSAGE);//show never happen
//                        } else 
//                        {
                    String feedBack = (String) JOptionPane.showInputDialog(null, "Rate this media", "Feedback", JOptionPane.PLAIN_MESSAGE, null, feedbackChoices, feedbackChoices[4]);
                    if (feedBack != null) {
//                                mediaStore1.getMedias().get(index).feedBack.add(Integer.parseInt(feedBack)); // add feedback to feedBack ArrayList
//                                mediaStore1.getMedias().get(index).feedBackUserID.add(mediaStore1.getCustomers().get(userIndex).ID);
//                                mediaStore1.getMedias().get(index).updateRating(); //update the rate
                        mediaStore1.getCustomers().get(userIndex).giveFeedBack(mediaStore1.getMedias(), purchaseHistoryTable.getValueAt(selectedRow, 2).toString(), purchaseHistoryTable.getValueAt(selectedRow, 1).toString(), Integer.parseInt(feedBack));
                        updatePurchaseHistoryTab();
                        updateLibrary();
                    }
//                        }
//                }
//else {
//                        JOptionPane.showMessageDialog(null, "can't find it in the library", null, JOptionPane.WARNING_MESSAGE);//show never happen
//                    }


                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);

                }
            } else {

                JOptionPane.showMessageDialog(null, "Please select Purchased Tab", null, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == tabs) {
            if (tabs.getTabCount() == 4) { //change only when login
                if (accountTypeJComboBox.getSelectedIndex() == 0) {//user account
                    //                purchaseFeedBackSwitchPanel.removeAll();
                    //                purchaseFeedBackSwitchPanel.validate();
                    //                constraints.gridy=2;
                    if (tabs.getSelectedIndex() <= 2) {
                        purchaseMediaButton.setVisible(true);
                        giveFeedbackButton.setVisible(false);
//                        purchaseMediaButton.setEnabled(true);
//                        giveFeedbackButton.setEnabled(false);
                        //                    purchaseFeedBackSwitchPanel.add(purchaseMediaButton);
                        //                    accountPanel.add(purchaseFeedBackSwitchPanel,constraints);
                    } else {
                        purchaseMediaButton.setVisible(false);
                        giveFeedbackButton.setVisible(true);
//                        purchaseMediaButton.setEnabled(false);
//                        giveFeedbackButton.setEnabled(true);
                    }
                } else { //manager account

                    if (tabs.getSelectedIndex() <= 2) {
                        removeMediaButton.setVisible(true);
                        checkPurchaseHistoryButton.setVisible(false);
//                        removeMediaButton.setEnabled(true);
//                        checkPurchaseHistoryButton.setEnabled(false);
                        //                    purchaseFeedBackSwitchPanel.add(purchaseMediaButton);
                        //                    accountPanel.add(purchaseFeedBackSwitchPanel,constraints);
                    } else {
                        removeMediaButton.setVisible(false);
                        checkPurchaseHistoryButton.setVisible(true);
//                        removeMediaButton.setEnabled(false);
//                        checkPurchaseHistoryButton.setEnabled(true);
                    }



                }
            }


        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        try {
            //star Derby engine
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();



            TopLevelGUI prog = new TopLevelGUI();
            prog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            prog.setSize(1200, 800);
            prog.setVisible(true);

            //mediaStore1

            prog.mediaStore1.addMedia(new Movie("Skyfall0", "Sam Mendes", "2:23:00", "Action & Adverture", 2012, 14.99)); // test for add
//            prog.mediaStore1.getManager().addMediaButton(prog.mediaStore1.getMedias(), new Movie("Skyfall0", "Sam Mendes", "2:23:00", "Action & Adverture", 2012, 14.99)); // test for add
            prog.mediaStore1.addMedia(new Movie("Skyfall1", "Sam Mendes", "2:23:00", "Action & Adverture", 2012, 14.99));
            prog.mediaStore1.addMedia(new Movie("Skyfall2", "Sam Mendes", "2:23:00", "Action & Adverture", 2012, 14.99));


            prog.mediaStore1.addCustomer(new Customer("jiz51180", "Jie Zheng", "Burrowes Street, State College", 200)); // test for addCustomerButton
//            prog.mediaStore1.getManager().addCustomerButton(prog.mediaStore1.getCustomers(), new Customer("jiz51180", "Jie Zheng", "Burrowes Street, State College", 200)); // test for addCustomerButton
            prog.mediaStore1.addCustomer(new Customer("jiz51181", "Jie Zheng", "Burrowes Street, State College", 200)); // test for addCustomerButton
            prog.mediaStore1.addCustomer(new Customer("jiz51182", "Jie Zheng", "Burrowes Street, State College", 200)); // test for addCustomerButton

            prog.mediaStore1.listAllMedias();

            prog.mediaStore1.removeMedia("Skyfall0", "movie"); // test for remove
//            prog.mediaStore1.getManager().removeMediaButton(prog.mediaStore1.getMedias(), "Skyfall0","movie"); // test for remove
            prog.mediaStore1.listAllMedias();

            prog.mediaStore1.addMedia(new Music("Two Lanes of Freedom0", "Tim MCGraw", "43:24", "Country", 13.99));
            prog.mediaStore1.addMedia(new Music("Two Lanes of Freedom1", "Tim MCGraw", "43:24", "Country", 13.99));
            prog.mediaStore1.addMedia(new Music("Two Lanes of Freedom2", "Tim MCGraw", "43:24", "Country", 13.99));
//            prog.mediaStore1.addMedia(new Music("The Lumineers", "Artist unkonwn", "45:34", "Alternative", 9.99));

            prog.mediaStore1.addMedia(new AudioBook("American Sniper0", "Chris Kyle", "10:22", "Biography", 21.95));
            prog.mediaStore1.addMedia(new AudioBook("American Sniper1", "Chris Kyle", "10:22", "Biography", 21.95));
            prog.mediaStore1.addMedia(new AudioBook("American Sniper2", "Chris Kyle", "10:22", "Biography", 21.95));



            prog.mediaStore1.getCustomers().get(0).purchase(prog.mediaStore1.getMedias(), "Skyfall1", "movie"); // test for purchase
//            prog.mediaStore1.getCustomers().get(0).purchase(prog.mediaStore1.getMedias(), "Skyfall1","movie"); // test for purchase
            prog.mediaStore1.getCustomers().get(0).purchase(prog.mediaStore1.getMedias(), "Skyfall2", "movie");
            prog.mediaStore1.getCustomers().get(0).purchase(prog.mediaStore1.getMedias(), "Two Lanes of Freedom0", "music");
            prog.mediaStore1.getCustomers().get(0).purchase(prog.mediaStore1.getMedias(), "Two Lanes of Freedom1", "music");
            prog.mediaStore1.getCustomers().get(0).purchase(prog.mediaStore1.getMedias(), "American Sniper0", "audioBook");
            prog.mediaStore1.getCustomers().get(0).purchase(prog.mediaStore1.getMedias(), "American Sniper2", "audioBook");
            prog.mediaStore1.getCustomers().get(1).purchase(prog.mediaStore1.getMedias(), "Skyfall1", "movie");
            prog.mediaStore1.getCustomers().get(2).purchase(prog.mediaStore1.getMedias(), "Skyfall1", "movie");


            prog.mediaStore1.CheckTotalNumberSold("Skyfall0", "movie"); // test CheckTotalNumberSold
            prog.mediaStore1.CheckTotalNumberSold("Skyfall1", "movie"); // test CheckTotalNumberSold

            prog.mediaStore1.CheckTotalSale(); // test CheckTotalSale

            prog.mediaStore1.RetrieveCustomerInfo("jiz51181"); // test RetrieveCustomerInfo
            prog.mediaStore1.RetrieveCustomerInfo("jiz51180"); // test RetrieveCustomerInfo

            prog.mediaStore1.getCustomers().get(1).deposit(200); // test for depositeButton
            prog.mediaStore1.RetrieveCustomerInfo("jiz51181"); // test for depositeButton

//			prog.mediaStore1.getCustomers().get(2).giveFeedBack(prog.mediaStore1.getMedias(), "Skyfall1"); // test for giveFeedBack
//			prog.mediaStore1.getCustomers().get(2).giveFeedBack(prog.mediaStore1.getMedias(), "Skyfall2"); // test for giveFeedBack
//			prog.mediaStore1.getCustomers().get(1).giveFeedBack(prog.mediaStore1.getMedias(), "Skyfall1"); // test for giveFeedBack


            prog.mediaStore1.listAllMedias(); // test for listAllMedias and updateAverageRanking(called inside listAllMedias method


            prog.updateLibrary();



//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();

        } catch (ArrayIndexOutOfBoundsException aioobe) {
            aioobe.printStackTrace();
            System.out.println("You've entered an invalid array index.");
        } catch (NullPointerException npe) {
            System.out.println("No record exists at one of the entered indices.");
//        } catch (InputMismatchException ime) {
//            System.out.println("You must enter right formate input");
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("An unknown error occurred.");
        }

    }
}
