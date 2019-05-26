package common.ag.graph.singleSourceShortestPath;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.ag.math.avg.IntAvgCalculator;

/**
 * SPFA全称是Shortest Path Faster Algorithm，
 * 
 * 是一种BellmanFord的队列优化，本质上还是一样的，最坏时间复杂度和BellmanFord一样
 * 
 * 但是也可以解决有负权值路径，但是也不能解决有负环的问题，这也和BellmanFord一样
 * 
 * 平均时间比BellmanFord要好不少，但是在没有负权值的情况下，比不上使用堆的Dijkstra
 * 
 * @see https://en.wikipedia.org/wiki/Shortest_Path_Faster_Algorithm
 * 
 * @author 87663
 *
 */
public final class SPFA {
	
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
	public static int[][] spfa(List<List<int[]>> adjacencyList, int from,
			final int MAX_LENGTH) {
		
		int n = adjacencyList.size();
		
		int[] distance = new int[n];
		int[] predecessor = new int[n];
		
		Arrays.fill(distance, MAX_LENGTH);
		Arrays.fill(predecessor, -1);
		
		distance[from] = 0;
		
		boolean[] visited = new boolean[n];//表示这个节点是否在队列queue内，而不是这个节点有没有被遍历过
		
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(from);
		
		while (!queue.isEmpty()) {
			
			Integer fromPoint = queue.poll();//选取下一个需要松弛的节点
			
			visited[fromPoint] = false;//防止多次入队，因为一个节点松弛后，不一定需要再次松弛
										//而多次入队可能会加大无用的计算量
			
			//松弛这个节点周围的节点
			List<int[]> edges = adjacencyList.get(fromPoint);
			
			for (int[] edge : edges) {
				
				int toPoint = edge[0];
				int dis = edge[1];
				
				if (distance[fromPoint] + dis < distance[toPoint]) {//松弛化
					distance[toPoint] = distance[fromPoint] + dis;
					predecessor[toPoint] = fromPoint;
					if (!visited[toPoint]) {//需要重新松弛的节点不在队列内（在的话无需重新添加）
						queue.offer(toPoint);
					}
				}
			}
			
		}
		
		return new int[][] {
				distance, predecessor
		};
	}
	
	public interface QueueOptimizer {
		void optimize(Deque<Integer> queue, int[] distance);
	}
	
	/**
	 * Small Label First 就是将小的元素移动到前面
	 */
	public final static QueueOptimizer	SLF;
	
	/**
	 * Large Label Last 就是将大的元素移动到后面
	 */
	public final static QueueOptimizer	LLL;
	
	static {
		SLF = (queue, distance) -> {
			while (distance[queue.getLast()] < distance[queue.getFirst()]) {
				queue.addFirst(queue.removeLast());
			}
		};
		
		LLL = (queue, distance) -> {
			
			IntAvgCalculator cal = new IntAvgCalculator(
					queue.size());
			
			for (Integer point : queue) {
				cal.offer(distance[point]);
			}
			
			int avg = cal.getAvg();
			
			while (distance[queue.getFirst()] > avg)
				queue.addLast(queue.removeFirst());
		};
	}
	
	public static int[][] spfaWithQueueOptimization(List<List<int[]>> adjacencyList, int from,
			final int MAX_LENGTH, QueueOptimizer queueOptimizer) {
		
		int n = adjacencyList.size();
		
		int[] distance = new int[n];
		int[] predecessor = new int[n];
		
		Arrays.fill(distance, MAX_LENGTH);
		Arrays.fill(predecessor, -1);
		
		distance[from] = 0;
		
		boolean[] visited = new boolean[n];//表示这个节点是否在队列queue内，而不是这个节点有没有被遍历过
		
		Deque<Integer> queue = new LinkedList<>();
		
		queue.addLast(from);
		
		while (!queue.isEmpty()) {
			
			Integer fromPoint = queue.removeFirst();//选取下一个需要松弛的节点
			
			visited[fromPoint] = false;//防止多次入队，因为一个节点松弛后，不一定需要再次松弛
										//而多次入队可能会加大无用的计算量
			
			//松弛这个节点周围的节点
			List<int[]> edges = adjacencyList.get(fromPoint);
			
			for (int[] edge : edges) {
				
				int toPoint = edge[0];
				int dis = edge[1];
				
				if (distance[fromPoint] + dis < distance[toPoint]) {//松弛化
					distance[toPoint] = distance[fromPoint] + dis;
					predecessor[toPoint] = fromPoint;
					if (!visited[toPoint]) {//需要重新松弛的节点不在队列内（在的话无需重新添加）
						queue.addLast(toPoint);
					}
				}
			}
			
			//进行队列优化，尽量先处理前面的元素
			queueOptimizer.optimize(queue, distance);
			
		}
		
		return new int[][] {
				distance, predecessor
		};
	}
	
}
