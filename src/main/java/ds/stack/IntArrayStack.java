package ds.stack;

import ds.linearList.IntArrayList;

public final class IntArrayStack implements IntStack{

	private IntArrayList list=new IntArrayList();
	
	private void emptyStackCheck(){
		if(list.size()==0)
			throw new EmptyStackException();
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public int top() {
		emptyStackCheck();
		return list.getLast();
	}

	@Override
	public int pop() {
		emptyStackCheck();
		int element=list.getLast();
		list.deleteLast();
		return element;
	}

	@Override
	public void push(int element) {
		list.add(element);
	}

	@Override
	public void clear() {
		list.clear();
	}

}
