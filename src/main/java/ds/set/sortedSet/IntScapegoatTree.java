package ds.set.sortedSet;

import java.util.NoSuchElementException;

import ds.IntIterable;
import ds.IntIterator;
import ds.set.IntSet;

public class IntScapegoatTree implements IntSet, IntIterable {

	private final static double		DEFAULT_ALPHA	= 0.7;

	private final static int		MIN_THRESHOLD	= 5;

	/**
	 * 左右子树占这颗子树权重占比的阈值，超过这个阈值就需要重构整颗子树
	 * 
	 * 范围应该是0.5-1.0
	 * 
	 * 越接近0.5，重构的越频繁，因为只要左子树或右子树比另一方重一点就需要重构
	 * 这样频繁的重构代价太高
	 * 
	 * 越接近1，重构的越少，因为即使左子树或右子树比另一方重很多也不会重构，那么
	 * 这颗子树的查找性能会很低，因为子树的深度可能会很高
	 * 
	 * 所以需要取一个比较中庸的值，一般使用0.7
	 */
	private double					alpha;

	private IntScapegoatTreeNode	head;

	public IntScapegoatTree() {
		this(DEFAULT_ALPHA);
	}

	public IntScapegoatTree(double alpha) {
		super();
		this.alpha = alpha;
	}

	private class IntScapegoatTreeNode {

		IntScapegoatTreeNode	left;
		IntScapegoatTreeNode	right;

		IntScapegoatTreeNode	parent;

		/**
		 * size 指的是这颗子树上有的全部元素的数量
		 * 
		 * nodeCount 指的是这颗子树上的全部节点的数量
		 * 
		 * 这两者是不一样的，因为删掉的节点并不是一删除就将节点删掉
		 * 而是只是打上标记而已
		 */
		int						val, size, nodeCount;

		boolean					deleted;

		public IntScapegoatTreeNode(int val, IntScapegoatTreeNode parent) {
			super();
			this.parent = parent;
			this.val = val;

			left = right = null;
			size = nodeCount = 1;
			deleted = false;
		}

		/**
		 * 如果左右某棵子树的重量占超过这个子树的全重的alpha比值，那么就需要重新平衡
		 * 加入一个小的阈值是避免在树过小的时候产生重构，因为树的高度很低，重构带来的
		 * 好处不会大于重构的代价
		 * 
		 * @return
		 */
		boolean needRebalance() {
			return left != null ? (left.nodeCount > alpha * nodeCount + MIN_THRESHOLD)
					: false || right != null ? (right.nodeCount > alpha * nodeCount + MIN_THRESHOLD) : false;
		}

		void reCalculateSizeAndNodeCount() {
			size = (deleted ? 0 : 1) + (left != null ? left.size : 0) + (right != null ? right.size : 0);
			nodeCount = 1 + (left != null ? left.nodeCount : 0) + (right != null ? right.nodeCount : 0);
		}

		private IntScapegoatTreeNode reblanceTree() {
			IntScapegoatTreeNode[] flatTree = new IntScapegoatTreeNode[size];
			dfs(this, flatTree, 0);
			return rebuild(flatTree, 0, size, parent);
		}

		void rebalance() {
			if (this != head)
				if (this == parent.left)
					parent.left = reblanceTree();
				else
					parent.right = reblanceTree();
			else
				head = reblanceTree();
		}

		boolean add(int val) {
			if (this.val == val) {

				if (!deleted)
					return false;

				deleted = false;

				IntScapegoatTreeNode node = this;

				while (node != null) {
					++node.size;
					node = node.parent;
				}

				return true;
			}
			if (val < this.val) {
				if (left != null)
					return left.add(val);
				left = new IntScapegoatTreeNode(val, this);
			} else {// this.val<val
				if (right != null)
					return right.add(val);
				right = new IntScapegoatTreeNode(val, this);
			}

			IntScapegoatTreeNode node = this;

			IntScapegoatTreeNode needReblancedNode = null;

			while (node != null) {
				++node.size;
				++node.nodeCount;
				if (node.needRebalance())// 收集最大的需要重构的子树
					needReblancedNode = node;
				node = node.parent;
			}

			// 只需要重构最大的子树即可，因为重构小子树并不能解决问题，依然还是需要重新重构大的子树
			if (needReblancedNode != null)
				needReblancedNode.rebalance();

			return true;
		}

		boolean contain(int val) {
			if (val < this.val)
				return left != null ? left.contain(val) : false;

			else if (this.val < val) // this.val<val
				return right != null ? right.contain(val) : false;

			if (deleted)
				return false;

			return true;
		}

		boolean delete(int val) {
			if (val < this.val)
				return left != null ? left.delete(val) : false;

			else if (this.val < val) // this.val<val
				return right != null ? right.delete(val) : false;

			if (deleted)
				return false;

			deleted = true;

			IntScapegoatTreeNode node = this;

			while (node != null) {
				--node.size;
				node = node.parent;
			}

			return true;
		}
	}

	/**
	 * 中序遍历子树，将子树拍扁成有序数组
	 * 
	 * @param node
	 * @param flatTree
	 * @param index
	 * @return
	 */
	private static int dfs(IntScapegoatTreeNode node, IntScapegoatTreeNode[] flatTree, int index) {

		if (node == null)
			return index;

		index = dfs(node.left, flatTree, index);
		if (!node.deleted)
			flatTree[index++] = node;
		index = dfs(node.right, flatTree, index);

		return index;
	}

	/**
	 * 把拍扁的子树重新拎起来，中间的节点为父节点，左右两边的分别形成左右子树
	 * 形成子树的规则是一样的
	 * 
	 * @param flatTree
	 * @param l
	 * @param r
	 * @return
	 */
	private static IntScapegoatTreeNode rebuild(IntScapegoatTreeNode[] flatTree, int l, int r,
			IntScapegoatTreeNode parent) {

		if (l >= r)
			return null;

		int mid = (l + r) >>> 1;

		IntScapegoatTreeNode node = flatTree[mid];

		node.left = rebuild(flatTree, l, mid, node);
		node.right = rebuild(flatTree, mid + 1, r, node);
		node.parent = parent;

		node.reCalculateSizeAndNodeCount();

		return node;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public int size() {
		return head == null ? 0 : head.size;
	}

	@Override
	public boolean add(int element) {
		if (head == null) {
			head = new IntScapegoatTreeNode(element, null);
			return true;
		}

		return head.add(element);
	}

	@Override
	public boolean contain(int element) {
		return head == null ? false : head.contain(element);
	}

	@Override
	public boolean remove(int element) {
		return head == null ? false : head.delete(element);
	}

	@Override
	public void clear() {
		head = null;
	}

	@Override
	public IntIterator iterator() {
		return new IntScapegoatTreeIterator(this);
	}

	private static class IntScapegoatTreeIterator implements IntIterator {

		private IntScapegoatTreeNode next;

		public IntScapegoatTreeIterator(IntScapegoatTree tree) {
			super();

			next = tree.head;

			if (next != null)
				while (next.left != null)
					next = next.left;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public int next() {
			if (next == null)
				throw new NoSuchElementException("No next element");

			int val = next.val;

			// 按照上面的规则找到下一个需要遍历的节点，即比这个节点大的最小节点
			if (next.right != null) {
				next = next.right;
				while (next.left != null)
					next = next.left;
			} else {
				IntScapegoatTreeNode ch = next;
				next = next.parent;
				while (next != null && ch == next.right) {
					ch = next;
					next = next.parent;
				}
			}

			return val;
		}
	}

}
