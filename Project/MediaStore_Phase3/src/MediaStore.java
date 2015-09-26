
import java.util.Collections;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * MediaStore that contain ArrayList of Media,ArrayList of Customer and the
 * Manager
 *
 * @author jiz5118
 *
 */
public class MediaStore {

//    protected ArrayList<Media> mediaObjects; //contain all the media info
//    private ArrayList<Customer> customers; // contain all the customer info
    private Manager manager; //the manager of the media store

    /**
     * default constructor for MediaStore class
     */
    public MediaStore() {
//        mediaObjects = new ArrayList<Media>(); //Allocate memory 
//        customers = new ArrayList<Customer>(); //Allocate memory 
        manager = new Manager("", "", "", "");
//        mediaID = 0;
    }

    /**
     * initial constructor for MediaStore class
     *
     * @param ID
     * @param name
     * @param address
     * @param password
     */
    public MediaStore(String ID, String name, String address, String password) {
//        mediaObjects = new ArrayList<Media>(); //Allocate memory 
//        customers = new ArrayList<Customer>(); //Allocate memory 
        manager = new Manager(ID, name, address, password); //initialize Manager
    }
//
//    public void searchForMedia() {
//        // to be implement 
//    }

//    /**
//     * display all the Media in the library
//     *
//     * @param mediaStore
//     */
//    public void listAllMedias() {
//        updateAverageRanking(); //sort by type and rank
//        System.out.printf("\nList of all media in the Media Store: \n");
//        for (int i = 0; i < mediaObjects.size(); i++) {
//            System.out.println(mediaObjects.get(i).toString());
//        }
//    }

//    /**
//     * display total numberSold for a particular media
//     *
//     * @param mediaObjects
//     * @param name
//     */
//    public int CheckTotalNumberSold(String name, String type) {
//        int foundIndex = foundMedia(name, type);
//        int NumberSold = 0;
//        if (foundIndex == -1) {//not found
//            System.out.printf("Can't find %s in the library \n", name);
//        } else { //found it
//            System.out.printf("Total number of %s sold: %d \n", name, mediaObjects.get(foundIndex).getNumberSold());
//            NumberSold = mediaObjects.get(foundIndex).getNumberSold();
//        }
//        return NumberSold;
//    }







//    /**
//     * display all the information about the customer and display
//     * purchaseHistory
//     *
//     * @param customers
//     * @param ID
//     */
//    public void RetrieveCustomerInfo(String ID) {
//        int foundCustomerIndex = foundCustomer(ID);
//        if (foundCustomerIndex == -1) { //not found
//            System.out.printf("Can't find this customer ID \n");
//        } else { //found it
//            // display customer info
//            System.out.println(customers.get(foundCustomerIndex));
//            // display purchasHistory
//            customers.get(foundCustomerIndex).displayPurchaseHistory();
//        }
//    }

    
    
    
    
    
//    
//    /**
//     * sort the mediaOjbects ArrayList base on mediaType and numberSold and then
//     * update the averageRank
//     */
//    public void updateAverageRanking() {
//        Collections.sort(mediaObjects, new MediaComparator()); //sort the mediaOjbects ArrayList base on mediaType and numberSold
//        for (int i = 1; i < mediaObjects.size(); i++) { //update the averageRan
//            if (mediaObjects.get(i).getType() == mediaObjects.get(i - 1).getType()) { // if same type
//                if (mediaObjects.get(i).numberSold < mediaObjects.get(i - 1).numberSold) {
//                    mediaObjects.get(i).rank = mediaObjects.get(i - 1).rank + 1; //ranking increase 
//                } else {
//                    mediaObjects.get(i).rank = mediaObjects.get(i - 1).rank; //ranking remain
//                }
//            }
//        }
//    }

    
    
    
    
    
    
    
//    /**
//     *
//     * @return customers vector
//     */
//    public ArrayList<Customer> getCustomers() {
//        return customers;
//    }

    /**
     *
     * @return manager vector
     */
    public Manager getManager() {
        return manager;
    }

//    /**
//     *
//     * @return mediaObjects vector
//     */
//    public ArrayList<Media> getMedias() {
//        return mediaObjects;
//    }
}
