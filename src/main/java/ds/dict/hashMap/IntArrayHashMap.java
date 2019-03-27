package ds.dict.hashMap;

import ds.dict.hashMap.intEntryCollection.IntArrayEntryCollection;
import ds.dict.hashMap.intEntryCollection.IntEntryCollection;

public final class IntArrayHashMap extends AbstractIntHashMap{

	@Override
	IntEntryCollection newInstanceOfIntEntryCollection() {
		return new IntArrayEntryCollection();
	}

	public IntArrayHashMap() {
		super();
	}

	public IntArrayHashMap(int capacity, double loadFactor) {
		super(capacity, loadFactor);
	}

}
