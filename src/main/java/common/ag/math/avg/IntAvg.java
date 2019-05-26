package common.ag.math.avg;

import java.util.Collection;

public class IntAvg {

	public static int avg(int[] array) {
		int ans = 0, res = 0, n = array.length;
		for (int i = 0; i < n; i++) {
			ans += array[i] / n;
			res += array[i] % n;
			ans += res / n;
			res = res % n;
		}
		return ans;
	}

	public static int avg(Collection<Integer> array) {
		int ans = 0, res = 0, n = array.size();

		for (int i : array) {
			ans += i / n;
			res += i % n;
			ans += res / n;
			res = res % n;
		}
		return ans;
	}

	public static int avg(Iterable<Integer> array) {
		int ans = 0, res = 0, n = 0;

		for (@SuppressWarnings("unused")
		int i : array)
			++n;
		for (int i : array) {
			ans += i / n;
			res += i % n;
			ans += res / n;
			res = res % n;
		}
		return ans;
	}
}
