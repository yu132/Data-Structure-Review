package common.ag.model.graph;

public class AdjacencyList {

	/**
	 * 形如此种的邻接矩阵    List<List<int[]>> adjacencyList
	 * 
	 * 第一层list中存储所有的点
	 * 
	 * 这些点包含一些边，对应第二层list中的边
	 * 
	 * 边则用一个数组int[]表示
	 * 
	 * [0]位置表示目标的点，[1]表示长度,起始点则用第一层list中的索引表示
	 * 
	 * 这样表示没有任何的空间浪费
	 * 
	 * 
	 * 是否是有向图则需要根据具体情况来说，这张图无向图和有向图都可以表示
	 * 对于无向图来说，起始点和目标点没有区别
	 */

}
