package ag.unionFind;

public abstract class AbstractUnionFind implements IUnionFind{

	protected int count;
	
	protected int[] uf;
	
	public AbstractUnionFind(int typeNumber) {
		super();
		this.uf = new int[typeNumber];
		count=typeNumber;
		for(int i=0;i<typeNumber;i++){
			uf[i]=i;
		}
	}
	
	@Override
	public boolean connected(int element1, int element2) {
		return find(element1)==find(element2);
	}

	@Override
	public int count() {
		return count;
	}
	
}
