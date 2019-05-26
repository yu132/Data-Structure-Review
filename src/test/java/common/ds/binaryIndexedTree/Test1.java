package common.ds.binaryIndexedTree;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import utils.MyIntArrays;

public class Test1 {

	@Test
	public void test() {

		int len = 200;

		IntBinaryIndexedTreeForSum bit = new IntBinaryIndexedTreeForSum(len);
		int[] array = new int[len];

		Random r = new Random();

		for (int i = 0; i < 10000; i++) {
			System.out.println(i);
			int index = r.nextInt(len);
			int val = (r.nextInt(10000) << 1) - 10000;
			//System.out.println(index + " " + val);
			bit.update(index, val);
			array[index] = val;

			//System.out.println(Arrays.toString(array));

			for (int j = 0; j < len; j++) {
				for (int k = j + 1; k < len; ++k) {
					//System.out.println(j + " " + k);
					Assert.assertEquals(MyIntArrays.sumRange(array, j, k),
							bit.getSum(k) - bit.getSum(j));
				}
			}
		}


	}

}
