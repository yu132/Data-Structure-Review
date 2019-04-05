package ag.sort;

public class MergeSort implements IntArraySort {

	public static void sortIntArray(int[] array, int f, int b) {

		if (f >= b - 1)
			return;

		int mid = (f + b) >>> 1;

		sortIntArray(array, f, mid);
		sortIntArray(array, mid, b);

		int[] copy = new int[b - f];

		System.arraycopy(array, f, copy, 0, b - f);

		int i1 = 0, i2 = mid - f, i3 = f, b1 = mid - f, b2 = b - f;

		while (true) {
			if (i1 < b1 && i2 < b2)
				if (copy[i1] <= copy[i2])
					array[i3++] = copy[i1++];
				else
					array[i3++] = copy[i2++];
			else if (i1 < b1)
				array[i3++] = copy[i1++];
			else if (i2 < b2)
				array[i3++] = copy[i2++];
			else
				break;
		}
	}

	@Override
	public void sort(int[] array) {
		sortIntArray(array, 0, array.length);
	}
}
