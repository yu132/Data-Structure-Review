package ag.common.dfsAndBfs.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ag.common.dfsAndBfs.NotLinearSearch;
import ag.common.dfsAndBfs.Visitable;

/**
 * 循环版本的dfs，没有爆栈的危险
 * 
 * @author 87663
 *
 */
public final class DFSLoop {

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
	 * 对dfs遍历没有顺序要求的实现
	 * 
	 * 总是先遍历一组内最后出现的那个
	 * 
	 * @see NotLinearSearch search(Visitable<T> visitable, T from, T target)
	 */
	public static <T> int dfs(Visitable<T> visitable, T from, T target) {

		Set<T> visited = new HashSet<>();

		Deque<Node<T>> stack = new LinkedList<>();

		stack.push(new Node<>(from, 0));

		while (!stack.isEmpty()) {
			Node<T> now = stack.pop();

			if (now.equals(target))
				return now.level;

			if (visited.contains(now.node))
				continue;

			visited.add(now.node);

			for (T next : visitable.next(now.node)) {
				stack.push(new Node<>(next, now.level + 1));
			}
		}

		return -1;
	}

	/**
	 * 对dfs遍历有顺序要求的实现
	 * 
	 * 总是遍历一个组内先出现的那个
	 * 
	 * @see NotLinearSearch search(Visitable<T> visitable, T from, T target)
	 */
	public static <T> int dfsFF(Visitable<T> visitable, T from, T target) {

		Set<T> visited = new HashSet<>();

		Deque<Node<T>> stack = new LinkedList<>();

		stack.push(new Node<>(from, 0));

		while (!stack.isEmpty()) {
			Node<T> now = stack.pop();

			if (now.equals(target))
				return now.level;

			if (visited.contains(now.node))
				continue;

			visited.add(now.node);

			List<T> nextList = new ArrayList<>();

			for (T next : visitable.next(now.node))
				nextList.add(next);

			Collections.reverse(nextList);

			for (T next : nextList) {
				stack.push(new Node<>(next, now.level + 1));
			}
		}

		return -1;
	}

}
