package ds.set.sortedSet;

import java.util.Arrays;

import ds.IntIterable;
import ds.IntIterator;
import ds.linearList.IntArrayList;
import ds.set.IntSet;

/**
 * B树的实现
 * 
 * B树有5个特性
 * 
 * 1、根结点至少有两个子女；
 * 2、每个非根节点所包含的关键字个数 j 满足：┌m/2┐ - 1 <= j <= m - 1；
 * 3、除根结点以外的所有结点（不包括叶子结点）的度数正好是关键字总数加1，故内部子树个数 k 满足：┌m/2┐ <= k <= m ；
 * 4、所有的叶子结点都位于同一层。
 * 
 * @author 87663
 */
public final class IntBTree implements IntSet, IntIterable {

	/**
	 * 默认的rank值
	 */
	private final static int	DEFAULT_RANK	= 100;

	/**
	 *  rank(秩) = B树是几叉树 = 节点存储值的数量-1 = 节点的最多的孩子数量
	 */
	private int					rank;

	/**
	 * 一个节点最少的元素数量（根节点不受这个限制）
	 * 值为 log2( ceil(rank / 2) ) - 1
	 */
	private int					minValNum;

	/**
	 * 头节点
	 */
	private IntBTreeNode		head;

	/**
	 * 元素数量
	 */
	private int					size;

	public IntBTree() {
		super();
		rank = DEFAULT_RANK;
	}

	public IntBTree(int rank) {
		super();
		this.rank = rank;
		minValNum = (int) Math.ceil(rank / 2.0) - 1;
	}

	private final class IntBTreeNode {//rank=childs.length

		/**
		 * 元素的数量
		 */
		int				valNum;

		/**
		 * 元素数组  元素个数 = valNum
		 */
		int[]			vals;

		/**
		 * 孩子节点数组 节点个数 = valNum+1
		 */
		IntBTreeNode[]	childs;

		/**
		 * 父亲节点
		 */
		IntBTreeNode	parent;

		public IntBTreeNode(int valNum, int[] vals, IntBTreeNode[] childs, IntBTreeNode parent) {
			super();
			this.valNum = valNum;
			this.vals = vals;
			this.childs = childs;
			this.parent = parent;
		}

		/**
		 * 
		 * 向这个节点中插入element，已经知道不包含这个元素了
		 * 
		 * 插入比较简单，实际上就是找到叶子层，然后插入，然后如果节点的元素个数达到rank值
		 * 就进行分裂操作，分裂是将一个节点拆成两个节点，各具有这个节点一半的元素
		 * 
		 * 首先中间的值为 midIndex = (valNum - 1) / 2
		 * 
		 * 对于vals来说，应该分成[0 to midIndex-1][midIndex][midIndex+1 to valNum-1]这三份
		 * 对于childs来说，应该分成[0 to midIndex][midIndex+1 to valNum]这两份
		 * 
		 * 新的两个节点其中一个由 [0 to midIndex-1]的vals和[0 to midIndex]的childs构成
		 * 另一个由[midIndex+1 to valNum-1]的vals和[midIndex+1 to valNum]的childs构成
		 * 多出来的[midIndex]的val应该插入到父节点中
		 * 
		 * 父节点会在midIndex的val应该插入的地方插入这个值，设其为index
		 * 则child应该删除index位置上的节点，即之前的这个节点
		 * 然后在index和index+1的位置上分别插入刚刚新产生的两个节点
		 * 
		 * 若父节点的元素个数达到rank值，则继续递归
		 * 
		 * @param element
		 * @param left
		 * @param right
		 */
		void insert(int element, IntBTreeNode left, IntBTreeNode right) {

			int index = -Arrays.binarySearch(vals, 0, valNum, element) - 1;// 找到插入位置

			// 预留了一个位置，无论如何都能插入
			System.arraycopy(vals, index, vals, index + 1, valNum - index);// 腾出index这个位置
			vals[index] = element;// 在index这个位置上插入需要插入的元素

			if (childs != null) {//如果不是叶子节点  那么还需要插入左节点和右节点

				System.arraycopy(childs, index + 1, childs, index + 2, valNum - index);// 腾出index和index+1这两个位置
				childs[index] = left;// 放入左节点
				childs[index + 1] = right;// 放入右节点

			}

			++valNum;//元素数加一

			fixNodeGrown();//如果元素数量达到最大值就需要分裂
		}

