package common.ag.math.maxMin;

import java.util.Arrays;
import java.util.Random;

import utils.MyIntArrays;

/**
 * 寻找第k小的数和第k大的数
 * 
 * 两者实现其一即可，因为第k小的数，就是第（总数+1-k）大的数
 * 
 * 有两种方法，一种是使用随机快排的思想，进行部分的排序
 * 
 * 另一种方法叫BFPRT，在前者上做了优化，使得选取的主元更加贴近中心
 * 使得最坏时间复杂度从O(n^2)变为O(n)
 * 
 * 两者的期望时间复杂度都是O(n)
 * 
 * @author 87663
 */
public final class KthMinMax {
	
	/**
	 * 利用随机快排的思想，随机选取主元，并且只进行需要选取元素位置的那边的partition
	 * 每次确定一个元素的位置，如果确定元素的位置就是k-1（因为数组索引从0开始，k从1开始）
	 * 那么就选取出来了
	 */
	private final static Random RANDOM = new Random();
	
	private final static int partition(int[] array, int f, int b) {
		int temp = array[f];
		int low = f;
		int high = b - 1;
		
		while (low < high) {
			while (low < high && array[high] >= temp)
				--high;
			array[low] = array[high];
			while (low < high && array[low] <= temp)
				++low;
			array[high] = array[low];
		}
		
		array[low] = temp;
		
		return low;
	}
	
	private final static int randomPartition(int[] array, int f, int b) {
		if (f == b - 1)
			return f;
		
		int pivot = RANDOM.nextInt(b - f) + f;
		
		MyIntArrays.swap(array, f, pivot);
		
		return partition(array, f, b);
	}
	
	/**
	 * 选取第k小的数
	 * 
	 * @param array		选取的数组
	 * @param from		选取开始的地方（包括）
	 * @param to		选取结束的地方（不包括）
	 * @param k			需要选取的from到to中的第k个位置，从1开始
	 * @return			from到to中的第k个位置
	 */
	public static int kthMinRP(int[] array, int from, int to, int k) {
		if (k > to - from)
			throw new IllegalArgumentException("K is out of bound");
		
		if (from == to - 1)
			return array[from];
		
		int mid = randomPartition(array, from, to);
		
		int midIndex = mid - from + 1;//mid在本数组内的相对位置
		
		if (midIndex == k - 1)
			return array[mid];
		else if (midIndex < k - 1)
			return kthMinRP(array, from, mid, k);
		else
			return kthMinRP(array, mid + 1, to, k - midIndex);//把第k截短，变成后半的相对距离
	}
	
	public static int kthMaxRP(int[] array, int from, int to, int k) {
		return kthMinRP(array, from, to, to - from + 1 - k);
	}
	
	/**
	 * 在随机快排的基础上使用了一个方法，选取了更好的主元，降低了最坏时间复杂度
	 * 
	 * 选取主元的方法是将所有数每5个分成一组，排序，取中位数（即第三个）
	 * 然后将这些中位数在排序，在选取中位数的中位数
	 * 
	 * 使用这个数作为主元，那么每次总不会是最差的情况，数学上可计算
	 * 最坏时间复杂度就是O(n)
	 */
	
	private final static int ELEMENT_NUMBER_OF_EACH_GROUP = 5;
	
	/**
	 * 选取第k小的数
	 * 
	 * @param array		选取的数组
	 * @param from		选取开始的地方（包括）
	 * @param to		选取结束的地方（不包括）
	 * @param k			k代表需要选取的是from到to中的第k小的数的，从1开始
	 * @return			from到to中的第k小的数
	 */
	public static int kthMinBFPRT(int[] array, int from, int to, int k) {
		if (k > to - from)
			throw new IllegalArgumentException("K is out of bound");
		
		int eleNum = ELEMENT_NUMBER_OF_EACH_GROUP;
		
		int groupNum = (int) Math.ceil((to - from + 1) * 1.0 / eleNum);
		
		for (int i = 0; i < groupNum - 1; ++i) {//每5个分成一组
			Arrays.sort(array, from + i * eleNum, from + (i + 1) * eleNum);//排序
			MyIntArrays.swap(array, from + i, from + i * eleNum + eleNum / 2);//将中位数移动到最前面
		}
		
		int LastGroupFrom = from + (groupNum - 1) * eleNum;
		Arrays.sort(array, LastGroupFrom, to);//最后一组可能比较短，因此到to为止
		MyIntArrays.swap(array, from + (groupNum - 1), LastGroupFrom + (to - LastGroupFrom) / 2);
		
		Arrays.sort(array, from, from + groupNum);//将所有中位数排序
		MyIntArrays.swap(array, from, from + groupNum / 2);//将中位数的中位数作为主元
		
		int mid = partition(array, from, to);//使用partition分组
		
		int midIndex = mid - from + 1;//计算mid的相对位置
		
		if (midIndex == k - 1)//如果是第k，则直接返回，否则看比k小还是大，再选择是进行前一部分还是后一部分
			return array[mid];
		else if (midIndex < k - 1)
			return kthMinBFPRT(array, from, mid, k);
		else
			return kthMinBFPRT(array, mid + 1, to, k - midIndex);//把第k截短，变成后半的相对距离
	}
	
	public static int kthMaxBFPRT(int[] array, int from, int to, int k) {
		return kthMinBFPRT(array, from, to, to - from + 1 - k);
	}
}
