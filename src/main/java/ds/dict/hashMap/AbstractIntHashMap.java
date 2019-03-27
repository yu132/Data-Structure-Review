package ds.dict.hashMap;

import java.util.Arrays;

import ds.dict.hashMap.intEntryCollection.IntEntryCollection;
import ds.dict.hashMap.intEntryCollection.IntEntryCollection.IntEntryCollectionIterator;

public abstract class AbstractIntHashMap implements IntHashMap{
	
	private final static int DEFAULT_CAPACITY=16;
	
	private final static int MAX_CAPACITY=1<<30;
	
	private final static double DEFAULT_LOAD_FACTOR=0.75d;

	private IntEntryCollection[] table;
	
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
	
	public AbstractIntHashMap() {
		super();
		this.table = new IntEntryCollection[DEFAULT_CAPACITY];
		Arrays.setAll(this.table, (i)->newInstanceOfIntEntryCollection());
		
		this.loadFactor=DEFAULT_LOAD_FACTOR;
		
		this.threshold=(int) (DEFAULT_CAPACITY*loadFactor);
		this.capacityMinus1=DEFAULT_CAPACITY-1;
	}
	
	public AbstractIntHashMap(int capacity,double loadFactor) {
		super();
		
		capacity=initializeCapacity(capacity);
		
		this.table = new IntEntryCollection[capacity];
		Arrays.setAll(this.table, (i)->newInstanceOfIntEntryCollection());
		
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
		
		IntEntryCollection[] newTable=new IntEntryCollection[newCap];
		Arrays.setAll(newTable, (i)->newInstanceOfIntEntryCollection());
		
		//将键值对转移到新表
		for(int i=0;i<table.length;++i){
			IntEntryCollectionIterator it=table[i].iterator();
			
			while(it.hasNext()){
				int[] kv=it.next();
				try {
					newTable[binIndex(kv[0])].put(kv[0], kv[1]);
				} catch (IntValueNotExistException e) {}
			}
		}
		
		table=newTable;
	}

	abstract IntEntryCollection newInstanceOfIntEntryCollection();

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int put(int key, int value) throws IntValueNotExistException{
		try{
			int lastVal=table[binIndex(key)].put(key, value);
			return lastVal;
		}catch (IntValueNotExistException e) {//以前没有这个键
			++size;
			if(size>threshold)
				grow();
			throw new IntValueNotExistException("No Int Value map to this key brfore");
		}
	}

	@Override
	public int get(int key) throws IntValueNotExistException {
		return table[binIndex(key)].get(key);
	}

	@Override
	public boolean contain(int key) {
		return table[binIndex(key)].contain(key);
	}

	@Override
	public int remove(int key) throws IntValueNotExistException{
		int val=table[binIndex(key)].remove(key);
		--size;
		return val;
	}

	@Override
	public void clear() {
		for(int i=0;i<table.length;++i)
			table[i].clear();
		size=0;
	}

}