		/**
		 * 处理节点分裂问题
		 * 
		 * 具体的分裂解释见上insert()上的注释
		 */
		private void fixNodeGrown() {
			if (valNum == vals.length) {// 元素数量达到最大值 已满 则分裂

				IntBTreeNode leftPart = null;
				IntBTreeNode rightPart = null;

				int[] leftVals = new int[vals.length];
				int[] rightVals = new int[vals.length];
				IntBTreeNode[] leftChilds = null;
				IntBTreeNode[] rightChilds = null;

				int midIndex = (valNum - 1) >> 1;// 需要拿到父亲的中间节点的索引

				int leftValNum = midIndex;// 分裂后左边节点的数量
				int rightValNum = valNum - midIndex - 1;// 分裂后右边节点的数量

				System.arraycopy(vals, 0, leftVals, 0, midIndex);// 左边节点分到的元素
				int midValue = vals[midIndex];// 给父亲节点的元素
				System.arraycopy(vals, midIndex + 1, rightVals, 0, valNum - midIndex - 1);// 右边节点分倒的元素

				if (childs != null) {//如果不是叶子节点
					leftChilds = new IntBTreeNode[vals.length + 1];
					rightChilds = new IntBTreeNode[vals.length + 1];

					System.arraycopy(childs, 0, leftChilds, 0, midIndex + 1);//左边节点分到的孩子
					System.arraycopy(childs, midIndex + 1, rightChilds, 0, valNum - midIndex);//右边节点分到的孩子
				}

				leftPart = new IntBTreeNode(leftValNum, leftVals, leftChilds, parent);// 创建左节点
				rightPart = new IntBTreeNode(rightValNum, rightVals, rightChilds, parent);// 创建右节点

				if (childs != null) {// 修正孩子的父亲指针
					for (int i = 0; i < midIndex + 1; ++i)
						leftChilds[i].parent = leftPart;// 左节点分到的孩子的父亲应该是左节点
					for (int i = 0; i < valNum - midIndex; ++i)
						rightChilds[i].parent = rightPart;// 右节点分到的孩子的父亲应该是右节点
				}

				if (this == head) {// 自己为头节点 那么需要特殊处理，因为分裂之后的元素不能交给父亲
										// 因为头节点没有父亲 那么我们需要新建一个新的头节点
					head = new IntBTreeNode(1, new int[vals.length],
							new IntBTreeNode[vals.length + 1], head);//新建头节点
					head.vals[0] = midValue;//这个头节点只有一个元素，就是需要给父节点的那个元素
					head.childs[0] = leftPart;//并且分别存储左节点
					head.childs[1] = rightPart;//和右节点

					leftPart.parent = head;//维护父亲指针
					rightPart.parent = head;
				} else {// 自己不为头节点
					parent.insert(midValue, leftPart, rightPart);//如果父节点不是头节点，那么直接插入父节点即可
				}

			}
		}

		/**
		 * 向以本节点为根的子树添加元素，如果不是叶子节点，
		 * 则需要找到能插入的叶子节点
		 * 
		 * @param element
		 * @return
		 */
		boolean add(int element) {

			int index = Arrays.binarySearch(vals, 0, valNum, element);

			if (index >= 0) // 值已经存在，返回假
				return false;

			index = -index - 1;//插入点

			if (childs != null) {// 有孩子，不是叶子节点，不能直接插入
				return childs[index].add(element);//递归下一层
			}

			insert(element, null, null);// 是叶子节点就插入这个值

			return true;
		}

		/**
		 * n叉树的查找，比二叉树略微麻烦，首先需要找到这个元素的索引
		 * 如果节点存在，那么索引值是正的，
		 * 否则可以得到插入位置，插入位置对应的childs即是需要继续寻找的孩子节点的索引
		 * 然后递归寻找，直到找到或者没有孩子为止
		 * 
		 * @param element
		 * @return
		 */
		boolean contain(int element) {
			int index = Arrays.binarySearch(vals, 0, valNum, element);//找到索引

			//索引为正，则元素存在，否则查看节点是否有孩子，如果有，则递归寻找，否则返回假
			return index >= 0 || (childs == null ? false : childs[-index - 1].contain(element));
		}

