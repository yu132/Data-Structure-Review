package ag.sort;

import ds.tournamentTree.IntArrayWinnerTree;

public class WinnerSort implements IntArraySort {

	public static void sortIntArray(int[] array) {
		IntArrayWinnerTree winnerTree = new IntArrayWinnerTree(array);

		int index = array.length;

		while (--index >= 0) {
			array[index] = winnerTree.getWinner();
			winnerTree.rePlay(winnerTree.getWinnerIndex(), Integer.MIN_VALUE);
		}
	}

	@Override
	public void sort(int[] array) {
		sortIntArray(array);
	}

}
