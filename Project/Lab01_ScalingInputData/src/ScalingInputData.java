
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 1
 * Data: 1/10/2013
 * Description: Scaling input Data base on the Max of all the number.
 *
 */

import java.util.Scanner;

/**
 * This class contains one methods for accepting most 50 number and end with Ctrl+Z, 
 * it computes the max number(magnitude only) enter and Scaling all input Data base
 * on the max number.
 * 
 * @author Jie Zheng
 * @version 1.0 1/10/2013
 *
 */

public class ScalingInputData {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); 
		double [] inputNumber=new double[50]; //use to store input data 
		double [] outputNumber=new double[50]; //use to store output data 
		String label; //description of data
		int i=0;
		double max=0;
		
		//Prompt to enter the label
		System.out.print("Type a description for your data and hit Enter: ");
		label=input.nextLine();
		
	
		// Enter double numbers, end with Ctrl+Z
		System.out.println("Enter data points, separated by whitespace. Type Ctrl+Z to end input.");
		while (input.hasNext()) {
			inputNumber[i]=Math.abs(input.nextDouble()); // store the data into inputNumber[]
			if (Math.abs(inputNumber[i])>max) {
				max=Math.abs(inputNumber[i]); // compute max each time new number enter
			}
			i++;
		}

		//Display the output
		System.out.printf("%nScaled form of %s: %n",label);
		for(int index=0; index<i;index++) {
			outputNumber[index]=(Math.abs(inputNumber[index]))/max;  //store output file to outputNumber[]
			System.out.printf("%s %d: %.2f %n", label,index,outputNumber[index] ); 
		}
	}

}
