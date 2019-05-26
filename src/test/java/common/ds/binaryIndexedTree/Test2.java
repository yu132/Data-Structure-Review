package common.ds.binaryIndexedTree;

import org.junit.Assert;
import org.junit.Test;

public class Test2 {

	@Test
	public void test() {

		int[] array = {
				1, 1, 1, 1, 1, 1, 1, 1, 1
		};

		int[] array1 = {
				1, 1, 3, 3, 3, 1, 1, 1, 1
		};

		int[] array2 = {
				1, 4, 6, 6, 6, 4, 4, 1, 1
		};

		IntBinaryIndexedTreeForSum2 bit = new IntBinaryIndexedTreeForSum2(array.length);

		bit.add(0, array.length, 1);

		for (int i = 0; i < array.length; ++i) {
			System.out.println(i);
			Assert.assertEquals(array[i], bit.get(i));
		}

		bit.addInclude(2, 4, 2);

		for (int i = 0; i < array.length; ++i)
			Assert.assertEquals(array1[i], bit.get(i));

		bit.addInclude(1, 6, 3);

		for (int i = 0; i < array.length; ++i)
			Assert.assertEquals(array2[i], bit.get(i));
	}

}
