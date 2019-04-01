package ds.set.sortedSet;

import java.util.NoSuchElementException;

import ds.IntIterable;
import ds.IntIterator;
import ds.set.IntSet;

/**
 * 
 * AVL平衡树 废弃 有bug 平衡因子调整不正常
 * 
 * 注释可以参考，应该还有点价值
 * 
 * @author 87663
 *
 */
public class WrongOne implements IntSet, IntIterable {

	private static class IntAVLTreeNode {
		int				val;
		int				balenceFactor;

		IntAVLTreeNode	parent;

		IntAVLTreeNode	left;
		IntAVLTreeNode	right;
	}

	private int				size;

	private IntAVLTreeNode	head;

	/**
	 * 从左向右旋转(A)
	 * 
	 *          A                        B
	 *      B       AR      =>      BL         A
	 *   BL   BR                            BR    AR
	 * 
	 * @param node
	 */
	private void llRotation(IntAVLTreeNode node) {
		if (node == head)
			head = node.left;
		else if (node.parent.left == node)
			node.parent.left = node.left;
		else// node.parent.right==node
			node.parent.right = node.left;

		node.parent = node.left;
		node.left = node.left.right;
	}

	/**
	 * 从右向左旋转(A)
	 * 
	 *           A                        B
	 *      AL       B      =>      A         BR
	 *            BL   BR        AL    BL
	 * 
	 * @param node
	 */
	private void rrRotation(IntAVLTreeNode node) {
		if (node == head)
			head = node.right;
		else if (node.parent.left == node)
			node.parent.left = node.right;
		else// node.parent.right==node
			node.parent.right = node.right;

		node.parent = node.right;
		node.right = node.right.left;
	}

	/**
	 * 先左旋(B)再右旋(A)
	 * 
	 *             A                              C
	 *       B             AR      =>        B          A
	 *   BL      C                       BL    CL    CR     AR
	 *         CL  CR
	 * 
	 * @param node
	 */
	private void lrRotation(IntAVLTreeNode node) {
		if (node == head)
			head = node.left.right;
		else if (node.parent.left == node)
			node.parent.left = node.left.right;
		else// node.parent.right==node
			node.parent.right = node.left.right;

		node.parent = node.left.right;

		node.left.right = node.parent.left;
		node.parent.left = node.right;

		node.left = node.parent.right;
		node.parent.right = node;
	}

	/**
	 * 先右旋(B)再左旋(A)
	 * 
	 *               A                               C
	 *       AL             B        =>        A            B
	 *                 C       BR           AL    CL    CR     BR
	 *              CL   CR
	 *              
	 * @param node
	 */
	private void rlRotation(IntAVLTreeNode node) {
		if (node == head)
			head = node.right.left;
		else if (node.parent.right == node)
			node.parent.right = node.right.left;
		else// node.parent.left==node
			node.parent.left = node.right.left;

		node.parent = node.right.left;

		node.right.left = node.parent.right;
		node.parent.right = node.left;

		node.right = node.parent.left;
		node.parent.left = node;
	}

	/**
	 * 
	 * 由于BL处进行了插入而产生的旋转
	 * 
	 * 进行了一次左旋，由于旋转之后所有子树的高度一样，所以平衡因子都为0
	 * 
	 * 旋转之后h(C)=h+2 和插入之前的h(A)的高度相等，所以上面的部分肯定是平衡的
	 * 
	 * 
	 *                 A                                 B
	 *          B           AR(h)     =>      B(h+1)              A
	 *   BL(h+1)  BR(h)                                     BR(h)    AL(h)
	 * 
	 * @param node
	 */
	private void LL(IntAVLTreeNode node) {
		llRotation(node);

		node.balenceFactor = 0;
		node.parent.balenceFactor = 0;
	}

	/**
	 * 由于BR处进行了插入而产生的旋转
	 * 
	 * 进行了一次右旋，由于旋转之后所有子树的高度一样，所以平衡因子都为0
	 * 
	 * 旋转之后h(C)=h+2 和插入之前的h(A)的高度相等，所以上面的部分肯定是平衡的
	 * 
	 *             A                                 B
	 *      AL(h)       B           =>         A         BR(h+1)
	 *            BL(h)   BR(h+1)        AL(h)   BL(h)
	 *            
	 * @param node
	 */
	private void RR(IntAVLTreeNode node) {
		rrRotation(node);

		node.balenceFactor = 0;
		node.parent.balenceFactor = 0;
	}

