package ds.set.hashSet;

import ds.dict.hashMap.IntArrayHashMap;
import ds.dict.hashMap.IntHashMap;
import ds.dict.hashMap.IntValueNotExistException;
import ds.set.IntSet;

public final class IntMapHashSet implements IntSet{

	private IntHashMap table;
	
	public IntMapHashSet() {
		super();
		table=new IntArrayHashMap();
	}
	
	public IntMapHashSet(int capacity,double loadFactor) {
		super();
		table=new IntArrayHashMap(capacity,loadFactor);
	}
	
	@Override
	public boolean isEmpty() {
		return table.isEmpty();
	}

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public boolean add(int element) {
		try {
			table.put(element, 0);
			return true;
		} catch (IntValueNotExistException e) {
			return false;
		}
	}

	@Override
	public boolean contain(int element) {
		return table.contain(element);
	}

	@Override
	public boolean remove(int element) {
		try {
			table.remove(element);
			return true;
		} catch (IntValueNotExistException e) {
			return false;
		}
	}

	@Override
	public void clear() {
		table.clear();
	}

}
