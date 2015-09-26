
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
public class Cube extends ThreeDimensionalShape{
	
	private double edge;	//The length of any edge (inch)
	
	public Cube(double X,double Y,double Z,double edge) {
		// POST: initialize (X,Y,Z) and set name to "Cube" and its edge length
		super(X,Y,Z); // call super() initial constructor
		this.name="Cube"; //it's Cube class
		this.edge=edge; //edge of Cube
		
	}
	
	public String toString() {
		// POST: String of Cube object information has been return
		return super.toString() +" Edge: "+edge;
	}
}
