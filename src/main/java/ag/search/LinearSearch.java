package ag.search;

import utils.MyIntArrays;

public final class LinearSearch {

	public static boolean normalSearch(int[] array, int target, int f, int b) {
		MyIntArrays.rangeCheck(array, f, b);
		for (int i = f; i < b; ++i)
			if (target == array[i])
				return true;
		return false;
	}

	public static boolean binarySearch(int[] array, int target, int f, int b) {
		MyIntArrays.rangeCheck(array, f, b);
		--b;
		while (f <= b) {
			int mid = (f + b) >>> 1;

			if (array[mid] < target)
				f = mid + 1;
			else if (array[mid] > target)
				b = mid - 1;
			else
				return true;
		}
		return false;
	}

}
