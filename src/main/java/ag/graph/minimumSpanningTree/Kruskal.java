package ag.graph.minimumSpanningTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ag.graph.UndirectedGraphs;
import ag.unionFind.UnionFind;
import ag.unionFind.WeightedUnionFind;

/**
 * 克鲁斯卡尔算法，用于寻找一个图内的最小生成树
 * 
 * 思想就是将边集排序，然后从最小的一个边开始选择是否要使用这个边生成最小生成树
 * 当这个边能够将两个联通分支连成一个联通分支的时候，就要这个边，否则不要
 * 判断两个点是否属于一个联通分支的方法是使用并查集
 * 
 * @author 87663
 */
public class Kruskal {

	/**
	 * @param adjacencyMatrix
	 * 
	 * 		邻接矩阵，是一个n x n的矩阵，其中数字表示两个点之间的距离,距离不为负
	 * 
	 * @return	最小生成树的边集，若不可能生成最小生成树（图不连通），则返回null
	 */
	public static List<int[]> kruskal(int[][] adjacencyMatrix) {

		List<int[]> edges = UndirectedGraphs.getEdgesFromAdjacencyMatrix(adjacencyMatrix);

		return kruskal(edges, adjacencyMatrix.length);
	}

	public static List<int[]> kruskal(List<List<int[]>> adjacencyList) {

		List<int[]> edges = UndirectedGraphs.getEdgesFromAdjacencyList(adjacencyList);

		return kruskal(edges, adjacencyList.size());
	}

	private static List<int[]> kruskal(List<int[]> edges, int pointsNumber) {

		Collections.sort(edges, (a, b) -> {
			return Integer.compare(a[2], b[2]);
		});

		UnionFind uf = new WeightedUnionFind(pointsNumber);

		List<int[]> edgesOfMST = new ArrayList<>();

		for (int[] edge : edges) {
			if (uf.connected(edge[0], edge[1]))
				continue;

			edgesOfMST.add(edge);

			uf.union(edge[0], edge[1]);
		}

		if (uf.count() > 1)
			return null;

		return edgesOfMST;
	}

	/**
	 * @param adjacencyMatrix
	 * 
	 * 		邻接矩阵，是一个n x n的矩阵，其中数字表示两个点之间的距离,距离不为负
	 * 
	 * @return	最小生成树的总长度，若不可能生成最小生成树（图不连通），则返回-1
	 */
	public static int kruskalLength(int[][] adjacencyMatrix) {

		List<int[]> edges = UndirectedGraphs.getEdgesFromAdjacencyMatrix(adjacencyMatrix);

		return kruskalLength(edges, adjacencyMatrix.length);
	}

	/**
	 * @param adjacencyList		邻接表	@see common.ag.model.graph.AdjacencyList
	 * 
	 * @return	最小生成树的总长度，若不可能生成最小生成树（图不连通），则返回-1
	 */
	public static int kruskalLength(List<List<int[]>> adjacencyList) {

		List<int[]> edges = UndirectedGraphs.getEdgesFromAdjacencyList(adjacencyList);

		return kruskalLength(edges, adjacencyList.size());
	}

	private static int kruskalLength(List<int[]> edges, int pointsNumber) {

		Collections.sort(edges, (a, b) -> {
			return Integer.compare(a[2], b[2]);
		});

		UnionFind uf = new WeightedUnionFind(pointsNumber);

		int totalLength = 0;

		for (int[] edge : edges) {
			if (uf.connected(edge[0], edge[1]))
				continue;

			totalLength += edge[2];

			uf.union(edge[0], edge[1]);
		}

		if (uf.count() > 1)
			return -1;

		return totalLength;
	}

}
