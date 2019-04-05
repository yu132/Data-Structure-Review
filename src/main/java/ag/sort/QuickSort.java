package ag.sort;

import java.util.Random;

import ds.utils.MyArrays;

public final class QuickSort implements IntArraySort {

	private final static int partition(int[] array, int f, int b) {
		int temp = array[f];
		int low = f;
		int high = b - 1;

		while (low < high) {
			while (low < high && array[high] >= temp)
				--high;
			array[low] = array[high];
			while (low < high && array[low] <= temp)
				++low;
			array[high] = array[low];
		}

		array[low] = temp;

		return low;

	}

	private final static void sortIntArray(int[] array, int f, int b) {
		if (f >= b - 1)
			return;

		int mid = partition(array, f, b);
		sortIntArray(array, f, mid);
		sortIntArray(array, mid + 1, b);
	}

	public final static class RandomQuickSort implements IntArraySort {

		private final static Random RANDOM = new Random();

		private final static void sortIntArray(int[] array, int f, int b) {
			if (f >= b - 1)
				return;

			int pivot = RANDOM.nextInt(b - f) + f;

			MyArrays.swap(array, f, pivot);

			int mid = partition(array, f, b);
			sortIntArray(array, f, mid);
			sortIntArray(array, mid + 1, b);
		}

		@Override
		public void sort(int[] array) {
			sortIntArray(array, 0, array.length);
		}

	}

	@Override
	public void sort(int[] array) {
		sortIntArray(array, 0, array.length);
	}
}