		/**
		 * 在以本节点为根的子树删除某个包含指定值的节点中的这个值
		 * 
		 * 删除很简单，有两种情况
		 * 
		 * 1.如果是叶子节点，找到节点并删除这个值即可
		 * 
		 * 2.如果不是叶子节点，那么需要找到比这个值小的最大的一个值，
		 * 并把这个值移动到需要删除的这个值的位置上
		 * 
		 * 为什么不找比这个值大的最小的值呢？
		 * 是因为拿走那个值，会删除第一个元素，需要搬动vals数组
		 * 如果是比这个值小的最大的一个值，则是最后一个元素，无需搬动数组
		 * 
		 * @param element
		 * @return
		 */
		boolean remove(int element) {

			int index = Arrays.binarySearch(vals, 0, valNum, element);//找到索引

			if (index < 0)//索引为负，则元素不存在，否则查看节点是否有孩子，如果有，则递归寻找，否则返回假
				return childs == null ? false : childs[-index - 1].remove(element);

			//元素存在

			IntBTreeNode needfixNode = this;

			if (childs == null) {//如果这个节点是叶子节点

				System.arraycopy(vals, index + 1, vals, index, valNum - index - 1);// 直接覆盖掉index这个值
				--valNum;// 数量减少1

			} else {// 如果不是叶子节点，就需要找到一个比这个值小的最大的一个值

				needfixNode = needfixNode.childs[index];// 这个值的左边的那个孩子

				while (needfixNode.childs != null)// 如果这个节点存在子节点，就去最右的那个子节点
					needfixNode = needfixNode.childs[needfixNode.valNum];

				// 是叶子节点，就拿那个最大的那个值顶替掉要删除的那个值，然后那个叶子节点元素数量减一
				this.vals[index] = needfixNode.vals[--needfixNode.valNum];

			}

			// 删除之后修正这个节点
			needfixNode.fixNodeShrunk();

			return true;
		}

