package ag.sort;

import ds.priorityQueue.BinaryHeap;

public class HeapSort implements IntArraySort {

	public static void sortIntArray(int[] array) {
		BinaryHeap heap = new BinaryHeap(array);

		int index = array.length;

		while (--index >= 0)
			array[index] = heap.pop();
	}

	@Override
	public void sort(int[] array) {
		sortIntArray(array);
	}

}
