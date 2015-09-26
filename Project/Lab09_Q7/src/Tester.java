
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object [] k=new Object[3];
		k[0]=new String("this is a String");
		k[1]=new Integer(1);
		k[2]=new Double(1.2);
		for(Object num: k) {
			 System.out.println(num.toString());
		}
	}

}
