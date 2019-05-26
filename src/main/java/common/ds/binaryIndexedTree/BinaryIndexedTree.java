package common.ds.binaryIndexedTree;

public interface BinaryIndexedTree {

	default int lowbit(int x) {
		return x & -x;
	}

}
