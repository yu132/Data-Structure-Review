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

		/**
		 * 调节左边插入的影响
		 * 
		 * 前注：
		 * 这个节点位于图中的A位置
		 * h(node) => 以node为根的某棵子树的高度
		 * bf(node) => 某个节点的平衡因子(balence factor)
		 * 
		 * 右高：
		 * 插入B后平衡，无需旋转
		 * 
		 *                 A            
		 *          B(h)        AR(h)
		 *          
		 *             （添加后）
		 *             
		 * 平衡：
		 * 插入B后左高，无需旋转
		 * 
		 *                 A            
		 *          B(h+1)       AR(h)      
		 *          
		 * 			      （添加后）
		 * 
		 * 左高：
		 * LL旋转：
		 * 
		 * 由于BL处进行了插入而产生的旋转
		 * 
		 * 进行了一次右旋，由于旋转之后所有子树的高度一样，所以平衡因子都为0
		 * 
		 * 旋转之后h(C)=h+2 和插入之前的h(A)的高度相等，所以上面的部分肯定是平衡的
		 * 
		 * 
		 *                 A                                 B
		 *          B           AR(h)       =>      B(h+1)            A
		 *   BL(h+1)  BR(h)                                     BR(h)    AL(h)
		 * 
		 * 				（添加后）						    （旋转后）
		 * 
		 * LR旋转：
		 * 
		 * 由于C处进行了插入而产生的旋转
		 * 
		 * 先进行左旋（B处），后进行右旋（A处）
		 * 
		 * p=h(CL)-h(CR) （旋转前）
		 * 
		 * 1.p=0 即h(CL)=h(CR)=h=0 那么旋转之后的高度相等,
		 * 即 h(BL)=h(CL)=h(CR)=h(AR)=h=0，那么bf(B)=bf(A)=0
		 * 
		 * 2.p=1 即h(CL)=h(CR)+1=h 那么选转后h(BL)=h(AR)=h(CL)=h(CR)+1=h
		 * 那么bf(B)=h(BL)-h(CL)=h-h=0  bf(A)=h(CR)-h(AR)=(h-1)-h=-1
		 * 
		 * 3.p=-1 即h(CL)+1=h(CR)=h 那么选转后h(BL)=h(AR)=h(CL)+1=h(CR)=h
		 * 那么bf(B)=h(BL)-h(CL)=h-(h-1)=1  bf(A)=h(CR)-h(AR)=h-h=0
		 * 
		 * 旋转之后h(C)=h+2 和插入之前的h(A)的高度相等，所以上面的部分肯定是平衡的
		 * 
		 *                    A                                 C
		 *           B             AR(h)     =>           B             A
		 *   BL(h)      C(h+1)                      BL(h)    CL    CR     AR(h)
		 *            CL    CR
		 *            
		 *                （添加后）						       （旋转后）
		 *            
		 */
		private boolean rebalanceLeftGrown() {
			switch (balanceFactor) {
				case LEFT_HIGH:// 左高
					if (left.balanceFactor == BF.LEFT_HIGH) {// 如果左子树高 LL旋转
						rotateCW();
						balanceFactor = BF.BALANCED;
						right.balanceFactor = BF.BALANCED;
					} else {// 右子树高则LR旋转
						BF s = left.right.balanceFactor;
						left.rotateCCW();
						rotateCW();
						switch (s) {// LR旋转的三种情况		// bf(B)=left.balanceFactor
							case LEFT_HIGH:// case2 		// bf(A)=right.balanceFactor
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.RIGHT_HIGH;
								break;
							case RIGHT_HIGH://case3
								left.balanceFactor = BF.LEFT_HIGH;
								right.balanceFactor = BF.BALANCED;
								break;
							default://case0 p=0
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.BALANCED;
						}
						balanceFactor = BF.BALANCED;
					}
					return false;
				case RIGHT_HIGH:// 右高 左增 则平衡
					balanceFactor = BF.BALANCED;
					return false;
				default:// 平衡 左增 则左高
					balanceFactor = BF.LEFT_HIGH;
					return true;
			}
		}

		/**
		 * 
		 * 进行了一次左旋，由于旋转之后所有子树的高度一样，所以平衡因子都为0
		 * 
		 * 旋转之后h(C)=h+2 和插入之前的h(A)的高度相等，所以上面的部分肯定是平衡的
		 * 
		 *             A                                 B
		 *      AL(h)       B           =>         A         BR(h+1)
		 *            BL(h)   BR(h+1)        AL(h)   BL(h)
		 *            
		 *            
		 *            
		 *            TODO
		 */
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

		// LL旋转 右旋 顺时针旋转90度
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

		// RR旋转 左旋 逆时针旋转90度
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
