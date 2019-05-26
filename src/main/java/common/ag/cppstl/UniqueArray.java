package common.ag.cppstl;

public class UniqueArray {

	/**
	 * 将一个数组内所有相邻的重复元素删除
	 * 
	 * @param array
	 * @return		删除后数组的长度
	 */
	public static int unique(int[] array) {
		int now = 0, mid = 0;

		while (++mid != array.length)
			if (array[now] != array[mid])
				array[++now] = array[mid];

		return now + 1;
	}

}
