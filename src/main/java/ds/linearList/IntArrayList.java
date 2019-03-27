package ds.linearList;

public final class IntArrayList extends ReinforcementIntLinearList {

	private UnchekedIntArrayList ucIntList;
	
	public IntArrayList() {
		super();
		ucIntList=new UnchekedIntArrayList();
	}

	public IntArrayList(int capacity) {
		super();
		ucIntList=new UnchekedIntArrayList(capacity);
	}

	public IntArrayList(double growRate) {
		super();
		ucIntList=new UnchekedIntArrayList(growRate);
	}

	public IntArrayList(int capacity, double growRate) {
		super();
		ucIntList=new UnchekedIntArrayList(capacity, growRate);
	}
	
	@Override
	public boolean isEmpty() {
		return ucIntList.isEmpty();
	}

	@Override
	public int size() {
		return ucIntList.size();
	}
	
	private void rangeCheck(int index){
		if(index<0||index>=ucIntList.size())
			throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public int get(int index) {
		rangeCheck(index);
		return ucIntList.get(index);
	}

	@Override
	public void set(int element, int index) {
		rangeCheck(index);
		ucIntList.set(element,index);
	}

	@Override
	public int indexOf(int element) {
		return ucIntList.indexOf(element);
	}

	@Override
	public boolean delete(int element) {
		return ucIntList.delete(element);
	}

	@Override
	public void deleteIndex(int index) {
		rangeCheck(index);
		ucIntList.deleteIndex(index);
	}

	@Override
	public void insert(int element, int index) {
		rangeCheck(index);
		ucIntList.insert(element, index);
	}

	@Override
	public void clear() {
		ucIntList.clear();
	}

	@Override
	public void add(int element) {
		ucIntList.add(element);
	}

}
