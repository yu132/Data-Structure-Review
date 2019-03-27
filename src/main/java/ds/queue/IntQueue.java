package ds.queue;

public interface IntQueue {

	boolean isEmpty();
	
	int size();
	
	int top();
	
	int pop();
	
	void push(int element);
	
	void clear();
	
}
