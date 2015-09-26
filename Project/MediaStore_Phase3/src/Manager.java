
/**
 * Manager class extends Account class
 * @author jiz5118
 *
 */
public class Manager extends Account{
	protected String password; // password for manager
	
	/**
	 * default constructor for Manager class
	 * @param ID
	 * @param name
	 * @param address
	 * @param password
	 */
	public Manager(String ID, String name,String address,String password) {
		super(ID,name,address); // call super class to set initial ID, name and address
		this.password=password; // set the initial password
	}

	
	
	
//	public void addMedia(ArrayList <Media> mediaObjects,String artist,String name,String time, String genre, double price) {
//		int foundIndex=foundMedia(mediaObjects,name);
//		if (foundIndex!=-1) {
//			System.out.printf("%s is already in the library \n",name);
//		} else {
//			mediaObjects.add(new Album(artist,name,time,genre,price));
//			
//			// allocate memory for an Album and add it to albums array
//	//		albums[Album.getNumberOfAlbum()]=new Album(artist,name,time,genre,averageRanking,price,numberSold); 
//			System.out.printf("%s has been added \n", name);
//		}
//	}
//	
//	public void addMovies(ArrayList <Media> mediaObjects,String name,String director,String time, String genre,int yearOfRelease, double price) {
//		// allocate memory for a Movie and add it to movies array
//		int foundIndex=foundMedia(mediaObjects,name);
//		if (foundIndex!=-1) {
//			System.out.printf("%s is already in the library \n",name);
//		} else {
//		
//			mediaObjects.add(new Movie(name,director,time,genre,yearOfRelease,price)); 
//			System.out.printf("%s has been added \n", name);
//		}
//	}
	
//	public void addAudioBooks(ArrayList <Media> mediaObjects,String name,String author,String time, String genre, double price) {
//		// allocate memory for an AudioBook and add it to audioBooks array
//		int foundIndex=foundMedia(mediaObjects,name);
//		if (foundIndex!=-1) {
//			System.out.printf("%s is already in the library \n",name);
//		} else {
//			mediaObjects.add(new AudioBook(name,author,time,genre,price)); 
//			System.out.printf("%s has been added \n", name);
//		}
//	}
	

	
	
	
	/**
	 * return String of info about Manager object
	 */
	public String toString() {
		String fs;
		fs=String.format("<br>Account ID: %s  </br>  <br>Name: %s </br> <br>Address: %s</br>", ID,name,address);
		return fs;
	}
	
	
}
