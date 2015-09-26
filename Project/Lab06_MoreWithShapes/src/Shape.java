
/**
 * Name: Jie Zheng
 * Section: 1
 * Program: Lab 6
 * Data: 1/31/2013
 * Description: This program is to help understand inheritance
 *
 */


import java.lang.String; // import String library


/**
 * This class is the super class for ThreeDimensionalShap, Sphere and Cube class.
 * 
 * @author Jie Zheng
 * @version 1.0 1/31/2013
 *
 */
public class Shape {
   protected String name;           // user-chosen name for the shape

   
   public Shape() {
      // POST: A default Shape is created with name set to an empty string
      name = "";
   }

   public void SetName(String name) {
      // PRE:  Assigned(name)
      // POST: class member name has been set to name
      this.name = name;
   }

   public String toString() {
      // POST: FCTVAL == String representation of Shape object
      return "Shape " + name;
   }
}
