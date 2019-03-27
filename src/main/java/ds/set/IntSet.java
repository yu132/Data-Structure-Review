package ds.set;

public interface IntSet {

	boolean isEmpty();
	
	int size();
	
	boolean add(int element);
	
	boolean contain(int element);
	
	boolean remove(int element);
	
	void clear();
	
}