		/**
		 * 处理删除后节点过少的问题
		 * 
		 * 有三种情况：
		 * 
		 * 1.左兄弟元素足够
		 * 
		 * 这种情况需要把左的最大的值和最后一个孩子拿出来，
		 * 值需要放到父亲节点中本节点和左兄弟之间的那个值的位置
		 * 顶替出来的值放到本节点的第一个位置，
		 * 然后左兄弟的孩子也放到本节点的第一个孩子的位置上
		 * 
		 * 2.右兄弟元素足够
		 * 
		 * 和左兄弟的情况几乎一样，就是右节点拿出第一个值和孩子
		 * 最后放在本节点的末尾
		 * 
		 * 3.都不够，合并自己和左右节点其中的一个
		 * 
		 * 这种情况比较麻烦，因为需要处理的东西比较多
		 * 
		 * 首先我们需要观察左右兄弟是否存在，如果都存在，选择任意一个即可
		 * 否则，我们只能选择存在的那个合并
		 * 
		 * 合并需要将兄弟的值和父亲中这两个节点夹住的那个值和本节点的值合并成一整个值数组
		 * 孩子数组需要合并兄弟和自己的孩子即可，最终产生一个新的节点，
		 * 包含这个新的值数组和孩子数组，这个新的节点需要插入到原来靠左边的那个被
		 * 合并的节点占的父亲节点的孩子数组中的位置，然后把父亲的值数组和孩子数组都向前收缩一位
		 * 因为父亲也减少了一个值，然后这两个节点合并成了一个节点，孩子数也少了1
		 * 
		 * 然后父亲节点也减少了一个元素，所以需要检查父亲节点是否元素过少，然后递归即可
		 * 
		 */
		private void fixNodeShrunk() {
			if (valNum >= minValNum)// 如果节点的数量足够多，那么无需修正
				return;

			if (this == head)// 头节点不受元素数量的限制，只要不为0即可，那个检查不在这里
				return;

			int parentIndex = -Arrays.binarySearch(parent.vals, 0, parent.valNum, vals[0]) - 1;// 找到自己位于父亲节点的索引

			IntBTreeNode broLeft = null;

			IntBTreeNode broRight = null;

			if (parentIndex - 1 >= 0// 先检查左兄弟是否存在，若左兄弟元素足够，从左兄弟要元素
					&& (broLeft = parent.childs[parentIndex - 1]).valNum >= minValNum + 1) {

				System.arraycopy(vals, 0, vals, 1, valNum);// 腾出需要放入的位置
				vals[0] = parent.vals[parentIndex - 1];// 把父亲节点给出的元素放进来
				parent.vals[parentIndex - 1] = broLeft.vals[broLeft.valNum - 1];//把左兄弟给出的元素放到父亲给的那个元素的位置上

				if (childs != null) {//如果不是叶子节点
					System.arraycopy(childs, 0, childs, 1, valNum + 1);//腾出一个孩子的值
					childs[0] = broLeft.childs[broLeft.valNum];//把左兄弟给出的孩子放进来
					childs[0].parent = this;//把那个拿过来的孩子的父亲指针指向自己

					broLeft.childs[broLeft.valNum] = null;//删除左兄弟对那个孩子的指针
				}

				--broLeft.valNum;//左兄弟元素数量减一
				++valNum;//本节点元素数量加一

			} else if (parentIndex < parent.valNum// 然后检查右兄弟是否存在，若右兄弟元素足够，从右兄弟要元素
					&& (broRight = parent.childs[parentIndex + 1]).valNum >= minValNum + 1) {// 不能认为左兄弟不存在右兄弟就存在
				vals[valNum] = parent.vals[parentIndex];// 把父亲节点给出的元素放进来                      // 因为还有可能左兄弟元素不够
				parent.vals[parentIndex] = broRight.vals[0];// 把右兄弟给出的元素放到父亲给的那个元素的位置上
				System.arraycopy(broRight.vals, 1, broRight.vals, 0, broRight.valNum - 1);//把右边节点的给出的那个值给覆盖掉

				if (childs != null) {//如果不是叶子节点
					childs[valNum + 1] = broRight.childs[0];//把右兄弟给出的孩子放进来
					childs[valNum + 1].parent = this;//把那个拿过来的孩子的父亲指针指向自己
					System.arraycopy(broRight.childs, 1, broRight.childs, 0, broRight.valNum);//把右边节点的给出的那个孩子给覆盖掉

					broRight.childs[broRight.valNum] = null;//删除右兄弟多余孩子的指针（因为数组复制，所以现在对最后一个元素维护了两个指针）
				}

				--broRight.valNum;//右兄弟元素数量减一
				++valNum;//本节点元素数量加一

			} else {//两个兄弟都没有足够数量的节点，只能合并兄弟了  相当于从父亲要元素

				IntBTreeNode node = this;//靠右边的节点

				if (broLeft == null) {//如果左兄弟不存在，那么合并右兄弟 把自己当作左兄弟 右兄弟当成自己
					broLeft = this;// 把自己当作左边的节点
					node = broRight;// 右边的节点设置为右兄弟
					++parentIndex;// 在父亲的位置上加一（因为右移了一位）
				}

				int[] newVals;
				IntBTreeNode[] newChilds = null;

				newVals = new int[vals.length];
				System.arraycopy(broLeft.vals, 0, newVals, 0, broLeft.valNum);// 把左边节点的值复制进来
				newVals[broLeft.valNum] = parent.vals[parentIndex - 1];// 把父亲节点的值放进了
				System.arraycopy(node.vals, 0, newVals, broLeft.valNum + 1, node.valNum);// 把右边节点的值复制进来

				if (childs != null) {//不是叶子节点
					newChilds = new IntBTreeNode[vals.length + 1];
					System.arraycopy(broLeft.childs, 0, newChilds, 0, broLeft.valNum + 1);// 把左边节点的孩子复制进来
					System.arraycopy(node.childs, 0, newChilds, broLeft.valNum + 1,// 把右边节点的孩子复制进来
							node.valNum + 1);
				}

				IntBTreeNode newNode = new IntBTreeNode(broLeft.valNum + 1 + node.valNum, newVals,
						newChilds, parent);// 建立新的节点

				if (childs != null) {// 如果不是叶子节点，还需要修正孩子的父亲指针的指向
					for (int i = 0, length = node.valNum + broLeft.valNum + 2; i < length; ++i)
						newChilds[i].parent = newNode;// 这些孩子的父亲是新的节点
				}

				if (parent == head && parent.valNum == 1) {// 如果父亲是头节点，并且节点数只剩下1，按照常规的合并方法
					head = newNode;							// 头节点的元素数量会变成0，然后只有一个指针指向新节点，很明显是不对的
					return;									// 应该把这个新的节点设置为头节点即可
				}

				parent.childs[parentIndex - 1] = newNode;// 父亲的孩子数组中插入新节点的引用

				System.arraycopy(parent.vals, parentIndex, parent.vals, parentIndex - 1,
						parent.valNum - parentIndex);// 值数组的向前覆盖

				System.arraycopy(parent.childs, parentIndex + 1, parent.childs, parentIndex,
						parent.valNum - parentIndex);// 孩子数组的向前覆盖

				parent.childs[parent.valNum] = null;// 删除多余的引用

				--parent.valNum;// 父亲的元素数量减一

				parent.fixNodeShrunk();// 如果父亲节点的数量过少，那么也需要修正

			}

		}

