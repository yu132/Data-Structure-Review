package ag.unionFind;

public interface IUnionFind {

	/**
	 * 合并两个元素的类别
	 * @param element1 元素1
	 * @param element2 元素2
	 */
	void union(int element1,int element2);
	
	/**
	 * 查找元素的类别
	 * @param elenemt 元素
	 * @return 类别
	 */
	int find(int elenemt);
	
	/**
	 * 检查两个元素是否是同一个类别
	 * @param element1 元素1
	 * @param element2 元素2
	 * @return 为同一类别时，返回真
	 */
	boolean connected(int element1,int element2);
	
	/**
	 * @return 联通分支的数量
	 */
	int count();
	
}
