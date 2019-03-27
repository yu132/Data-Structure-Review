package ds.tournamentTree;

public interface IntWinnerTree {
	
	int size();

	int getWinner();
	
	int getWinnerIndex();
	
	void rePlay(int index,int score);
	
}
