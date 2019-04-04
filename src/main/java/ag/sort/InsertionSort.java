package ag.sort;

public class InsertionSort implements IntArraySort {

	/**
	 * 普通版的插入排序，顺序的查找需要插入的位置，但是是稳定排序
	 */
	public static void sortIntArray(int[] array, int f, int b) {
		if (f > b || f < 0 || b > array.length)
			throw new IllegalArgumentException();

		for (int i = f + 1; i < b; ++i) {
			int num = array[i];

			int index = 0;

			while (array[index] <= num && index < i)
				++index;

			if (index != i)
				System.arraycopy(array, index, array, index + 1, i - index);

			array[index] = num;
		}
	}

	/**
	 * 二分查找的插入排序，二分查找需要插入的位置，不稳定
	 */
	public static void sortIntArrayBS(int[] array, int f, int b) {
		if (f > b || f < 0 || b > array.length)
			throw new IllegalArgumentException();

		for (int i = f + 1; i < b; ++i) {
			int num = array[i];

			int low = f, high = i;

			//二分查找位置
			while (low <= high) {

				int mid = (low + high) >>> 1;

				if (array[mid] < num)
					low = mid + 1;
				else if (array[mid] > num)
					high = mid - 1;
				else {
					low = mid;
					break;
				}
			}

			//复制数组，条件是为了防止越界检查抛出异常 => (low + 1)==array.length
			if (low != i)
				System.arraycopy(array, low, array, low + 1, i - low);

			array[low] = num;
		}
	}

	/**
	 * 稳定的二分查找插入排序，就是二分的时候，相等也不退出，而是继续寻找比其大的最小的元素
	 */
	public static void sortIntArrayBSStable(int[] array, int f, int b) {
		if (f > b || f < 0 || b > array.length)
			throw new IllegalArgumentException();

		for (int i = f + 1; i < b; ++i) {
			int num = array[i];

			int low = f, high = i;

			while (low <= high) {

				int mid = (low + high) >>> 1;

				if (array[mid] <= num)//即使相等，也要把后出现的元素排在后面
					low = mid + 1;
				else
					high = mid - 1;
			}

			if (low > i)
				low = i;

			if (low != i)
				System.arraycopy(array, low, array, low + 1, i - low);

			array[low] = num;
		}
	}

	@Override
	public void sort(int[] array) {
		sortIntArrayBSStable(array, 0, array.length);
	}

}
