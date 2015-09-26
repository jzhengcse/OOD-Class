
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 2
 * Data: 1/19/2013
 * Description: This program generate 20 random number and display it before and after sorting.
 *
 */


import java.util.Arrays;

/**
 * This class contains one methods for generating two normal random numbers and store them
 *  in the array at the index specified and the next index.
 * 
 * @author Jie Zheng
 * @version 1.0 1/10/2013
 *
 */

public class RandomNumbers {

	private static final int NUMOFRANDOM=20;  //total number of normally-distributed random number
	private static double[] numberArray=new double[NUMOFRANDOM];  // used to store normally-distributed random number.
	
	/**
	 * Generate two normally distributed random numbers and store them in the data array at the index
	 * specified and the next index.
	 * 
	 * @param data array: is used to store generated normally distributed random number.
	 * @param index: the location to store random number and next random number
	 */
	public static void ShiftRandomNumber(double [] data, int index) {
		// PRE: Array that can hold 20 double number; index>=0
		// POST: Store normally distributed random numbers in the data at the index specified and the next index
		double u1=Math.random(); // make Java generate an uniformly distributed random number
		double u2 = Math.random(); // make Java generate another uniformly distributed random number
		
		double rSquare=-2*Math.log(u1); //formula given in the specification to generate normal-distributed random number.
		double angel=2*Math.PI*u2; //formula given in the specification to generate normal-distributed random number.
		
		data[index]=Math.sqrt(rSquare)*Math.cos(angel); // store first normal-distributed random number to index
		data[index+1]=Math.sqrt(rSquare)*Math.sin(angel); // store second normal-distributed random number to next index
	}
	
	
	public static void main(String[] args) {
		
		// Calling class method ShiftRandomNumber in the main
		for (int i=0; i<NUMOFRANDOM;i=i+2) {
			ShiftRandomNumber(numberArray,i);
		}
		
		// Display random number before sorting
		System.out.printf("Display numbers before sorting: %n%n");		
		for (double num: numberArray) {
			System.out.println(num);
		}
		
		// Sort the array
		Arrays.sort(numberArray);
	
		// Display random number after sorting
		System.out.printf("%n%nDisplay numbers after sorting: %n%n");
		for (double num: numberArray) {
			System.out.println(num);
		}
	}
}
		