	/**
	 * 由于C处进行了插入而产生的旋转
	 * 
	 * 先左旋再右旋的旋转方法
	 * 
	 * 实现中没有旋转两次，为了避免重复处理头部的问题
	 * 
	 * h(node) => 以node为根的某棵子树的高度
	 * bf(node) => 某个节点的平衡因子(balence factor)
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
	 *                    A                                  C
	 *           B             AR(h)      =>           B             A
	 *   BL(h)      C(h+1)                       BL(h)    CL    CR     AR(h)
	 *            CL    CR
	 * @param node
	 */
	private void LR(IntAVLTreeNode node) {
		lrRotation(node);

		if (node.parent.balenceFactor == 0) {
			node.balenceFactor = 0;
			node.parent.left.balenceFactor = 0;
		} else if (node.parent.balenceFactor == 1) {
			node.balenceFactor = -1;
			node.parent.left.balenceFactor = 0;
		} else {// node.parent.balenceFactor==-1;
			node.balenceFactor = 0;
			node.parent.left.balenceFactor = 1;
		}
	}

	/**
	 * 由于C处进行了插入而产生的旋转
	 * 
	 * 先右旋再左旋的旋转方法
	 * 
	 * 实现中没有旋转两次，为了避免重复处理头部的问题
	 * 
	 * h(node) => 以node为根的某棵子树的高度
	 * bf(node) => 某个节点的平衡因子(balence factor)
	 * 
	 * p=h(CL)-h(CR) （旋转前）
	 * 
	 * 1.p=0 即h(CL)=h(CR)=h=0 那么旋转之后的高度相等,
	 * 即 h(AL)=h(CL)=h(CR)=h(BR)=h=0，那么bf(B)=bf(A)=0
	 * 
	 * 2.p=1 即h(CL)=h(CR)+1=h 那么旋转后h(AL)=h(BR)=h(CL)=h(CR)+1=h
	 * 那么bf(A)=h(AL)-h(CL)=h-h=0  bf(B)=h(CR)-h(BR)=(h-1)-h=-1
	 * 
	 * 3.p=-1 即h(CL)+1=h(CR)=h 那么选转后h(AL)=h(BR)=h(CL)+1=h(CR)=h
	 * 那么bf(A)=h(AL)-h(CL)=h-(h-1)=1  bf(B)=h(CR)-h(BR)=h-h=0
	 * 
	 * 旋转之后h(C)=h 和插入之前的h(A)的高度相等，所以上面的部分肯定是平衡的
	 * 
	 *                 A                                  C
	 *       AL(h)             B          =>        A            B
	 *                 C(h+1)    BR(h)          AL(h)  CL    CR     BR(h)
	 *              CL   CR
	 * 
	 * @param node
	 */
	private void RL(IntAVLTreeNode node) {
		rlRotation(node);

		if (node.parent.balenceFactor == 0) {
			node.balenceFactor = 0;
			node.parent.right.balenceFactor = 0;
		} else if (node.parent.balenceFactor == -1) {
			node.balenceFactor = 1;
			node.parent.right.balenceFactor = 0;
		} else {// node.parent.balenceFactor==1;
			node.balenceFactor = 0;
			node.parent.right.balenceFactor = -1;
		}
	}

	/**
	 * 由于AL处进行了删除而产生的旋转
	 * 
	 * p=bf(B)=h(BL)-h(BR) （旋转前）
	 * 
	 * p=0 则 h(BL)=h 时，旋转后的 bf(A)=h(AL)-h(BL)=(h-1)-h=-1
	 * h(A)=MAX(h(AL),h(BL))+1=h+1  bf(B)=h(A)-h(BR)=(h+1)-h=1
	 * 
	 * p=-1 则 h(BL)=h-1 时，旋转后的  bf(A)=h(AL)-h(BL)=(h-1)-(h-1)=0
	 * h(A)=MAX(h(AL),h(BL))+1=(h-1)+1=h  bf(B)=h(A)-h(BL)=h-h=0
	 * 
	 *               A                              B
	 *      AL(h-1)       B        =>          A         BR(h)
	 *                 BL   BR(h)        AL(h-1)  BL
	 *   
	 * @param node
	 */
	private void L0Minus1(IntAVLTreeNode node) {
		rrRotation(node);

		if (node.parent.balenceFactor == 0) {// LO
			node.balenceFactor = -1;
			node.parent.balenceFactor = 1;
		} else {// node.parent.balenceFactor==-1 //L-1
			node.balenceFactor = 0;
			node.parent.balenceFactor = 0;
		}
	}