		/**
		 * 测试用代码
		 */
		boolean check() {
			if (this != head && parent == null) {
				System.out.println("e4");
				return false;
			}
			if (valNum == vals.length) {
				System.out.println("e1");
				return false;
			}
			if (this != head && valNum < minValNum) {
				System.out.println("e2");
				return false;
			}
			if (childs != null)
				for (int i = 0; i < valNum + 1; ++i) {
					if (childs[i].parent != this) {
						System.out.println(this.parent);
						System.out.println("e3");
						return false;
					}
					if (!childs[i].check())
						return false;
				}
			return true;
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
			head = new IntBTreeNode(1, new int[rank], null, null);
			head.vals[0] = element;
			++size;
			return true;
		}
		if (head.add(element)) {
			++size;
			return true;
		}
		return false;
	}

	@Override
	public boolean contain(int element) {
		return head == null ? false : head.contain(element);
	}

	@Override
	public boolean remove(int element) {
		if (head == null)
			return false;

		if (head.remove(element)) {
			--size;
			if (head.valNum == 0)// 在这个地方处理头节点元素为0的情况，
				head = null;		//头节点元素为0，应该将其设置成null
			return true;
		}

		return false;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public IntIterator iterator() {
		return new IntBTreeIterator(this);
	}

	/**
	 * 最简单的n叉树迭代器，先dfs遍历一遍，收集全部的元素，然后再展示的方法
	 * 
	 * @author 87663
	 */
	private static class IntBTreeIterator implements IntIterator {
		private IntArrayList	list;
		private int				index	= 0;

		IntBTreeIterator(IntBTree bTree) {
			list = new IntArrayList(bTree.size);
			if (bTree.head != null)
				dfs(bTree.head, list);
		}

		private static void dfs(IntBTreeNode node, IntArrayList list) {
			if (node.childs != null)
				dfs(node.childs[0], list);
			for (int i = 0; i < node.valNum; ++i) {
				list.add(node.vals[i]);
				if (node.childs != null)
					dfs(node.childs[i + 1], list);
			}
		}

		@Override
		public boolean hasNext() {
			return index != list.size();
		}

		@Override
		public int next() {
			if (index == list.size())
				throw new IllegalArgumentException("No more element");
			return list.get(index++);
		}

	}

	/**
	 * 测试用代码
	 */
	public boolean check() {
		if (head == null)
			return true;
		return head.check();
	}

	//	private static class IntBTreeIterator implements IntIterator { //@BUG
	//
	//		private IntBTreeNode		node;
	//
	//		private int					index;
	//
	//		private FastIntArrayStack	stack;
	//
	//		private boolean				hasNext;
	//
	//		private int					nextVal;
	//
	//		public IntBTreeIterator(IntBTree bTree) {
	//			super();
	//
	//			node = bTree.head;
	//
	//			if (node == null) {
	//				hasNext = false;
	//				return;
	//			}
	//
	//			int maxHigh = (int) (Math.log((bTree.size + 1) / 2) / Math.log(bTree.rank));
	//
	//			stack = new FastIntArrayStack(maxHigh);
	//
	//			while (node.childs != null) {
	//				node = node.childs[0];
	//				stack.push(0);
	//			}
	//
	//			index = 1;
	//
	//			nextVal = node.vals[0];
	//
	//			hasNext = true;
	//		}
	//
	//		@Override
	//		public boolean hasNext() {
	//			return hasNext;
	//		}
	//
	//		@Override
	//		public int next() {
	//			if (!hasNext)
	//				throw new IllegalArgumentException("No more element");
	//
	//			int val = nextVal;
	//
	//			if (index != node.valNum)
	//				nextVal = node.vals[index++];
	//			else {
	//				IntBTreeNode p = node.parent;
	//				while (true) {
	//					int pIndex = stack.pop();
	//
	//					if (pIndex == p.valNum) {
	//						if ((p = p.parent) == null) {
	//							hasNext = false;
	//							break;
	//						}
	//						continue;
	//					}
	//
	//					nextVal = p.vals[pIndex++];
	//
	//					stack.push(pIndex);
	//
	//					p = p.childs[pIndex];
	//
	//					while (p.childs != null) {
	//						p = p.childs[0];
	//						stack.push(0);
	//					}
	//
	//					index = 0;
	//
	//					break;
	//				}
	//			}
	//
	//			return val;
	//		}
	//	}

}
