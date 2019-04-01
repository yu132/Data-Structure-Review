package ds.set.sortedSet;

import java.util.NoSuchElementException;

import ds.IntIterable;
import ds.IntIterator;
import ds.set.IntSet;

/**
 * 
 * 红黑树  基本上是自己写的，除了添加部分参考了HashMap的红黑树实现
 * 
 * @author 87663
 *
 */
public class IntRedBlackTree implements IntSet, IntIterable {

	private IntRedBlackTreeNode	head;

	private int					size;

	private class IntRedBlackTreeNode {
		int					val;

		IntRedBlackTreeNode	left;
		IntRedBlackTreeNode	right;
		IntRedBlackTreeNode	parent;

		boolean				red;

		public IntRedBlackTreeNode(int val, IntRedBlackTreeNode parent) {
			super();
			this.val = val;
			this.parent = parent;
		}

	}

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
			head = new IntRedBlackTreeNode(element, null);
			head.red = false;
			++size;
			return true;
		}

		IntRedBlackTreeNode node = head;

		while (true) {
			if (element == node.val)
				return false;
			if (element < node.val) {

				if (node.left == null) {
					node.left = new IntRedBlackTreeNode(element, node);
					node = node.left;
					break;
				}

				node = node.left;

			} else {// element>node.val

				if (node.right == null) {
					node.right = new IntRedBlackTreeNode(element, node);
					node = node.right;
					break;
				}

				node = node.right;

			}
		}

		++size;

		// 维护红黑树性质 仿HashMap红黑树部分(java8)实现
		for (IntRedBlackTreeNode x = node, xp, xpp, xppl, xppr;;) {

			// 新加入的节点是红色的（或者递归需要变成红色）
			x.red = true;

			// 父亲不存在，为根节点，涂黑即可
			if ((xp = x.parent) == null) {
				x.red = false;
				return true;
			}

			// 父亲是黑节点，或者父亲节点是根节点（那应该必为黑节点，其实有点奇怪），那么无需操作
			if (!xp.red || (xpp = xp.parent) == null)
				return true;

			// 父亲是红色节点
			// 如果父亲是祖父的左子节点
			if (xp == (xppl = xpp.left)) {

				// 如果叔叔节点是非空节点并且为红色，那么将其和父亲涂黑即可（保证左右子树的到叶子节点上黑色节点数量相同）
				// 祖父涂成红色，保证整颗子树从根到叶子节点的黑色节点数量不变（因为父亲变黑了，祖父是黑的（因为叔叔是红色的），所以祖父需要变红）
				if ((xppr = xpp.right) != null && xppr.red) {

					xppr.red = false;
					xp.red = false;
					// xpp.red = true;
					x = xpp;// 循环

				} else {

					if (x == xp.right) // 如果是右子节点，先进行左旋变成左子节点
						rotateLeft(xp);// 然后就和是左字节点的情况一样了

					// 如果是左子节点，直接右旋就可以了
					rotateRight(xpp);

					// 由于父亲是红色的，祖父是黑色的，右旋之后只是父亲变成叔叔，所以颜色都不变
					// 祖父没有变色，所以无需递归
					break;
				}

			} else {// 和上面同理，就是左边变成了右边的情况

				if (xppl != null && xppl.red) {

					xppl.red = false;
					xp.red = false;
					// xpp.red = true;
					x = xpp;// 循环

				} else {

					if (x == xp.left)
						rotateRight(xp);

					rotateLeft(xpp);

					break;
				}

			}
		}

		return true;
	}

	// 右旋 向右旋转 顺时针旋转90度
	// 由于这里的旋转实际上是没有改变节点的上下位置，就是把左边的节点拿到了右边
	// 所以很多情况下的颜色并没有发生改变
	// 同时也无需注意node的父节点和这颗子树的root的关系
	// 是一种很好的实现方法，就是可读性会变差
	public void rotateRight(IntRedBlackTreeNode node) {
		int tempVal = node.val;
		node.val = node.left.val;
		node.left.val = tempVal;

		IntRedBlackTreeNode tempNode = node.left;
		node.left = tempNode.left;
		tempNode.left = tempNode.right;
		tempNode.right = node.right;
		node.right = tempNode;

		if (node.left != null)
			node.left.parent = node;
		if (node.right.right != null)
			node.right.right.parent = node.right;
	}

	// 左旋 向左旋转 逆时针旋转90度
	// 原理同上
	public void rotateLeft(IntRedBlackTreeNode node) {
		int tempVal = node.val;
		node.val = node.right.val;
		node.right.val = tempVal;

		IntRedBlackTreeNode tempNode = node.right;
		node.right = tempNode.right;
		tempNode.right = tempNode.left;
		tempNode.left = node.left;
		node.left = tempNode;

		if (node.right != null)
			node.right.parent = node;
		if (node.left.left != null)
			node.left.left.parent = node.left;
	}

	@Override
	public boolean contain(int element) {
		if (head == null)
			return false;

		IntRedBlackTreeNode node = head;

		while (node != null) {
			if (element < node.val)
				node = node.left;
			else if (element > node.val)
				node = node.right;
			else// element==node.val
				return true;
		}

		return false;
	}

	@Override
	public boolean remove(int element) {
		if (head == null)
			return false;

		if (head.left == null && head.right == null && head.val == element) {
			head = null;
			return true;
		}

		IntRedBlackTreeNode node = head;

		while (true) {
			if (node == null)
				return false;

			if (element == node.val)
				break;
			if (element < node.val)
				node = node.left;
			else//element > node.val
				node = node.right;
		}

		--size;

		IntRedBlackTreeNode toDeleteNode;

		//寻找一个可以删除的叶子节点，并将值替换上去
		if (node.left != null && node.right != null) {
			toDeleteNode = node.right;
			while (toDeleteNode.left != null)
				toDeleteNode = toDeleteNode.left;
			node.val = toDeleteNode.val;
		} else if (node.left != null) {
			toDeleteNode = node.left;
			node.val = toDeleteNode.val;
		} else if (node.right != null) {
			toDeleteNode = node.right;
			node.val = toDeleteNode.val;
		} else {//node.left == null && node.right == null
			toDeleteNode = node;
		}

		//维护红黑树性质 思想见:https://www.cnblogs.com/qingergege/p/7351659.html 实现是我自己写的
		IntRedBlackTreeNode p = toDeleteNode;
		while (true) {
			//case1 p为红色节点
			if (p.red || p == head) {
				break;
			}

			//p 为黑的情况

			//case2 p为黑，并且有且仅有一个红色子节点 仅当p是需要删除的节点时才能这样
			if (toDeleteNode == p) {

				if (p.left != null) {
					p.val = p.left.val;
					toDeleteNode = p.left;
					break;
				} else if (p.right != null) {
					p.val = p.right.val;
					toDeleteNode = p.right;
					break;
				}
			}

			//p为黑且是叶子节点
			IntRedBlackTreeNode pp = p.parent, ppl = p.parent.left, ppr = p.parent.right;

			if (p == ppl) {
				//case3 自己为黑 兄弟节点为红 父亲节点为黑 兄弟节点必定有两个黑色子节点
				if (ppr.red) {
					rotateLeft(pp);//左旋后统一情况
					pp = p.parent;
					ppr = p.parent.right;
				}

				//现在自己为黑，兄弟节点为黑，需要判断远侄子节点是什么颜色 现在父亲节点的颜色是未知的
				IntRedBlackTreeNode pprl = ppr.left, pprr = ppr.right;

				//case4 远侄子为红色
				if (pprr != null && pprr.red) {//如果是删除的那个节点，其侄子非空肯定是红色的，
					rotateLeft(pp);				//但是由于可以递归，所以并不是这样，这个地方很坑啊
					pprr.red = false;//旋转后，原来的远侄子节点置为黑色
					break;
				}

				//case5 近侄子节点为红
				if (pprl != null && pprl.red) {
					rotateRight(ppr);//旋转之后远侄子变成红色，会变成case4的情况
					rotateLeft(pp);//然后再像case4一样旋转就可以了，但是不可以先判断case5，并转换成case4
					pprl.red = false;	//因为远侄子节点可能是红色的，先判定的话，会出现ppr和pprr都是红色的
					break;				//并且是相邻节点的情况，会违背红黑树的性质5
				}

				//两个侄子都是空节点

				//case6 父节点为红色 自己为黑 兄弟也是黑
				if (pp.red) {
					pp.red = false;
					ppr.red = true;
					break;
				}

				//case7 父亲节点是黑色  自己为黑 兄弟也是黑

				ppr.red = true;

				//由于处理完之后pp为根的这颗子树的黑高度减少了1，
				//所以需要递归处理，相当于要删除p，但是实际上是不删的

				p = pp;

			} else {
				//case3
				if (ppl.red) {
					rotateRight(pp);//to other case
					pp = p.parent;
					ppl = p.parent.left;
				}

				IntRedBlackTreeNode ppll = ppl.left, pplr = ppl.right;

				//case4
				if (ppll != null && ppll.red) {
					rotateRight(pp);
					ppll.red = false;
					break;
				}

				//case5
				if (pplr != null && pplr.red) {
					rotateLeft(ppl);
					rotateRight(pp);
					pplr.red = false;
					break;
				}

				//case6
				if (pp.red) {
					pp.red = false;
					ppl.red = true;
					break;
				}

				//case7
				ppl.red = true;

				p = pp;
			}
		}

		//删除节点
		if (toDeleteNode == toDeleteNode.parent.left) {
			toDeleteNode.parent.left = null;
		} else {
			toDeleteNode.parent.right = null;
		}

		return true;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public IntIterator iterator() {
		return new IntRedBlackTreeIterator(this);
	}

	private class IntRedBlackTreeIterator implements IntIterator {
		private IntRedBlackTreeNode next;

		public IntRedBlackTreeIterator(IntRedBlackTree tree) {
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
				IntRedBlackTreeNode ch = next;
				next = next.parent;
				while (next != null && ch == next.right) {
					ch = next;
					next = next.parent;
				}
			}

			return val;
		}
	}

	// 下面的代码都是用来测试的，和实现没有关系

	public int height() {
		if (head == null)
			return 0;
		return height(head, 1);
	}

	public int height(IntRedBlackTreeNode node, int h) {
		if (node == null)
			return h - 1;

		int left = 0;
		if (node.left != null)
			left = height(node.left, h + 1);
		int right = 0;
		if (node.right != null)
			right = height(node.right, h + 1);

		return Math.max(left, right);
	}

	public boolean check() {
		return check(head);
	}

	public int checkH(IntRedBlackTreeNode node) {
		if (node == null)
			return 0;
		return checkH(node.left) + (node.red ? 0 : 1);
	}

	private boolean check(IntRedBlackTreeNode node) {
		if (node == null)
			return true;

		IntRedBlackTreeNode p = node, pp = node.parent, pl = node.left, pr = node.right;

		if (pp != null && p != pp.left && p != pp.right) {
			System.out.println("e1");
			return false;
		}

		if (pl != null && (pl.parent != p || pl.val > p.val)) {
			System.out.println("e2");
			return false;
		}

		if (pr != null && (pr.parent != p || pr.val < p.val)) {
			System.out.println("e3");
			return false;
		}

		if (p.red && pl != null && pl.red) {
			System.out.println("e4");
			return false;
		}

		if (p.red && pr != null && pr.red) {
			System.out.println("e5");
			return false;
		}

		if (checkH(pl) != checkH(pr)) {
			System.out.println(checkH(pl) + " " + checkH(pr));
			System.out.println("e8");
			return false;
		}

		if (pl != null && !check(pl)) {
			System.out.println("e6");
			return false;
		}

		if (pr != null && !check(pr)) {
			System.out.println("e7");
			return false;
		}

		return true;
	}
}
