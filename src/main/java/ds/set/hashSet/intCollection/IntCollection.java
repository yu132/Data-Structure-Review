package ds.set.hashSet.intCollection;

public interface IntCollection {

	boolean add(int element);
	
	boolean contain(int element);
	
	boolean remove(int element);
	
	void clear();
	
	IntCollectionIterator iterator();
	
	public static interface IntCollectionIterator{
		
		int next();
		
		boolean hasNext();
		
	}
	
}
