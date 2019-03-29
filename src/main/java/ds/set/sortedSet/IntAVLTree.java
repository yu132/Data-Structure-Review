package ds.set.sortedSet;

import java.util.NoSuchElementException;

import ds.IntIterable;
import ds.IntIterator;
import ds.set.IntSet;

/**
 * @see org.apache.commons.math3.geometry.partitioning.utilities.AVLTree
 * 
 * 从这个类修改得到的，我改的这个类还是不能加入重复元素的，一般的平衡二叉树
 * 
 * @author 87663
 *
 */
public class IntAVLTree implements IntSet, IntIterable {

	private IntAVLTreeNode	head;

	private int				size;

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(int element) {
		if (head == null) {
			head = new IntAVLTreeNode(element, null);
			++size;
			return true;
		}

		if (head.insert(element)) {
			++size;
			return true;
		}

		return false;
	}

	@Override
	public boolean contain(int element) {
		return head == null ? false : head.find(element);
	}

	@Override
	public boolean remove(int element) {

		// if (!contain(element))
		// return false;

		if (head == null)
			return false;

		if (head.parent == null && head.left == null && head.right == null && head.val == element) {
			head = null;
			size = 0;
			return true;
		}

		if (head.delete(element)) {
			--size;
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	private enum BF {
		LEFT_HIGH, RIGHT_HIGH, BALANCED;
	}

	public final class IntAVLTreeNode {
		private int				val;

		private IntAVLTreeNode	parent;
		private IntAVLTreeNode	left;
		private IntAVLTreeNode	right;

		private BF				balanceFactor;

		IntAVLTreeNode(int val, IntAVLTreeNode parent) {
			this.val = val;

			this.parent = parent;
			this.left = null;
			this.right = null;

			this.balanceFactor = BF.BALANCED;
		}

		public int getValue() {
			return val;
		}

		public int size() {
			return 1 + (left == null ? left.size() : 0) + (right == null ? right.size() : 0);
		}

		public IntAVLTreeNode getSmallest() {
			IntAVLTreeNode node = this;
			while (node.left != null)
				node = node.left;
			return node;
		}

		public IntAVLTreeNode getLargest() {
			IntAVLTreeNode node = this;
			while (node.right != null)
				node = node.right;
			return node;
		}

		public IntAVLTreeNode getPrevious() {
			if (left != null)
				return left.getLargest();

			for (IntAVLTreeNode node = this; node.parent != null; node = node.parent)
				if (node != node.parent.left)
					return node;

			return null;
		}

		public IntAVLTreeNode getNext() {
			if (right != null)
				return right.getSmallest();

			for (IntAVLTreeNode node = this; node.parent != null; node = node.parent)
				if (node != node.parent.right)
					return node;

			return null;
		}

		boolean insert(int val) {
			if (val == this.val)
				return false;

			boolean leftGrown;
			if (val < this.val) {
				if (left == null) {
					left = new IntAVLTreeNode(val, this);
					leftGrown = true;
				} else
					return left.insert(val);
			} else {// val>this.val

				if (right == null) {
					right = new IntAVLTreeNode(val, this);
					leftGrown = false;
				} else
					return right.insert(val);
			}

			IntAVLTreeNode node = this;
			while (leftGrown ? node.rebalanceLeftGrown() : node.rebalanceRightGrown()) {
				if (node.parent == null)
					break;
				leftGrown = node == node.parent.left;
				node = node.parent;
			}

			return true;
		}

		boolean find(int val) {
			if (val < this.val)
				return left != null ? left.find(val) : false;
			else if (val > this.val)
				return right != null ? right.find(val) : false;

			return true;
		}

		boolean delete(int val) {
			if (val < this.val)
				return left != null ? left.delete(val) : false;
			else if (val > this.val)
				return right != null ? right.delete(val) : false;

			IntAVLTreeNode node;
			IntAVLTreeNode child;

			if (left == null && right == null) {
				node = this;
				child = null;
			} else {
				node = left != null ? left.getLargest() : right.getSmallest();
				this.val = node.val;
				child = node.left != null ? node.left : node.right;
			}

			boolean leftShrunk = node == node.parent.left;

			node = node.parent;

			if (leftShrunk)
				node.left = child;
			else
				node.right = child;

			if (child != null)
				child.parent = node;

			while (leftShrunk ? node.rebalanceLeftShrunk() : node.rebalanceRightShrunk()) {
				if (node.parent == null)
					break;
				leftShrunk = node == node.parent.left;
				node = node.parent;
			}

			return true;
		}

		private boolean rebalanceLeftGrown() {
			switch (balanceFactor) {
				case LEFT_HIGH:
					if (left.balanceFactor == BF.LEFT_HIGH) {
						rotateCW();
						balanceFactor = BF.BALANCED;
						right.balanceFactor = BF.BALANCED;
					} else {
						BF s = left.right.balanceFactor;
						left.rotateCCW();
						rotateCW();
						switch (s) {
							case LEFT_HIGH:
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.RIGHT_HIGH;
								break;
							case RIGHT_HIGH:
								left.balanceFactor = BF.LEFT_HIGH;
								right.balanceFactor = BF.BALANCED;
								break;
							default:
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.BALANCED;
						}
						balanceFactor = BF.BALANCED;
					}
					return false;
				case RIGHT_HIGH:
					balanceFactor = BF.BALANCED;
					return false;
				default:
					balanceFactor = BF.LEFT_HIGH;
					return true;
			}
		}

		private boolean rebalanceRightGrown() {
			switch (balanceFactor) {
				case LEFT_HIGH:
					balanceFactor = BF.BALANCED;
					return false;
				case RIGHT_HIGH:
					if (right.balanceFactor == BF.RIGHT_HIGH) {
						rotateCCW();
						balanceFactor = BF.BALANCED;
						left.balanceFactor = BF.BALANCED;
					} else {
						final BF s = right.left.balanceFactor;
						right.rotateCW();
						rotateCCW();
						switch (s) {
							case LEFT_HIGH:
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.RIGHT_HIGH;
								break;
							case RIGHT_HIGH:
								left.balanceFactor = BF.LEFT_HIGH;
								right.balanceFactor = BF.BALANCED;
								break;
							default:
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.BALANCED;
						}
						balanceFactor = BF.BALANCED;
					}
					return false;
				default:
					balanceFactor = BF.RIGHT_HIGH;
					return true;
			}
		}

		private boolean rebalanceLeftShrunk() {
			switch (balanceFactor) {
				case LEFT_HIGH:
					balanceFactor = BF.BALANCED;
					return true;
				case RIGHT_HIGH:
					if (right.balanceFactor == BF.RIGHT_HIGH) {
						rotateCCW();
						balanceFactor = BF.BALANCED;
						left.balanceFactor = BF.BALANCED;
						return true;
					} else if (right.balanceFactor == BF.BALANCED) {
						rotateCCW();
						balanceFactor = BF.LEFT_HIGH;
						left.balanceFactor = BF.RIGHT_HIGH;
						return false;
					} else {
						final BF s = right.left.balanceFactor;
						right.rotateCW();
						rotateCCW();
						switch (s) {
							case LEFT_HIGH:
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.RIGHT_HIGH;
								break;
							case RIGHT_HIGH:
								left.balanceFactor = BF.LEFT_HIGH;
								right.balanceFactor = BF.BALANCED;
								break;
							default:
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.BALANCED;
						}
						balanceFactor = BF.BALANCED;
						return true;
					}
				default:
					balanceFactor = BF.RIGHT_HIGH;
					return false;
			}
		}

		private boolean rebalanceRightShrunk() {
			switch (balanceFactor) {
				case RIGHT_HIGH:
					balanceFactor = BF.BALANCED;
					return true;
				case LEFT_HIGH:
					if (left.balanceFactor == BF.LEFT_HIGH) {
						rotateCW();
						balanceFactor = BF.BALANCED;
						right.balanceFactor = BF.BALANCED;
						return true;
					} else if (left.balanceFactor == BF.BALANCED) {
						rotateCW();
						balanceFactor = BF.RIGHT_HIGH;
						right.balanceFactor = BF.LEFT_HIGH;
						return false;
					} else {
						final BF s = left.right.balanceFactor;
						left.rotateCCW();
						rotateCW();
						switch (s) {
							case LEFT_HIGH:
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.RIGHT_HIGH;
								break;
							case RIGHT_HIGH:
								left.balanceFactor = BF.LEFT_HIGH;
								right.balanceFactor = BF.BALANCED;
								break;
							default:
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.BALANCED;
						}
						balanceFactor = BF.BALANCED;
						return true;
					}
				default:
					balanceFactor = BF.LEFT_HIGH;
					return false;
			}
		}

		// 左旋
		private void rotateCW() {

			int tempVal = val;
			val = left.val;
			left.val = tempVal;

			IntAVLTreeNode tempNode = left;
			left = tempNode.left;
			tempNode.left = tempNode.right;
			tempNode.right = right;
			right = tempNode;

			if (left != null)
				left.parent = this;
			if (right.right != null)
				right.right.parent = right;
		}

		// 右旋
		private void rotateCCW() {

			int tempVal = val;
			val = right.val;
			right.val = tempVal;

			IntAVLTreeNode tempNode = right;
			right = tempNode.right;
			tempNode.right = tempNode.left;
			tempNode.left = left;
			left = tempNode;

			if (right != null)
				right.parent = this;
			if (left.left != null)
				left.left.parent = left;
		}
	}

	@Override
	public IntIterator iterator() {
		return new IntAVLTreeIterator(this);
	}

	private static class IntAVLTreeIterator implements IntIterator {

		private IntAVLTreeNode next;

		public IntAVLTreeIterator(IntAVLTree tree) {
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
				IntAVLTreeNode ch = next;
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
