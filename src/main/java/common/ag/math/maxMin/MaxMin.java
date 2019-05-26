package common.ag.math.maxMin;

public final class MaxMin {
	
	/**
	 * 返回数组最大值
	 * 
	 * @param array		需要查询的数组
	 * @return			数组的最大值
	 */
	public static int max(int[] array) {
		if (array.length == 0)
			throw new IllegalArgumentException("No max value");
		
		int max = array[0];
		
		for (int num : array)
			if (max < num)
				max = num;
			
		return max;
	}
	
	/**
	 * 返回数组最小值
	 * 
	 * @param array		需要查询的数组
	 * @return			数组的最小值
	 */
	public static int min(int[] array) {
		if (array.length == 0)
			throw new IllegalArgumentException("No min value");
		
		int min = array[0];
		
		for (int num : array)
			if (min > num)
				min = num;
			
		return min;
	}
	
	/**
	 * 同时返回数组最大值和最小值
	 * 
	 * 结对比较，大的和最大值比较，小的和最小值比较，这样处理两个元素只需要3次比较
	 * 
	 * 而不是对于每个元素分别进行两次比较，每两个元素的比较次数减少1
	 * 
	 * 和朴素方法相比只是线性时间差异，因此差异可能不明显
	 * 
	 * @param array		需要查询的数组
	 * @return			数组的最大值和最小值的数组 [0]是最大值  [1]最小值
	 */
	public static int[] maxAndMin(int[] array) {
		if (array.length == 0)
			throw new IllegalArgumentException("No max value and max value");
		
		int max, min, from;
		
		if (array.length % 2 == 0) {//偶数个元素需要变成奇数个元素的情况，即首次处理两个元素
			if (array[0] >= array[1]) {
				max = array[0];
				min = array[1];
			} else {
				max = array[1];
				min = array[0];
			}
			from = 2;
		} else {					//对于奇数个元素的情况，除第一个元素外的每对元素进行比较，然后
			max = min = array[0];	//再和最大值和最小值比较
			from = 1;
		}
		
		for (int i = from; i < array.length; i += 2) {
			if (array[i] >= array[i + 1]) {//结对比较
				if (array[i] > max)
					max = array[i];
				if (array[i + 1] < min)
					min = array[i + 1];
			} else {
				if (array[i + 1] > max)
					max = array[i + 1];
				if (array[i] < min)
					min = array[i];
			}
		}
		
		return new int[] {
				max, min
		};
	}
	
}
