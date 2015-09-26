
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 4
 * Data: 1/23/2013
 * Description: This program is to implement an Enemy class and write a separate test driver class
 *
 */


import java.util.Arrays;

/**
 * This class contains various methods for simulate the behaviors of a Video Game Enemy and write a separate test driver class
 * 
 * @author Jie Zheng
 * @version 1.0 1/23/2013
 *
 */
public class Enemy {
	private int strength; // strength is enemy range from 0 to 100
	private final int strengthLosePerHit; // strength to be subtract when enemy is hit
	private static int dangerousThreshold=15; // threshold to be consider as dangerous
	private int verticalLevel; //vertical level of enemy
	private static boolean ifActive=true; //status of enemy class active or inactive
	
	
	/**
	 * default constructor
	 */
	public Enemy() {
		// POST:An Enemy object create with all the default data; 
		strength=100;
		strengthLosePerHit=10;
		verticalLevel=0;
	
	}
	
	/**
	 * initial constructor initial with three parameters below
	 * @param strength
	 * @param strengthLosePerHit
	 * @param verticalLevel
	 */
	public Enemy(int strength,int strengthLosePerHit, int verticalLevel) {
		// POST: initialize Enemy with strength,strengthLosePerHit and verticalLevel
		this.strength=strength;
		this.strengthLosePerHit=strengthLosePerHit;
		this.verticalLevel=verticalLevel;
	
	}
	
	/**
	 * simulate when enemy be hit. strength decrease by strengthLosePerHit when strength=0 when strength is less than strengthLosePerHit
	 */
	public void hit() {
		// POST: enemy's strength decrease by strengthLosePerHit or enemy die
		if(strength>strengthLosePerHit)
			strength=strength-strengthLosePerHit;
		else {
			strength=0;
		}
			
	}
	
	/**
	 * set all enemies to be active
	 */
	public static void renderedInactive() {
		// POST: ifActive will be false
		ifActive=false;
	}
	
	/**
	 * set all enemies to be inactive
	 */
	public static void renderedActive() {
		// POST: ifActive will be true
		ifActive=true;
	}
	
	/**
	 * get strength
	 * @return strength
	 */
	public int getStrength() {
		// POST: return the strength of enemy
		return strength;
	}
	
	/**
	 * method to compare two enemies to see if Enemy B is dangerous to current Enemy
	 * @param B
	 * @return return true when Enemy B is stronger than A; return false otherwise
	 */
	public boolean compareStrength(Enemy B) {
		// POST: return true when Enemy B is stronger than A; return false otherwise
		if(B.strength>this.strength)
			return true;
		else
			return false;
	}
	
	/**
	 * get danger
	 * @return return enemy's strength if enemy is active and its strength is at least 15; return 0 otherwise
	 */
	public int getDanger() {
		// POST: return enemy's strength if enemy is active and its strength is at least 15; return 0 otherwise
		if(ifActive&&(strength>=dangerousThreshold))
			return strength;
		else
			return 0;
	}
	
	/**
	 * to display information about the class
	 */
	public String toString() {
		// POST: return String contain strength, strengthLosePerHit, verticalLevel
		return "Strength:" + strength +" strengthLosePerHit: "+strengthLosePerHit+" verticalLevel: "+verticalLevel+" strengthLosePerHit:"+strengthLosePerHit+" ifActive:"+ifActive;
	}
}
