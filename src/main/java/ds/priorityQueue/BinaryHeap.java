package ds.priorityQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import ds.IntComparator;
import ds.utils.GetNext2Pow;
import ds.utils.MyArrays;

/**
 * 这个实现里面heap[0]是没有使用的，浪费了一个空间
 * 其实可以使用0作为根 child1=2*parent+1 child2=2*(parent+1)即可
 * 
 * 这里1为根 child1=2*parent child2=2*parent+1
 * 
 * 最大堆和最小堆的区别就在于comparator的不同
 * 
 * @author 87663
 *
 */
public class BinaryHeap implements IntPriorityQueue{
	
	public final static IntComparator MAX_COMPARATOR=(x,y)->x-y;
	
	public final static IntComparator MIN_COMPARATOR=(x,y)->y-x;
	
	private final static IntComparator DEFAULT_COMPARATOR=MAX_COMPARATOR;
	
	private final static int DEFAULT_CAPACITY=16;
	
	private final static int MAX_CAPACITY=Integer.MAX_VALUE-8;

	private IntComparator comparator;
	
	private int[] heap;
	
	private int size=0;
	
	public BinaryHeap() {
		super();
		this.comparator = DEFAULT_COMPARATOR;
		this.heap = new int[DEFAULT_CAPACITY];
	}
	
	public BinaryHeap(int capacity) {
		this(null,capacity,DEFAULT_COMPARATOR);
	}
	
	public BinaryHeap(int[] initArray) {
		this(initArray, initArray.length, DEFAULT_COMPARATOR);
	}
	
	public BinaryHeap(IntComparator comparator) {
		this(null, DEFAULT_CAPACITY, comparator);
	}
	
	public BinaryHeap(int capacity,IntComparator comparator) {
		this(null, capacity, comparator);
	}
	
	public BinaryHeap(int[] initArray, int capacity) {
		this(initArray, capacity, DEFAULT_COMPARATOR);
	}
	
	public BinaryHeap(int[] initArray,IntComparator comparator) {
		this(initArray, initArray.length, comparator);
	}
	
	public BinaryHeap(int[] initArray,int capacity,IntComparator comparator) {
		super();
		
		this.comparator = comparator;
		
		if(initArray==null){
		
			if(capacity<=0)
				throw new IllegalArgumentException("Capacity should be positive");
			
			if(capacity>DEFAULT_CAPACITY){
			
				capacity=GetNext2Pow.get(capacity);
				
				if(capacity<0){
					capacity=MAX_CAPACITY;
				}
			}else
				capacity=DEFAULT_CAPACITY;
			
			this.heap = new int[capacity];
			
		}else{
			
			if(capacity<=initArray.length)
				capacity=initArray.length;
			
			if(capacity>DEFAULT_CAPACITY){
			
				capacity=GetNext2Pow.get(capacity);
				
				if(capacity<0){
					capacity=MAX_CAPACITY;
				}
			}else
				capacity=DEFAULT_CAPACITY;
			
			this.heap = new int[capacity];
			
			System.arraycopy(initArray, 0, heap, size+1, initArray.length);
			
			size=initArray.length;
			
			heapify();
		}
	}
	
	private void grow(int capacity){
		if(capacity<0){
			if(heap.length==MAX_CAPACITY)
				throw new IllegalStateException("Heap is too big");
			else
				capacity=MAX_CAPACITY;
		}
		
		heap=Arrays.copyOf(heap, capacity);
	}
	
	private void grow(){
		grow(heap.length<<1);
	}
	
	private void fastGrow(int capacity){
		if(capacity<=0||capacity>MAX_CAPACITY)
			throw new IllegalStateException("Heap is too big");
		grow(GetNext2Pow.get(capacity));
	}
	
	/**
	 * 自上向下检查一颗子树是否符合堆的要求
	 * @param father
	 */
	private void checkDown(int father){
		
		int child=father<<1;// father*2
		
		while(child<=size){
			
			//选择子节点中大的那个节点
			if(child+1<=size && heap[child+1]>heap[child])
				++child;
			
			//子节点小于等于父节点,证明这颗树符合堆的要求
			if(comparator.compare(heap[child], heap[father])<=0)
				break;
			
			//否则将两者置换，并且检查那颗子树是否符合，
			//另一颗子树由于没有改变，所以无需处理
			MyArrays.swap(heap, child, father);
			
			father=child;
			child=child<<1;
		}
	}
	
	/**
	 * 自下向上检查一颗子树是否符合要求
	 * @param child
	 */
	private void checkUp(int child){
		
		int father=child>>1;// child/2
		
		while(father>=1){
			
			//如果这个堆中的父亲节点比子节点大，那么符合要求
			
			if(comparator.compare(heap[father], heap[child])>=0)
				break;
			
			//否则将两者置换，然后我们需要继续检查这个父节点
			//和其祖先节点是否符合规范
			MyArrays.swap(heap, child, father);
			
			child=father;
			father=child>>1;
		}
	}

	/**
	 * 将数组堆化
	 */
	private void heapify(){
		//对于每个非叶子节点，都从上向下检查以其为根节点的子树是否合法
		for(int root=size/2;root>=1;--root)
			checkDown(root);
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
	public int top() {
		if(size==0)
			throw new NoSuchElementException("Heap is empty");
		return heap[1];
	}

	@Override
	public int pop() {
		if(size==0)
			throw new NoSuchElementException("Heap is empty");
		
		int element=heap[1];
		
		//将最后一个节点拿到第一个节点上
		heap[1]=heap[size--];
		
		//然后自上向下检查是否合法
		checkDown(1);
		
		return element;
	}

	@Override
	public void push(int element) {
		if(size>=heap.length-1)
			grow();
		
		//把新的节点放到堆的最后一个位置
		heap[++size]=element;
		
		//自下向上检查是否合法
		checkUp(size);
	}

	@Override
	public void clear() {
		size=0;
	}

	@Override
	public void pushAll(int[] array) {
		
		if(array.length<size/100)
			for(int i=0;i<array.length;++i)
				this.push(array[i]);
		else{
			if(size+array.length>=heap.length-1)
				fastGrow(size+array.length);
			
			System.arraycopy(array, 0, heap, size+1, array.length);
			
			size+=array.length;
			
			heapify();
		}
	}
	
}
