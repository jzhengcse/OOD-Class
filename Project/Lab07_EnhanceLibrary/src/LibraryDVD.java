
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 7
 * Data: 1/31/2013
 * Description: This program is to help us understand inheritance and polymorphism.
 *
 */



/**
 * This class contains various methods for simulate the behaviors of a LibraryDVD, including checkOut() checkIn() toString()
 * 
 * @author Jie Zheng
 * @version 1.0 1/31/2013
 *
 */

public class LibraryDVD extends LibraryObject {
    private String genre;       // type of DVD, e.g., "movie," "educational"
    protected static double lateFees=.5; // Late fee per day in $
    
    public LibraryDVD() {
    	// POST: Super() default constructor has been called; genre name has been set to empty string
    	super();
    	this.genre="";
    }
    
    public LibraryDVD(String title,String call_number,String genre) {
    	// POST: Super initial constructor has been called to set title and call_number;genre name has been set
    	super(title,call_number);
    	this.genre=genre;
    }
    
    
    @Override
    public void checkOut() {
        // PRE:  class member isAvail == true
        // POST: isAvail is set to false and monthDue and dateDue are reset
        //       to 5 days in the future
        isAvail = false;
        resetDueDate(5);        // DVD is due 5 days from now
    }

	@Override
	public String checkIn() {
		// PRE: isAvail is false
		// POST: isAvail has been set to true and a string of whether the DVD is late due and the associate late fee has been return
		isAvail = true;
		if (curDate>dateDue) {
			return "This DVD was returned late, the late fee is "+ lateFees*(curDate-dateDue);
		}
		else {
			return  "This DVD was returned on time";
		}
	}
	
	@Override
	public String toString() {
		// POST: A String of info about the DVD has been return
		return "Type: DVD  Title: " + title+ "  Call Number: " + call_number+ "  Status: " +getStatus()+"  Genre: "+genre;
	}
	
}