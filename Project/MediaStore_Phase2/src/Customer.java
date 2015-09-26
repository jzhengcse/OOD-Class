//import java.util.Scanner;
//import java.util.ArrayList;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Customer class extends Account class
 *
 * @author jiz5118
 *
 */
public class Customer extends Account {

//    private Connection dbConnection;
//    private Statement dbStatement;
//    private ResultSet dbResultSet;
    private String sql;
    private ArrayList<String> mediaIDList;
    private ArrayList<Media> purchaseHistory; //purchaseHistory is a ArrayList of Media
    protected double credit; // credit of customer

    /**
     * initial constructor for Customer class
     *
     * @param ID
     * @param name
     * @param address
     * @param numberOfPurchase
     * @param credit
     */
    public Customer(String ID, String name, String address, double credit) {
        super(ID, name, address); // call super initial constructor to set ID,name, and address
        this.credit = credit; // set initial credit
        purchaseHistory = new ArrayList<Media>(); // Allocate memory for purchaseHistory ArrayList
//        try {
//            dbConnection = DriverManager.getConnection("jdbc:derby:MediaStoreDB; create=true");
//            dbStatement = dbConnection.createStatement();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        

    }

//	public void listAllAlbums(ArrayList<Media> mediaObjects) {
//		// display all the albums info
//		
//		for(int i=0; i<mediaObjects.size();i++) {
//			if(mediaObjects.get(i) instanceof Album) {
//				System.out.println(mediaObjects.get(i).toString());
//			}
//		}	
//	}
//	
//	public void listAllAudioBooks(ArrayList<Media> mediaObjects) {
//		// display all the albums info
//		
//		for(int i=0; i<mediaObjects.size();i++) {
//			if(mediaObjects.get(i) instanceof AudioBook) {
//				System.out.println(mediaObjects.get(i).toString());
//			}
//		}	
//	}
//	
//	
//	public void listAllMovies(ArrayList<Media> mediaObjects) {
//		// display all the albums info
//		
//		for(int i=0; i<mediaObjects.size();i++) {
//			if(mediaObjects.get(i) instanceof Movie) {
//				System.out.println(mediaObjects.get(i).toString());
//			}
//		}	
//	}
    /**
     * search whether certain media is in the library
     *
     * @param mediaObjects
     * @param name
     * @return index of media object in the ArrayList if found; other return -1
     * (not found)
     */
    public int foundMedia(ArrayList<Media> mediaObjects, String name, String type) {
//        sql = String.format("select * from medias where name='%s' and type='%s'", name, type);
//        try {
//            dbResultSet=dbStatement.executeQuery(sql);
//            while(dbResultSet.next()){
//                if(name==dbResultSet.getString("name") && type==dbResultSet.getString("type")) {
//                    return 1;
//                }
//            }
//            return -1;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        
        
        
        if (mediaObjects.isEmpty()) {
            return -1; //not found
        } else {
            int i = 0;
            while (i < mediaObjects.size() && !(mediaObjects.get(i).name.equals(name) && mediaObjects.get(i).getType().toLowerCase().equals(type.toLowerCase()))) {
                i++;
            }
            if (i < mediaObjects.size()) {
                return i; // found it
            } else {
                return -1; //not found
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
    public void purchase(ArrayList<Media> mediaObjects, String name, String type) {
        int foundIndex = foundMedia(mediaObjects, name, type);
        if (foundIndex == -1) { //not found in the library
            System.out.printf("Can't find %s in the library \n", name);
        } else if (ifPurchased(mediaObjects.get(foundIndex))) { // already purchase it
            System.out.printf("You already purchased %s \n", name);
            JOptionPane.showMessageDialog(null, "You already purhcase " + name + " before", null, JOptionPane.WARNING_MESSAGE);
        } else if (credit >= mediaObjects.get(foundIndex).price) { // make the purchase if the customer have enough credit

            credit -= mediaObjects.get(foundIndex).price; //deducts album price from credit
            purchaseHistory.add(mediaObjects.get(foundIndex));// add to customer's purchaseHisotry
            mediaObjects.get(foundIndex).numberSold++; //increase number sold for this particular album
            System.out.printf("%s has been purchased \n", name);
//                        JOptionPane.showMessageDialog(null, name+ " has been purhcased", null, JOptionPane.WARNING_MESSAGE);
//            Collections.sort(purchaseHistory, new MediaComparator());

        } else {
            // display error info if the customer don't have enough credit
//			System.out.printf("You don't have enough credit \n");
            JOptionPane.showMessageDialog(null, "You don't have enough credit", null, JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * make a deposit
     *
     * @param credit
     */
    public void deposit(double credit) {
        if (credit > 0) {
            this.credit += credit;
            System.out.printf("You have %f credit \n", this.credit);
        } else {
            System.out.printf("%f is not a positive number \n", this.credit);
        }
    }

    /**
     * test whether media has been purchased
     *
     * @param media
     * @return true if it has been purchased;false otherwise
     */
    public boolean ifPurchased(Media media) {

        if (purchaseHistory.isEmpty()) { //not purchased
            return false;
        } else {
            int i = 0;
            while (i < purchaseHistory.size() && !(purchaseHistory.get(i).name.equals(media.name) && purchaseHistory.get(i).getType().equals(media.getType()))) {
                i++;
            }
            return i < purchaseHistory.size(); //i<purchaseHistory.size() means it has been already purchased;otherwise not been purhcased
        }

    }

    public boolean ifRated(ArrayList<String> feedBackUserID) {
        int i = 0;
        while (i < feedBackUserID.size() && !(feedBackUserID.get(i).equals(ID))) {
            i++;
        }
        return i < feedBackUserID.size();

    }

//            int index = mediaStore1.foundMedia(purchaseHistoryTable.getValueAt(selectedRow, 2).toString(), purchaseHistoryTable.getValueAt(selectedRow, 1).toString());
////                    System.out.println(index);
//            if (index != -1) { // found it in the library
//                if (mediaStore1.getCustomers().get(userIndexNumber).ifRated(mediaStore1.getMedias().get(index).feedBackUserID)) { //multiplyrate
//                    JOptionPane.showMessageDialog(null, "You already rate it before", null, JOptionPane.WARNING_MESSAGE);//show never happen
//                } else {
//                    String feedBack = (String) JOptionPane.showInputDialog(null, "Rate this media", "Feedback", JOptionPane.PLAIN_MESSAGE, null, feedbackChoices, feedbackChoices[4]);
//                    if (feedBack != null) {
//                        mediaStore1.getMedias().get(index).feedBack.add(Integer.parseInt(feedBack)); // add feedback to feedBack ArrayList
//                        mediaStore1.getMedias().get(index).feedBackUserID.add(mediaStore1.getCustomers().get(userIndexNumber).ID);
//                        mediaStore1.getMedias().get(index).updateRating(); //update the rate
//                        updatePurchaseHistory();
//                        updateLibrary();
//                    }
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "can't find it in the library", null, JOptionPane.WARNING_MESSAGE);//show never happen
//            }
//        
    /**
     * customer can giveFeedBack after he/she has purchased it
     *
     * @param mediaObjects
     * @param name
     */
    public void giveFeedBack(ArrayList<Media> mediaObjects, String name, String type, int feedback) {
        int foundIndex = foundMedia(mediaObjects, name, type);
        if (foundIndex != -1) { //found in the library
            if (ifRated(mediaObjects.get(foundIndex).feedBackUserID)) {
                JOptionPane.showMessageDialog(null, "You already rate it before", null, JOptionPane.WARNING_MESSAGE);//show never happen

            } else {
                mediaObjects.get(foundIndex).feedBack.add(feedback); // add feedback to feedBack ArrayList
                mediaObjects.get(foundIndex).feedBackUserID.add(ID); // add id to the feedBackUserID ArrayList
                mediaObjects.get(foundIndex).updateRating(); //update the rate
            }

        } else {
            JOptionPane.showMessageDialog(null, "can't find it in the library", null, JOptionPane.WARNING_MESSAGE);//show never happen
            System.out.printf("Can't find %s in the library \n", name);


        }






    }

//	/**
//	 * customer can giveFeedBack after he/she has purchased it
//	 * @param mediaObjects
//	 * @param name
//	 */
//	public void giveFeedBack(ArrayList<Media> mediaObjects, String name,String type) {
//		int foundIndex=foundMedia(mediaObjects,name,type);
//		if (foundIndex==-1) { //not found in the library
//			System.out.printf("Can't find %s in the library \n",name);
//			return;
//		} 
//		if(!ifPurchased(mediaObjects.get(foundIndex))) { //has not purchased it
//			System.out.printf("You can't rate %s because you did not purchase it \n",name);
//			return;
//		}
//		Scanner input = new Scanner(System.in); //call system.in to get input
//		char WantGiveGeedBack; //Y/y for Yes, N/n for No
//		System.out.printf("Do you want to give feedback for %s ? (Y/y for Yes, N/n for No) \n",mediaObjects.get(foundIndex).name);
//		WantGiveGeedBack=input.next().charAt(0); //get the first char of next input
//		if(WantGiveGeedBack=='Y'||WantGiveGeedBack=='y') {
//			Integer feedBack;
//			System.out.println("Give you feedback. (5 means best, 1 means worst)"); 
//			feedBack=new Integer(input.nextInt());
//			while(feedBack.intValue()<1 || feedBack.intValue()>5) {
//				System.out.printf("You didn't give a value between 1 and 5. Give you feedback. (5 means best, 1 means worst) \n"); 
//				feedBack=new Integer(input.nextInt());
//			}
//			mediaObjects.get(foundIndex).feedBack.add(feedBack); // add feedback to feedBack ArrayList
//			mediaObjects.get(foundIndex).updateRating(); //update the rate
//		} else if (WantGiveGeedBack=='N'||WantGiveGeedBack=='n') {
//			System.out.println("You can give feeback anytime"); 
//		} else {
//			System.out.printf("You did not type Y/y or N/n, but you can give feeback anytime \n");
//		}
//		
//	}
    /**
     * return String of info about Customer object
     */
    public String toString() {
        String fs;
        fs = String.format("<br>Account ID: %s</br>  <br>Name: %s  </br>  <br>Credit %.2f</br> <br>Number of Purchase: %d  </br> <br>Address: %s  </br>", ID, name, credit, purchaseHistory.size(), address);
        return fs;
    }

    /**
     * display the purchaseHistory
     */
    public void displayPurchaseHistory() {
        System.out.println("Purchase History:");
        for (int i = 0; i < purchaseHistory.size(); i++) {
            System.out.println(purchaseHistory.get(i));
        }
    }

    public ArrayList<Media> getPurchaseHistory() {
        return purchaseHistory;
    }
}
