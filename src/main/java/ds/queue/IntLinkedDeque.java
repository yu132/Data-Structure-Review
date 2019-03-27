package ds.queue;

import ds.linearList.IntLinkedList;

public class IntLinkedDeque implements IntDeque{

	private IntLinkedList deque=new IntLinkedList();

	@Override
	public boolean isEmpty() {
		return deque.isEmpty();
	}

	@Override
	public int size() {
		return deque.size();
	}

	@Override
	public void pushFirst(int element) {
		deque.addFirst(element);
	}

	@Override
	public void pushLast(int element) {
		deque.addLast(element);
	}

	@Override
	public int topFirst() {
		return deque.getFirst();
	}

	@Override
	public int topLast() {
		return deque.getLast();
	}

	@Override
	public int popFirst() {
		int element=deque.getFirst();
		deque.deleteFirst();
		return element;
	}

	@Override
	public int popLast() {
		int element=deque.getLast();
		deque.deleteLast();
		return element;
	}

	@Override
	public void clear() {
		deque.clear();
	}
}
