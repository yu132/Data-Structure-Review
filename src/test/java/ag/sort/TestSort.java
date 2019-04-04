package ag.sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;

public class TestSort {

	public void test(IntArraySort sorter) {

		for (int i = 0; i < 100; ++i) {
			System.out.println(i);
			int[] array = new int[100000];

			Random r = new Random();

			Arrays.setAll(array, (x) -> r.nextInt(Integer.MAX_VALUE) * (r.nextBoolean() ? 1 : -1));

			int[] copy = new int[100000];

			System.arraycopy(array, 0, copy, 0, 100000);

			sorter.sort(array);

			Arrays.sort(copy);

			Assert.assertArrayEquals(copy, array);
		}
	}

}
