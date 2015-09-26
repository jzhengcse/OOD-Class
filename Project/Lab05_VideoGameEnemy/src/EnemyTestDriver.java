/**
 * This class is the test driver for Enemy class. It has only main function and it test all the method made for BankAccount
 * 
 * @author Jie Zheng
 * @version 1.0 1/23/2013
 *
 */


public class EnemyTestDriver {

	public static void main(String[] args) {
		// Test for default constructor
		Enemy one=new Enemy();
		System.out.println(one);
		// Test for initial constructor
		Enemy two=new Enemy(95,5,1);
		Enemy three=new Enemy(100,20,2);
		System.out.println(two);
		System.out.println(three);

		// Test for hit method
		one.hit();
		System.out.println(one);
		// Test for renderedInactive
		Enemy.renderedActive();
		System.out.println("Danger of enemy one:" + one.getDanger());
		
		// Test for renderedActive
		Enemy.renderedInactive();
		System.out.println("Danger of enemy one: " +one.getDanger());

		// Test for getStrength method
		System.out.println("Current Strength of enemy one: " + one.getStrength());
		
		// Test for getDanger method
		System.out.println("The danger of enemy one poses to the player located at given vertical level: " + one.getDanger());
		Enemy.renderedActive();
		System.out.println("The danger of enemy one poses to the player located at given vertical level: " + one.getDanger());
		for (int i=0; i<8;i++)
			one.hit();
		System.out.println(one);
		System.out.println("The danger of enemy one poses to the player located at given vertical level: " + one.getDanger());

		// Test for compareStrength method
		
		System.out.println(one);
		System.out.println(two);
		System.out.println(three);
		if(two.compareStrength(one))
			System.out.println("Enemy one is dangerous to Enemy two");
		else
			System.out.println("Enemy one is not dangerous to Enemy two");
		
		if(two.compareStrength(three))
			System.out.println("Enemy three is dangerous to Enemy two");
		else
			System.out.println("Enemy three is not dangerous to Enemy two");
		
		
		// Test for toString method
		System.out.println(one);
	}

}
