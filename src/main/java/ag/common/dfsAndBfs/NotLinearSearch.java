package ag.common.dfsAndBfs;

public interface NotLinearSearch {

	/**
	 * 非线性搜索的方法的解释 
	 * 
	 * @param visitable		类似于图的结构，能找到一个节点的后续节点
	 * @param from			起点节点
	 * @param target		目标节点
	 * @return				起点节点距离目标节点的距离，如果不可达，返回-1
	 */
	public <T> int search(Visitable<T> visitable, T from, T target);

}
