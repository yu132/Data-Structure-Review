package common.ag.math;

public final class DoubleMath {
	
	public final static boolean equals(double a, double b, double delta) {
		if (Double.isNaN(a) || Double.isNaN(b) || Double.isInfinite(a)
				|| Double.isInfinite(b))
			return false;
		return Math.abs(a - b) < delta;
	}
	
	/**
	 * 2表示不知道
	 * 1表示大于
	 * 0表示等于
	 * -1表示小于
	 * 
	 * @param a
	 * @param b
	 * @param delta
	 * @return
	 */
	public final static int compare(double a, double b, double delta) {
		if (Double.isNaN(a) || Double.isNaN(b))
			return 2;
		
		if (Double.POSITIVE_INFINITY == a && Double.POSITIVE_INFINITY != b)
			return 1;
		if (Double.POSITIVE_INFINITY != a && Double.POSITIVE_INFINITY == b)
			return -1;
		
		if (Double.NEGATIVE_INFINITY == a && Double.NEGATIVE_INFINITY != b)
			return -1;
		if (Double.NEGATIVE_INFINITY != a && Double.NEGATIVE_INFINITY == b)
			return 1;
		
		double nowDelta = a - b;
		
		if (Math.abs(nowDelta) < delta)
			return 0;
		
		return nowDelta > 0 ? 1 : -1;
	}
	
}
