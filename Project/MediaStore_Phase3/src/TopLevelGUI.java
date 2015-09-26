
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TopLevelGUI extends JFrame implements ActionListener, ChangeListener {
    //DataBase

    private String currentUserID;
    private int maxMediaID;
    private Connection dbConnection;
    private Statement dbStatement;
    private ResultSet dbResultSet;
    // reserve for current user
    private ResultSet currentUserResultSet;
    private Statement currentUserStatement;
    private String sql;
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
    private JButton giveRateButton;
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
    private static final Object[] rateChoices = {"1", "2", "3", "4", "5"};
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
    private String purchase_media_id;
    private JPanel searchTab;
    private JPanel searchTabSearchFieldPanel;
    private JPanel searchTabTablePanel;
    private JTextField searchName;
    private JComboBox filterTypeJComboBox;
    private JButton searchButton;
    private JTable searchTable;
    private JComboBox mediaTypeJComboBox;
    private JPanel mediaYearOfReleasePanel;

    public void updateSearchTablePanel() {
        
        searchTabTablePanel.removeAll();
        searchTabTablePanel.validate();

        searchTabTablePanel.setLayout(new BorderLayout());

        MyTableModel searchTableModel = new MyTableModel();
        searchTable = new JTable(searchTableModel);
        for (int i = 0; i < PurchaseHistoryCols.size(); i++) {
            searchTableModel.addColumn(PurchaseHistoryCols.get(i));
        }
        searchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchTable.setRowSelectionAllowed(true);
        searchTable.setColumnSelectionAllowed(false);
        searchTable.setAutoCreateRowSorter(true);



        searchTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        searchTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        searchTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        searchTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        searchTable.getColumnModel().getColumn(6).setPreferredWidth(40);
        searchTable.getColumnModel().getColumn(8).setPreferredWidth(40);
        JScrollPane searchScrollPane = new JScrollPane(searchTable);
        searchTable.setFillsViewportHeight(true);

        searchTabTablePanel.add(searchScrollPane, BorderLayout.CENTER);
        searchTab.add(searchTabTablePanel, BorderLayout.CENTER);


        int i;
        try {
            i = 0;

            String searchString;
                searchString = "%" + searchName.getText() + "%";

            switch (filterTypeJComboBox.getSelectedIndex()) {

                case 0:

                    sql = String.format("select * from medias where upper(name) like upper('%s') and available=1 order by type,name", searchString);
                    break;
                case 1:
                    sql = String.format("select * from medias where upper(name) like upper('%s')  and type='movie' and available=1  order by type ,name", searchString);

                    break;
                case 2:
                    sql = String.format("select * from medias where upper(name) like upper('%s')  and type='music' and available=1 order by type ,name", searchString);

                    break;
                case 3:
                    sql = String.format("select * from medias where upper(name) like upper('%s')  and type='audioBook' and available=1 order by type ,name", searchString);

                    break;




            }




            dbResultSet = dbStatement.executeQuery(sql);
            while (dbResultSet.next()) {

                String number = String.format("%d", i + 1);
                String type = dbResultSet.getString("type");
                String name = dbResultSet.getString("name");
                String author = dbResultSet.getString("author");
                String time = dbResultSet.getString("time");
                String genre = dbResultSet.getString("genre");
                String averageRanking = String.format("%d", dbResultSet.getInt("rank"));
                String price = String.format("%.2f", dbResultSet.getDouble("price"));
                String soldNum = String.format("%d", dbResultSet.getInt("sold_num"));
                String rating = String.format("%.2f", dbResultSet.getDouble("rate"));
                String year = String.format("%d", dbResultSet.getInt("year_released"));
                String[] currentRow = {number, type, name, author, time, genre, averageRanking, price, soldNum, rating, year};
                searchTableModel.addRow(currentRow);
                i++;

            }



        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        repaint();

    }

    public void initSearchTab() {

        searchTab.setLayout(new BorderLayout());
        searchTabSearchFieldPanel = new JPanel();
        searchTabSearchFieldPanel.setLayout(new FlowLayout());
        searchTabTablePanel = new JPanel();
        searchTabTablePanel.setLayout(new BorderLayout());



        searchTabSearchFieldPanel.add(new JLabel("Name:"));
        searchName = new JTextField(10);
        searchTabSearchFieldPanel.add(searchName);


        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchTabSearchFieldPanel.add(searchButton);


        searchTabSearchFieldPanel.add(new JLabel("Filter:"));
        filterTypeJComboBox = new JComboBox(new String[]{"All", "Movie", "Music", "AudioBook"});
        filterTypeJComboBox.addActionListener(this);
        filterTypeJComboBox.setMaximumRowCount(4);
        filterTypeJComboBox.setPreferredSize(new Dimension(passWord.getPreferredSize().width, passWord.getPreferredSize().height));
        searchTabSearchFieldPanel.add(filterTypeJComboBox);


        searchTab.add(searchTabSearchFieldPanel, BorderLayout.NORTH);

        updateSearchTablePanel();








        repaint();
    }

    public TopLevelGUI() {
        super("Media Store");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    if (dbResultSet != null) {
                        dbResultSet.close();
                        System.out.println("close dbResultSet");
                    }
                    if (dbStatement != null) {
                        dbStatement.close();
                        System.out.println("close dbStatement");
                    }
                    if (dbConnection != null) {
                        dbConnection.close();
                        System.out.println("close dbConnection");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);

            }
        });





        dbConnection = null;
        dbStatement = null;
        dbResultSet = null;

        setUpDB();
        userInfoLabel = new JLabel(); //need here!!

        // get the max mediaID from data base;

        try {
            sql = "select max(media_id) from medias";

//            Statement tempStatement = dbConnection.createStatement();
            dbResultSet = dbStatement.executeQuery(sql);
            if (dbResultSet.next()) {
                maxMediaID = dbResultSet.getInt(1);
            } else {
                System.out.println("error when get the max media_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        mediaStore1 = new MediaStore("jiz5118", "Jie", "College Ave, State College", "123"); //ID, userName , userAddress , password
        tabs = new JTabbedPane();

        this.setResizable(
                false);



        movieTab = new JPanel();

        tabs.addTab(
                "Movie", null, movieTab, "The Movie tab");

        musicTab = new JPanel();

        tabs.addTab(
                "Music", null, musicTab, "The Music tab");

        audioBookTab = new JPanel();

        tabs.addTab(
                "AudioBook", null, audioBookTab, "The AudioBook Tab");

        searchTab = new JPanel();

        tabs.addTab(
                "Search", null, searchTab, "Search Media Tab");

        add(tabs, BorderLayout.CENTER);    // put tab system in application

        tabs.addChangeListener(
                this);
        accountPanel = new JPanel();
//        add(accountPanel,BorderLayout.WEST);

        accountPanel.setPreferredSize(
                new Dimension(300, 800));
        add(accountPanel, BorderLayout.WEST);
        myFont = getFont();

        logout();

        initSearchTab();

        updateLibrary();
    }

    /**
     * add a customer to verctor
     *
     * @param customers
     * @param customer
     */
    public void addCustomer(Customer customer) {
        sql = String.format("select * from users where user_id='%s'", customer.ID);
        try {

            dbResultSet = dbStatement.executeQuery(sql);
            if (dbResultSet.next()) {//find it
                System.out.printf("%s already exist \n", customer.ID);
                JOptionPane.showMessageDialog(null, "Account with User ID \"" + customer.ID + "\" is already exist", null, JOptionPane.WARNING_MESSAGE);
            } else { //not found add it
                sql = String.format("insert into users (user_id,name,credit,purchase_num,address)"
                        + "values ('%s','%s',%.2f,%d,'%s')", customer.ID, customer.name, customer.credit, customer.numberPurchase, customer.address);
                dbStatement.execute(sql);
//                customers.add(customer);
                System.out.printf("You add a customer with ID: %s \n", customer.ID);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//
    }

    public void addMedia(Media media) {
        sql = String.format("select * from medias where name='%s' and type='%s'", media.name, media.getType());
        try {
            dbResultSet = dbStatement.executeQuery(sql);
            if (dbResultSet.next()) { //found it reactive it
                if (dbResultSet.getInt("available") == 0) {
                    sql = String.format("update medias set available=1 where name='%s' and type='%s'", media.name, media.getType());
                    dbStatement.execute(sql);
//                    mediaObjects.get(foundIndex).avaliable = true;
                    System.out.printf("%s has been readd \n", media.name);
                    JOptionPane.showMessageDialog(null, String.format("%s has been readd \n", media.name), null, JOptionPane.WARNING_MESSAGE);
                } else {

                    System.out.printf("%s already exist \n", media.name);
                    JOptionPane.showMessageDialog(null, String.format("%s already exist \n", media.name), null, JOptionPane.WARNING_MESSAGE);

                }
            } else { //did not find it add it.
                System.out.println(maxMediaID);
                maxMediaID++;
                System.out.println(maxMediaID);

                media.mediaID = maxMediaID;


                sql = String.format("insert into medias (media_id,available,type,name,author,time,genre,rank,price,sold_num,rate_num,rate,rate_total,year_released)"
                        + "values (%d,%d,'%s','%s','%s','%s','%s',%d,%.2f,%d,%d,%.2f,%.2f,%d)", media.mediaID, media.avaliable, media.getType(), media.name, media.author, media.time, media.genre, media.rank, media.price, media.soldNum, media.rateNum, media.rate, media.rateTotal, media.getYear());
                dbStatement.execute(sql);


                // allocate memory for an Album and add it to albums array
                //		albums[Album.getNumberOfAlbum()]=new Album(artist,name,time,genre,rank,price,soldNum); 
                System.out.printf("%s: %s has been added \n", media.getType(), media.name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//}

    /**
     * remove media from mediaObject vector if found
     *
     * @param mediaObjects
     * @param name
     */
    public void removeMedia(String name, String type) {

        sql = String.format("select * from medias where name='%s' and type='%s'", name, type);
        try {
            dbResultSet = dbStatement.executeQuery(sql);
            if (dbResultSet.next()) { //found it and remove it from mediaObjects vector
                if (dbResultSet.getInt("available") == 1) {
                    sql = String.format("update medias set available=0 where name='%s' and type='%s'", name, type);
                    dbStatement.execute(sql);
                    System.out.printf("%s has been removed \n", name);

                } else {

                    System.out.printf("%s was removed before\n", name);
                    JOptionPane.showMessageDialog(null, String.format("%s was removed before\n", name), null, JOptionPane.WARNING_MESSAGE);

                }
            } else { // not found
                System.out.printf("%s can't not be found in library \n", name);
                JOptionPane.showMessageDialog(null, String.format("%s can't not be found in library \n", name), null, JOptionPane.WARNING_MESSAGE);




                // allocate memory for an Album and add it to albums array
                //		albums[Album.getNumberOfAlbum()]=new Album(artist,name,time,genre,rank,price,soldNum); 

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateCurrentUserResultSet() {

        try {
            sql = String.format("select * from users where user_id='%s'", currentUserID);

            currentUserResultSet = currentUserStatement.executeQuery(sql);
            if (currentUserResultSet.next()) {
                System.out.println("can find the user_ID");
                userInfoLabel.setText(String.format("<html><br>User Information: </br> <br>Account ID: %s</br>  <br>Name: %s  </br>  <br>Credit %.2f</br> <br>Number of Purchase: %d  </br> <br>Address: %s  </br> </html>", currentUserResultSet.getString("user_id"), currentUserResultSet.getString("name"), currentUserResultSet.getDouble("credit"), currentUserResultSet.getInt("purchase_num"), currentUserResultSet.getString("address")));


            } else {
                System.out.println("can't find the user_ID");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * display total Sale from the media store
     *
     * @param medisObjects
     */
    public double CheckTotalSale() {
        sql = "select sum(sold_num*price) total_sale from medias";
        try {
            dbResultSet = dbStatement.executeQuery(sql);
            dbResultSet.next();
            return dbResultSet.getDouble(1);

//            return dbResultSet.getDouble("sum(sold_num*price)");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }

    }

//    
    public boolean ifRated(int mediaID) {

        Statement tempStatement = null;
        ResultSet tempResultSet = null;






        try {
            sql = String.format("select * from rates where media_id=%d", mediaID);
            tempStatement = dbConnection.createStatement();
            tempResultSet = tempStatement.executeQuery(sql);
            while (tempResultSet.next()) {
                if (tempResultSet.getString("user_id").equals(currentUserID)) {
                    return true; //rated
                }
            }
            return false; //not rate

        } catch (SQLException ex) {
            ex.printStackTrace();
            return true; //error return anything
        } finally {
            try {
                if (tempResultSet != null) {
                    tempResultSet.close();
                }
                if (tempStatement != null) {
                    tempStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    /**
     * customer can giveRate after he/she has purchased it
     *
     * @param mediaObjects
     * @param name
     */
    public void giveRate(String name, String type) {
        try {
            sql = String.format("select * from medias where name='%s' and type='%s'", name, type);

//            tempStatement1 = dbConnection.createStatement();
            dbResultSet = dbStatement.executeQuery(sql);
            if (dbResultSet.next()) {//found in the library
                if (ifRated(dbResultSet.getInt("media_id"))) {
                    JOptionPane.showMessageDialog(null, "You already rate it before", null, JOptionPane.WARNING_MESSAGE);//show never happen


                } else {


                    String feedBack = (String) JOptionPane.showInputDialog(null, "Rate this media", "Feedback", JOptionPane.PLAIN_MESSAGE, null, rateChoices, rateChoices[4]);
                    if (feedBack != null) {


                        sql = String.format("insert into rates (media_id,user_id) values (%d,'%s')", dbResultSet.getInt("media_id"), currentUserID);
                        dbStatement.execute(sql); // add to customer's purchaseHisotry

                        sql = String.format("update medias set rate_total=rate_total+%d, rate_num=rate_num+1 where name='%s' and type='%s'", Integer.parseInt(feedBack), name, type);
                        dbStatement.execute(sql);


                        sql = String.format("update medias set rate=rate_total/rate_num where name='%s' and type='%s'", name, type);
                        dbStatement.execute(sql);//update the rate


                        updateLibrary();
                        updatePurchaseHistoryTab();

                    }





                }
            } else {
                JOptionPane.showMessageDialog(null, "can't find it in the library", null, JOptionPane.WARNING_MESSAGE);//show never happen
                System.out.printf("Can't find %s in the library \n", name);

            }






        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * test whether media has been purchased
     *
     * @param media
     * @return true if it has been purchased;false otherwise
     */
    public boolean ifPurchased(int mediaID) {

        Statement tempStatement = null;
        ResultSet tempResultSet = null;
        try {
            sql = String.format("select * from purchases where user_id='%s'", currentUserID);
            tempStatement = dbConnection.createStatement();
            tempResultSet = tempStatement.executeQuery(sql);
            while (tempResultSet.next()) {
                if (tempResultSet.getInt("media_id") == mediaID) {
                    return true;
                }
            }
            return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (tempResultSet != null) {
                    tempResultSet.close();
                }
                if (tempStatement != null) {
                    tempStatement.close();

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }


    }

    /**
     * purchase the media if it's found in the MediaStore, it has not been
     * purchased by this customer and the customer has enough credit
     *
     * @param mediaObjects
     * @param name
     */
    public void purchase(String name, String type) {

        Statement tempStatement = null;

        sql = String.format("select * from medias where name='%s' and type='%s'", name, type);
        try {
            dbResultSet = dbStatement.executeQuery(sql);
            if (!dbResultSet.next()) {//not found in the library
                System.out.printf("Can't find %s in the library \n", name);
            } else if (ifPurchased(dbResultSet.getInt("media_id"))) {
                System.out.printf("You already purchased %s \n", name);
                JOptionPane.showMessageDialog(null, "You already purhcase " + name + " before", null, JOptionPane.WARNING_MESSAGE);
            } else if (currentUserResultSet.getDouble("credit") >= dbResultSet.getDouble("price")) {

//                mediaStore1.getCustomers().get(userIndex).credit -= dbResultSet.getDouble("price"); 
                tempStatement = dbConnection.createStatement();
                sql = String.format("update users set credit=credit-%f where user_id='%s'", dbResultSet.getDouble("price"), currentUserID);

                tempStatement.execute(sql); //deducts album price from credit

                sql = String.format("insert into purchases (user_id,media_id) values ('%s',%d)", currentUserID, dbResultSet.getInt("media_id"));
                tempStatement.execute(sql); // add to customer's purchaseHisotry          





                sql = String.format("update medias set sold_num=sold_num+1 where name='%s' and type='%s'", name, type);
                tempStatement.execute(sql); //increase number sold for this particular album
                System.out.println("purchase_num increase");

                sql = String.format("update users set purchase_num=purchase_num+1 where user_id='%s'", currentUserID);
                tempStatement.execute(sql); //increase purchase_num

//                updateAverageRanking();
//                updateLibrary();
//
//                updatePurchaseHistoryTab();


                updateCurrentUserResultSet(); //dirty jobs
                updateLibrary();
                updatePurchaseHistoryTab();
                System.out.printf("%s has been purchased \n", name);
            } else {
                JOptionPane.showMessageDialog(null, "You don't have enough credit", null, JOptionPane.WARNING_MESSAGE);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (tempStatement != null) {
                    tempStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }

    public void setUpDB() {
        try {
            // start Derby engine
            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
            Class.forName(driver).newInstance();
            String protocol = "jdbc:derby:";
            String dbName = "MediaStoreDB";
            dbConnection = DriverManager.getConnection("jdbc:derby:MediaStoreDB; create=true");
//            dbConnection.setAutoCommit(false);
            dbStatement = dbConnection.createStatement();
            currentUserStatement = dbConnection.createStatement();

            // test to see if we need to run the initial squl script
            dbMeta = dbConnection.getMetaData();
            String[] tableTypes = {"TABLE"};
            dbResultSet = dbMeta.getTables(null, null, "%", tableTypes);










            if (!dbResultSet.next()) {
                // no table found, we need to execut the initial sql statments
                // first read the sql script
                InputStream is = TopLevelGUI.class
                        .getResourceAsStream("init.sql");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                line = br.readLine();
                while (line
                        != null) {
                    sb.append(line);
                    if (line.contains(";")) {
                        sql = sb.toString();
                        sb = new StringBuilder();
                        // executeQuery cannot process ; so remove it from sql
                        sql = sql.substring(0, sql.indexOf(';'));
                        // run sql here
                        if (sql.toUpperCase().contains("SELECT")) {
                            dbStatement.executeQuery(sql);
                        } else {
                            dbStatement.execute(sql);
                        }
                    }
                    line = br.readLine();
                }
            }
// test
            sql = "select * from users";
            dbResultSet = dbStatement.executeQuery(sql);
            while (dbResultSet.next()) {
                System.out.println(dbResultSet.getString("user_id"));
            }
            sql = "select * from medias";
            dbResultSet = dbStatement.executeQuery(sql);
            while (dbResultSet.next()) {
                System.out.println(dbResultSet.getString("name"));
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // could not start Derby engine
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            // could not connect to Derby
            e.printStackTrace();
        }

    }

    public void logout() {

        accountPanel.removeAll();
        accountPanel.validate();


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


        if (tabs.getTabCount() == 5) {
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




//        userInfoLabel = new JLabel();
        updateCurrentUserResultSet();
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
        giveRateButton = new JButton("Rate");
        giveRateButton.addActionListener(this);
        giveRateButton.setVisible(false);
//        giveRateButton.setEnabled(false);
        accountPanel.add(giveRateButton, constraints);



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




        // initialize the history tab
        updatePurchaseHistoryTab();
        repaint();

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
                + "<br>Store TotalSale:" + String.format("%.2f", CheckTotalSale()) + "</br></html>");
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

    public void updatePurchaseHistoryTable() {


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




        int i;
        try {
            i = 0;

            sql = String.format("select * from purchases where user_id='%s'", currentUserID);
            dbResultSet = dbStatement.executeQuery(sql);
            String purchaseList = "-1";
//            if (dbResultSet.next()) {
//                purchaseList += dbResultSet.getInt("media_id");
//            }


            while (dbResultSet.next()) {
                purchaseList += String.format(",%d", dbResultSet.getInt("media_id"));
            }



            sql = String.format("select * from medias where media_id in (%s) order by type,name", purchaseList);
            dbResultSet = dbStatement.executeQuery(sql);
            while (dbResultSet.next()) {

                String number = String.format("%d", i + 1);
                String type = dbResultSet.getString("type");
                String name = dbResultSet.getString("name");
                String author = dbResultSet.getString("author");
                String time = dbResultSet.getString("time");
                String genre = dbResultSet.getString("genre");
                String averageRanking = String.format("%d", dbResultSet.getInt("rank"));
                String price = String.format("%.2f", dbResultSet.getDouble("price"));
                String soldNum = String.format("%d", dbResultSet.getInt("sold_num"));
                String rating = String.format("%.2f", dbResultSet.getDouble("rate"));
                String year = String.format("%d", dbResultSet.getInt("year_released"));
                String[] currentRow = {number, type, name, author, time, genre, averageRanking, price, soldNum, rating, year};
                purchaseHistoryTableModel.addRow(currentRow);
                i++;

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


//        repaint();

    }

    public void updatePurchaseHistoryTab() {
        purchaseHistoryTab.removeAll();

        purchaseHistoryTab.setLayout(new BorderLayout());
        updatePurchaseHistoryTable();
        purchaseHistoryTab.add(purchaseHistoryScrollPane, BorderLayout.CENTER);
        purchaseHistoryTab.validate();
        repaint();
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
//        userTable=new JTable(userData,UserCols);




        userTable.getColumnModel().getColumn(5).setPreferredWidth(300);
        JScrollPane userScrollPane = new JScrollPane(userTable);
        userTable.setFillsViewportHeight(true);
        userTab.add(userScrollPane, BorderLayout.CENTER);



        int i;
        try {
            i = 0;
            sql = "select * from users order by user_id";
            dbResultSet = dbStatement.executeQuery(sql);
            while (dbResultSet.next()) {

                String number = String.format("%d", i + 1);
                String ID = dbResultSet.getString("user_id");
                String name = dbResultSet.getString("name");
                String credit = String.format("%.2f", dbResultSet.getDouble("credit"));
                String numberPurchase = String.format("%d", dbResultSet.getInt("purchase_num"));
                String address = dbResultSet.getString("address");
                String[] currentRow = {number, ID, name, credit, numberPurchase, address};
                userTableModel.addRow(currentRow);
                i++;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }



        repaint();


    }
    
    public boolean isNonNegativeDouble(String string) {
        try {
            if (Double.parseDouble(string) >= 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "can't accept Negative number", null, JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Number Format Error", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public boolean isPositiveDouble(String string) {
        try {
            if (Double.parseDouble(string) > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "can't accept non positive number", null, JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Number Format Error", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    /**
     * sort the mediaOjbects ArrayList base on mediaTypeJComboBox and numberSold
     * and then update the averageRank
     */
    public void updateAverageRanking() {
        int currentRank = 1;
        String previousType;
        int previousSoldNumber;
        int previousRank;
        int currentMediaID;
//        Statement tempStatement1;

        // set all rank to 1;


        Statement tempStatement = null;


        try {
//            tempStatement1 = dbConnection.createStatement();



            sql = "update medias set rank=1";
            dbStatement.execute(sql);

            sql = "select * from medias order by type,sold_num desc";
            dbResultSet = dbStatement.executeQuery(sql);
            dbResultSet.next();

            previousType = dbResultSet.getString("type");
            previousSoldNumber = dbResultSet.getInt("sold_num");
            previousRank = dbResultSet.getInt("rank");
            System.out.println(String.format("%s,number_sold: %d, Rank: %d", previousType, previousSoldNumber, previousRank));

            while (dbResultSet.next()) {
                if (dbResultSet.getString("type").equals(previousType)) { //if same type
//                    System.out.println("Sametype");
                    currentMediaID = dbResultSet.getInt("media_id");

                    tempStatement = dbConnection.createStatement();
//                    ResultSet tempResultSet2=tempStatement2.
                    if (dbResultSet.getInt("sold_num") < previousSoldNumber) { //rank increase
//                        System.out.println("previousRank"+" increase");
                        currentRank++;
                        sql = String.format("update medias set rank=%d where media_id=%d", currentRank, currentMediaID);
                        tempStatement.execute(sql);

                    } else {  //rank is the same as previous
//                        System.out.println(previousRank+" remaind");
                        sql = String.format("update medias set rank=%d where media_id=%d", currentRank, currentMediaID);
                        tempStatement.execute(sql);

                    }
                } else {//reset the current rank to 1
                    currentRank = 1;
                }
                previousType = dbResultSet.getString("type");
                previousSoldNumber = dbResultSet.getInt("sold_num");
                previousRank = dbResultSet.getInt("rank");
                System.out.println(String.format("%s,number_sold: %d, Rank: %d", previousType, previousSoldNumber, previousRank));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (tempStatement != null) {
                    tempStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    public void updateLibrary() {

        //resort the library
        //***********************************************************************************
        updateAverageRanking();
        updateSearchTablePanel();

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


        int i;
        try {
            sql = "select * from medias where type='movie' and available=1 order by name";

//            sql = "select * from medias order by type,sold_num desc,name";
            dbResultSet = dbStatement.executeQuery(sql);
            i = 0;
            while (dbResultSet.next()) {
                String number = String.format("%d", i + 1);
                String name = dbResultSet.getString("name");
                String author = dbResultSet.getString("author");
                String time = dbResultSet.getString("time");
                String genre = dbResultSet.getString("genre");
                String averageRanking = String.format("%d", dbResultSet.getInt("rank"));
                String price = String.format("%.2f", dbResultSet.getDouble("price"));
                String numberSold = String.format("%d", dbResultSet.getInt("sold_num"));
                String rating = String.format("%.2f", dbResultSet.getDouble("rate"));
                String year = String.format("%d", dbResultSet.getInt("year_released"));
                String[] currentRow = {number, name, author, time, genre, averageRanking, price, numberSold, rating, year};
                movieTableModel.addRow(currentRow);
                i++;
            }


            sql = "select * from medias where type='music' and available=1 order by name";
            dbResultSet = dbStatement.executeQuery(sql);
            i = 0;
            while (dbResultSet.next()) {
                String number = String.format("%d", i + 1);
                String name = dbResultSet.getString("name");
                String author = dbResultSet.getString("author");
                String time = dbResultSet.getString("time");
                String genre = dbResultSet.getString("genre");
                String averageRanking = String.format("%d", dbResultSet.getInt("rank"));
                String price = String.format("%.2f", dbResultSet.getDouble("price"));
                String numberSold = String.format("%d", dbResultSet.getInt("sold_num"));
                String rating = String.format("%.2f", dbResultSet.getDouble("rate"));
//                String year = String.format("%d", dbResultSet.getInt("year_released"));
                String[] currentRow = {number, name, author, time, genre, averageRanking, price, numberSold, rating};
                musicTableModel.addRow(currentRow);
                i++;
            }




            sql = "select * from medias where type='audioBook' and available=1  order by name";
            dbResultSet = dbStatement.executeQuery(sql);
            i = 0;
            while (dbResultSet.next()) {
                String number = String.format("%d", i + 1);
                String name = dbResultSet.getString("name");
                String author = dbResultSet.getString("author");
                String time = dbResultSet.getString("time");
                String genre = dbResultSet.getString("genre");
                String averageRanking = String.format("%d", dbResultSet.getInt("rank"));
                String price = String.format("%.2f", dbResultSet.getDouble("price"));
                String numberSold = String.format("%d", dbResultSet.getInt("sold_num"));
                String rating = String.format("%.2f", dbResultSet.getDouble("rate"));
//                String year = String.format("%d", dbResultSet.getInt("year_released"));
                String[] currentRow = {number, name, author, time, genre, averageRanking, price, numberSold, rating};
                audioBookTableModel.addRow(currentRow);
                i++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
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

                try {
                    sql = String.format("select * from users where user_id='%s'", userID.getText());
                    currentUserResultSet = currentUserStatement.executeQuery(sql);
                    if (currentUserResultSet.next()) {
                        currentUserID = currentUserResultSet.getString("user_id");
//                        sql = String.format("select * from users where user_id='%s'", userID.getText());
//                        currentUserResultSet = currentUserStatement.executeQuery(sql);
                        loginUserAccount();
                    } else {
                        JOptionPane.showMessageDialog(null, "Can't find UserID \"" + userID.getText() + "\" in the database", null, JOptionPane.WARNING_MESSAGE);

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
//                userIndex = mediaStore1.foundCustomer(userID.getText());
//                if (userIndex != -1) { // find it and login
//                    loginUserAccount();
////                    updateLibrary();
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Can't find UserID \"" + userID.getText() + "\" in the database", null, JOptionPane.WARNING_MESSAGE);
//                }

            } else { // manager account
                if (mediaStore1.getManager().ID.equals(userID.getText()) && Arrays.equals(mediaStore1.getManager().password.toCharArray(), passWord.getPassword())) {
                    loginManagerAccount();
//                    updateLibrary();

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Manager userID and password combination", null, JOptionPane.WARNING_MESSAGE);
                }

            }
        }



        if (e.getSource() == searchButton) {
            updateSearchTablePanel();
        }

        if (e.getSource() == filterTypeJComboBox) {

            updateSearchTablePanel();
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
                if (userID.getText().isEmpty() || userName.getText().isEmpty() || userAddress.getText().isEmpty() || userCredit.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty inputs is not allow", null, JOptionPane.WARNING_MESSAGE);

                } else {
                    if (isNonNegativeDouble(userCredit.getText())) {
                        addCustomer(new Customer(userID.getText(), userName.getText(), userAddress.getText(), Double.parseDouble(userCredit.getText())));
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
            mediaTypeJComboBox = new JComboBox(new String[]{"Movie", "Music", "AudioBook"});
            mediaTypeJComboBox.addActionListener(this);
            mediaTypeJComboBox.setPreferredSize(new Dimension(passWord.getPreferredSize().width, passWord.getPreferredSize().height));
            mediaTypePanel.add(mediaTypeJComboBox);
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



            mediaYearOfReleasePanel = new JPanel();
            mediaYearOfReleasePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            mediaYearOfReleasePanel.add(new JLabel("Year:"));
            mediaYearOfRelease = new JTextField(10);
//            mediaYearOfRelease.setText("Movie Only");
            mediaYearOfReleasePanel.add(mediaYearOfRelease);
            addMediaPanel.add(mediaYearOfReleasePanel);


            int result = JOptionPane.showConfirmDialog(null, addMediaPanel, "Add a media", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);


            if (result == JOptionPane.OK_OPTION) {
                if (mediaName.getText().isEmpty() || mediaAuthor.getText().isEmpty() || mediaTime.getText().isEmpty() || mediaGenre.getText().isEmpty() || mediaPrice.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty inputs is not allow", null, JOptionPane.WARNING_MESSAGE);

                } else {


                    if (mediaTypeJComboBox.getSelectedIndex() == 0) {
                        if (mediaYearOfRelease.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Empty inputs is not allow", null, JOptionPane.WARNING_MESSAGE);

                        } else {
                            if (isPositiveDouble(mediaYearOfRelease.getText()) && isPositiveDouble(mediaPrice.getText())) {

                                addMedia(new Movie(mediaName.getText(), mediaAuthor.getText(), mediaTime.getText(), mediaGenre.getText(),
                                        Integer.parseInt(mediaYearOfRelease.getText()), Double.parseDouble(mediaPrice.getText())));
                                updateLibrary();
                            }


                        }

                    } else if (mediaTypeJComboBox.getSelectedIndex() == 1) {
                        if (isPositiveDouble(mediaPrice.getText())) {
                            addMedia(new Music(mediaName.getText(), mediaAuthor.getText(), mediaTime.getText(), mediaGenre.getText(),
                                    Double.parseDouble(mediaPrice.getText())));
                            updateLibrary();
                        }

                    } else {
                        if (isPositiveDouble(mediaPrice.getText())) {
                            addMedia(new AudioBook(mediaName.getText(), mediaAuthor.getText(), mediaTime.getText(), mediaGenre.getText(),
                                    Double.parseDouble(mediaPrice.getText())));
                            updateLibrary();
                        }

                    }
                }
            }


        }






        if (e.getSource() == removeMediaButton) {


            if (tabs.getSelectedIndex() == 0) {
                selectedRow = movieTable.getSelectedRow();
                if (movieTable.isRowSelected(selectedRow)) {
                    removeMedia(movieTable.getValueAt(selectedRow, 1).toString(), "movie");
                    updateLibrary();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media to be removed", null, JOptionPane.WARNING_MESSAGE);
                }

            } else if (tabs.getSelectedIndex() == 1) {
                selectedRow = musicTable.getSelectedRow();
                if (musicTable.isRowSelected(selectedRow)) {
                    removeMedia(musicTable.getValueAt(selectedRow, 1).toString(), "music");
                    updateLibrary();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media to be removed", null, JOptionPane.WARNING_MESSAGE);
                }
            } else if (tabs.getSelectedIndex() == 2) {
                selectedRow = audioBookTable.getSelectedRow();
                if (audioBookTable.isRowSelected(selectedRow)) {
                    removeMedia(audioBookTable.getValueAt(selectedRow, 1).toString(), "audioBook");
                    updateLibrary();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media to be removed", null, JOptionPane.WARNING_MESSAGE);
                }
            } else if (tabs.getSelectedIndex() == 3) {
                selectedRow = searchTable.getSelectedRow();
                if (searchTable.isRowSelected(selectedRow)) {
                    removeMedia(searchTable.getValueAt(selectedRow, 2).toString(), searchTable.getValueAt(selectedRow, 1).toString());
                    updateLibrary();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media to be removed", null, JOptionPane.WARNING_MESSAGE);
                }
            }





        }



        if (e.getSource() == checkPurchaseHistoryButton) {
            selectedRow = userTable.getSelectedRow();
            if (userTable.isRowSelected(selectedRow)) {

                currentUserID = userTable.getValueAt(selectedRow, 1).toString();
                updateCurrentUserResultSet();
                System.out.println(currentUserID);
                checkPurchaseHistoryPanel = new JPanel();
                checkPurchaseHistoryPanel.setLayout(new BorderLayout());
                checkPurchaseHistoryPanel.setPreferredSize(new Dimension(900, 700));
                updatePurchaseHistoryTable();
                checkPurchaseHistoryPanel.add(purchaseHistoryScrollPane, BorderLayout.CENTER);
                repaint();
                JOptionPane.showConfirmDialog(userTab, checkPurchaseHistoryPanel, userTable.getValueAt(selectedRow, 1).toString() + ": Purchase History", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
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

                    if (isPositiveDouble(deposite)) {
//                        mediaStore1.getCustomers().get(userIndex).deposit(Double.parseDouble(deposite));
                        try {
                            sql = String.format("update users set credit=credit+%f where user_id='%s'", Double.parseDouble(deposite), currentUserResultSet.getString("user_id"));
                            dbStatement.execute(sql);
                            updateCurrentUserResultSet();


                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    }


                }


            }

        }



        if (e.getSource() == purchaseMediaButton) {

            if (tabs.getSelectedIndex() == 0) {
                selectedRow = movieTable.getSelectedRow();
                if (movieTable.isRowSelected(selectedRow)) {
                    purchase(movieTable.getValueAt(selectedRow, 1).toString(), "movie");

                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);
                }

            } else if (tabs.getSelectedIndex() == 1) {
                selectedRow = musicTable.getSelectedRow();
                if (musicTable.isRowSelected(selectedRow)) {
                    purchase(musicTable.getValueAt(selectedRow, 1).toString(), "music");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);
                }
            } else if (tabs.getSelectedIndex() == 2) {
                selectedRow = audioBookTable.getSelectedRow();
                if (audioBookTable.isRowSelected(selectedRow)) {
                    purchase(audioBookTable.getValueAt(selectedRow, 1).toString(), "audioBook");

//                    updatePurchaseHistoryTab();
//                    updateLibrary();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);
                }
            } else if (tabs.getSelectedIndex() == 3) {

                selectedRow = searchTable.getSelectedRow();
                if (searchTable.isRowSelected(selectedRow)) {
                    purchase(searchTable.getValueAt(selectedRow, 2).toString(), searchTable.getValueAt(selectedRow, 1).toString());

                } else {
                    JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);
                }
            }
//            updatePurchaseHistoryTab();
//            updateLibrary();

        }


        if (e.getSource() == mediaTypeJComboBox) {
            if (mediaTypeJComboBox.getSelectedIndex() == 0) {
                mediaYearOfReleasePanel.setVisible(true);
            } else {
                mediaYearOfReleasePanel.setVisible(false);

            }

        }


        if (e.getSource() == giveRateButton) {
//            if (tabs.getSelectedIndex() == 3) { // if one item is selected 

            selectedRow = purchaseHistoryTable.getSelectedRow();
            if (purchaseHistoryTable.isRowSelected(selectedRow)) {

                giveRate(purchaseHistoryTable.getValueAt(selectedRow, 2).toString(), purchaseHistoryTable.getValueAt(selectedRow, 1).toString());

//                }



            } else {
                JOptionPane.showMessageDialog(null, "Please select a media", null, JOptionPane.WARNING_MESSAGE);

            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == tabs) {
            if (tabs.getTabCount() == 5) { //change only when login
                if (accountTypeJComboBox.getSelectedIndex() == 0) {//user account
                    if (tabs.getSelectedIndex() <= 3) {
                        purchaseMediaButton.setVisible(true);
                        giveRateButton.setVisible(false);
                    } else {
                        purchaseMediaButton.setVisible(false);
                        giveRateButton.setVisible(true);
                    }
                } else { //manager account

                    if (tabs.getSelectedIndex() <= 3) {
                        removeMediaButton.setVisible(true);
                        checkPurchaseHistoryButton.setVisible(false);
                    } else {
                        removeMediaButton.setVisible(false);
                        checkPurchaseHistoryButton.setVisible(true);
                    }



                }
            }


        }
    }

    public static void main(String[] args) {
        try {
            //star Derby engine



            TopLevelGUI prog = new TopLevelGUI();

            prog.setSize(1200, 800);
            prog.setVisible(true);

            //mediaStore1


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
