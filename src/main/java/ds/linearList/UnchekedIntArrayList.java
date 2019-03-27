package ds.linearList;

import java.util.Arrays;

public final class UnchekedIntArrayList implements IntLinearList{

	private final static int DEFAULT_CAPACITY=10;
	
	private final static int MAX_CAPACITY=Integer.MAX_VALUE-8;
	
	private double growRate=2;
	
	private final static int NEED_REDUCE_RATE_BIT=4;
	
	private final static int REDUCE_RATE_BIT=3;
	
	private int[] array;
	
	private int size;
	
	public UnchekedIntArrayList() {
		super();
		array=new int[DEFAULT_CAPACITY];
	}

	public UnchekedIntArrayList(int capacity) {
		super();
		array=new int[capacity];
	}

	public UnchekedIntArrayList(double growRate) {
		super();
		this.growRate = growRate;
	}

	public UnchekedIntArrayList(int capacity, double growRate) {
		super();
		this.growRate = growRate;
		array=new int[capacity];
	}
	
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int get(int index) {
		return array[index];
	}

	@Override
	public void set(int element, int index) {
		array[index]=element;
	}

	@Override
	public int indexOf(int element) {
		for(int i=0;i<size;++i)
			if(array[i]==element)
				return i;
		return -1;
	}

	@Override
	public boolean delete(int element) {
		for(int i=0;i<size;++i)
			if(array[i]==element){
				System.arraycopy(array, i+1, array, i, size-i-1);
				--size;
				
				reduceCapacityIfNeeded();
				
				return true;
			}
		return false;
	}

	@Override
	public void deleteIndex(int index) {
		System.arraycopy(array, index+1, array, index, size-index-1);
		--size;
		
		reduceCapacityIfNeeded();
	}
	
	private void reduceCapacityIfNeeded(){
		int capacity=array.length;
		if(size<(capacity>>NEED_REDUCE_RATE_BIT)){
			array=Arrays.copyOf(array, capacity>>REDUCE_RATE_BIT);
		}
	}

	@Override
	public void insert(int element, int index) {
		growIfNeeded(size+1);
		
		System.arraycopy(array, index, array, index+1, size-index);
		array[index]=element;
		++size;
	}
	
	private void growIfNeeded(int neededCapacity){
		
		int oldCapacity=array.length;
		
		if(oldCapacity<neededCapacity){
			
			int newCapacity=(int) (oldCapacity*growRate);
			
			if(newCapacity<neededCapacity)
				newCapacity=neededCapacity;
			
			if(newCapacity>MAX_CAPACITY)
				throw new RuntimeException("Size of this list is too large");
			
			array=Arrays.copyOf(array, newCapacity);
		}
	}

	@Override
	public void clear() {
		size=0;
	}

	public void add(int element) {
		growIfNeeded(size+1);
		
		array[size]=element;
		++size;
	}

}
