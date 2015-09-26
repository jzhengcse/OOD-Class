import javax.swing.JFrame;


public class DrawingPolygonsDriver {
 
     public static void main(String [] args)
      {


          JFrame application = new JFrame("Application");
          DrawingPolygons theApplet = new DrawingPolygons();
          theApplet.init();   // invoke the applet's init() method
          theApplet.start();  // starts the applet
          application.add(theApplet);

          application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set frame to exit when it is closed
          application.setSize(500, 400);         // window is 500 pixels wide, 400 high
          application.setVisible(true);   
          
        }
 
}