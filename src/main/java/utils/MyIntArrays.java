package utils;

public class MyIntArrays {

	public static void rangeCheck(int[] array, int frontRange, int backRange) {
		if (frontRange < 0 || backRange > array.length || frontRange > backRange)
			throw new IllegalArgumentException("Range is incorrect");
	}

	public static void swap(int[] array, int x, int y) {
		int num = array[x];
		array[x] = array[y];
		array[y] = num;
	}

	public static void reverse(int[] array, int from, int length) {
		for (int to = from + length - 1, mid = from + (length >> 1); from < mid; ++from, --to)
			swap(array, from, to);
	}

}
