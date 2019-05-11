package ag.common.longestIncreasingSubsequence;

import ag.search.BinarySearchBound;

public class LongestIncreasingSubsequence {

	/**
	 * 最长上升子序列
	 * 
	 * @param array		需要寻找最长上升子序列的数组
	 * @return 			最长上升子序列的长度
	 */
	public static int lIS(int[] array) {

		if (array.length == 0)
			return 0;

		int[] stack = new int[array.length];
		stack[0] = array[0];

		int len = 1;

		for (int i = 0; i < array.length; ++i)
			if (stack[len - 1] < array[i])
				stack[len++] = array[i];
			else
				stack[BinarySearchBound.lowerBound(stack, 0, len, array[i])] = array[i];

		return len;
	}

}
