/**
 * This is the test driver class for Lab6_MoreWithShapes. It creates an array of shapes class, and instantiate objects of it's subclass.
 * Then use an enhance for loop to call toStirng() for each object.
 * @author Jason
 *
 */
public class Lab6Main {
	public static void main(String[] args) {
		Shape[] shapes=new Shape[4]; // shapes array
		
		shapes[0]=new ThreeDimensionalShape(0,0,1);
		shapes[1]=new Sphere(0,1,1,3);
		shapes[2]=new Cube(1,1,1,4);
		shapes[3]=new Cube(2,2,2,4);
		
		for(Shape cur:shapes) {
			System.out.println(cur.toString());
		}
		

	}

}
