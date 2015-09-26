
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 6
 * Data: 1/31/2013
 * Description: This program is to help understand inheritance
 *
 */


/**
 * This class contains a initial constructor and a toString() method
 * 
 * @author Jie Zheng
 * @version 1.0 1/31/2013
 *
 */
public class Sphere extends ThreeDimensionalShape{
	
	private double radius; //radius of sphere (inch)
	
	public Sphere(double X,double Y, double Z,double radius) {
		// POST:Center of mass coordinate has been set to (X,Y,Z); name set to "Sphere";radius set to radius
		super(X,Y,Z); //call superclass constructor to set the center of mass
		this.name="Sphere";
		this.radius=radius;
	}

	public String toString() {
		// POST: String of informations about Sphere have been return
		return super.toString() + " Radius: "+radius;
	}
}
