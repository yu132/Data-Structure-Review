package ds.stack;

import java.util.Arrays;

public class FastIntArrayStack implements IntStack{

	private final static int GROW_RATE=2;
	
	private final static int DEFAULT_CAPACITY=10;
	
	private final static int MAX_CAPACITY=Integer.MAX_VALUE-8;
	
	private int[] stack=new int[DEFAULT_CAPACITY];
	
	private int size;
	
	private void emptyStackCheck(){
		if(size==0)
			throw new EmptyStackException();
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
		emptyStackCheck();
		return stack[size-1];
	}

	@Override
	public int pop() {
		emptyStackCheck();
		return stack[--size];
	}

	@Override
	public void push(int element) {
		growIfNeeded(size+1);
		stack[size++]=element;
	}
	
	private void growIfNeeded(int neededCapacity){
		
		int oldCapacity=stack.length;
		
		if(oldCapacity<neededCapacity){
			
			int newCapacity=oldCapacity*GROW_RATE;
			
			if(newCapacity<neededCapacity)
				newCapacity=neededCapacity;
			
			if(newCapacity>MAX_CAPACITY)
				throw new IllegalStateException("Size of this stack is too large");
			
			stack=Arrays.copyOf(stack, newCapacity);
		}
	}

	@Override
	public void clear() {
		size=0;
	}

}
