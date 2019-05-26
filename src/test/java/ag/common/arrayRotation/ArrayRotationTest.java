package ag.common.arrayRotation;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import common.ag.array.arrayRotation.ArrayRotation;

public class ArrayRotationTest {

	@Test
	public void testReverse() {
		int[] case1 = {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
		};
		int[] case2 = {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
		};
		int[] case3 = {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
		};

		int[] expectation1 = {
				7, 8, 9, 10, 11, 1, 2, 3, 4, 5, 6
		};
		int[] expectation2 = {
				1, 2, 6, 7, 8, 3, 4, 5, 9, 10, 11
		};
		int[] expectation3 = {
				2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1
		};

		ArrayRotation.arrayRotation(case1, 0, case1.length, 6);
		Assert.assertArrayEquals(expectation1, case1);

		ArrayRotation.arrayRotation(case2, 2, 6, 3);
		System.out.println(Arrays.toString(case2));
		Assert.assertArrayEquals(expectation2, case2);

		ArrayRotation.arrayRotation(case3, 0, case3.length, 1);
		Assert.assertArrayEquals(expectation3, case3);
	}

}
