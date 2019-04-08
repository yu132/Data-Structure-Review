package ag.search;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class Bs {

	@Test
	public void test() {

		Random r = new Random();

		int[] array = new int[100000];

		Arrays.setAll(array, (i) -> r.nextInt(1000000) * (r.nextBoolean() ? -1 : 1));

		Arrays.sort(array);

		for (int i = 0; i < 1000000; ++i) {
			int num = r.nextInt(1000000);
			Assert.assertEquals(Arrays.binarySearch(array, num) >= 0,
					LinearSearch.binarySearch(array, num, 0, array.length));
		}

	}

}
