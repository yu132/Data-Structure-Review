package common.ag.countInversion;

import common.ag.discretization.Discretization;
import common.ds.binaryIndexedTree.IntBinaryIndexedTreeForSum;

public class CountInversionUsingBinaryIndexingArray {

	public static int countInversion(int[] array) {

		int[] b = Discretization.discretize(array);//离散化

		IntBinaryIndexedTreeForSum bit = new IntBinaryIndexedTreeForSum(array.length + 1);

		int count = 0;

		for (int i = 0; i < b.length; ++i) {//元素索引从0开始，i + 1表示当前元素数量
			bit.update(b[i], 1);//当前元素的位置上加1
			count += i + 1 - bit.getSumInclude(b[i]);//总计数加上 当前总量减去排在这个元素前面和这个元素本身的数量
														//即总计数加上比这个数大的数量，而且这些元素都比这个元素出现的早
		}

		return count;
	}

}
