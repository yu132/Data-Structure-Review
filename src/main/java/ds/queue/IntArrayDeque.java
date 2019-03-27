package ds.queue;

public class IntArrayDeque implements IntDeque{
	
	private final static int DEFAULT_CAPACITY=16;
	
	private final static int GROW_RATE_BIT=1;
	
	private int[] deque;
	
	/**
	 * 第一个元素所在的位置
	 */
	private int frontIndex;
	
	/**
	 * 尾部下一个元素加入的位置
	 */
	private int backIndex;
	
	private int totalLengthMinus1;
	
	public IntArrayDeque() {
		super();
		this.deque = new int[DEFAULT_CAPACITY];
		
		totalLengthMinus1=DEFAULT_CAPACITY-1;
	}

	public IntArrayDeque(int capacity) {
		super();
		this.deque = new int[initializeCapacity(capacity)];
		
		totalLengthMinus1=deque.length-1;
	}

	/**
	 * 这个方法可以帮助我们快速的将requiredCapacity增大到比其大的最小的2次幂
	 * 为什么要把容量设置成2的幂次呢？
	 * 那是因为由于队列是循环的，那么我们每次在计算index+-1的时候，会出现越界的问题，
	 * 一般来说我们会使用(index+length)%length来检测这个情况，但是这样我们就需要计算求模
	 * 而且我们无法保证(index+length)不会越界，那么我们还需要分情况进行讨论，
	 * 在遇到index-1时，我们判断结果是不是-1，若是，就变为length-1；
	 * 遇到index+1时，若结果为length，则置为0
	 * 
	 * 可是在长度为2的幂次的情况下，我们就有更好的方法来处理边界问题：
	 * -1 & (length-1) = length-1
	 * length & (length-1) = 0
	 * x & (length-1) = x (0<x && x<length)
	 * 
	 * 以上这三条公式告诉我们，我们只需要对进行变化的index的结果和
	 * (length-1)进行或操作，我们会自动的解决这个边界问题，将问题简化
	 * 
	 * 同时，在计算队列长度的时候我们可以通过公式 (backIndex-frontIndex) & (length-1)计算出结果
	 */
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
	
	/**
	 * 由于增长的时候队列总是满的，所以frontIndex总是等于backIndex
	 * 如果我们要保持原有的队列样式，那么我们需要在中间增加长度
	 * 就需要将两段（0-backIndex 和 frontIndex-length）分别复制到两端
	 * 结果就是 {[0 -> backIndex](有元素) [backIndex -> (newLength-length+frontIndex)](空)
	 * [backIndex-(newLength-length+frontIndex) -> newLength](有元素)}
	 * 
	 * 但是我们可以使用一种更好的办法，那就是将后段复制到前面，前段复制到后面
	 * 这样新的队列就是一个有序的状态，同时我们可以少算几个值，加快速度
	 * 结果是{[0-length](有元素) [length-newLength](空)}
	 * 
	 */
	private void growIfNeed(){
		if(frontIndex==backIndex){
			int length=deque.length-frontIndex;
			
			int newCapacity=deque.length<<GROW_RATE_BIT;
			
			if(newCapacity<0)
				throw new IllegalStateException("Size of this deque is too large");
			
			int[] newQueue=new int[newCapacity];
			
			System.arraycopy(deque, frontIndex, newQueue, 0, length);
			System.arraycopy(deque, 0, newQueue, length, frontIndex);
			
			deque=newQueue;
			
			frontIndex=0;
			backIndex=deque.length>>GROW_RATE_BIT;
			
			totalLengthMinus1=newCapacity-1; 
		}
	}
	
	@Override
	public boolean isEmpty() {
		return frontIndex==backIndex;
	}

	@Override
	public int size() {
		return (backIndex-frontIndex) & totalLengthMinus1;
	}

	@Override
	public void pushFirst(int element) {
		frontIndex=(frontIndex-1) & totalLengthMinus1;
		deque[frontIndex]=element;
		
		growIfNeed();
	}

	@Override
	public void pushLast(int element) {
		deque[backIndex]=element;
		backIndex=(backIndex+1) & totalLengthMinus1;
		
		growIfNeed();
	}

	@Override
	public int topFirst() {
		if(isEmpty())
			throw new EmptyQueueException();
		return deque[frontIndex];
	}

	@Override
	public int topLast() {
		if(isEmpty())
			throw new EmptyQueueException();
		return deque[(backIndex-1) & totalLengthMinus1];
	}

	@Override
	public int popFirst() {
		if(isEmpty())
			throw new EmptyQueueException();
		int element=deque[frontIndex];
		frontIndex=(frontIndex+1) & totalLengthMinus1;
		return element;
	}

	@Override
	public int popLast() {
		if(isEmpty())
			throw new EmptyQueueException();
		backIndex=(backIndex-1) & totalLengthMinus1;
		return deque[backIndex];
	}

	@Override
	public void clear() {
		backIndex=frontIndex;
	}
}
