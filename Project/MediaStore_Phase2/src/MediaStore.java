import java.util.Collections;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * MediaStore that contain ArrayList of Media,ArrayList of Customer and the Manager
 * @author jiz5118
 *
 */
public class MediaStore {
	 protected ArrayList<Media> mediaObjects; //contain all the media info
	 private ArrayList<Customer> customers; // contain all the customer info
	 private Manager manager; //the manager of the media store
         private int mediaID;
	 /**
	  * default constructor for MediaStore class
	  */
	public MediaStore() {
		mediaObjects=new ArrayList<Media>(); //Allocate memory 
		customers=new ArrayList<Customer>(); //Allocate memory 
		manager=new Manager("","","","");
                mediaID=0;
	}
	
	/**
	 * initial constructor for MediaStore class
	 * @param ID
	 * @param name
	 * @param address
	 * @param password
	 */
	public MediaStore(String ID,String name,String address,String password) {
		mediaObjects=new ArrayList<Media>(); //Allocate memory 
		customers=new ArrayList<Customer>(); //Allocate memory 
		manager=new Manager(ID,name,address,password); //initialize Manager
	}
	
        
	public void searchForMedia() {
		// to be implement 
	}
        
	/**
	 * display all the Media in the library
	 * @param mediaStore
	 */
	public void listAllMedias() {
		updateAverageRanking(); //sort by type and rank
		System.out.printf("\nList of all media in the Media Store: \n");
		for(int i=0; i<mediaObjects.size();i++) {
			System.out.println(mediaObjects.get(i).toString());
		}	
	}
        
        
        
         /**
	 * search whether certain media is in the library
	 * @param mediaObjects
	 * @param name
	 * @return index of media object in the ArrayList if found; other return -1 (not found)
	 */
	public int foundMedia(String name,String type) {
		
		if(mediaObjects.isEmpty()) {
			return -1; //not found
		}
		else {
			int i=0;
			while (i<mediaObjects.size()&& !(mediaObjects.get(i).name.equals(name) && mediaObjects.get(i).getType().toLowerCase().equals(type.toLowerCase())) )
				i++;
			if(i<mediaObjects.size())
				return i; // found it
			else
				return -1; //not found
		}
		
	}
        
        
        public void addMedia(Media media) {
		int foundIndex=foundMedia(media.name,media.getType());
		if (foundIndex!=-1) {//found it reactive it
                    if(mediaObjects.get(foundIndex).avaliable==false) {
                        mediaObjects.get(foundIndex).avaliable=true;
			System.out.printf("%s has been readd \n",media.name);
                        JOptionPane.showMessageDialog(null, String.format("%s has been readd \n",media.name), null, JOptionPane.WARNING_MESSAGE);
                    } else {
                        
                        System.out.printf("%s already exist \n",media.name);
                        JOptionPane.showMessageDialog(null, String.format("%s already exist \n",media.name), null, JOptionPane.WARNING_MESSAGE);

                    }
		} else { //can't find it add it
                        media.mediaID=mediaID;
                        mediaID++;
			mediaObjects.add(media);
                        
			
			// allocate memory for an Album and add it to albums array
	//		albums[Album.getNumberOfAlbum()]=new Album(artist,name,time,genre,rank,price,numberSold); 
			System.out.printf("%s: %s has been added \n",media.getType(), media.name);
		}
	}
        
        
	/**
	 * remove media from mediaObject vector if found
	 * @param mediaObjects
	 * @param name
	 */
	public void removeMedia(String name,String type) {
			int indexFound=foundMedia(name, type); 
			if(indexFound!=-1 ) { //found it and remove it from mediaObjects vector
                                if (mediaObjects.get(indexFound).avaliable==true) {
//                                 mediaObjects.remove(indexFound);
                                   mediaObjects.get(indexFound).avaliable=false; //set avaliable to false
                                   System.out.printf("%s has been removed \n", name);
                                } else {
                                   System.out.printf("%s was removed before\n", name);
                                   JOptionPane.showMessageDialog(null, String.format("%s was removed before\n", name), null, JOptionPane.WARNING_MESSAGE);

                                }

			} else { //not found
				System.out.printf("%s can't not be found in library \n", name);
                                JOptionPane.showMessageDialog(null, String.format("%s can't not be found in library \n", name), null, JOptionPane.WARNING_MESSAGE);

                        }
		
	}

