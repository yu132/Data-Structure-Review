package ds.linearList;

public interface IntLinearList {

	/**
	 * 检查线性表是否为空
	 * 
	 * @return 空时为真
	 */
	boolean isEmpty();
	
	/**
	 * @return 线性表大小
	 */
	int size();
	
	/**
	 * @param index 线性表元素索引
	 * @return 该索引上的元素
	 */
	int get(int index);
	
	/**
	 * @param element 要设置的元素
	 * @param index 要设置的位置
	 */
	void set(int element,int index);
	
	/**
	 * @param element 需要查找位置的元素
	 * @return 该元素出现的第一个位置
	 */
	int indexOf(int element);
	
	/**
	 * @param element 需要删除的元素（只删第一个）
	 * @return 是否有这个元素被删除
	 */
	boolean delete(int element);
	
	/**
	 * @param index 删除指定位置上的元素
	 */
	void deleteIndex(int index);
	
	/**
	 * @param element 插入的元素
	 * @param index 插入元素的位置
	 */
	void insert(int element,int index);
	
	/**
	 * 清除线性表
	 */
	void clear();
	
}