	/**
	 * 由于AR处进行了删除而产生的旋转
	 * 
	 * p=bf(C)=h(CL)-h(CR) （旋转前）
	 * 
	 * 1.p=0 时 h(CL)=h(CR)=0 h(C)=h=1 h(AL)=h(BR)=0
	 * h(AL)=h(CL)=h(CR)=h(BR)=0 旋转后 bf(A)=bf(B)=0
	 * h(B)=MAX(h(BL),h(CL))+1=h-1+1=h=1  h(A)=MAX(h(CR),h(AR))+1=h-1+1=h=1
	 * bf(C)=h(B)-h(A)=0
	 * 
	 * 2.p=1 时 h(CL)=h(CR)+1=h-1  h(AL)=h(CL)=h(CR)+1=h(BR)=h-1
	 * 旋转后  bf(A)=h(AL)=h(CL)=(h-1)-(h-1)=0  bf(B)=h(CR)-h(BR)=(h-2)-(h-1)=-1
	 * h(B)=MAX(h(BL),h(CL))+1=h-1+1=h  h(A)=MAX(h(CR),h(AR))+1=h-1+1=h
	 * bf(C)=h(B)-h(A)=0
	 * 
	 * 3.p=-1 时 h(CL)+1=h(CR)=h-1  h(AL)=h(CL)+1=h(CR)=h(BR)=h-1
	 * 旋转后  bf(A)=h(AL)=h(CL)=(h-1)-(h-2)=1  bf(B)=h(CR)-h(BR)=(h-1)-(h-1)=0
	 * h(B)=MAX(h(BL),h(CL))+1=h-1+1=h  h(A)=MAX(h(CR),h(AR))+1=h-1+1=h
	 * bf(C)=h(B)-h(A)=0
	 * 
	 *                  A                                        C
	 *       AL(h-1)           B             =>          A              B
	 *                    C(h)   BR(h-1)           AL(h-1)    CL    CR     BR(h-1)
	 *                 CL   CR    
	 *              
	 * @param node
	 */
	private void L1(IntAVLTreeNode node) {
		rlRotation(node);

		if (node.parent.balenceFactor == 0) {
			node.balenceFactor = 0;
			node.parent.right.balenceFactor = 0;
		} else if (node.parent.balenceFactor == -1) {
			node.balenceFactor = 1;
			node.parent.right.balenceFactor = 0;
		} else {// node.parent.balenceFactor==1;
			node.balenceFactor = 0;
			node.parent.right.balenceFactor = -1;
		}

		node.parent.balenceFactor = 0;
	}

	/**
	 * 由于AR处进行了删除而产生的旋转
	 * 
	 * p=bf(B)=h(BL)-h(BR) （旋转前）
	 * 
	 * p=0 则 h(BR)=h 时，旋转后的 bf(A)=h(BR)-h(AR)=h-(h-1)=1
	 * h(A)=MAX(h(BR),h(AR))+1=h+1  bf(B)=h(BL)-h(A)=h-(h+1)=-1
	 * 
	 * p=1 则 h(BR)=h-1 时，旋转后的  bf(A)=h(BR)-h(AR)=(h-1)-(h-1)=0
	 * h(A)=MAX(h(BR),h(AR))+1=(h-1)+1=h  bf(B)=h(BL)-h(A)=h-h=0
	 * 
	 *            A                               B
	 *        B       AR(h-1)      =>      BL(h)      A
	 *   BL(h)   BR                                BR    AR(h-1)
	 *   
	 *   
	 * @param node
	 */
	private void R01(IntAVLTreeNode node) {
		llRotation(node);

		if (node.parent.balenceFactor == 0) {// RO
			node.balenceFactor = 1;
			node.parent.balenceFactor = -1;
		} else {// node.parent.balenceFactor==1 //R1
			node.balenceFactor = 0;
			node.parent.balenceFactor = 0;
		}
	}

