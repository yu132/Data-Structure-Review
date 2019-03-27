package ds.tournamentTree;

import ds.IntComparator;

/**
 * winnerTree的节点应该分成两部分，
 * 内部节点应该是从 [0 - (n-2)] （左右都包含）
 * 外部节点为 [(n-1) - (2n-2)] （左右都包含）
 * 总长度为 2n-1
 * 
 * 当n为奇数的时候，我们必须特殊的处理  n-1这个节点的父节点，
 * 即(n-2)/2这个节点，因为这个节点的左子节点是一个内部节点，
 * 而右子节点是一个外部节点。
 * 这个节点下的所有子节点的编号为n-1,2n-3,2n-2
 * 
 *                  (n-2)/2(内)
 * 
 *         n-2(内)             n-1(外)
 * 
 * 2n-3(外)    2n-2(外)      无                  无
 * 
 * 那么计算内部节点数值的时候，会分成3部分[0 - (n-2)/2-1]
 * [(n-2)/2 - (n-2)/2] [(n-2)/2+1 - (n-2)]
 * 前者的子节点都是内部节点，其子节点中存储的都是胜者的索引
 * 所以比较的时候需要比较 winnerTree[子节点的值]，更新的值是“子节点的值”；
 * 中间的是左边是内部节点，右边是外部节点，
 * 就是比较 winnerTree[左子节点的值]和 右子节点的值
 * 更新的值是“左子节点的值”或 “右子节点的索引”；
 * 最后是子节点都是外部节点的情况，比较子节点的值即可
 * 更新的值是“子节点的索引”
 * 
 * 当n为偶数的时候，就不存在这个问题，更新写起来就少了一类
 * 即[0 - (n-2)/2-1]和[(n-2)/2 - (n-2)]，对应上面的第一类和第三类
 * 
 * 由于这个特殊性，所以我们有3种解决方法:
 * 1.要么我们对于奇数的情况来做一个小的调整
 * 给其补一个特殊节点，其在最后一位，永远是最小值，
 * 由于同样大小的情况下，总是选择前者，所以这个节点永远不会被选到(个人认为最好)
 * 
 * 2.要么我们分类讨论，对于奇偶两种情况分别建立新的子类，或者在一个类里也可以
 * 不过就需要加上判断条件，但是编码时间会比较长
 * 
 * 3.要么我们将n补充到2的幂次，对于n比较小的情况还比较适用
 * 对于n刚好等于2的幂次加一这种情况来说，性能损失很大
 * 
 * 下面的实现是选择第一种解决方案，使用一位空间，将两种情况统一，
 * 并且对性能没有任何的影响的一种方案
 * 
 * 对于数组的空间安排如下：
 * 我使用0开头的方式
 * 子节点1=父节点*2+1
 * 子节点2=父节点*2+2
 * 
 * 父节点=（任意子节点-1）/2   因为: 父节点*2+2-1=父节点*2+1（父节点*2+1)/2=父节点
 * 
 * @author 87663
 * 
 */
public class IntArrayWinnerTree implements IntWinnerTree{
	
	private final static IntComparator DEFAULT_COMPARATOR=IntComparator.GREATER;
	
	private int[] winnerTree;

	private IntComparator comparator;
	
	private int size;
	
	private int n;
	
	public IntArrayWinnerTree(int[] competitor) {
		this(competitor, DEFAULT_COMPARATOR);
	}

	public IntArrayWinnerTree(int[] competitor,IntComparator comparator){
		
		if(competitor.length<=1)
			throw new IllegalArgumentException("The number of competitor shouble be more than 1");
		
		this.comparator=comparator;
		
		size=competitor.length;
		
		//对于奇偶分别初始化外部节点，如果是奇数，就多加一个节点，使其变成偶数个节点
		//将两种情况统一处理
		if((size&1)==0){//size%2==0 偶数
			winnerTree=new int[size<<1-1];// size*2-1;
			System.arraycopy(competitor, 0, winnerTree, size-1, competitor.length);
			
			n=size;
		}else{//奇数
			winnerTree=new int[size<<1];//  size*2-1 +1
			
			System.arraycopy(competitor, 0, winnerTree, size-1, competitor.length);
			winnerTree[winnerTree.length-1]=Integer.MIN_VALUE;//最小值保证其无效
			
			n=size+1;
		}
		
		//初始化第二类内部节点
		for(int index=n-2;index>=(n-2)<<1;--index){
			int child1=index<<1+1,child2=index<<1+2;
			winnerTree[index]=comparator.compare(winnerTree[child1],
					winnerTree[child2])>=0?child1:child2;
		}
		
		//初始化第一类内部节点
		for(int index=(n-2)<<1-1;index>=0;--index){
			int child1=winnerTree[index<<1+1],child2=winnerTree[index<<1+2];
			winnerTree[index]=comparator.compare(winnerTree[child1],
					winnerTree[child2])>=0?child1:child2;
		}
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public int getWinner() {
		return winnerTree[winnerTree[0]];
	}

	@Override
	public int getWinnerIndex() {
		return winnerTree[0]-n+1;//将绝对的数组索引移动到相对索引再返回
	}

	@Override
	public void rePlay(int index, int score) {
		
		if(index<0||index>size-1)//即使我们补充了一个元素，我们并不允许给其赋值，即其是透明的
			throw new IllegalArgumentException("Index should be between 0 to size-1");
		
		//将相对索引移动到绝对的数组索引上
		index=n-1+index;
		
		//更新外部节点
		winnerTree[index]=score;
		
		//父节点索引为 （子节点索引-1）/2
		index=(index-1)>>1;
		
		//更新第二类内部节点
		int child1=index<<1+1,child2=index<<1+2;
		winnerTree[index]=comparator.compare(winnerTree[child1],
				winnerTree[child2])>=0?child1:child2;
				
		do{//对于每个第一类内部节点
			
			index=(index-1)>>1;
			
			//更新这个节点
			child1=winnerTree[index<<1+1];
			child2=winnerTree[index<<1+2];
			winnerTree[index]=comparator.compare(winnerTree[child1],
					winnerTree[child2])>=0?child1:child2;
			
		}while(index!=0);//如果节点的值是0，证明已经到顶了，同时这个节点
							//已经被我们处理完了，我们可以退出了
	}
}
