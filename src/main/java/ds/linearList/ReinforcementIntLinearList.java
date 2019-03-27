package ds.linearList;

public abstract class ReinforcementIntLinearList implements IntLinearList,CommonOperationsOfIinearList{

	public void addFirst(int element){
		this.insert(element, 0);
	}
	
	public void addLast(int element){
		this.add(element);
	}
	
	public void deleteFirst(){
		this.deleteIndex(0);
	}
	
	public void deleteLast(){
		this.deleteIndex(this.size()-1);
	}
	
	public int getFirst(){
		return this.get(0);
	}
	
	public int getLast(){
		return this.get(this.size()-1);
	}
	
}
