package ag.unionFind;

import java.util.Arrays;

/**
 * 在最简单的实现上加上了每个联通分支的重量
 * 总是会把小树连接到大树上，从而降低了find方法的复杂度
 * 使得union和find都是接近O（1）的，但是不完全等于O（1）
 * 
 * @author 87663
 *
 */
public final class WeightedUnionFind extends AbstractUnionFind{
	
	private int[] size;
	
	public WeightedUnionFind(int typeNumber) {
		super(typeNumber);
		size=new int[typeNumber];
		Arrays.fill(size, 1);
	}
	
	@Override
	public int find(int element){
		return uf[element]==element?element:(uf[element]=find(uf[element]));
	}

	@Override
	public void union(int element1, int element2) {
		int type1=find(element1);
		int type2=find(element2);
		
		if(type1==type2)
			return;
		
		if(size[type1]<size[type2]){
			uf[type1]=type2;
			size[type2]+=size[type1];
		}else{
			uf[type2]=type1;
			size[type1]+=size[type2];
		}
		--count;
	}
	
}
