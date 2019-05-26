package common.ag.search.dfsAndBfs.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import ag.unionFind.UnionFind;
import ag.unionFind.WeightedUnionFind;
import common.ag.search.dfsAndBfs.NotLinearSearch;
import common.ag.search.dfsAndBfs.Visitable;

/**
 * 多向的BFS，以双向为主，因为一般就一个起点，一个终点
 * 
 * @author 87663
 *
 */
public class MultidirectionalBFS {

	private static class Node<T> {
		T	node;
		int	level;
		T	startFrom;

		public Node(T node, int level, T startFrom) {
			super();
			this.node = node;
			this.level = level;
			this.startFrom = startFrom;
		}
	}

	/**
	 * 双向BFS
	 * 
	 * @see NotLinearSearch search(Visitable<T> visitable, T from, T target)
	 */
	public static <T> int twoDirectionalBfs(Visitable<T> visitable, T from, T target) {

		Map<T, Node<T>> visited = new HashMap<>();

		Queue<Node<T>> queue = new LinkedList<>();

		queue.offer(new Node<>(from, 0, from));
		queue.offer(new Node<>(target, 0, target));

		while (!queue.isEmpty()) {

			Node<T> now = queue.poll();

			if (visited.containsKey(now.node)) {

				Node<T> visitedNode = visited.get(now.node);

				if (visitedNode.startFrom != now.startFrom)
					return now.level + visitedNode.level + 1;

				continue;
			}

			visited.put(now.node, now);

			for (T next : visitable.next(now.node)) {
				queue.offer(new Node<>(next, now.level + 1, now.startFrom));
			}
		}

		return -1;
	}

	public static class Distance<T> {

		private T	point1;
		private T	point2;
		private int	distance;

		Distance(T point1, T point2, int distance) {
			super();
			this.point1 = point1;
			this.point2 = point2;
			this.distance = distance;
		}

		public T getPoint1() {
			return point1;
		}

		public T getPoint2() {
			return point2;
		}

		public int getDistance() {
			return distance;
		}
	}

	/**
	 * 多向BFS
	 * 
	 * 返回能联通所有起始点的最短路径
	 */
	public static <T> Iterable<Distance<T>> multidirectionalBfs(Visitable<T> visitable,
			Iterable<T> startPoints) {

		Map<T, Node<T>> visited = new HashMap<>();

		Queue<Node<T>> queue = new LinkedList<>();

		Set<Distance<T>> distance = new HashSet<>();

		Map<T, Integer> indexPoint = new HashMap<>();

		for (T startPoint : startPoints) {
			queue.add(new Node<>(startPoint, 0, startPoint));
			indexPoint.put(startPoint, indexPoint.size());
		}

		UnionFind uf = new WeightedUnionFind(indexPoint.size());

		while (!queue.isEmpty() && uf.count() > 1) {

			Node<T> now = queue.poll();

			if (visited.containsKey(now.node)) {

				Node<T> visitedNode = visited.get(now.node);

				int point1 = indexPoint.get(visitedNode.startFrom);
				int point2 = indexPoint.get(now.startFrom);

				if (!uf.connected(point1, point2)) {
					distance.add(new Distance<T>(now.startFrom, visitedNode.startFrom,
							now.level + visitedNode.level + 1));
					uf.union(point1, point2);
				}

				continue;
			}

			visited.put(now.node, now);

			for (T next : visitable.next(now.node)) {
				queue.offer(new Node<>(next, now.level + 1, now.startFrom));
			}
		}

		return distance;
	}

}
