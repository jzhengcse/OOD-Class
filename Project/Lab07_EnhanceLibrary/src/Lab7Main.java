/**
 * This is the test driver class for Lab7_EnhanceLibrary. It test all the function written or modified by me
 * @author Jason
 *
 */

public class Lab7Main {

	public static void main(String[] args) {
		
		// Display before checkout
		LibraryObject[] lib=new LibraryObject[4];
		lib[0] = new LibraryBook("A Memory of Light","0765325950","Robert Jordan");
        lib[1] = new LibraryDVD("Brave ","B008YWY0HK","Family and Kids");
        lib[2] = new LibraryCD("21","B004EBT5CU","Adele","Pop");
        lib[3] = new LibraryCD("Red","B008XNZMOU","Red Taylor Swift","Pop");

        System.out.println("Initial Library: ");
        for(LibraryObject cur : lib) {           
            System.out.println(cur.getStatus());
        }

		// Display before checkout
        lib[0].checkOut();                      
        lib[1].checkOut();
        lib[2].checkOut(); 

        System.out.println("\nLibrary after check outs:");
        for(LibraryObject cur : lib) {        
            System.out.println(cur.getStatus());
        }
        
        //Check the resetMonthDate() method
		LibraryObject.resetMonthDate(9, 22);
		System.out.println(LibraryObject.getCurMonthData());
		
		// Check it out for the convenience of check in all items (in line 46-48)
		lib[3].checkOut(); 
		// Display before checkIn
		System.out.println("\nPrint information before check ins: ");
		for(LibraryObject cur:lib){
			System.out.println(cur.toString());
		}
		
		// Check in all items
		System.out.println("\nCheck ins all items: ");
		for(LibraryObject cur:lib){
			 System.out.println(cur.checkIn());
		}

		// Display after checkIn
		System.out.println("\nPrint information after check ins: ");
		for(LibraryObject cur:lib){
			System.out.println(cur.toString());
		}
	}

}
