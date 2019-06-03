package common.ag.math;

public final class IntMath {
	
	public static int avg(int a, int b) {
		return (a & b) + ((a ^ b) >> 1);
	}
	
	public static int avg2(int a, int b) {
		return b + (a - b) / 2;
	}
	
	
	private final static int[] sizeTable = {
			-9, -99, -999, -9999, -99999, -999999, -9999999,
			-99999999, -999999999, Integer.MIN_VALUE
	};
	
	/**
	 * 返回一个数的长度
	 * 
	 * @param num
	 * @return
	 */
	public static int length(int num) {
		if (num > 0)
			num = -num;
		for (int i = 0;; i++)
			if (num >= sizeTable[i])
				return i + 1;
	}
	
}
