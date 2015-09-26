/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab0
 * Data: 1/8/2013
 * Description: Accept an argument maxCount, display "Hello, World (i)" when i is even 
 * number, display "Welcome to CMPSC 221! (i)" when i is odd number. i is from 0 to maxCount.
 *
 */

import javax.swing.JOptionPane;


public class Hello {
	public static void main(String[] args) {
		
		int courseNum=221;
		int maxCount=Integer.parseInt(args[0]);
		for (int i=0;i<=maxCount;i++) {
			if(i%2==0)
				JOptionPane.showMessageDialog(null,"Hello, World ("+i+")");
			else
				JOptionPane.showMessageDialog(null,"Welcome to CMPSC "+courseNum+"! ("+i+")");
		}
		
	}
}
