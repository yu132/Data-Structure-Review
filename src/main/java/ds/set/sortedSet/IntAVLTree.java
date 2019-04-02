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
public final class IntAVLTree implements IntSet, IntIterable {

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

	public IntAVLTreeNode getHead() {
		return head;
	}

	/**
	 * 平衡因子的状态（3种）
	 * 
	 * @author 87663
	 */
	private enum BF {
		LEFT_HIGH, // 左高
		RIGHT_HIGH, // 右高
		BALANCED; // 平衡
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

		/**
		 * @return 这个节点的值
		 */
		public int getValue() {
			return val;
		}

		/**
		 * @return 以这个节点为根的子树的节点个数
		 */
		public int size() {
			return 1 + (left == null ? left.size() : 0) + (right == null ? right.size() : 0);
		}

		/**
		 * @return 以这个节点为根的子树上面最小的节点
		 */
		public IntAVLTreeNode getSmallest() {
			IntAVLTreeNode node = this;
			while (node.left != null)
				node = node.left;
			return node;
		}

		/**
		 * @return 以这个节点为根的子树上面最大的节点
		 */
		public IntAVLTreeNode getLargest() {
			IntAVLTreeNode node = this;
			while (node.right != null)
				node = node.right;
			return node;
		}

		/**
		 * @return 比这个节点的值要小的最大的一个节点
		 */
		public IntAVLTreeNode getPrevious() {
			if (left != null)
				return left.getLargest();

			for (IntAVLTreeNode node = this; node.parent != null; node = node.parent)
				if (node != node.parent.left)
					return node.parent;

			return null;
		}

		/**
		 * @return 比这个节点的值要大的最小的一个节点
		 */
		public IntAVLTreeNode getNext() {
			if (right != null)
				return right.getSmallest();

			for (IntAVLTreeNode node = this; node.parent != null; node = node.parent)
				if (node != node.parent.right)
					return node.parent;

			return null;
		}

		/**
		 * 在以这个节点为根的子树上插入值等于val的节点
		 * 
		 * @param val 插入节点的值
		 * @return 如果节点插入成功，返回真，否则该值已经存在于这颗子树上面
		 */
		boolean insert(int val) {
			if (val == this.val)// 存在则插入失败
				return false;

			boolean leftGrown;

			// 查看是否能够插入
			if (val < this.val) {
				if (left == null) {//为空则可以
					left = new IntAVLTreeNode(val, this);
					leftGrown = true;
				} else
					return left.insert(val);//递归搜索子树
			} else {// val>this.val

				if (right == null) {
					right = new IntAVLTreeNode(val, this);
					leftGrown = false;
				} else
					return right.insert(val);
			}

			IntAVLTreeNode node = this;//是插入节点的父节点
			while (leftGrown ? node.rebalanceLeftGrown() : node.rebalanceRightGrown()) {
				if (node.parent == null)                // 看是插入节点是父节点的左子节点还是右子节点来选择平衡的方式
					break; 								// 调整树的平衡因子，如果需要递归调整父节点的平衡因子
				leftGrown = node == node.parent.left;	// 如果递归，此时则查看是父节点的左子树还是右子树来选择平衡方式
				node = node.parent;						// 且循环就继续，否则就退出
			}

			return true;// 插入成功，返回真
		}

		/**
		 * 查找以这个节点为根的子树上值等于val的节点
		 * 
		 * 就是普通二叉平衡树的搜索，没什么特殊的
		 * 
		 * @param val 需要查找的值
		 * @return 存在这个节点，返回真
		 */
		public boolean find(int val) {
			if (val < this.val)
				return left != null ? left.find(val) : false;
			else if (val > this.val)
				return right != null ? right.find(val) : false;

			return true;
		}

