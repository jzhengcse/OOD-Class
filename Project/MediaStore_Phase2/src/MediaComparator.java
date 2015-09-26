import java.util.Comparator;

/**
 * a class that implements Comparator for the sorting in the MediaSotre updateAverageRanking() method
 * @author jiz5118
 *
 */
public class MediaComparator implements Comparator<Media> {

	@Override
	public int compare(Media media1, Media media2) {
		// TODO Auto-generated method stub
//		return media1.name.compareTo(media2.name);
		
//		if (media1.getType()!=media2.getType()) //first sort by type descend 
//			return media2.getType()-media1.getType();
//		else
//			return media1.name.compareTo(media2.name); //the sort by name descend
		
		
		
		// TODO Auto-generated method stub
		if (media1.getType()!=media2.getType()) //first sort by type descend 
			return media1.getType().compareTo(media2.getType());
		else
			return media2.numberSold-media1.numberSold; //the sort by numberSold descend
	}

}
