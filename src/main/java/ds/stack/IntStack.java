package ds.stack;

public interface IntStack {

	/**
	 * @return 栈为空时返回真
	 */
	boolean isEmpty();
	
	/**
	 * @return 栈中的元素数量
	 */
	int size();
	
	/**
	 * 查看栈顶的元素
	 * @return 栈顶的元素
	 */
	int top();
	
	/**
	 * 删除栈顶的元素
	 * @return 栈顶的元素
	 */
	int pop();
	
	/**
	 * 向栈顶压入元素
	 */
	void push(int element);
	
	/**
	 * 清空栈内元素
	 */
	void clear();
}