		/**
		 * 删除以这个节点为根的子树上值等于val的节点
		 * 
		 * @param val 如果节点的值等于这个值，就删除
		 * @return 删除了这个节点，返回真
		 */
		boolean delete(int val) {
			// 值不相等
			if (val < this.val)
				return left != null ? left.delete(val) : false;// 递归搜索子树
			else if (val > this.val)
				return right != null ? right.delete(val) : false;

			IntAVLTreeNode node;
			IntAVLTreeNode child;

			if (left == null && right == null) {// 如果左右子节点都为空，则是叶子节点
				node = this;					// 那么我们就删除这个节点
				child = null;
			} else {							// 否则我们需要找一个替罪羊，来代替这个节点被删除
				node = left != null ? left.getLargest() : right.getSmallest();
				this.val = node.val;            // 同时将替罪羊节点的值赋给本需要被删除节点
				child = node.left != null ? node.left : node.right;
			}

			boolean leftShrunk = node == node.parent.left;

			node = node.parent;

			if (leftShrunk)// 把被删除节点的孩子（最多有一个），连接到被删除节点的父亲上
				node.left = child;
			else
				node.right = child;

			if (child != null)// 如果被删除节点存在孩子，就将其连接到被删除节点的父亲上
				child.parent = node;

			while (leftShrunk ? node.rebalanceLeftShrunk() : node.rebalanceRightShrunk()) {
				if (node.parent == null)				// 看是插入节点是父节点的左子节点还是右子节点来选择平衡的方式
					break;								// 调整树的平衡因子，如果需要递归调整父节点的平衡因子
				leftShrunk = node == node.parent.left;	// 如果递归，此时则查看是父节点的左子树还是右子树来选择平衡方式
				node = node.parent;						// 且循环就继续，否则就退出
			}

			return true;// 删除成功，返回真
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
		 * 插入B后平衡，无需旋转，并且子树高度不变，无需递归处理
		 * 
		 *                 A  
		 *               /   \     
		 *            B(h)    AR(h)
		 *          
		 *             （添加后）
		 *             
		 * 平衡：
		 * 插入B后左高，无需旋转，子树高度增加1，需要递归处理
		 * 
		 *                 A  
		 *               /   \          
		 *           B(h+1)   AR(h)      
		 *          
		 * 			      （添加后）
		 * 
		 * 左高：
		 * 由于插入前左边比右边高1，插入后左边会比右边高2，违背AVL树的性质，需要旋转
		 * 旋转后子树根节点处平衡，最终子树高度不变，无需递归
		 * 
		 * LL旋转：
		 * 
		 * 由于BL处进行了插入而产生的旋转
		 * 
		 * 进行了一次右旋，由于旋转之后所有子树的高度一样，所以平衡因子都为0
		 * 
		 * 旋转之后h(C)=h+2 和插入之前的h(A)的高度相等，所以上面的部分肯定是平衡的
		 * 
		 * 
		 *               A                             B
		 *             /   \                         /   \
		 *            B     AR(h)       =>      B(h+1)    A
		 *          /   \                               /   \   
		 *      BL(h+1) BR(h)                         BR(h)  AL(h)
		 * 
		 * 		      （添加后）						（旋转后）
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
		 *               A                               C
		 *             /   \                          /     \
		 *            B    AR(h)        =>           B       A
		 *          /   \                          /   \   /   \
		 *       BL(h)  C(h+1)                  BL(h)  CL  CR   AR(h)
		 *             /   \
		 *            CL    CR
		 *            
		 *           （添加后）						    （旋转后）
		 *           
		 * @return 是否需要递归调整
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
		 * 调节右边插入的影响
		 * 
		 * 前注：
		 * 这个节点位于图中的A位置
		 * h(node) => 以node为根的某棵子树的高度
		 * bf(node) => 某个节点的平衡因子(balence factor)
		 * 
		 * 左高：
		 * 插入B后平衡，无需旋转，并且子树高度不变，无需递归处理
		 * 
		 *                 A  
		 *               /   \     
		 *            AL(h)  B(h)    
		 *          
		 *             （添加后）
		 *             
		 * 平衡：
		 * 插入B后右高，无需旋转，并且子树高度增加1，需要递归处理
		 * 
		 *                 A  
		 *               /   \          
		 *            AL(h)  B(h+1)    
		 *          
		 * 			      （添加后）
		 * 
		 * 右高：
		 * 由于插入前右边比左边高1，插入后右边会比左边高2，违背AVL树的性质，需要旋转
		 * 旋转后子树根节点处平衡，最终子树高度不变，无需递归处理
		 * 
		 * RR旋转:
		 * 
		 * 由于BR处插入而产生的旋转
		 * 
		 *             A                                 B
		 *           /   \                             /   \
		 *        AL(h)   B             =>            A    BR(h+1)
		 *              /   \                       /   \
		 *           BL(h)   BR(h+1)             AL(h)   BL(h)
		 *    
		 * 		        （添加后）						（旋转后）         
		 *            
		 * RL旋转：  
		 *            
		 * 由于C处进行了插入而产生的旋转
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
		 *             A                                 C
		 *           /   \                            /     \
		 *       AL(h)    B             =>           A       B
		 *              /   \                      /   \   /   \
		 *            C(h+1) BR(h)              AL(h)  CL CR   BR(h)
		 *          /   \  
		 *         CL   CR
		 *         
		 * 		        （添加后）						（旋转后）      
		 * 
		 * @return 是否需要递归调整
		 */
		private boolean rebalanceRightGrown() {
			switch (balanceFactor) {
				case LEFT_HIGH:// 左高
					balanceFactor = BF.BALANCED;
					return false;
				case RIGHT_HIGH:// 右高
					if (right.balanceFactor == BF.RIGHT_HIGH) {// RR旋转
						rotateCCW();
						balanceFactor = BF.BALANCED;
						left.balanceFactor = BF.BALANCED;
					} else {// RL旋转
						final BF s = right.left.balanceFactor;
						right.rotateCW();
						rotateCCW();
						switch (s) {
							case LEFT_HIGH:// p=1
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.RIGHT_HIGH;
								break;
							case RIGHT_HIGH:// p=-1
								left.balanceFactor = BF.LEFT_HIGH;
								right.balanceFactor = BF.BALANCED;
								break;
							default:// p=0
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.BALANCED;
						}
						balanceFactor = BF.BALANCED;
					}
					return false;
				default:// 平衡
					balanceFactor = BF.RIGHT_HIGH;
					return true;
			}
		}

		/**
		 * 调整左边删除所造成的影响
		 * 
		 * 前注：
		 * 这个节点位于图中的A位置
		 * h(node) => 以node为根的某棵子树的高度
		 * bf(node) => 某个节点的平衡因子(balence factor)
		 * 
		 * 左高：
		 * 于AL为根的子树中删除后平衡，无需旋转，但是子树高度减一，需要递归处理
		 * 
		 *                 A  
		 *               /   \     
		 *           AL(h-1)  B(h-1)    
		 *          
		 *             （删除后）
		 *             
		 * 平衡：
		 * 于AL为根的子树中删除后右高，无需旋转，子树高度不变，无需递归处理
		 * 
		 *                 A  
		 *               /   \     
		 *           AL(h-1)  B(h)    
		 *          
		 *             （删除后）
		 *             
		 * 右高：
		 * 删除后右边比左边高2，那么需要旋转来平衡
		 * 由于不同的删除情况子树的高度改变情况不一样，还需要分类讨论
		 * 
		 * L0和L-1旋转：
		 * 
		 * 由于AL处进行了删除而产生的旋转，同时BR的高度为h，BL的高度为h或h-1
		 * 
		 * p=bf(B)=h(BL)-h(BR) （旋转前）
		 * 
		 * p=0 则 h(BL)=h 时，旋转后的 bf(A)=h(AL)-h(BL)=(h-1)-h=-1
		 * h(A)=MAX(h(AL),h(BL))+1=h+1  bf(B)=h(A)-h(BR)=(h+1)-h=1
		 * 同时h(B)[删除后]=h(A)[删除前]=h+2 所以删除后高度不变，无需递归处理
		 * 
		 * p=-1 则 h(BL)=h-1 时，旋转后的  bf(A)=h(AL)-h(BL)=(h-1)-(h-1)=0
		 * h(A)=MAX(h(AL),h(BL))+1=(h-1)+1=h  bf(B)=h(A)-h(BL)=h-h=0
		 * 同时h(B)[删除后]=h+1  h(A)[删除前]=h+2 所以删除后高度减一，需要递归处理
		 * 
		 *               A                                B
		 *             /   \                            /   \
		 *         AL(h-1)  B           =>             A     BR(h)
		 *                /   \                      /   \
		 *               BL   BR(h)              AL(h-1)  BL
		 *               
		 * 		        （删除后）						（旋转后）      
		 * 
		 * L1旋转：
		 * 
		 * 由于AR处进行了删除而产生的旋转，同时BR高度为h-1
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
		 * 无论哪种情况 最后 h(B)[删除后]=h+1  h(A)[删除前]=h+2 所以删除后高度减一，需要递归处理
		 * 
		 *                A                                 C
		 *              /   \                            /     \
		 *          AL(h-1)  B            =>            A       B
		 *                 /   \                      /   \   /   \
		 *               C(h)   BR(h-1)          AL(h-1)  CL CR   BR(h-1)
		 *              /   \ 
		 *             CL   CR    
		 *         
		 * 		        （删除后）						      （旋转后）   
		 * 
		 * @return 是否需要递归调整
		 */
		private boolean rebalanceLeftShrunk() {
			switch (balanceFactor) {
				case LEFT_HIGH:// 左高
					balanceFactor = BF.BALANCED;
					return true;
				case RIGHT_HIGH:// 右高
					if (right.balanceFactor == BF.RIGHT_HIGH) {// L-1旋转
						rotateCCW();
						balanceFactor = BF.BALANCED;
						left.balanceFactor = BF.BALANCED;
						return true;
					} else if (right.balanceFactor == BF.BALANCED) {// L0旋转
						rotateCCW();
						balanceFactor = BF.LEFT_HIGH;
						left.balanceFactor = BF.RIGHT_HIGH;
						return false;
					} else {// L1旋转
						final BF s = right.left.balanceFactor;
						right.rotateCW();
						rotateCCW();
						switch (s) {
							case LEFT_HIGH:// p=1
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.RIGHT_HIGH;
								break;
							case RIGHT_HIGH:// p=-1
								left.balanceFactor = BF.LEFT_HIGH;
								right.balanceFactor = BF.BALANCED;
								break;
							default:// p=0
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.BALANCED;
						}
						balanceFactor = BF.BALANCED;
						return true;
					}
				default:// 平衡
					balanceFactor = BF.RIGHT_HIGH;
					return false;
			}
		}

		/**
		 * 调整左边删除所造成的影响
		 * 
		 * 前注：
		 * 这个节点位于图中的A位置
		 * h(node) => 以node为根的某棵子树的高度
		 * bf(node) => 某个节点的平衡因子(balence factor)
		 * 
		 * 右高：
		 * 于AL为根的子树中删除后平衡，无需旋转，但是子树高度减一，需要递归处理
		 * 
		 *                 A  
		 *               /   \     
		 *           B(h-1)  AR(h-1)
		 *          
		 *             （删除后）
		 *             
		 * 平衡：
		 * 于AL为根的子树中删除后左高，无需旋转，子树高度不变，无需递归处理
		 * 
		 *                 A  
		 *               /   \          
		 *            B(h)   AR(h-1)      
		 *          
		 * 			      （删除后）
		 * 
		 * 左高：
		 * 删除后左边比右边高2，那么需要旋转来平衡
		 * 由于不同的删除情况子树的高度改变情况不一样，还需要分类讨论
		 * 
		 * R0和R1旋转：
		 * 
		 * 由于AR处进行了删除而产生的旋转，同时BL的高度为h，BR的高度为h或h-1
		 * 
		 * p=bf(B)=h(BL)-h(BR) （旋转前）
		 * 
		 * p=0 则 h(BR)=h 时，旋转后的 bf(A)=h(BR)-h(AR)=h-(h-1)=1
		 * h(A)=MAX(h(BR),h(AR))+1=h+1  bf(B)=h(BL)-h(A)=h-(h+1)=-1
		 * 同时h(B)[删除后]=h(A)[删除前]=h+2 所以删除后高度不变，无需递归处理
		 * 
		 * p=1 则 h(BR)=h-1 时，旋转后的  bf(A)=h(BR)-h(AR)=(h-1)-(h-1)=0
		 * h(A)=MAX(h(BR),h(AR))+1=(h-1)+1=h  bf(B)=h(BL)-h(A)=h-h=0
		 * 同时h(B)[删除后]=h+1  h(A)[删除前]=h+2 所以删除后高度减一，需要递归处理
		 * 
		 *                A                                  B
		 *              /   \                              /   \
		 *             B     AR(h-1)         =>         BL(h)   A
		 *           /   \                                    /   \
		 *        BL(h)   BR                                 BR    AR(h-1)
		 *  
		 * 		        （删除后）						          （旋转后）  
		 *  
		 * R-1旋转： 
		 *  
		 * 由于AR处进行了删除而产生的旋转，同时BL的高度为h-1
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
		 * 无论哪种情况 最后 h(B)[删除后]=h+1  h(A)[删除前]=h+2 所以删除后高度减一，需要递归处理
		 * 
		 *                 A                                   C
		 *               /   \                              /     \
		 *              B    AR(h-1)         =>            B       A
		 *            /   \                              /   \   /   \
		 *        BL(h-1)  C(h)                     BL(h-1)  CL CR    AR(h-1)
		 *               /   \
		 *              CL   CR
		 *              
		 * 		         （删除后）						             （旋转后）  
		 *   
		 * @return 是否需要递归调整
		 */
		private boolean rebalanceRightShrunk() {
			switch (balanceFactor) {
				case RIGHT_HIGH:// 右高
					balanceFactor = BF.BALANCED;
					return true;
				case LEFT_HIGH:// 左高
					if (left.balanceFactor == BF.LEFT_HIGH) {// R1旋转
						rotateCW();
						balanceFactor = BF.BALANCED;
						right.balanceFactor = BF.BALANCED;
						return true;
					} else if (left.balanceFactor == BF.BALANCED) {// R0旋转
						rotateCW();
						balanceFactor = BF.RIGHT_HIGH;
						right.balanceFactor = BF.LEFT_HIGH;
						return false;
					} else {//R-1旋转
						final BF s = left.right.balanceFactor;
						left.rotateCCW();
						rotateCW();
						switch (s) {
							case LEFT_HIGH:// p=1
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.RIGHT_HIGH;
								break;
							case RIGHT_HIGH:// p=-1
								left.balanceFactor = BF.LEFT_HIGH;
								right.balanceFactor = BF.BALANCED;
								break;
							default:// p=0
								left.balanceFactor = BF.BALANCED;
								right.balanceFactor = BF.BALANCED;
						}
						balanceFactor = BF.BALANCED;
						return true;
					}
				default:// 平衡
					balanceFactor = BF.LEFT_HIGH;
					return false;
			}
		}

		// LL旋转 右旋 顺时针旋转90度
		// 由于这里的旋转实际上是没有改变节点的上下位置
		// 就是把左边的节点拿到了右边，但是值发生了交换
		// 同时也无需注意node的父节点和这颗子树的root的关系
		// 是一种很好的实现方法，就是可读性会变差
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
		// 注释同上
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

			next = tree.head == null ? null : tree.head.getSmallest();

			//			next = tree.head;
			//
			//			if (next != null)
			//				while (next.left != null)
			//					next = next.left;
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
			//			if (next.right != null) {
			//				next = next.right;
			//				while (next.left != null)
			//					next = next.left;
			//			} else {
			//				IntAVLTreeNode ch = next;
			//				next = next.parent;
			//				while (next != null && ch == next.right) {
			//					ch = next;
			//					next = next.parent;
			//				}
			//			}

			next = next.getNext();

			return val;
		}
	}

}
