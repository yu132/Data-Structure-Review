package ds.tournamentTree;

import ds.IntComparator;

/**
 * abandoned 错误的实现
 * 
 * @author 87663
 *
 */
public class WrongOne implements IntWinnerTree{

	private int[] winnerTree;
	
	//TODO 写默认的比较器 大小
	private IntComparator comparator;
	
	private int size;
	
	public WrongOne(int[] competitor) {
		super();
		
		size=competitor.length;
		
		int length=size<<1-1;
		winnerTree=new int[length];
		
		//将所有竞赛者复制到队尾
		System.arraycopy(competitor, 0, winnerTree, size-1, size);
		
		//对于每个非叶子节点，都有一场比赛
		for(int father=size-2,minFather=(size-2)>>1+1;father>=minFather;--father){
			int child=father<<1+1;
			//选择一个较大的元素，如果相等那么选择序号小的元素
			winnerTree[father]=comparator.compare(winnerTree[child], winnerTree[child+1])>=0?child:child+1;
		}
		
		//处理边界元素
		if(size%2!=0)
			winnerTree[(size-2)>>1]=comparator.compare(winnerTree[winnerTree[size-2]], winnerTree[size-1])>=0?winnerTree[size-2]:size-1;
		else
			winnerTree[(size-2)>>1]=comparator.compare(winnerTree[size-1], winnerTree[size])>=0?size-1:size;
		
		//2级以上的比赛
		for(int father=(competitor.length-2)>>1-1;father>=0;--father){
			winnerTree[father]=comparator.compare(winnerTree[winnerTree[father<<1+1]], winnerTree[winnerTree[father<<1+2]])>=0?
					winnerTree[father<<1+1]:winnerTree[father<<1+2];
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
		return winnerTree[0];
	}

	@Override
	public void rePlay(int index, int score) {
		
		//计算这名权手的分数的位置
		int pos=winnerTree.length-1+index;
		
		winnerTree[pos]=score;
		
		int father=(pos-1)>>1;
		
		if(comparator.compare(winnerTree[pos], winnerTree[winnerTree[father]])<=0)
			return;
		
		winnerTree[father]=pos;
		pos=father;
		
		while(pos!=0){
			father=(pos-1)>>1;
		
			//看看这个权手的成绩是否大于
			if(comparator.compare(winnerTree[winnerTree[pos]], winnerTree[winnerTree[father]])<=0)
				break;
			
			winnerTree[father]=winnerTree[pos];
			pos=father;
		}
	}
}
