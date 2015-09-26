
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double x=.000071;
		double y=-.995;
		System.out.printf("%10.2e\n",x); System.out.printf("%10.2e\n",y);
		System.out.printf("%10.6f\n",x); System.out.printf("%10.6f\n",y);
		System.out.printf("%.6f\n",x); System.out.printf("%.6f\n",y);
		
		Scrabble b=new Scrabble("dfd",0);
		b.nextMove();
		System.out.println(b.getnumMoves());
	}

}
