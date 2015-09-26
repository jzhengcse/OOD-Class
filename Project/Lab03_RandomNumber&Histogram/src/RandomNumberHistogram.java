
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 3
 * Data: 1/19/2013
 * Description: This program is an extension of Lab2 and it generate a histogram for the normally-distributed random numbers.
 *
 */


import java.util.Arrays;

/**
 * This class contains one method for generating two normal-distributed random numbers and store them
 *  in the array at the index specified and the next index, another method for generating histogram
 *  base on the frequency of different range of normal-distributed random.
 * 
 * @author Jie Zheng
 * @version 1.0 1/19/2013
 *
 */

public class RandomNumberHistogram {

	private static final int NUMOFRANDOM=300;  //total number of normal-distributed random numbers
	private static final int FREQUENCYRANGE=24; //frequency ranges of normal-distributed random numbers
	private static final double LOWERBOUND=-3; //Lower bound of the ranges
	private static final double HIGHERBOUND=3;  //higher bound of the range
	private static final double INCREMENT=0.25; //increment of the ranges
	
	private static double[] numberArray=new double[NUMOFRANDOM];  // used to store normally distributed random number.
	private static int[] frequencyArray=new int[FREQUENCYRANGE]; // used to store the frequency
	private static double[] lowerBoundArray=new double[FREQUENCYRANGE]; //used to store the lower bound of eatch range
	
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
		double u1=Math.random(); //generate a random number
		double u2 = Math.random(); //generate an other random number
		
		double rSquare=-2*Math.log(u1); //formula given in the specification to generate normal-distributed random number.
		double angel=2*Math.PI*u2;	//formula given in the specification to generate normal-distributed random number.
		
		data[index]=Math.sqrt(rSquare)*Math.cos(angel); // store first normal-distributed random number to index
		data[index+1]=Math.sqrt(rSquare)*Math.sin(angel); // store second normal-distributed random number to next index
	}
	
	/**
	 * Generate two normally distributed random numbers and store them in the data array at the index
	 * specified and the next index.
	 * 
	 * @param data array: is used to store generated normally distributed random number.
	 * @param index: the location to store random number and next random number
	 */
	
	public static void GenerateHistogram (double [] data) {
		// PRE: Array of normal-distributed random number
		// POST: compute the frequency table and display the histogram
		
		//Filling the lower bound array
		lowerBoundArray[0]=LOWERBOUND; //set the first lower bound
		int i=0;
		while(i<FREQUENCYRANGE-1) { // dynamically filling the rest of the lower bound
			lowerBoundArray[i+1]=lowerBoundArray[i]+INCREMENT;
			i++;
		}
		
		Arrays.fill(frequencyArray, 0);  //zero frequency array (I found it not necessary, it automatically set to zero when I allocate it in line 34)
		
		//This loop is used to compute the frequency of each category
		for (int index=0; index<NUMOFRANDOM;index++) { 
			if(data[index]>=LOWERBOUND && data[index]<=HIGHERBOUND) { //discard the number that is out of bound [-3,3] 
				if(data[index]==HIGHERBOUND) //3 does not fit in the formula in else statement (if data[index]=3 we will have out data[24]++, which is out of range)
					frequencyArray[(int)((HIGHERBOUND+HIGHERBOUND)/INCREMENT-1)]++;
				else
					frequencyArray[(int)((data[index]+HIGHERBOUND)/INCREMENT)]++; // used this formula to compute frequency in each range
			}
		}
		
		// Display the Histogram
		System.out.printf("%nHISTOGRAM: %n");
		
		for (int index=0;index<FREQUENCYRANGE;index++) { //This loop is used to display the whole histogram, following the specifications
			System.out.printf("%5.2f  to  %5.2f:  ",lowerBoundArray[index],lowerBoundArray[index]+INCREMENT);
			for (int j=0; j<frequencyArray[index];j++) { // display the stars *
				System.out.print("*");
			}
			System.out.printf("(%d) \n",frequencyArray[index]); //display the frequency fro each categroy in parentheses
		}		
		
	}
	
	public static void main(String[] args) {
		
		// Calling class method ShiftRandomNumber in main method
		for (int i=0; i<NUMOFRANDOM;i=i+2) {
			ShiftRandomNumber(numberArray,i);
		}
		
		
		// Sort the array
		Arrays.sort(numberArray);
	
		
		// Display random number after sorting
		System.out.printf("Display numbers after sorting: %n%n");
		for (double num: numberArray) {
			System.out.printf("%6.2f\t",num);
		}
		System.out.printf("\n");
		
		// call the GenerateHistogram in main method
		GenerateHistogram(numberArray);
		
	}
}
		
