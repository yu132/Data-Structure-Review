package ds.set.hashSet;

import java.util.Arrays;

import ds.set.IntSet;
import ds.set.hashSet.intCollection.IntCollection;
import ds.set.hashSet.intCollection.IntCollection.IntCollectionIterator;

public abstract class AbstractIntHashSet implements IntSet{
	
	private final static int DEFAULT_CAPACITY=16;
	
	private final static int MAX_CAPACITY=1<<30;
	
	private final static double DEFAULT_LOAD_FACTOR=0.75d;

	private IntCollection[] table;
	
	private int size;
	
	private double loadFactor;
	
	private int threshold;
	
	private int capacityMinus1;
	
	private int initializeCapacity(int requiredCapacity){
		
		int initialCapacity=DEFAULT_CAPACITY;
		
		if(requiredCapacity>initialCapacity){
			initialCapacity=requiredCapacity-1;
			
	        initialCapacity |= (initialCapacity >>>  1);
	        initialCapacity |= (initialCapacity >>>  2);
	        initialCapacity |= (initialCapacity >>>  4);
	        initialCapacity |= (initialCapacity >>>  8);
	        initialCapacity |= (initialCapacity >>> 16);
	        initialCapacity++;
	        
	        if (initialCapacity < 0)//0x80000000 -> Integer.MIN_VALUE
                initialCapacity >>>= 1;//0x40000000 -> 2^30
		}
		
		return initialCapacity;
	}
	
	public AbstractIntHashSet() {
		super();
		this.table = new IntCollection[DEFAULT_CAPACITY];
		Arrays.setAll(this.table, (i)->newInstanceOfIntCollection());
		
		this.loadFactor=DEFAULT_LOAD_FACTOR;
		
		this.threshold=(int) (DEFAULT_CAPACITY*loadFactor);
		this.capacityMinus1=DEFAULT_CAPACITY-1;
	}
	
	public AbstractIntHashSet(int capacity,double loadFactor) {
		super();
		
		capacity=initializeCapacity(capacity);
		
		this.table = new IntCollection[capacity];
		Arrays.setAll(this.table, (i)->newInstanceOfIntCollection());
		
		this.loadFactor=loadFactor;
		
		this.threshold=(int) (capacity*loadFactor);
		
		if(this.threshold>MAX_CAPACITY)
			this.threshold=Integer.MAX_VALUE;
		
		this.capacityMinus1=capacity-1;
	}
	
	private int hash(int key){
		return key^(key>>>16);
	}
	
	private int binIndex(int key){
		return hash(key)&capacityMinus1;
	}
	
	private void grow(){
		int newCap=table.length<<1;
		
		if(newCap<0)
			throw new IllegalStateException("HashMap too big");
		
		capacityMinus1=newCap-1;
		
		double newThr=newCap*loadFactor;
		
		threshold=(newCap<=MAX_CAPACITY&&newThr<=MAX_CAPACITY)?(int) newThr:Integer.MAX_VALUE;
		
		IntCollection[] newTable=new IntCollection[newCap];
		Arrays.setAll(newTable, (i)->newInstanceOfIntCollection());
		
		//将键值对转移到新表
		for(int i=0;i<table.length;++i){
			IntCollectionIterator it=table[i].iterator();
			
			while(it.hasNext()){
				int element=it.next();
				newTable[binIndex(element)].add(element);
			}
		}
		
		table=newTable;
	}

	abstract IntCollection newInstanceOfIntCollection();

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(int element){
		if(!table[binIndex(element)].add(element))
			return false;
		++size;
		if(size>threshold)
			grow();
		return true;
	}

	@Override
	public boolean contain(int element) {
		return table[binIndex(element)].contain(element);
	}

	@Override
	public boolean remove(int element){
		if(!table[binIndex(element)].remove(element))
			return false;
		--size;
		return true;
	}

	@Override
	public void clear() {
		for(int i=0;i<table.length;++i)
			table[i].clear();
		size=0;
	}

}
