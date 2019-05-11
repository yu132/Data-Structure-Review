package ag.common.dfsAndBfs.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import ag.common.dfsAndBfs.NotLinearSearch;
import ag.common.dfsAndBfs.Visitable;

public class BFS {

	private static class Node<T> {
		T	node;
		int	level;

		public Node(T node, int level) {
			super();
			this.node = node;
			this.level = level;
		}
	}

	/**
	 * bfs的实现，由于bfs不可以用迭代实现，只有循环的这种实现方法
	 * 
	 * @see NotLinearSearch search(Visitable<T> visitable, T from, T target)
	 */
	public static <T> int bfs(Visitable<T> visitable, T from, T target) {

		Set<T> visited = new HashSet<>();

		Queue<Node<T>> queue = new LinkedList<>();

		queue.offer(new Node<>(from, 0));

		while (!queue.isEmpty()) {
			Node<T> now = queue.poll();

			if (now.equals(target))
				return now.level;

			if (visited.contains(now.node))
				continue;

			visited.add(now.node);

			for (T next : visitable.next(now.node)) {
				queue.offer(new Node<>(next, now.level + 1));
			}
		}

		return -1;

	}

}
