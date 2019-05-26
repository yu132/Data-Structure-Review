package util;

import org.junit.Assert;
import org.junit.Test;

import utils.MyIntArrays;

public class MyIntArrayTest {

	@Test
	public void testReverse() {
		int[] case1 = {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
		};
		int[] case2 = {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
		};
		int[] case3 = {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
		};


		int[] expectation1 = {
				11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
		};
		int[] expectation2 = {
				1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 11
		};

		int[] expectation3 = {
				12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
		};
		MyIntArrays.reverse(case1, 0, case1.length);
		Assert.assertArrayEquals(expectation1, case1);

		MyIntArrays.reverse(case2, 1, case1.length - 2);
		Assert.assertArrayEquals(expectation2, case2);

		MyIntArrays.reverse(case3, 0, case3.length);
		Assert.assertArrayEquals(expectation3, case3);
	}

	@Test
	public void testPrint() {
		int[] array = {
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		};
		MyIntArrays.printArray(array, 1, 7);
	}

}
