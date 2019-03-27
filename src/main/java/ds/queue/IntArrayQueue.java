package ds.queue;

public class IntArrayQueue implements IntQueue{

	private IntArrayDeque queue;
	
	public IntArrayQueue() {
		super();
		this.queue = new IntArrayDeque();
	}

	public IntArrayQueue(int capacity) {
		super();
		this.queue = new IntArrayDeque(capacity);
	}

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
		return queue.topLast();
	}

	@Override
	public int pop() {
		return queue.popLast();
	}

	@Override
	public void push(int element) {
		queue.pushFirst(element);
	}

	@Override
	public void clear() {
		queue.clear();
	}

}
