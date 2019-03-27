package ds.queue;

import ds.linearList.IntLinkedList;

public class IntLinkedQueue implements IntQueue{

	private IntLinkedList queue=new IntLinkedList();

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public int top() {
		return queue.getFirst();
	}

	@Override
	public int pop() {
		int element=queue.getFirst();
		queue.deleteFirst();
		return element;
	}

	@Override
	public void push(int element) {
		queue.addLast(element);
	}

	@Override
	public void clear() {
		queue.clear();
	}
}
