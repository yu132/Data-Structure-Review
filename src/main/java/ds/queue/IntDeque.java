package ds.queue;

public interface IntDeque {

	boolean isEmpty();
	
	int size();
	
	void pushFirst(int element);
	
	void pushLast(int element);
	
	int topFirst();
	
	int topLast();
	
	int popFirst();
	
	int popLast();
	
	void clear();
	
}
