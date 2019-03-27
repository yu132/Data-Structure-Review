package ds.dict.hashMap;

public interface IntHashMap {

	boolean isEmpty();
	
	int size();
	
	int put(int key,int value) throws IntValueNotExistException;
	
	int get(int key) throws IntValueNotExistException;
	
	boolean contain(int key);
	
	int remove(int key) throws IntValueNotExistException;
	
	void clear();
	
}
