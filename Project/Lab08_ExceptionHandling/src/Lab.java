
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 8
 * Data: 2/5/2013
 * Description: This program is to help us understand exception
 *
 */



/**
 * This class contains only main class
 * 
 * @author Jie Zheng
 * @version 1.0 2/5/2013
 *
 */


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException; 

public class Lab {
   public static final int MAX_SIZE = 5;         // Max number of data points to store

   public static void main(String[] args) {
	   try {
    	  double[] data;                      // Numbers read from file
    	  int logSize;                        // Logical size of data  
    	  Scanner input;                      // Object for reading from file

    	  data = new double[MAX_SIZE]; 
    	  logSize = 0;
    	  input = new Scanner(new File("input.txt"));
      
    	  while(input.hasNext()) {                 // Read from the file
    		  data[logSize] = input.nextDouble(); 

    		  System.out.println(data[logSize]);
    		  logSize++; 
    	  }
	   }
	   catch (FileNotFoundException e) {
  		// TODO Auto-generated catch block
		   System.out.println("File was not found.");
	   }
	   catch(ArrayIndexOutOfBoundsException aioobe) {
		   System.out.println("You've entered an invalid array index.");
		   System.out.println("Please choose indices between 0 and " + (MAX_SIZE-1));
	   }
	   
	   catch(InputMismatchException ime) {
		   System.out.println("You must enter double number"); 
	   }
	   
	   catch(Exception e) {
		   System.out.println("An unknown error occurred."); 
	   }
   }   
}
