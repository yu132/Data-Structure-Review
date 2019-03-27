package ds.dict.hashMap.intEntryCollection;

import ds.dict.hashMap.IntValueNotExistException;

public interface IntEntryCollection {

	int put(int key,int value) throws IntValueNotExistException;
	
	int get(int key) throws IntValueNotExistException;
	
	boolean contain(int key);
	
	int remove(int key) throws IntValueNotExistException;
	
	void clear();
	
	IntEntryCollectionIterator iterator();
	
	public static interface IntEntryCollectionIterator{
		
		int[] next();
		
		boolean hasNext();
		
	}
	
}
