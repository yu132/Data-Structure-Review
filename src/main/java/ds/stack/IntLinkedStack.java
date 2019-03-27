package ds.stack;

import ds.linearList.IntLinkedList;

public final class IntLinkedStack implements IntStack{

	private IntLinkedList list=new IntLinkedList();
	
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
		return list.getFirst();
	}

	@Override
	public int pop() {
		emptyStackCheck();
		int element=list.getFirst();
		list.deleteFirst();
		return element;
	}

	@Override
	public void push(int element) {
		list.addFirst(element);
	}

	@Override
	public void clear() {
		list.clear();
	}

}
