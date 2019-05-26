package utils;

public class MyIntArrays {

	public static void rangeCheck(int[] array, int frontRange, int backRange) {
		if (frontRange < 0 || backRange > array.length || frontRange > backRange)
			throw new IllegalArgumentException("Range is incorrect");
	}

	public static int sumRange(int[] array, int from, int to) {
		rangeCheck(array, from, to);

		int sum = 0;
		for (int i = from; i < to; ++i)
			sum += array[i];
		return sum;
	}

	public static int productRange(int[] array, int from, int to) {
		rangeCheck(array, from, to);

		int sum = 1;
		for (int i = from; i < to; ++i)
			sum *= array[i];
		return sum;
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

	public static String toString(int[] array, int from, int to) {
		rangeCheck(array, from, to);

		StringBuilder sb = new StringBuilder(array.length * 5);

		sb.append("[");

		if (from == to) {
			sb.append("]");
			return sb.toString();
		}

		sb.append(array[from]);

		for (int i = from + 1; i < to; ++i) {
			sb.append(",");
			sb.append(array[i]);
		}

		sb.append("]");

		return sb.toString();
	}

	public static void printArray(int[] array, int from, int to) {
		System.out.println(toString(array, from, to));
	}

}
