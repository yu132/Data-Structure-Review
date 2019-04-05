package ag.sort;

import ds.utils.MyArrays;

public class SelectionSort implements IntArraySort {

	public static void sortIntArray(int[] array, int f, int b) {

		--b;

		while (f <= b) {
			int min = f;
			int max = f;

			for (int i = f + 1; i <= b; ++i) {
				if (array[min] > array[i])
					min = i;
				if (array[max] < array[i])
					max = i;
			}

			if (min != f)
				MyArrays.swap(array, f, min);

			if (max != f) {
				MyArrays.swap(array, b, max);
				--b;
			}

			++f;
		}
	}

	@Override
	public void sort(int[] array) {
		sortIntArray(array, 0, array.length);
	}

}
