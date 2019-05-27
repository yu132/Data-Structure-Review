package common.ag.subsequenceAndSubstring.maximumContinuousSubsequenceSumAndProduct;

public class MaximumContinuousSubsequenceSum {

	//dp的思想，就是一段子序列的和已经是负的，就需要抛弃，但是如果是正的
	//那么加上它总是比不加要大，因此需要加上
	public static int mcss(int[] array) {
		if (array.length == 0)
			return 0;

		int max = array[0];
		int now = array[0];

		for (int i = 1; i < array.length; ++i) {
			now = Math.max(now, 0) + array[i];
			max = Math.max(max, now);
		}

		return max;
	}

	//思想是区间和最大减去最小即为一段最长的连续序列和
	public static int mcss1(int[] array) {
		if (array.length == 0)
			return 0;

		int max = array[0];
		int min = array[0];
		int now = array[0];

		for (int i = 1; i < array.length; ++i) {
			now += array[i];
			min = Math.min(min, now);
			max = Math.max(max, array[i] - min);
		}

		return max;
	}



}
