package common.ag.allPermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.MyIntArrays;

public class AllPermutation {

	/**
	 * 打印全排列的全部情况
	 */
	public static void printAllPermutation(int[] array, int from, int to) {
		printAllPermutationHelper(array, from, from, to);
	}

	private static void printAllPermutationHelper(int[] array, int index, int from, int to) {
		if (index == array.length)
			System.out.println(MyIntArrays.toString(array, from, to));
		for (int i = index; i < array.length; ++i) {
			MyIntArrays.swap(array, index, i);

			//if(条件)
			printAllPermutationHelper(array, index + 1, from, to);

			MyIntArrays.swap(array, index, i);
		}
	}

	/**
	 * @return	全排列的全部情况
	 */
	public static List<int[]> allPermutationList(int[] array, int from, int to) {
		List<int[]> ans = new ArrayList<>(array.length * array.length);
		allPermutationHelper(array, from, from, to, ans);
		return ans;
	}

	private static void allPermutationHelper(int[] array, int index, int from, int to,
			List<int[]> ans) {
		if (index == array.length)
			ans.add(Arrays.copyOfRange(array, from, to));
		for (int i = index; i < array.length; ++i) {
			MyIntArrays.swap(array, index, i);

			//if(条件)
			allPermutationHelper(array, index + 1, from, to, ans);

			MyIntArrays.swap(array, index, i);
		}
	}

	/**
	 * @return	全排列的种类，如果不加条件，那么是n!
	 */
	public static long allPermutation(int[] array, int from, int to) {
		return allPermutationHelper(array, from, from, to);
	}

	private static long allPermutationHelper(int[] array, int index, int from, int to) {
		if (index == array.length)
			return 1;

		int count = 0;

		for (int i = index; i < array.length; ++i) {
			MyIntArrays.swap(array, index, i);

			//if(条件)
			count += allPermutationHelper(array, index + 1, from, to);

			MyIntArrays.swap(array, index, i);
		}

		return count;
	}

}
