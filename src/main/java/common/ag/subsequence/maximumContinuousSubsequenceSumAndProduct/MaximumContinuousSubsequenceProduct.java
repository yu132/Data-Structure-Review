package common.ag.subsequence.maximumContinuousSubsequenceSumAndProduct;

public class MaximumContinuousSubsequenceProduct {

	//dp,和最大子序列和不同的是保留两个最大值，一个是正的最大值，一个是负的最大值
	//这样使得每次遇到负数的时候，那么原来负的数就会变成正数

	//如果初始化的值是正数，那么对于接下来每一个正数，
	//maxNow为这些正数的积，minNow为最后出现的一个正数，
	//当遇到负数的时候，maxNow和minNow互换
	//然后maxNow为这个负数，minNow为所有序列的乘积（负的）
	//然后乘下一个正数，maxNow会是这个正数，minNow依然为所有的乘积
	//然后现在继续乘正数，maxNow是从截断处的最大乘积，minNow依然为所有的乘积
	//然后乘负数，maxNow和minNow互换
	//现在maxNow为所有的总乘积，因为乘了两个负数，所以是正的
	//minNow是从截断处的总乘积
	//如果遇到0，则maxNow和minNow都会变成0
	public static int mcsp(int[] array) {
		if (array.length == 0)
			return 0;

		int maxNow = array[0];
		int minNow = array[0];
		int max = array[0];

		for (int i = 1; i < array.length; ++i) {
			if (array[i] < 0) {
				int temp = maxNow;
				maxNow = minNow;
				minNow = temp;
			}
			maxNow = Math.max(maxNow * array[i], array[i]);
			minNow = Math.min(minNow * array[i], array[i]);

			max = Math.max(maxNow, max);
		}

		return max;
	}

}