	/**
	 * 由于AR处进行了删除而产生的旋转
	 * 
	 * p=bf(C)=h(CL)-h(CR) （旋转前）
	 * 
	 * 1.p=0 时 h(CL)=h(CR)=0 h(C)=h=1 h(BL)=h(AR)=0
	 * h(BL)=h(CL)=h(CR)=h(AR)=0 旋转后 bf(B)=bf(A)=0
	 * h(B)=MAX(h(BL),h(CL))+1=h-1+1=h=1  h(A)=MAX(h(CR),h(AR))+1=h-1+1=h=1
	 * bf(C)=h(B)-h(A)=0
	 * 
	 * 2.p=1 时 h(CL)=h(CR)+1=h-1  h(BL)=h(CL)=h(CR)+1=h(AR)=h-1
	 * 旋转后 bf(B)=h(BL)-h(CL)=(h-1)-(h-1)=0  bf(A)=h(CR)=h(AR)=(h-2)-(h-1)=-1
	 * h(B)=MAX(h(BL),h(CL))+1=h-1+1=h  h(A)=MAX(h(CR),h(AR))+1=h-1+1=h
	 * bf(C)=h(B)-h(A)=0
	 * 
	 * 3.p=-1 时 h(CL)+1=h(CR)=h-1  h(BL)=h(CL)+1=h(CR)=h(AR)=h-1
	 * 旋转后 bf(B)=h(BL)-h(CL)=(h-1)-(h-2)=1  bf(A)=h(CR)=h(AR)=(h-1)-(h-1)=0
	 * h(B)=MAX(h(BL),h(CL))+1=h-1+1=h  h(A)=MAX(h(CR),h(AR))+1=h-1+1=h
	 * bf(C)=h(B)-h(A)=0
	 * 
	 *                      A                                         C
	 *            B                AR(h-1)      =>              B             A
	 *   BL(h-1)      C(h)                              BL(h-1)    CL      CR    AR(h-1)
	 *              CL   CR
	 *         
	 * @param node
	 */
	private void RMinus1(IntAVLTreeNode node) {
		lrRotation(node);

		if (node.parent.balenceFactor == 0) {
			node.balenceFactor = 0;
			node.parent.left.balenceFactor = 0;
		} else if (node.parent.balenceFactor == 1) {
			node.balenceFactor = -1;
			node.parent.left.balenceFactor = 0;
		} else {// node.parent.balenceFactor==-1;
			node.balenceFactor = 0;
			node.parent.left.balenceFactor = 1;
		}

		node.parent.balenceFactor = 0;
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

		// 如果头节点为空，那么我们直接将元素插入即可
		if (head == null) {
			head = new IntAVLTreeNode();
			head.val = element;

			++size;
			return true;
		}

		IntAVLTreeNode node = head;

		// 寻找插入位置
		while (true) {
			if (element == node.val)// 有值相同的节点，直接返回失败
				return false;

			if (element < node.val) {// 检查左边的节点
				if (node.left == null) {// 不存在左子节点，那么我们插入的位置应该就是这里
					node.left = new IntAVLTreeNode();

					node.left.val = element;
					node.left.parent = node;

					node = node.left;
					break;
				} else {// head.left!=null 存在左子节点，继续寻找插入位置
					node = node.left;
				}
			} else {// element>head.val
				if (node.right == null) {// 不存在右子节点，那么我们插入的位置应该就是这里
					node.right = new IntAVLTreeNode();

					node.right.val = element;
					node.right.parent = node;

					node = node.right;
					break;
				} else {// head.left!=null 存在右子节点，继续寻找插入位置
					node = node.right;
				}
			}
		}

		// 向上寻找需要旋转的位置
		do {
			if (node == node.parent.left) {

				++node.parent.balenceFactor;

				if (node.parent.balenceFactor == 2) {

					if (node.balenceFactor == 1) {
						LL(node.parent);
						break;
					} else if (node.balenceFactor == -1) {
						LR(node.parent);
						break;
					}
				}

			} else {// node==node.parent.right

				--node.parent.balenceFactor;

				if (node.parent.balenceFactor == -2) {

					if (node.balenceFactor == -1) {
						RR(node.parent);
						break;
					} else if (node.balenceFactor == 1) {
						RL(node.parent);
						break;
					}

				}
			}

			node = node.parent;

		} while (node.parent != head);

		++size;

		return true;
	}

	@Override
	public boolean contain(int element) {
		IntAVLTreeNode node = head;

		while (node != null) {
			if (node.val == element)
				return true;
			if (node.val < element)
				node = node.left;
			else// node.val>element
				node = node.right;
		}

		return false;
	}

