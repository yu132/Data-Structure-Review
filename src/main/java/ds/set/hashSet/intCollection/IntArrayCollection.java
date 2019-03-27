package ds.set.hashSet.intCollection;

import java.util.NoSuchElementException;
import java.util.Objects;

import ds.linearList.UnchekedIntArrayList;

public class IntArrayCollection implements IntCollection{

	private UnchekedIntArrayList elements=new UnchekedIntArrayList();
	
	@Override
	public boolean add(int element) {
		int index=elements.indexOf(element);
		
		if(index!=-1)
			return false;
		
		elements.add(element);
		return true;
	}

	@Override
	public boolean contain(int element) {
		return elements.indexOf(element)!=-1;
	}

	@Override
	public boolean remove(int element) {
		return elements.delete(element);
	}

	@Override
	public void clear() {
		elements.clear();
	}

	@Override
	public IntCollectionIterator iterator() {
		return new IntArrayCollectionIterator(this);
	}
	
	public static class IntArrayCollectionIterator implements IntCollectionIterator{
		
		private IntArrayCollection list;

		private int index=0;
		
		public IntArrayCollectionIterator(IntArrayCollection list) {
			super();
			Objects.requireNonNull(list);
			this.list = list;
		}

		@Override
		public int next() {
			if (index >= list.elements.size())
                throw new NoSuchElementException("No more elements");
			return list.elements.get(index);
		}

		@Override
		public boolean hasNext() {
			return index!=list.elements.size();
		}
	}

}
