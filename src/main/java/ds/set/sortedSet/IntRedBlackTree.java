package ds.set.sortedSet;

import java.util.NoSuchElementException;

import ds.IntIterable;
import ds.IntIterator;
import ds.set.IntSet;

/**
 * 
 * 红黑树  基本上是自己写的，除了添加部分参考了HashMap的红黑树实现
 * 
 * 红黑树的5个性质：
 * 1.红黑树的节点是红色或者黑色的
 * 2.红黑树的根节点是黑色的
 * 3.红黑树的外部节点是黑色的
 * 4.红黑树的红色节点的孩子是黑色的
 * 5.红黑树从根节点到每个外部节点的每条路径上黑色节点的个数相同
 * 
 * 这里的实现用null表示外部节点，不存在的节点都被认为是黑色的
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

	/**
	 * 红黑树的插入节点总是红色的（因为如果插入黑色节点，那么很容易违背性质5）
	 * 
	 * 首先我们必须找到一个节点插入，和AVL树是一样的
	 * 
	 * 然后我们需要判断父亲和叔叔的颜色，来决定要怎么维护红黑树性质
	 * 
	 * 黑父：
	 * 		case1：（自己为红色，父亲为黑色，兄弟为红或者不存在）
	 * 		因为插入后不违背任何性质，所以无需任何修正操作
	 * 
	 * 			          XP(黑)
	 * 	                 /  \
	 *                 X(红) XPR(红或者不存在)
	 *       
	 * 		 （插入X后（或递归），性质保持，此图为P为左孩子的情况，为右孩子也是一样的）
	 * 
	 * 红父：（祖父为黑，叔叔待定，兄弟一定不存在（红色节点肯定不能只有一个孩子））
	 * 		插入之后违背性质4，需要将父亲变为黑色，但是这样父亲为根的子树的黑高度会
	 * 		增加1，那么会违背性质5，需要另外找办法修正
	 * 
	 * 		红叔：
	 * 			case2：（自己为红色，父亲为红色，叔叔为红色，祖父为黑色）
	 * 			此时修正操作很简单，将父亲和叔叔变为黑，祖父变成红色，
	 * 			此时以祖父为根的子树黑高度不变，而且性质4也符合
	 * 			但是由于祖父变成红色了，祖父和祖父的父节点是否符合性质4还需要递归处理
	 * 
	 *                  XPP(黑)                            XPP(红)
	 * 			       /     \                            /     \
	 *                XP(红)  XPPR(红)        =>         XP(黑)  XPPR(黑)
	 *              /   \                              /   \
	 * 			   X(红) XPR(不存在)                  X(红) XPR(不存在)
	 * 
	 * 		            （插入X后（或递归））                           （改变颜色，性质保持）
	 * 
	 * 		       （对于X处于任何位置，都是一样的，没有什么区别，只要父亲和叔叔是红色，祖父是黑色即可）
	 * 
	 * 		黑叔：
	 * 			此时不能将叔叔节点变色来解决问题，那么此时就需要旋转来解决问题，
	 * 			思想很简单，由于自己和祖父之间夹了一个多的红色节点，而且祖父和叔叔都是黑色的
	 * 			所以我们可以把这个红色的节点放到祖父和叔叔中间，就不违反性质4了，
	 * 			而且黑色的节点的位置没有发生违背性质5的改变
	 * 
	 * 			但是要根据父亲和自己位于祖父的什么位置来决定需要什么旋转方案
	 * 
	 * 			这4种方案和AVL树的旋转一模一样： LL,LR,RR,RL
	 * 
	 * 			case3：LL 
	 * 			此时很简单，就是将祖父节点右旋，将父亲变成黑色，祖父变成红色即可
	 * 
	 * 			(*)表示这个节点为黑色或者不存在（为外部节点）
	 * 
	 *                  XPP(黑)                            XP(黑)
	 * 			       /     \                            /   \
	 *                XP(红)  XPPR(*)        =>         X(红) XPP(红)
	 *              /   \                                    /     \
	 * 			   X(红) XPR(*)                           XRP(*) XPPR(*)
	 * 
	 * 		             （插入X后（或递归））                               （一次旋转，性质保持）
	 * 
	 * 	 		（为*的节点同时存在或不存在，如果存在，则都为黑，并且X不是插入的节点，而是递归的节点）
	 * 
	 * 			case4：LR
	 * 			此时需要两次旋转，先将父亲进行左旋，这个旋转无需变色，然后就变成case3
	 * 			然后将祖父节点右旋，将父亲变成黑色，祖父变成红色即可
	 * 
	 * 			(*)表示这个节点为黑色或者不存在（为外部节点）
	 * 
	 *                        XPP(黑)                         XPP(黑)
	 * 			             /     \                         /     \                    X(黑)
	 *                    XP(红)  XPPR(*)     =>          X(红)  XPPR(*)               /    \
	 *                   /   \                           /   \             =>      XP(红)    XPP(红)   
	 * 			     XPL(*) X(红)                    XP(红)  XR(*)                /   \       /    \
	 * 			           /   \                     /   \                    XPL(*)  XL(*) XR(*)  XPPR(*)
	 *                  XL(*) XR(*)             XPL(*)   XL(*)  
	 * 		                 
	 *            （插入X后（或递归））                  （一次旋转得到中间状态）                   （二次旋转，性质保持）                        
	 *            
	 * 
	 * 	 		（为*的节点同时存在或不存在，如果存在，则都为黑，并且X不是插入的节点，而是递归的节点）
	 * 
	 * 			case5：RR
	 * 			此时很简单，就是将祖父节点左旋，将父亲变成黑色，祖父变成红色即可
	 * 
	 * 			（图和case3LL相反，其余一样）
	 * 			
	 * 			case6：RL
	 * 			此时需要两次旋转，先将父亲进行右旋，这个旋转无需变色，然后就变成case5
	 * 			然后将祖父节点左旋，将父亲变成黑色，祖父变成红色即可
	 * 
	 *          （图和case4LR相反，其余一样）
	 * 
	 * 			旋转之后子树根节点变成原来的父亲节点，依然为黑色，所以不需要递归处理
	 * 
	 * 
	 * 接下来我们分析递归情况：
	 * 
	 * 由于只有case2会触发递归情况，而case1和case3-6都会直接退出
	 * 并且case2只调整了颜色，需要旋转的情况都能够直接退出
	 * 所以只进行最多两次旋转（case4或case6）
	 * 
	 */
	@Override
	public boolean add(int element) {

		//特判头节点为空的情况，特殊处理
		if (head == null) {
			head = new IntRedBlackTreeNode(element, null);
			head.red = false;
			++size;
			return true;
		}

		IntRedBlackTreeNode node = head;

		//找到插入位置
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
		for (IntRedBlackTreeNode x = node, xp, xxp, xxpl, xxpr;;) {

			// 新加入的节点是红色的（或者递归需要变成红色）
			x.red = true;

			// 父亲不存在，为根节点，涂黑即可
			if ((xp = x.parent) == null) {
				x.red = false;
				return true;
			}

			// 父亲是黑节点，或者父亲节点是根节点（那应该必为黑节点，其实有点奇怪），那么无需操作
			if (!xp.red || (xxp = xp.parent) == null)
				return true;

			// 父亲是红色节点
			// 如果父亲是祖父的左子节点
			if (xp == (xxpl = xxp.left)) {

				// 如果叔叔节点是非空节点并且为红色，那么将其和父亲涂黑即可（保证左右子树的到叶子节点上黑色节点数量相同）
				// 祖父涂成红色，保证整颗子树从根到叶子节点的黑色节点数量不变
				//（因为父亲变黑了，祖父是黑的（因为叔叔是红色的），所以祖父需要变红）
				if ((xxpr = xxp.right) != null && xxpr.red) {

					xxpr.red = false;
					xp.red = false;
					// xxp.red = true;
					x = xxp;// 循环

				} else {

					if (x == xp.right) // 如果是右子节点，先进行左旋变成左子节点
						rotateLeft(xp);// 然后就和是左字节点的情况一样了

					// 如果是左子节点，直接右旋就可以了
					rotateRight(xxp);

					// 由于父亲是红色的，祖父是黑色的，右旋之后只是父亲变成叔叔，所以颜色都不变
					// 祖父没有变色，所以无需递归
					break;
				}

			} else {// 和上面同理，就是左边变成了右边的情况

				if (xxpl != null && xxpl.red) {

					xxpl.red = false;
					xp.red = false;
					// xxp.red = true;
					x = xxp;// 循环

				} else {

					if (x == xp.left)
						rotateRight(xp);

					rotateLeft(xxp);

					break;
				}

			}
		}

		return true;
	}

	/**
	 * 红黑树的查找和任意的二叉搜索树一样
	 */
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

	/**
	 * 红黑树的删除是我写过逻辑最麻烦数据结构的代码之一，情况实在是太多了
	 * 但是尽管实现非常麻烦，但是效率却很好，最多只有3次旋转
	 * AVL树的删除的旋转次数是没有上限的，所以红黑树要好得多
	 * 
	 * 一开始的思想和AVL树的删除几乎是一模一样，我们需要先找到我们需要的那个节点
	 * 即值和要删除的值一样的节点，这个和搜索的代码也是非常相似的
	 * 
	 * 然后我们需要找到一个最多只有一个孩子的节点来代替这个节点被删除，然后把值转移
	 * 到这个本要被删除的节点上，由于这个节点可能是根节点，所以我们实现由于麻烦，所以
	 * 总是寻找尽可能低的节点来代替删除
	 * 
	 * 找到了替罪羊节点之后，我们就需要研究怎么删除这个节点而不影响红黑树的性质，
	 * 总共有12种情况，其中5种是对称的，为了节省篇幅，我只列出被删除节点是左孩子的图
	 * 对称的情况翻转一下就可以了，比较简单
	 * 
	 * 此时，情况和自己的、孩子的、兄弟的、近侄子和远侄子的，父亲的颜色都有关系
	 * 
	 * 以下为7种处理情况：
	 * 
	 * =====================================================================================
	 * [自己为红]：
	 * 		case1：
	 * 		这种情况最简单了，由于这个节点最多只有一个孩子，但是由于这个节点是红色的，
	 * 		它不可能有一个红色的孩子，否则违背性质4，如果它只有一个黑孩子的话，
	 * 		那么其左右子树的黑高度不一致，违背性质5，所以其不可能有孩子
	 * 		所以是一个叶子节点，所以删除这个节点没有任何影响，直接删掉就可以了
	 * 
	 * 					  XP(黑)                                  
	 * 					 /     \                                  XP(黑)
	 * 				  X(红)    XPR(红或不存在)      =>            /   \
	 * 			    /      \                                  NULL    XPR(红或不存在)
	 *     XL(一定不存在)   XR(一定不存在)
	 * 
	 *                 （X待删除）                                                       （删除后）
	 *                  
	 *                           （X处于XPR的位置也是一样的，对称而已）
	 * 
	 * [自己为黑]：
	 * 		自己为黑的情况删除就不可避免的会减低1黑高度，所以我们必须想办法把这黑高度给
	 * 		补回来，要么从孩子这边找，要么向兄弟要，要不然就只能向祖先借
	 * 		所以这种情况比较复杂，需要进一步讨论，首先讨论孩子的情况，就行上面一样的，
	 * 		不可能出现只有一个黑孩子的情况，但是还有可能有红孩子或者没有孩子两种情况
	 * 
	 * 		[孩子为红]：
	 * 			case2（递归不可使用这种情况）：
	 * 			我们知道删除红节点肯定不会影响黑高度，所以我们还需要进一步的转嫁被删除的命运
	 * 			此时我们删除这个红孩子即可，当然我们需要先将要被删除节点和这个红孩子的值给换一下
	 * 			此时黑高度不变，性质5保持了下来
	 * 
	 * 			注：
	 * 			?表示节点的颜色未知，但是一定存在（不是外部节点）
	 * 
	 * 					  XP(?)                                  
	 * 					 /     \                         XP(?)
	 * 				  X(黑)    XPR(?)         =>         /   \
	 * 			    /      \                          XL(黑)  XPR(?)
	 *    		 XL(红)   XR(一定不存在)
	 * 
	 *                  （X待删除）                                        （删除后）
	 *                  
	 *      （XL和XR哪个存在无所谓，存在的那个顶替上来即可，并且X处于XPR的位置也是一样的，对称而已）
	 * 		
	 * 		[没有孩子]：（递归的情况即使有孩子也必须当作没有孩子来处理）
	 * 			这个情况就更加复杂了，需要先根据兄弟的颜色来判断情况
	 * 
	 * 			兄弟为红：
	 * 			case3：
	 * 			这种情况比较难受，我们无法直接处理这种情况，因为我们无法向兄弟借这1黑高度，
	 * 			我们需要将其转换一下，我们首先将父节点朝自己这个方向进行旋转，
	 * 			并将父亲和兄弟的颜色互换，然后就会变成兄弟为黑的情况
	 * 
	 * 			注：
	 * 			#表示节点存在与否无关紧要，但是这个地方并不是不可能没有节点
	 * 			
	 * 					  XP(黑)                                 XRP(黑) 
	 * 					 /      \                                /    \   
	 * 				  X(黑)       XPR(红)           =>       XP(红)  XPRR(黑)
	 * 			      /   \      /      \                    /   \
	 *    		    XL(#) XR(#) XPRL(黑) XPRR(黑)         X(黑)  XPRL(黑)
	 * 
	 *                  （删除前）                                     （旋转后，兄弟为黑的状态,忽略#节点）
	 *                  
	 *              （X位于XPR时就进行右旋即可，反正总是向自己这个方向旋转父亲节点）
	 * 
	 * 			[兄弟为黑]：
	 * 				此时我们可以向兄弟借这1黑高度了，但是兄弟这边黑高度就少了1，那么就需要
	 * 				兄弟的远的那个孩子（即远侄子）来顶上来，成为一个黑节点，来补充这1黑高度，
	 * 				前提是远侄子存在且为红，所以根据情况又要分类了，需要判断近侄子和远侄子的情况
	 * 
	 * 				[远侄子为红]：
	 * 					case4：
	 * 					这种情况将父亲向自己这个方向旋转即可，并将父亲和兄弟的颜色互换，
	 * 					然后远侄子节点的颜色变黑，这样便可以把兄弟的黑高度借到这边，
	 * 					并且旋转完性质4依然维持
	 * 
	 * 					注：
	 * 					?表示节点的颜色未知，但是一定存在（不是外部节点）
	 * 					*表示节点可能是[[红色]]或者不存在（是外部节点）
	 * 					#表示节点存在与否无关紧要，但是这个地方并不是不可能没有节点
	 * 
	 * 					      XP(?)                        XRP(?)                  XRP(?) 
	 * 					    /      \                      /    \                   /    \
	 * 				     X(黑)      XPR(黑)       =>     XP(黑) XPRR(红)   =>      XP(黑) XPRR(黑) 
	 * 			        /   \       /      \           /   \                     /   \
	 *    		      XL(#) XR(#) XPRL(*)  XPRR(红)  X(黑)  XPRL(*)            X(黑)  XPRL(*)
	 * 
	 *                      （删除前）                          （旋转后,忽略#节点）             （XPRR变色，X待删除）
	 *                      
	 *                 	（X位于XPR位置时，那么应该是XPLL节点为红，并进行右旋，删除X后，性质保持）
	 * 
	 * 
	 * 				[近侄子为红且远侄子不存在或为黑]：
	 * 					case5：
	 * 					将兄弟节点向远侄子的方向旋转即可，并将近侄子和兄弟节点的颜色互换，
	 * 					这样近侄子就变成兄弟节点了，而原来的兄弟变成远侄子，
	 * 					这样便会转换成case4的情况，然后按case4的方案解决即可
	 * 
	 * 					注：
	 * 					?表示节点的颜色未知，但是一定存在（不是外部节点）
	 * 					*表示节点可能是[[黑色]]或者不存在（是外部节点）
	 * 					#表示节点存在与否无关紧要，但是这个地方并不是不可能没有节点
	 * 
	 * 					          
	 * 					         XP(?)                                    XP(?) 
	 * 					       /      \                                 /     \ 
	 * 				      X(黑)        XPR(黑)               =>       X(黑)    XPRL(黑)
	 * 			          /   \       /       \                               /   \ 
	 *    		       XL(#) XR(#) XPRL(红)   XPRR(*)                    XPRLL(*) XPR(红)
	 *    					      /    \                                         /    \
	 *                       XPRLL(*)  XPRLR(*)                              XPRLR(*) XPRR(*)
	 * 
	 *                      （删除前）                                                   （旋转后，变成case4，忽略#节点） 
	 *                      
	 *                 	（X位于XPR位置时，那么应该是XPLL节点为红，并进行右旋，删除X后，性质保持）
	 * 
	 * 				[兄弟没有孩子或孩子都是黑色的]：
	 * 					这个时候就比较麻烦了，由于不能将红色的远侄子变成黑色来增加黑高度
	 * 					但是还有父亲最后一棵救命稻草，根据父亲的颜色进行最后的分类
	 * 
	 * 					[父亲为红色]：
	 * 						case6：
	 * 						此时还可以向父亲借这一黑高度，将父亲变黑，兄弟变红，这样两颗
	 * 						子树的黑高度同时减一，但是以父亲为节点的子树黑高度不变，
	 * 						维持了性质5
	 * 
	 * 						注：
	 * 						#表示节点存在与否无关紧要，但是这个地方并不是不可能没有节点
	 * 
	 * 					          XP(红)                           XP(黑) 
	 * 					        /       \                        /       \ 
	 * 				         X(黑)      XPR(黑)       =>       X(黑)      XPR(红)
	 * 			            /   \      /      \               /   \       /     \ 
	 *    		          XL(#) XR(#) XPRL(#) XPRR(#)       XL(#) XR(#) XPRL(#) XPRR(#)
	 *    
	 *    					         （删除前）                                     （变色后，X待删除）
	 * 
	 * 					[父亲为黑色]：
	 * 						case7：
	 * 						此时没有任何方法可以得到黑高度，所以干脆放弃，将兄弟变成红的
	 * 						这样两颗子树黑高度相同，但是以父亲为节点的子树黑高度少了1
	 * 						所以我们需要向祖先借这1黑高度，需要递归处理，就相当于要删除
	 * 						父节点一样，但是递归的情况中我们不能再进入case2，因为此时我们
	 * 						要删除的节点已经确定，不能再转移
	 * 
	 * 						注：
	 * 						#表示节点存在与否无关紧要，但是这个地方并不是不可能没有节点
	 * 
	 * 					          XP(黑)                           XP(黑) 
	 * 					        /       \                        /       \ 
	 * 				         X(黑)      XPR(黑)       =>       X(黑)      XPR(红)
	 * 			            /   \      /      \               /   \       /     \ 
	 *    		          XL(#) XR(#) XPRL(#) XPRR(#)       XL(#) XR(#) XPRL(#) XPRR(#)
	 *    
	 *    					         （删除前）                          （变色后，X待删除，并需要递归处理）
	 *    
	 * ========================================================================================
	 * 
	 * 接下来分析递归情况：
	 * 
	 * 删除的递归情况比较复杂，但是可以证明旋转的次数最多为3次：
	 * 
	 * 我们肯定不能把情况当成插入一样那么简单的处理
	 * 
	 * 情况中case7引发递归
	 * 而case3和case5引发状态转移
	 * case1、case2、case4、case6直接退出
	 * 
	 * 其中case3、case4、case4、case5有旋转操作
	 * 
	 * 如果简单的分析可能觉得有可能出现case3和case7递归出现
	 * 那么旋转次数可能很多，然而并不是这样
	 * 
	 * 接下来仔细分析：
	 * 
	 * 如果判中了case1、case2、case4、case6会直接退出
	 * 
	 * case5转移向case4，那么也会退出
	 * 
	 * case3由于旋转后，需要删除的节点的父亲是红色，那么不可能会
	 * 进入case7，只能在case4、case5和case6中选一个
	 * 所以最多旋转次数的情况应该是判中case3后接case5再接case4
	 * 三个case都有一次旋转，共三次旋转
	 * 
	 */
	@Override
	public boolean remove(int element) {
		if (head == null)
			return false;

		//特判仅有根节点且被删除的情况，如果这棵树不只有根节点，删除的节点无论如何都不是根节点
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
		IntRedBlackTreeNode x = toDeleteNode;
		while (true) {
			//case1 p为红色节点
			if (x.red || x == head) {
				break;
			}

			//p 为黑的情况

			//case2 p为黑，并且有且仅有一个红色子节点 仅当p是需要删除的节点时才能这样
			if (toDeleteNode == x) {

				if (x.left != null) {
					x.val = x.left.val;
					toDeleteNode = x.left;
					break;
				} else if (x.right != null) {
					x.val = x.right.val;
					toDeleteNode = x.right;
					break;
				}
			}

			//p为黑且是叶子节点
			IntRedBlackTreeNode xp = x.parent, xpl = x.parent.left, xpr = x.parent.right;

			if (x == xpl) {
				//case3 自己为黑 兄弟节点为红 父亲节点为黑 兄弟节点必定有两个黑色子节点
				if (xpr.red) {
					rotateLeft(xp);//左旋后统一情况
					xp = x.parent;
					xpr = x.parent.right;
				}

				//现在自己为黑，兄弟节点为黑，需要判断远侄子节点是什么颜色 现在父亲节点的颜色是未知的
				IntRedBlackTreeNode xprl = xpr.left, xprr = xpr.right;

				//case4 远侄子为红色
				if (xprr != null && xprr.red) {//如果是删除的那个节点，其侄子非空肯定是红色的，
					rotateLeft(xp);				//但是由于可以递归，所以并不是这样，这个地方很坑啊
					xprr.red = false;//旋转后，原来的远侄子节点置为黑色
					break;
				}

				//case5 近侄子节点为红
				if (xprl != null && xprl.red) {
					rotateRight(xpr);//旋转之后远侄子变成红色，会变成case4的情况
					rotateLeft(xp);//然后再像case4一样旋转就可以了，但是不可以先判断case5，并转换成case4
					xprl.red = false;	//因为远侄子节点可能是红色的，先判定的话，会出现xpr和xprr都是红色的
					break;				//并且是相邻节点的情况，会违背红黑树的性质5
				}

				//两个侄子都是空节点

				//case6 父节点为红色 自己为黑 兄弟也是黑
				if (xp.red) {
					xp.red = false;
					xpr.red = true;
					break;
				}

				//case7 父亲节点是黑色  自己为黑 兄弟也是黑

				xpr.red = true;

				//由于处理完之后xp为根的这颗子树的黑高度减少了1，
				//所以需要递归处理，相当于要删除p，但是实际上是不删的

				x = xp;

			} else {
				//case3
				if (xpl.red) {
					rotateRight(xp);//to other case
					xp = x.parent;
					xpl = x.parent.left;
				}

				IntRedBlackTreeNode xpll = xpl.left, xplr = xpl.right;

				//case4
				if (xpll != null && xpll.red) {
					rotateRight(xp);
					xpll.red = false;
					break;
				}

				//case5
				if (xplr != null && xplr.red) {
					rotateLeft(xpl);
					rotateRight(xp);
					xplr.red = false;
					break;
				}

				//case6
				if (xp.red) {
					xp.red = false;
					xpl.red = true;
					break;
				}

				//case7
				xpl.red = true;

				x = xp;
			}
		}

		//删除节点 被删除的节点一定没有任何孩子 因为在平衡的时候转移了
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

	// 右旋 向右旋转 顺时针旋转90度
	// 由于这里的旋转实际上是没有改变节点的上下位置，就是把左边的节点拿到了右边
	// 所以很多情况下的颜色并没有发生改变
	// 同时也无需注意node的父节点和这颗子树的root的关系
	// 是一种很好的实现方法，就是可读性会变差
	private void rotateRight(IntRedBlackTreeNode node) {
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
	private void rotateLeft(IntRedBlackTreeNode node) {
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

	// ****** 下面的代码都是用来测试的，和实现没有关系 ******

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

		IntRedBlackTreeNode p = node, xp = node.parent, pl = node.left, pr = node.right;

		if (xp != null && p != xp.left && p != xp.right) {
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
