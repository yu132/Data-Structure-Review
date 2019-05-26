package common.ds.binaryIndexedTree;

import static java.lang.Math.max;

/**
 * 树状数组的区间最大值的模板
 * 
 * 注意树状数组不使用0位置
 * 
 * 没有范围检查
 * 
 * @author 87663
 *
 */
public class IntBinaryIndexedTreeForMax implements BinaryIndexedTree {

	private int[] t;//对应的树状数组

	public int size() {
		return t.length - 1;
	}

	public IntBinaryIndexedTreeForMax(int length) {
		t = new int[length + 1];//0处不使用，所以需要多一位的长度
	}


	/**
	 * 单点更新
	 * 
	 * @param index		为了保持习惯，依然当作0开始，但实际上修改的是数组中的index+1位置
	 * @param num		让index位置上的值修改为这个值
	 */
	public void update(int index, int num) {
		for (int i = index + 1; i < t.length; i += lowbit(i))
			t[i] = max(t[i], num);
	}

	/**
	 * 区间查询（不包括）
	 * 
	 * @param index		为了习惯，写成了不包含这个位置的格式
	 * @return			返回0-index(不包括)的最大值
	 */
	public int query(int index) {
		int max = 0;

		//由于index对应数组index+1的位置，从index开始加正好不包括index+1位置上的值
		for (int i = index; i > 0; i -= lowbit(i))
			max = Math.max(max, t[i]);

		return max;
	}

	/**
	 * 区间查询（包括）
	 * 
	 * @param index		
	 * @return			返回0-index(包括)的最大值
	 */
	public int queryInclude(int index) {
		int max = 0;

		//由于index对应数组index+1的位置，从index开始加正好不包括index+1位置上的值
		for (int i = index + 1; i > 0; i -= lowbit(i))
			max = Math.max(max, t[i]);

		return max;
	}

}
