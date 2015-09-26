/**
 * Music class extends Media class
 * @author jiz5118
 *
 */
public class Music extends Media{

	private static final String type="music"; //type 1 mean album
	//private static final String mediaType="Music"; //mediaType name
	
	/**
	 * default constructor for Music class
	 */
	public Music() {
		super(); //call super default constructor to set some default data member
	}
	
	/**
	 * initial constructor for Music class
	 * @param name
	 * @param artist
	 * @param time
	 * @param genre
	 * @param price
	 */
	public Music(String name,String artist,String time,String genre,double price) {
		super(name,artist,time,genre,price); // call superclass to set name, time, genre, rank, price
	}
	

	/**
	 * 
	 * @return the type of media base on type: 1 for Music; 2 for Movie; 3 for AudioBook (doing this for sort method in MediaStore method updateAverageRanking()) 
	 */
	@Override
	public String getType() {
		return type;
	}
	
	/**
	 *
	 * @return  return a String to describe the Music Object
	 */
	@Override
	public String toString() {
		// return String of info for Music object
		String fs;
		fs=String.format("Name: %s  %nArtist: %s  %nTime: %s  %nGenre: %s  %nAverage Ranking: %d  %nPrice: %.2f  %nNumberSold: %d  %nRating: %.2f",name,author,time,genre,rank,price,numberSold,rate);
		return fs;
		
	}

    @Override
    public int getYear() {
        return 0;
    }
	
}
