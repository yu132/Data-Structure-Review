package ds;

public interface IntComparator {

	public final static IntComparator	GREATER	= (x, y) -> (x < y) ? -1 : ((x == y) ? 0 : 1);

	public final static IntComparator	SMALLER	= (x, y) -> (x > y) ? -1 : ((x == y) ? 0 : 1);

	int compare(int a, int b);

}
