/**
 * AudioBook class extends Media class
 * @author jiz5118
 *
 */
public class AudioBook extends Media{
	private static final String type="audioBook"; //type 3 stands for AudioBook 
//	private static final String mediaType="Audio Book"; //mediaType of audio book

	/**
	 * default constructor for AudioBook class
	 */
	public AudioBook() {
		super(); //call super default constructor to set some default data member

	}
	
	/**
	 * initial constructor for AudioBook class
	 * @param name
	 * @param author
	 * @param time
	 * @param genre
	 * @param price
	 */
	public AudioBook(String name,String author,String time,String genre,double price) {
		super(name,author,time,genre,price); // call superclass to set name, time, genre, rank, price

	}

	/**
	 * 
	 * @return the type of media base on type: 1 for Album; 2 for Movie; 3 for AudioBook (doing this for sort method in MediaStore method updateAverageRanking()) 
	 */

	@Override
	public String getType() {
		return type;
	}
	
	/**
	 *
	 * @return  return a String to describe the AudioBook object; 
	 */
	@Override
	public String toString() {
		String fs;
		fs=String.format("Name: %s  %nAuthor: %s  %nTime: %s  %nGenre: %s  %nAverage Ranking: %d  %nPrice: %.2f  %nNumberSold: %d  %nRating: %.2f", name,author,time,genre,rank,price,numberSold,rate);
//		fs=String.format("Media Type: Audio Book  Name: %s  Author: %s  Time: %s  Genre: %s  Average Ranking: %d  Price: %.2f  NumberSold: %d  Rating: %.2f", name,author,time,genre,rank,price,numberSold,rate);
		return fs;
		
	}

    @Override
    public int getYear() {
        return 0;
    }

}