	@Override
	public boolean remove(int element) {
		IntAVLTreeNode node = head;

		// 寻找待删节点
		while (node != null) {
			if (node.val == element)
				break;
			if (node.val < element)
				node = node.left;
			else// node.val>element
				node = node.right;
		}

		// 如果待删节点不存在，那么返回假
		if (node == null)
			return false;

		// 根据待删节点的类型，将情况分成4类,找到最后需要删除的节点
		if (node.left == null && node.right == null)
			;// 如果待删节点为叶子节点，那么待删节点就是这个节点
		else if (node.left == null) {// 如果待删节点有右子树，那么右子树的节点数量肯定是一个，那么我们将右子节点
			node.val = node.right.val;// 的值赋值到待删节点上，然后将待删节点改成右子节点即可
			node = node.right;
		} else if (node.right == null) {// 和第二种类似，就是待删节点改为左子节点
			node.val = node.left.val;
			node = node.left;
		} else {// node.left!=null&&node.right!=null //如果两个子树都存在，那么我们需要寻找一个
			IntAVLTreeNode nodeNext = node.right;		// 比待删节点大的最小的节点，这个节点肯定
			while (nodeNext.left != null)				// 在右子节点的最低的那个左子节点上
				nodeNext = nodeNext.left;				// 这个节点可能有右子节点，但肯定没有左子节点
			if (nodeNext.right == null) {				// （因为是最后一个左子节点）
				node.val = nodeNext.val;				// 那么我们又需要分两种情况：
				node = nodeNext;						// 1.如果没有右节点，那么我们就应该
			} else {// nodeNext.right!=null //把这个最低左子节点的值赋给待删节点
				node.val = nodeNext.val;				// 然后待删节点改成这个节点即可
				nodeNext.val = nodeNext.right.val;	// 2.如果有右子节点，那么我们需要把最低左
				node = nodeNext.right;				// 的值赋给待删节点，然后把右子节点的值赋给
			}										// 最低左子节点，然后把待删节点改成这个右子节点
		}

		// 我们已经找到待删节点，接下来就需要删除这个节点，向上回溯，将平衡因子重新计算，并且完成平衡操作

		// 如果有子节点，那么我们总是删除子节点，但是如果待删节点最终是头节点，那么证明整棵树里面就只有一个
		if (node == head) {// 头节点，并且需要删除，那么我就删除即可，并且由于删除后树是空的，无需平衡
			head = null;
			--size;
			return true;
		}

		IntAVLTreeNode toDelete = node;

		// 如果待删节点不是头节点,我们需要先更新平衡因子，并且将树进行平衡操作
		do {

			if (node == node.parent.left) {

				--node.parent.balenceFactor;

				if (node.parent.balenceFactor == -2) {

					if (node.balenceFactor == 0 || node.balenceFactor == -1) {
						L0Minus1(node.parent);
						break;
					} else if (node.balenceFactor == 1) {
						L1(node.parent);
						break;
					}
				}

			} else {// node==node.parent.right

				++node.parent.balenceFactor;

				if (node.parent.balenceFactor == 2) {

					if (node.balenceFactor == 0 || node.balenceFactor == 1) {
						R01(node.parent);
						break;
					} else if (node.balenceFactor == -1) {
						RMinus1(node.parent);
						break;
					}
				}

			}

		} while (node != head);

		// 然后我们再删除待删节点
		if (toDelete.parent.left == toDelete) {
			toDelete.parent.left = null;
		} else {
			toDelete.parent.right = null;
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
		return new IntAVLTreeIterator(this);
	}

	/**
	 * 
	 * 二叉树的中序遍历的非递归实现1，
	 * 对于搜索树来说就是正常的迭代方式，
	 * 最终元素是按大小的顺序被遍历的
	 * 
	 * 思想是先遍历左边的节点，小的节点总是在栈顶
	 * 遍历完一个节点后，我们去遍历其右子节点的所有左子节点
	 * 这些节点的值的大小是仅次于这个最小节点的，比最小节点的
	 * 父节点的值小，所以应该优先遍历，当这些右子树的节点遍历完之后
	 * 剩下就是父节点，然后再依次向上，最终返回元素是按顺序的
	 * 
	 * @author 87663
	 *
	 */
	/*
	 * private static class IntAVLTreeIterator implements IntIterator{
	 * 
	 * private Deque<IntAVLTreeNode> stack;
	 * 
	 * public IntAVLTreeIterator(IntAVLTree tree) { super();
	 * 
	 * IntAVLTreeNode node=tree.head;
	 * 
	 * stack=new ArrayDeque<>((int)(Math.log(tree.size)/Math.log(2)));
	 * 
	 * //将所有的左节点压入栈，最终栈顶是最小元素的节点 while(node!=null){ stack.push(node);
	 * node=node.left; } }
	 * 
	 * @Override public boolean hasNext() { return !stack.isEmpty(); }
	 * 
	 * @Override public int next() { if(stack.isEmpty()) throw new
	 * NoSuchElementException("No next element");
	 * 
	 * //从栈顶中取出最小元素的节点 IntAVLTreeNode node=stack.pop();
	 * 
	 * //最小值 int val=node.val;
	 * 
	 * //将右子节点的所有左节点压入栈 node=node.right; while(node!=null){ stack.push(node);
	 * node=node.left; }
	 * 
	 * //返回最小值 return val; }
	 * 
	 * }
	 */

	/**
	 * 更好的非递归实现的迭代器，纯粹就是依次遍历各个节点
	 * 
	 * 首先先将next置于最低的左子节点，即整棵树中的最小节点
	 * 
	 * 然后遍历完每个节点时，首先先检查这个节点是否有右子节点
	 * 如果有，那么还有比这个节点大，但是比其父节点小的一些元素
	 * 这些元素在这个节点的右子树上，那么就需要找到这个子树上面
	 * 最小的一个元素，所以需要找这个右子节点的最小的左子节点
	 * 如果这个右子节点没有左子节点，那么它就是最小的，next就指向它
	 * 
	 * 如果这个节点没有右子节点，那么就需要进行回溯，需要找到这个节点的父节点，
	 * 如果这个节点是父节点的左子节点，那么这个父节点就是最小的
	 * 因为比这个父节点小的元素都在其左子树上，而左子树已经遍历完成了，
	 * 返回到这个父节点上，父节点的父节点和右子节点上的值都比它大，所以
	 * 是最小的。
	 * 如果这个节点是父节点的右子节点，那么父节点肯定已经遍历过了，那么就需要去
	 * 找父节点的父节点，并且还需要检查父节点是爷爷节点的左节点还是右节点，
	 * 还是按照上面的规则
	 * 
	 * 当遍历到最后一个节点，即最低的右子节点上时，由于每个节点都是其父节点的
	 * 右子节点，那么next最终会等于head.parent，即null，证明这次遍历结束了
	 * 
	 * @author 87663
	 *
	 */
	private static class IntAVLTreeIterator implements IntIterator {

		private IntAVLTreeNode next;

		public IntAVLTreeIterator(WrongOne tree) {
			super();

			next = tree.head;

			while (next != null)
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

	public boolean check() {
		return check(head);
	}

	private boolean check(IntAVLTreeNode node) {
		if (node == null)
			return true;

		int left = height(node.left, 0);
		int right = height(node.right, 0);

		if (node.balenceFactor != left - right) {

			System.out.println(node.balenceFactor + " " + (left - right));

			System.out.println(node);

			System.out.println(height(node, 0));

			System.out.println(height(node.left, 0));

			System.out.println(height(node.right, 0));

			return false;
		}
		return check(node.right) && check(node.left);
	}

	private int height(IntAVLTreeNode node, int h) {
		if (node == null)
			return h - 1;
		return Math.max(height(node.left, h + 1), height(node.right, h + 1));
	}

	//红黑树删除废弃部分
	//		for (IntRedBlackTreeNode x = toDeleteNode, xp, xpl, xpr;;) {
	//			//			if(x==null||x==head)
	//			//				break;
	//			if ((xp = x.parent) == null) {
	//				x.red = false;
	//				break;
	//			}
	//			if (x.red) {
	//				x.red = false;//？
	//				break;
	//			}
	//			if ((xpl = xp.left) == x) {
	//
	//				//右兄弟为红，父节点为黑
	//				if ((xpr = xp.right) != null && xpr.red) {
	//					xpr.red = false;
	//					xp.red = true;
	//					rotateLeft(xp);
	//					xpr = (xp = x.parent) == null ? null : xp.right;
	//				}
	//
	//			} else {
	//
	//			}
	//
	//		}

}
