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
//    private String sql;
    protected int numberPurchase;
//    private ArrayList<Integer> mediaIDList; //purchaseHistory is a ArrayList of Media
    protected double credit; // credit of customer
//    protected String purchaseMediaID;
//    pr

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
//        mediaIDList = new ArrayList<Integer>(); // Allocate memory for mediaIDList ArrayList
        numberPurchase=0;
//        purchaseMediaID="-1";
//        try {
//            dbConnection = DriverManager.getConnection("jdbc:derby:MediaStoreDB; create=true");
//            dbStatement = dbConnection.createStatement();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        

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
    
    
//    
//    /**
//     * customer can giveFeedBack after he/she has purchased it
//     *
//     * @param mediaObjects
//     * @param name
//     */
//    public void giveFeedBack(ArrayList<Media> mediaObjects, String name, String type, int feedback) {
//        int foundIndex = foundMedia(mediaObjects, name, type);
//        if (foundIndex != -1) { //found in the library
//            if (mediaObjects.get(foundIndex).ifRated(ID)) {
//                JOptionPane.showMessageDialog(null, "You already rate it before", null, JOptionPane.WARNING_MESSAGE);//show never happen
//
//            } else {
//                mediaObjects.get(foundIndex).feedBack.add(feedback); // add feedback to feedBack ArrayList
//                mediaObjects.get(foundIndex).feedBackUserID.add(ID); // add id to the feedBackUserID ArrayList
//                mediaObjects.get(foundIndex).updateRating(); //update the rate
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(null, "can't find it in the library", null, JOptionPane.WARNING_MESSAGE);//show never happen
//            System.out.printf("Can't find %s in the library \n", name);
//
//
//        
//    }
        
        
        

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
    
//    /**
//     * return String of info about Customer object
//     */
//    public String toString() {
//        String fs;
//        fs = String.format("<br>Account ID: %s</br>  <br>Name: %s  </br>  <br>Credit %.2f</br> <br>Number of Purchase: %d  </br> <br>Address: %s  </br>", ID, name, credit, mediaIDList.size(), address);
//        return fs;
//    }


}
