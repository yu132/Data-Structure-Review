package ag.common.dfsAndBfs;

/**
 * 类似于图的结构，能找到一个节点的后续节点
 * 
 * @author 87663
 *
 * @param <T> 存储的内容的类型
 */
public interface Visitable<T> {

	/**
	 * 返回一个节点的后续节点
	 * 
	 * @param element	某个节点
	 * @return			这个节点的后续节点（周围的节点）
	 */
	Iterable<T> next(T element);

}