package ag.math;

public class IntArrayMath {

	public static int avg(int array[]) {
		int ans = 0, res = 0, n = array.length;
		for (int i = 0; i < n; i++) {
			ans += array[i] / n;
			res += array[i] % n;
			ans += res / n;
			res = res % n;
		}
		return ans;
	}

}
