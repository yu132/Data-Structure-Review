package ag.sort;

import utils.MyIntArrays;

public class BubbleSort implements IntArraySort {

	public static void sortIntArray(int[] array, int f, int b) {

		int k = b - 1;

		for (int i = f; i < b - 1; i++) {

			boolean flag = true;
			int lastPos = -1;

			for (int j = f; j < k; ++j) {
				if (array[j] > array[j + 1]) {
					MyIntArrays.swap(array, j, j + 1);
					flag = false;
					lastPos = j;
				}
			}

			if (flag)// 快速结束，如果没有一次交换，则证明数组已经有序
				break;

			k = lastPos;// 最后一次交换的位置后面肯定是有序数组，无需遍历
		}

	}

	@Override
	public void sort(int[] array) {
		sortIntArray(array, 0, array.length);
	}

}
