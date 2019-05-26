package ag.graph.minimumSpanningTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * prim算法的思想是从一个点开始扩展，
 * 总是选取一个连接 已经到达过的点 和 未到达过的点 的边
 * 并且这个边是所有符合条件中最短的那个
 * 直到所有的点都已经到达过
 * 我们选取的这些边，就是最小的生成树的边集
 * 
 * @author 87663
 */
public class Prim {

	/**
	 * @param adjacencyList		邻接表	@see common.ag.model.graph.AdjacencyList
	 * @return					最小生成树的边集
	 */
	public static List<int[]> prim(List<List<int[]>> adjacencyList) {
		PriorityQueue<int[]> edgesHeap = new PriorityQueue<>((a, b) -> {
			return Integer.compare(a[2], b[2]);
		});

		if (adjacencyList.size() < 0)
			return Collections.emptyList();

		List<int[]> point0 = adjacencyList.get(0);

		for (int i = 0; i < point0.size(); ++i) {
			int[] edge = point0.get(i);
			edgesHeap.add(new int[] {
					0, edge[0], edge[1]
			});
		}

		boolean[] connected = new boolean[adjacencyList.size()];
		int count = adjacencyList.size();

		List<int[]> edges = new ArrayList<>();

		while (count != 1) {
			if (edgesHeap.isEmpty())
				return null;

			int[] edge = edgesHeap.poll();

			if (connected[edge[1]])
				continue;

			connected[edge[1]] = true;
			--count;

			edges.add(edge);

			List<int[]> edgesOfpoint = adjacencyList.get(edge[1]);

			for (int i = 0; i < edgesOfpoint.size(); ++i) {
				int[] edge1 = edgesOfpoint.get(i);
				edgesHeap.add(new int[] {
						0, edge1[0], edge1[1]
				});
			}
		}

		return edges;
	}

	/**
	 * @param adjacencyList		邻接表	@see common.ag.model.graph.AdjacencyList
	 * @return					最小生成树的长度
	 */
	public static int primLength(List<List<int[]>> adjacencyList) {
		PriorityQueue<int[]> edgesHeap = new PriorityQueue<>((a, b) -> {
			return Integer.compare(a[1], b[1]);
		});

		if (adjacencyList.size() < 0)
			return 0;

		List<int[]> point0 = adjacencyList.get(0);

		for (int i = 0; i < point0.size(); ++i)
			edgesHeap.add(point0.get(i));

		boolean[] connected = new boolean[adjacencyList.size()];
		int count = adjacencyList.size();

		int totalLength = 0;

		while (count != 1) {
			if (edgesHeap.isEmpty())
				return -1;

			int[] edge = edgesHeap.poll();

			if (connected[edge[0]])
				continue;

			connected[edge[0]] = true;
			--count;

			totalLength += edge[1];

			List<int[]> edgesOfpoint = adjacencyList.get(edge[0]);

			for (int i = 0; i < edgesOfpoint.size(); ++i)
				edgesHeap.add(edgesOfpoint.get(i));
		}

		return totalLength;
	}

}
