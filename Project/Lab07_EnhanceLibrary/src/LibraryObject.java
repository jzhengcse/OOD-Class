
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 7
 * Data: 1/31/2013
 * Description: This program is to help us understand inheritance and polymorphism.
 *
 */



/**
 * This class contains various methods for simulate the behaviors of a LibraryObject, including checkOut() checkIn() toString()
 * 
 * @author Jie Zheng
 * @version 1.0 1/31/2013
 *
 */
public abstract class LibraryObject {
    protected static int curMonth = 9;   // current month number
    protected static int curDate = 1;    // current day number

    protected String title;              // title of object
    protected String call_number;        // call number of object in library system
    protected boolean isAvail;           // availability indicator: true when object is 
                                         //   not checked out, false when it is
    protected int monthDue;              // number of month object is due back
    protected int dateDue;               // date of monthDue when object is due back

    
    
    public LibraryObject() {
        // POST: new LibraryObject constructed such that 
        //       title and call_number are set to blank strings and isAvail is true
        title = "";
        call_number = "";
        isAvail = true;
    }

    public LibraryObject(String title,String call_number) {
        this.title =title;
        this.call_number = call_number;
        this.isAvail = true;
 
    }
    
    public abstract void checkOut();
        // PRE:  class member isAvail == true
        // POST: isAvail is set to false and monthDue and dateDue are reset
        //       according to what type of item this is

    public abstract String checkIn();
    	// PRE: class member isAvail == false;
    	// POST: isAvail is set to true
    
    
    public static String getCurMonthData() {
    	return "Current Month and Date: " + curMonth+ "/"+curDate;
    }
    public static void resetMonthDate(int Month, int Date) {
    	curMonth=Month;
    	curDate=Date;
    }
    public void resetDueDate(int numDays) {
        // PRE:  numDays &gt; 0
        // POST: monthDue and dateDue are adjusted numDays into the future
        monthDue = curMonth; 
        dateDue = curDate + numDays;   // lazy implementation -- can you fix it?
    }

    public String getStatus() {
        // POST: FCTVAL == string stating "Available" if item is available or
        //                 "Due Back [month]/[date]" is item is checked out
        if(isAvail) {                    // item is not checked out
            return "Available";
        }
        else {                           // item is checked out
            return "Due Back " + monthDue + "/" + dateDue;
        }
    }
    
    public abstract String toString();
    	// POST: return a String to describe the object
    
    
}