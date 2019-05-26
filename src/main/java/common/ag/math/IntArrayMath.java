package common.ag.math;

public final class IntArrayMath {

	public static int avgLess(int[] array) {
		int ans = 0, res = 0, n = array.length;

		for (int i = 0; i < array.length; ++i) {

			ans += array[i] / n;

			int mod = array[i] % n;

			int temp = res + mod;
			if (temp < 0) {
				ans += res / n;
				res = mod + res % n;
			} else
				res = temp;
		}

		return ans;
	}

}
