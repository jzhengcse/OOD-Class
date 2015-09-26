
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 4
 * Data: 1/23/2013
 * Description: This program is to implement and test BankAccount class
 *
 */


import java.util.Arrays;

/**
 * This class contains contains various method for functions of Bank Account.
 * 
 * @author Jie Zheng
 * @version 1.0 1/23/2013
 *
 */
public class BankAccount
{
    private String name;      // name of account holder
    private double balance;   // how much money is in account, $
    private boolean ifSavingAccount;	//type of account
    
	/**
	 * Default Constructor
	 * 
	 */
    public BankAccount() {
       // POST: A default BankAccount object is created with the name set to a blank and
       //       and the balance set to $0.00
       this(0.0); 
    }

    /**
     * Initial Constructor
     * @param balance the balance to be set
     */
    public BankAccount(double balance) {
       // PRE:  balance >= 0.00 and is in dollars
       // POST: A BankAccount object is created with the name set to a blank
       //       and the class member balance set to balance
       name = " ";
      
       if(balance >= 0)              // validate proposed initial balance
          this.balance = balance; 
       else
          this.balance = 0; 
    }
    
    /**
     * Another constructor that allows specification of type of account
     * @param ifSavingAccount
     */
    public BankAccount (boolean ifSavingAccount) {
    	// POST: A BankAccount object is created with the name set to a blank and the class member balance set to 0 and
    	// account type set to type
    	this(0.0);
    	this.ifSavingAccount=ifSavingAccount;
    }
    
    /**
     * reset Account type and account balance
     * @param newName the name to be reset
     * @param newBalance the newBalacne to be reset
     */
    public void resetAccount(String newName, double newBalance) {
       // PRE:  newName has been assigned a value
       //       && newBalance >= 0.00 and is in dollars
       // POST: The account object is reset with the name set to newName
       //       and the balance set to newBalance
        name = newName;            // Match up private variables with parameters
        balance = newBalance;      // Could do error checking here with an if(balance >= 0)
    }

    /**
     * to withdraw fund from balance
     * @param amount the dollar to be withdraw
     * @return true if balance>=amount, return false otherwise
     */
    public boolean withdraw(double amount) {
       // PRE:  amount >= 0.00 and is in dollars
       // POST: amount is deducted from the balance stored for the account
        if (amount<=balance) { //there is enough fund to withdraw
        	balance = balance - amount;
        	return true;
        } else { // there is not enough fund to withdraw
        	return false;
        }
    }

    /**
     * get account type
     * @return account Type
     */
    public boolean getAccountType() {
    	// POST: Return the type of the account
    	return ifSavingAccount;
    }
    
    /**
     * get balance
     * @return balance
     */
    public double getBalance() {
       // POST: Returns the current balance of the account
        return balance;
    }

    /**
     * display balance with format ("You balance is currently $%.2f\n", balance)
     */
    public void displayBalance() {
       // POST: Displays the current balance of the account to the screen
        System.out.printf("Your balance is currently $%.2f\n", balance); 
    }
}