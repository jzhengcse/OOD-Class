
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 7
 * Data: 1/31/2013
 * Description: This program is to help us understand inheritance and polymorphism.
 *
 */



/**
 * This class contains various methods for simulate the behaviors of a LibraryBook, including checkOut() checkIn() toString()
 * 
 * @author Jie Zheng
 * @version 1.0 1/31/2013
 *
 */

public class LibraryBook extends LibraryObject {
    private String author;              // author of the book
    protected static double lateFees=.2; // Late fee per day in $
    
    
    public LibraryBook() {
    	// POST: Call super default constructor and set author to empty string
    	super();
    	this.author="";
    }
    
    public LibraryBook(String title,String call_number,String author) {
		// POST: Super() initial constructor have been call to set title and call_number; author name has been set
    	super(title,call_number);
    	this.author=author;
    }
	
    @Override
    public void checkOut() {
        // PRE:  class member isAvail == true
        // POST: isAvail is set to false and monthDue and dateDue are reset
        //       to 3 weeks in the future
        isAvail = false;
        resetDueDate(21);      // book will be checked out for 3 weeks = 21 days
    }
    
	@Override
	public String checkIn() {
		// PRE: isAvail is false
		// POST: isAvail has been set to true and a string of whether the book is late due and the associate late fee has been return
		isAvail = true;
		if (curDate>dateDue) { // Test if the book is late due
			return "This book was returned late, the late fee is "+ lateFees*(curDate-dateDue);
		}
		else {
			return  "This book was returned on time";
		}
	}
	
	@Override
	public String toString() {
		// POST: A String of info about the book has been return
		return "Type: Book  Title: " + title+ "  Call Number: " + call_number+ "  Status: " +getStatus()+"  Author: "+author;
	}
    
}