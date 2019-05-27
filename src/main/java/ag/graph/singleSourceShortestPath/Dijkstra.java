package ag.graph.singleSourceShortestPath;

import java.util.List;
import java.util.PriorityQueue;

import common.ag.model.graph.AdjacencyList;

/**
 * 迪杰斯特拉是一种非常经典的求单源最短路径的方法，
 * 但是其有自身的缺陷，那就是这张图不能有负权值
 * 
 * 其思想是每次选择一个 没选择过 但是 到过的 且 距离出发点最小 的一个点
 * 并且扩展这个点到达没选择过的点（因为选择过的点距离出发点的最近距离已经算出来了
 * 为何不能有负权值也是这个原因，如果存在，此处的贪心不成立，会使算法错误），
 * 并更新这些点的距离，然后依次循环，直到找到的点是终点为止
 * 
 * 其实，是不是感觉和DFS和BFS有点像，就是把BFS的队列，DFS的栈换成了优先级队列
 * 在其中查找最小的距离出发点最近的点
 * 
 * @author 87663
 *
 */
public class Dijkstra {
	
	private static class Node {
		int distance;
		int toPointId;
		
		public Node(int distance, int toPointId) {
			super();
			this.distance = distance;
			this.toPointId = toPointId;
		}
	}
	
	/**
	 * dijkstra的实现
	 * 
	 * @param adjacencyList		邻接表(限制权值为正)	@see {@link AdjacencyList}
	 * @return					单源最短路径的长度
	 */
	public static int dijkstra(List<List<int[]>> adjacencyList, int from, int to) {
		boolean[] visited = new boolean[adjacencyList.size()];
		
		PriorityQueue<Node> heap = new PriorityQueue<>(adjacencyList.size(),
				(a, b) -> Integer.compare(a.distance, b.distance));
		
		heap.offer(new Node(0, from));
		
		while (!heap.isEmpty()) {
			
			Node next = heap.poll();//选取距离原点最小的点，认为当前距离就是最小距离，
									//对于无负权值的边的图，是正确的
			
			//接下来就是遍历这个点能到达的，但是没有经历过像这样遍历的点
			
			if (next.toPointId == to)
				return next.distance;
			
			if (visited[next.toPointId])
				continue;
			
			visited[next.toPointId] = true;
			
			List<int[]> edges = adjacencyList.get(next.toPointId);
			
			for (int[] edge : edges)
				if (!visited[edge[0]])
					heap.offer(new Node(next.distance + edge[1], edge[0]));
		}
		
		return -1;//如果所有的点都已经用尽，则没有单源最短路径，因为图不连通
	}
	
}
