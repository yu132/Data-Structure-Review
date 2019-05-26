package common.ag.cppstl;

/**
 * 二分查找上下界
 * 
 * 即一堆相等的里面，下界即为第一个元素，上界即为最后一个元素的下一个
 * 
 * @author C++ std <algorithm>
 */
public class BinarySearchBound {

	/**
	 * 下界，即为第一个大于等于key的位置，不存在的话返回长度
	 * 
	 * @param array		需要查找的数组
	 * @param from		查找的起始位置（包括）
	 * @param to		查找的终止位置（不包括）
	 * @param key		需要查找界的key
	 * @return			下界，如果不存在大于等于key的值，则返回数组长度
	 */
	public static int lowerBound(int[] array, int from, int to, int key) {
		int first = from, middle;
		int half, len;
		len = to;

		while (len > 0) {
			half = len >> 1;
			middle = first + half;
			if (array[middle] < key) {
				first = middle + 1;
				len = len - half - 1;       //在右边子序列中查找
			} else
				len = half;            //在左边子序列（包含middle）中查找
		}
		return first;
	}

	/**
	 * 上界，即为第一个大于key的值，不存在的话返回长度
	 * 
	 * @param array		需要查找的数组
	 * @param from		查找的起始位置（包括）
	 * @param to		查找的终止位置（不包括）
	 * @param key		需要查找界的key
	 * @return			下界，如果不存在大于等于key的值，则返回数组长度
	 */
	public static int upperBound(int[] array, int from, int to, int key) {
		int first = from, len = to - 1;
		int half, middle;

		while (len > 0) {
			half = len >> 1;
			middle = first + half;
			if (array[middle] > key)     //中位数大于key,在包含last的左半边序列中查找。
				len = half;
			else {
				first = middle + 1;    //中位数小于等于key,在右半边序列中查找。
				len = len - half - 1;
			}
		}
		return first;
	}
}
