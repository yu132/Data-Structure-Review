package common.ag.array.arrayRotation;

import utils.MyIntArrays;

public class ArrayRotation {

	public static void arrayRotation(int[] array, int from, int length, int k) {
		MyIntArrays.reverse(array, from, k);
		MyIntArrays.reverse(array, from + k, length - k);
		MyIntArrays.reverse(array, from, length);
	}

}
