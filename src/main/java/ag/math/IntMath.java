package ag.math;

public class IntMath {

	public static int avg(int a, int b) {
		return (a & b) + ((a ^ b) >> 1);
	}

	public static int avg2(int a, int b) {
		return b + (a - b) / 2;
	}

}
