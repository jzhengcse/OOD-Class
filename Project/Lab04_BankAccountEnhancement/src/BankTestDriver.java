/**
 * This class is the test driver for BankAccount class. It has only main function and it test the improvement made for BankAccount
 * 
 * @author Jie Zheng
 * @version 1.0 1/23/2013
 *
 */


public class BankTestDriver {
	public static void main(String[] args) {
		// Test for initial constructor with specification of account type
		BankAccount accountOne=new BankAccount(true); 
		
		// Test accessor of account type
		if(accountOne.getAccountType())
			System.out.println("Account one is a saving account");
		else
			System.out.println("Account one is a checking account");
		
		// Test for resetAccount method
		accountOne.resetAccount("Jason", 500); 
		accountOne.displayBalance();
		
		
		// Test withdraw method with withdraw amount greater than balance
		System.out.println("\nTrying to withdraw $600 from a account with $500 balance");
		if (accountOne.withdraw(600))
			System.out.printf("withdraw secceed\n");
		else 
			System.out.printf("withdraw fail\n");
		accountOne.displayBalance();
		
		// Test withdraw method with withdraw amount less than balance
		System.out.println("\nTrying to withdraw $400 from a account with $500 balance");
		if (accountOne.withdraw(400))
			System.out.printf("withdraw succeed\n");
		else 
			System.out.printf("withdraw fail\n");
		
		accountOne.displayBalance();
		
	}
}
