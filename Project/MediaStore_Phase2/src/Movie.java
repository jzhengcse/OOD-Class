/**
 * Movie class extends Media class
 * @author jiz5118
 *
 */
public class Movie extends Media{

	private int yearOfRelease; // year of release of the movie
	private static final String type="movie"; //type 2 mean movie 
//	private static final String mediaType="Movie"; //mediaType of Movie
	
	/**
	 * default constructor for Movie class
	 */
	public Movie() {
		super(); //call super default constructor to set some default data member

		this.yearOfRelease=0; //set yearOfRelease to 0
	}
	
	/**
	 * initial constructor for Movie class
	 * @param name
	 * @param director
	 * @param time
	 * @param genre
	 * @param yearOfRelease
	 * @param price
	 */
	public Movie(String name,String director,String time,String genre,int yearOfRelease,double price) {
		super(name,director,time,genre,price); // call superclass to set name, time, genre, rank, price
		this.yearOfRelease=yearOfRelease; //set yearOfRelease
	}
	
	
	/**
	 * 
	 * @return the type of media base on type: 1 for Album; 2 for Movie; 3 for AudioBook (doing this for sort method in MediaStore method updateAverageRanking()) 
	 */
	@Override
	public String getType() {
		return type;
	}
	
//
//        public int getYear() {
//		return yearOfRelease;
//	}
	/**
	 *
	 * @return  return a String to describe the Movie Object
	 */
	@Override
	public String toString() {
		String fs;
		fs=String.format("Name: %s  %nDirector: %s  %nTime: %s  %nGenre: %s  %nAverage Ranking: %d  %nPrice: %.2f %nNumberSold: %d  %nRating: %.2f  %nYear of release: %d",name,author,time,genre,rank,price,numberSold,rate,yearOfRelease);
		return fs;
		
	}

    @Override
    public int getYear() {
        return yearOfRelease;
    }

}
