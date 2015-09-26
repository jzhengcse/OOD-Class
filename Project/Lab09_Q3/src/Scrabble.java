
public class Scrabble implements Winnable{

	private String winnerName;
	private int numMoves;
	
	public Scrabble(String winnerName,int numMoves) {
		// POST:winnerName and numMovies have been initialized
		this.winnerName=winnerName;
		this.numMoves=numMoves;
	}
	
	@Override
	public void nextMove() {
		// TODO Auto-generated method stub
		numMoves++;
	}

	@Override
	public String getWinner() {
		// TODO Auto-generated method stub
		return winnerName;
	}

	public int getnumMoves() {
		return numMoves;
	}
	
}
