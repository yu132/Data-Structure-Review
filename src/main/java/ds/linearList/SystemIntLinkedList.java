package ds.linearList;

import java.util.LinkedList;

public class SystemIntLinkedList extends ReinforcementIntLinearList{

	private LinkedList<Integer> list=new LinkedList<>();
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public int get(int index) {
		return list.get(index);
	}

	@Override
	public void set(int element, int index) {
		list.set(index, element);
	}

	@Override
	public int indexOf(int element) {
		return list.indexOf(element);
	}

	@Override
	public boolean delete(int element) {
		return list.remove(Integer.valueOf(element));
	}

	@Override
	public void deleteIndex(int index) {
		list.remove(index);
	}

	@Override
	public void insert(int element, int index) {
		list.add(index, element);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public void add(int element) {
		list.add(element);
	}

}
