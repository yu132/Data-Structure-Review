package ds.set.hashSet;

import ds.set.hashSet.intCollection.IntArrayCollection;
import ds.set.hashSet.intCollection.IntCollection;

public final class IntArrayHashSet extends AbstractIntHashSet{

	@Override
	IntCollection newInstanceOfIntCollection() {
		return new IntArrayCollection();
	}

	public IntArrayHashSet() {
		super();
	}

	public IntArrayHashSet(int capacity, double loadFactor) {
		super(capacity, loadFactor);
	}
	
}
