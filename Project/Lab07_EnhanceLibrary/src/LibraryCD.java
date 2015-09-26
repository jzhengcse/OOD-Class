
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 7
 * Data: 1/31/2013
 * Description: This program is to help us understand inheritance and polymorphism.
 *
 */



/**
 * This class contains various methods for simulate the behaviors of a LibraryCD, including checkOut() checkIn() toString()
 * 
 * @author Jie Zheng
 * @version 1.0 1/31/2013
 *
 */

public class LibraryCD extends LibraryObject {
	private String artist;	// artist of the CD
	private String genre;	// type of CD
	protected static double lateFees=.3;// Late fee per day in $
	
    public LibraryCD() {
    	// POST: artist and genre have been set with empty string
    	this.artist="";
    	this.genre="";
    }
	public LibraryCD(String title,String call_number,String artist,String genre) {
		// POST: Super() initial constructor have been call to set title and call_number; artist and genre names have been set.
    	super(title,call_number);
    	this.artist=artist;
    	this.genre=genre;
    }
	
	@Override
	public void checkOut() {
		// PRE: isAvail is true
		// POST: isAvail has been set to false and monthDue and dateDue are reset
        //       to 1 weeks in the future
		isAvail=false;
		resetDueDate(7);	// The CDs will be checkout for 1 week
		
	}
	
	@Override
	public String checkIn() {
		// PRE: isAvail is false
		// POST: isAvail has been set to true and a string of whether the CD is late due and the associate late fee has been return
		isAvail = true;
		if (curDate>dateDue) {
			return "This CD was returned late, the late fee is "+ lateFees*(curDate-dateDue);
		}
		else {
			return  "This CD was returned on time";
		}
	}
	
	@Override
	public String toString() {
		// POST: A String of info about the CD has been return
		return "Type: CD  Title: " + title+ "  Call Number: " + call_number+ "  Status: " +getStatus()+"  Artist: "+artist+" Genre: "+genre;
	}
	
}