	/**
	 * display total numberSold for a particular media
	 * @param mediaObjects
	 * @param name
	 */
	public int CheckTotalNumberSold(String name,String type) {
		int foundIndex=foundMedia(name,type);
		int NumberSold=0;
		if (foundIndex==-1) {//not found
			System.out.printf("Can't find %s in the library \n",name);
		} else { //found it
			System.out.printf("Total number of %s sold: %d \n" ,name,mediaObjects.get(foundIndex).getNumberSold());
			NumberSold=mediaObjects.get(foundIndex).getNumberSold();
		}
		return NumberSold;
	}
        
        
	/**
	 * display total Sale from the media store
	 * @param medisObjects
	 */
	
	public double CheckTotalSale() {
		double TotalSale=0;
		// for loops to sum up total sale for the music store
		for(int i=0; i<mediaObjects.size();i++){
			TotalSale+=mediaObjects.get(i).numberSold*mediaObjects.get(i).price;
		}

		System.out.printf("Total Sale: %.2f \n",TotalSale); //display the total sale
		return TotalSale;
	}
        
	/**
	 * search the customer in the vector
	 * @param customers
	 * @param ID
	 * @return the index of customer in the ArrayList if found; return -1 otherwise
	 */
	public int foundCustomer(String ID) {
		if(customers.isEmpty()) {
			return -1; //not found
		}
		else {
			int i=0;
			while (i<customers.size()&& !customers.get(i).ID.equals(ID))
				i++;
			if(i<customers.size()) //found it
				return i;
			else
				return -1; //not found
		}
		
	}
        
        
        
        
        
        
        
	/**
	 * add a customer to verctor
	 * @param customers
	 * @param customer
	 */
	public void addCustomer(Customer customer) {
		if(foundCustomer(customer.ID)!=-1) { // ID already exist
			System.out.printf("%s already exist \n",customer.ID);
                        JOptionPane.showMessageDialog(null, "Account with User ID \"" + customer.ID + "\" is already exist", null, JOptionPane.WARNING_MESSAGE);         

		} else {
			customers.add(customer);
			System.out.printf("You add a customer with ID: %s \n",customer.ID);
		}
	}
        
        
	/**
	 * display all the information about the customer and display purchaseHistory
	 * @param customers
	 * @param ID
	 */
	public void RetrieveCustomerInfo(String ID) {
		int foundCustomerIndex=foundCustomer(ID);
		if (foundCustomerIndex==-1) { //not found
			System.out.printf("Can't find this customer ID \n");
		} else { //found it
			// display customer info
			System.out.println(customers.get(foundCustomerIndex));
			// display purchasHistory
			customers.get(foundCustomerIndex).displayPurchaseHistory();
		}
	}
	
        
        
	/**
	 * sort the mediaOjbects ArrayList base on mediaType and numberSold and then update the averageRank
	 */
	public void updateAverageRanking() {
		Collections.sort(mediaObjects, new MediaComparator()); //sort the mediaOjbects ArrayList base on mediaType and numberSold
		for(int i=1; i<mediaObjects.size();i++) { //update the averageRan
			if (mediaObjects.get(i).getType()==mediaObjects.get(i-1).getType()) { // if same type
				if(mediaObjects.get(i).numberSold<mediaObjects.get(i-1).numberSold)
					mediaObjects.get(i).rank=mediaObjects.get(i-1).rank+1; //ranking increase 
				else
					mediaObjects.get(i).rank=mediaObjects.get(i-1).rank; //ranking remain
			}
		}
	}
	

	
	/**
	 * 
	 * @return customers vector
	 */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	/**
	 * 
	 * @return manager vector
	 */
	public Manager getManager() {
		return manager;
	}
	
	/**
	 * 
	 * @return mediaObjects vector
	 */
	public ArrayList<Media> getMedias() {
		return mediaObjects;
	}

}
