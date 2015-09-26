

import java.util.*;

/**
 * Media class has variety methods to simulate media  
 * @author jiz5118
 *
 */
public abstract class Media{
        protected int mediaID;
	protected String name; // name of media
	protected String author; // artist for album;director for movie;author for audio book
	protected String time; // duration of media
	protected String genre; //Genre of media
	protected int rank; //ranking of media
	protected double price; // price of media
	protected int numberSold; // number sold
	protected double rate; //average feedback from all buyers
	protected ArrayList<Integer> feedBack; //feedback from all buyers
        protected ArrayList<String> feedBackUserID;
        protected boolean avaliable;
	
	public Media() {
//                mediaID=0;
		name="";
		author="";
		time="";
		genre="";
		rank=1;
		price=0;
		numberSold=0;
		rate=0;
		feedBack=new ArrayList<Integer>();
                feedBackUserID=new ArrayList<String>();
                avaliable=true;
	}
	/**
	 * initial constructor for Media Object
	 * @param name
	 * @param author
	 * @param time
	 * @param genre
	 * @param price
	 */
	public Media(String name,String author,String time,String genre,double price) {
		this.name=name; // set name of media
		this.author=author; // set author of media
		this.time=time; // set time of media
		this.genre=genre; // set genre of media
		this.rank=1; //average ranking of media
		this.price=price; // set price of media
		this.numberSold=0; //set number of sold for media
		this.rate=0; // set rate
		feedBack=new ArrayList<Integer>(); // allocate memory for feedBack ArrayList
                feedBackUserID=new ArrayList<String>();
                avaliable=true;
	}
	

	/**
	 * 
	 * @return numberSold
	 */
	public int getNumberSold() {
		return numberSold; // Accessor to get numberSold
	}
	
	/**
	 * computer the rate by sumOfFeedBak/numberOfFeedBack
	 */
	public void updateRating() {
		double sum=0;
		for (int i=0; i<feedBack.size();i++) {
			sum+=feedBack.get(i); //loop over the vector and get sum of all rate
		}
		rate=sum/(double)(feedBack.size()); //get the average of rate
	}
	
	/**
	 * abstract method to be overridden
	 * @return the type of media base on type: 1 for Album; 2 for Movie; 3 for AudioBook (doing this for sort method in MediaStore method updateAverageRanking()) 
	 */

	public abstract String getType();
        
        public abstract int getYear();
	
	/**
	 * abstract method to be overridden
	 * @return  return a String to describe the object; to be implement in subclass 
	 */
	public abstract String toString();

}
