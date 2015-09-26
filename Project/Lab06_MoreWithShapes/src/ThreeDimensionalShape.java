
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

public class ThreeDimensionalShape extends Shape{
	protected double xLoc;	//x coordinate of the center (inch)
	protected double yLoc;	//y coordinate of the center (inch)
	protected double zLoc;	//z coordinate of the center (inch)
	

	public ThreeDimensionalShape(double xLoc,double yLoc,double zLoc) {
		// POST: A default Shape is created with name set to an empty string
		this.name = "Three Dimensionl Shape";
		this.xLoc=xLoc;
		this.yLoc=yLoc;
		this.zLoc=zLoc;
	}
	
	public String toString() {
		// POST: String of info about this class object has been return
		return super.toString() + " with center of mass X: "+xLoc+" Y: "+yLoc+" Z: " +zLoc;
	}
}
