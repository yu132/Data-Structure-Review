package common.ds.binaryIndexedTree;

/**
 * 树状数组的区间和模板
 * 
 * 区间求和，区间更新
 * 
 * 注意树状数组不使用0位置
 * 
 * 没有范围检查
 * 
 * 在使用差分的基础上，发现范围和实际上是p个a[0]+(p-1)个a[1]+...+a[p]
 * 所以可以推出公式，通过两个数组快速计算出范围和
 * 
 * @author 87663
 *
 */
public class IntBinaryIndexedTreeForSum3 implements BinaryIndexedTree {

	private int[]	sum1;																//对应的树状数组
	private int[]	sum2;																//对应的树状数组

	public int size() {
		return sum1.length - 1;
	}

	public IntBinaryIndexedTreeForSum3(int length) {
		sum1 = new int[length + 1];//0处不使用，所以需要多一位的长度
		sum2 = new int[length + 1];
	}

	public void add(int index, int num) {
		for (int i = index + 1; i < sum1.length; i += lowbit(i)) {
			sum1[i] += num;
			sum2[i] += num * index;
		}
	}

	/**
	 * 区间赋值
	 * 
	 * @param index		为了保持习惯，依然当作0开始，但实际上修改的是数组中的index+1位置
	 * @param num		让index位置上的值加上这个值
	 */
	public void add(int indexFrom, int indexTo, int num) {
		add(indexFrom, num);
		add(indexTo, -num);
	}

	public void addInclude(int indexFrom, int indexTo, int num) {
		add(indexFrom, num);
		add(indexTo + 1, -num);
	}

	/**
	 * 单点查询
	 * 
	 * @param index		为了习惯，写成了不包含这个位置的格式
	 * @return			返回index位置的值
	 */
	public int get(int index) {
		int sum = 0;

		//由于index对应数组index+1的位置，从index开始加正好不包括index+1位置上的值
		for (int i = index; i > 0; i -= lowbit(i))
			sum += (index + 1) * sum1[i] - sum2[i];

		return sum;
	}

	/**
	 * 区间查询
	 * 
	 * @param indexFrom		区间的开始索引
	 * @param indexTo		区间的结束索引（包括）
	 * @return				区间和
	 */
	public int getRange(int indexFrom, int indexTo) {
		return get(indexTo - 1) - get(indexFrom - 1);
	}

	/**
	 * 区间查询
	 * 
	 * @param indexFrom		区间的开始索引
	 * @param indexTo		区间的结束索引（不包括）
	 * @return				区间和
	 */
	public int getRangeInclude(int indexFrom, int indexTo) {
		return get(indexTo) - get(indexFrom - 1);
	}

}
