package ag.unionFind;

/**
 * 最简单的实现，有可能会将大树连接到小树上，如果总是这样连接，
 * 那么find方法的时间复杂度为O（树的高度）
 * 由于union方法需要执行find方法，所以其时间复杂度总是大于等于O（树的高度）的
 * @author 87663
 *
 */
public final class UnionFind extends AbstractUnionFind{

	public UnionFind(int typeNumber) {
		super(typeNumber);
	}

	@Override
	public int find(int element){
		return uf[element]==element?element:(uf[element]=find(uf[element]));
	}
	
	@Override
	public void union(int element1,int element2){
		int type1=find(element1);
		int type2=find(element2);
		
		if(type1==type2)
			return;
		
		uf[type1]=type2;
		--count;
	}
	
}
