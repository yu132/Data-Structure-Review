package common.ag.search.dfsAndBfs.dfs;

import java.util.HashSet;
import java.util.Set;

import common.ag.search.dfsAndBfs.NotLinearSearch;
import common.ag.search.dfsAndBfs.Visitable;

/**
 * 递归版本的DFS，需要考虑深度，注意不要爆栈
 * 
 * @author 87663
 *
 */
public final class DFSRecursion {

	/**
	 * @see NotLinearSearch search(Visitable<T> visitable, T from, T target)
	 */
	public static <T> int dfs(Visitable<T> visitable, T from, T target) {

		Set<T> visited = new HashSet<>();

		return dfsHelper(visitable, from, target, visited, 0);
	}

	private static <T> int dfsHelper(Visitable<T> visitable, T now, T target, Set<T> visited,
			int level) {

		if (now.equals(target))
			return level;

		if (visited.contains(now))
			return -1;

		visited.add(now);

		for (T next : visitable.next(now)) {
			int targetLevel = dfsHelper(visitable, next, target, visited, level + 1);
			if (targetLevel != -1)
				return targetLevel;
		}

		return -1;
	}

}
