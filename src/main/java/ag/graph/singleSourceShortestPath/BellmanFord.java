package ag.graph.singleSourceShortestPath;

import java.util.Arrays;
import java.util.List;

/**
 * 这个算法是BellmanFord，感觉和floyd算法非常像，都是基于松弛的算法
 * 
 * @see https://wenku.baidu.com/view/5de4b20fbb68a98271fefa1b.html
 * @see https://en.m.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm
 * 
 * @author 87663
 */
public final class BellmanFord {

	/**
	 * @param adjacencyList		邻接表	@see common.ag.model.graph.AdjacencyList
	 * 
	 * 				有一个要求，从一个点到另一个点之间任意路径的大小不能小于Integer.MIN_VALUE
	 * 				否则可能出错
	 * 
	 * @param from				起始点
	 * @param MAX_LENGTH		最大长度，超过这个大小的长度，被认为是无穷大
	 * 
	 * @return					一个二维数组（或null）
	 * 
	 * 				二维数组的情况下，其中包含两个一维数组：
	 * 
	 * 				[0][i]为distance，表示从起始点from到各点i的最短距离，如果无法到达，则值为MAX_LENGTH
	 * 
	 * 				[1][i]为predecessor，表示各点i的前序节点为那个节点，即起点需要通过什么节点到达本节点i
	 * 					有可能会通过很多个节点才能到达某个节点，递归遍历数组即可求得路径
	 * 					也可能有的节点无法到达，则值为-1
	 * 
	 * 				也会在出现负环的时候返回null，因为本算法无法解决有负环的图的单源最短路径
	 * 
	 */
	public static int[][] bellmanFord(List<List<int[]>> adjacencyList, int from,
			final int MAX_LENGTH) {

		int n = adjacencyList.size();

		int[] distance = new int[n];
		int[] predecessor = new int[n];

		Arrays.fill(distance, MAX_LENGTH);
		Arrays.fill(predecessor, -1);

		distance[from] = 0;

		for (int i = 0; i < n - 1; ++i) {//按照原始算法，必须进行n-1次迭代，但是如果没有改变，则可以提前退出
											//这个地方使用while(true)是不对的，因为如果出现负环，则会无穷的进行下去

			boolean notChanged = true;

			//遍历边集
			for (int fromPoint = 0; fromPoint < n; ++fromPoint) {

				List<int[]> edges = adjacencyList.get(fromPoint);

				for (int[] edge : edges) {

					int toPoint = edge[0];
					int dis = edge[1];

					if (distance[fromPoint] + dis < distance[toPoint]) {//松弛化
						distance[toPoint] = distance[fromPoint] + dis;
						predecessor[toPoint] = fromPoint;
						notChanged = false;
					}
				}
			}

			if (notChanged)//没有改变则提前退出
				break;
		}

		//检查是否有负环，这也是上面不能用while(true)的原因，内部和内层循环几乎是一样的
		for (int fromPoint = 0; fromPoint < n; ++fromPoint) {

			List<int[]> edges = adjacencyList.get(fromPoint);

			for (int[] edge : edges) {

				int toPoint = edge[0];
				int dis = edge[1];

				if (distance[fromPoint] + dis < distance[toPoint])//如果经过n-1次迭代还能继续松弛
					return null;									//则图内存在负环，本算法对这种情况无能为力
			}
		}

		return new int[][] {
				distance, predecessor
		};
	}

}
