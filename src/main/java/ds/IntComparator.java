package ds;

public interface IntComparator {
	
	public final static IntComparator GREATER=(x,y)->x-y;
	
	public final static IntComparator SMALLER=(x,y)->y-x;
	
	int compare(int a,int b);
	
}
