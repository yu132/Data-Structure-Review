package ds.dict.hashMap.intEntryCollection;

import java.util.NoSuchElementException;
import java.util.Objects;

import ds.dict.hashMap.IntValueNotExistException;
import ds.linearList.UnchekedIntArrayList;

public class IntArrayEntryCollection implements IntEntryCollection{

	private UnchekedIntArrayList keys=new UnchekedIntArrayList();
	
	private UnchekedIntArrayList values=new UnchekedIntArrayList();

	@Override
	public int put(int key, int value) throws IntValueNotExistException {
		int index=keys.indexOf(key);
		
		if(index!=-1){
			int oldValue=values.get(index);
			values.set(value, index);
			
			return oldValue;
		}
		
		keys.add(key);
		values.add(value);
		
		throw new IntValueNotExistException("Old value does not exist");
	}

	@Override
	public int get(int key) throws IntValueNotExistException {
		int index=keys.indexOf(key);
		
		if(index==-1)
			throw new IntValueNotExistException("Value does not exist");
		
		return values.get(index);
	}

	@Override
	public boolean contain(int key) {
		return keys.indexOf(key)!=-1;
	}

	@Override
	public int remove(int key) throws IntValueNotExistException {
		int index=keys.indexOf(key);
		
		if(index==-1)
			throw new IntValueNotExistException("Old value does not exist");
		
		int oldValue=values.get(index);
		
		keys.deleteIndex(index);
		values.deleteIndex(index);
		
		return oldValue;
	}

	@Override
	public void clear() {
		keys.clear();
		values.clear();
	}

	@Override
	public IntEntryCollectionIterator iterator() {
		return new IntArrayEntryCollectionIterator(this);
	}
	
	public static class IntArrayEntryCollectionIterator implements IntEntryCollectionIterator{
		
		private IntArrayEntryCollection list;

		private int index=0;
		
		public IntArrayEntryCollectionIterator(IntArrayEntryCollection list) {
			super();
			Objects.requireNonNull(list);
			this.list = list;
		}

		@Override
		public int[] next() {
			if (index >= list.keys.size())
                throw new NoSuchElementException();
			return new int[]{list.keys.get(index),list.values.get(index++)};
		}

		@Override
		public boolean hasNext() {
			return index!=list.keys.size();
		}
	}
}
