package common.ds.binaryIndexedTree;

/**
 * 树状数组的区间和模板
 * 
 * 单点求和，区间更新
 * 
 * 注意树状数组不使用0位置
 * 
 * 没有范围检查
 * 
 * 思想是使用差分，使得总和才是数，这样在区间开始处增加值，结尾处减少值
 * 使得区间外的值不变，但是区间内的值增大了，这样可以仅仅修改两个值就完成区间内的更新
 * 但是缺点是从区间查询变成了单点查询
 * 
 * @author 87663
 *
 */
public class IntBinaryIndexedTreeForSum2 implements BinaryIndexedTree {

	private int[] sum;//对应的树状数组

	public int size() {
		return sum.length - 1;
	}

	public IntBinaryIndexedTreeForSum2(int length) {
		sum = new int[length + 1];//0处不使用，所以需要多一位的长度
	}

	public void add(int index, int num) {
		for (int i = index + 1; i < sum.length; i += lowbit(i))
			sum[i] += num;
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
	 * @param index		
	 * @return			返回index位置的值
	 */
	public int get(int index) {
		int sum = 0;

		//由于index对应数组index+1的位置，从index开始加正好不包括index+1位置上的值
		for (int i = index + 1; i > 0; i -= lowbit(i))
			sum += this.sum[i];

		return sum;
	}

}
