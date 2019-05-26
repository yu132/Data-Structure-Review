package ag.graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphs {

	/**
	 * @param adjacencyMatrix 邻接矩阵
	 * @return
	 * 		返回边的列表，边用长度为3的数组表示，[0]表示小的那个点的序号
	 * 		[1]表示大的那个点的序号，[2]表示两个点之间的距离
	 */
	public static List<int[]> getEdgesFromAdjacencyMatrix(int[][] adjacencyMatrix) {

		List<int[]> edges = new ArrayList<>();

		for (int i = 0; i < adjacencyMatrix.length; ++i)
			for (int j = i; j < adjacencyMatrix.length; ++j)
				if (adjacencyMatrix[i][j] > 0)
					edges.add(new int[] {
							i, j, adjacencyMatrix[i][j]
					});

		return edges;
	}

	/**
	 * @param adjacencyMatrix
	 * @return
	 * 		返回边的列表，边用长度为3的数组表示，[0]表示小的那个点的序号
	 * 		[1]表示大的那个点的序号，[2]表示两个点之间的距离
	 */
	public static List<int[]> getEdgesFromAdjacencyList(List<List<int[]>> adjacencyList) {

		List<int[]> edges = new ArrayList<>();

		for (int i = 0; i < adjacencyList.size(); ++i) {

			List<int[]> edgesOfPointi = adjacencyList.get(i);

			for (int[] edge : edgesOfPointi) {
				if (edge[0] < i)//这个边的另一个点编号比这个点的编号小，之前肯定在那个点的边集中遍历过了
					continue;		//不需要重复加入到边集中
				edges.add(new int[] {
						i, edge[0], edge[1]
				});
			}
		}

		return edges;
	}

}
