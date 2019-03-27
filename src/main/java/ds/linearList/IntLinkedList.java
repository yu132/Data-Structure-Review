package ds.linearList;

import ds.IntIterable;
import ds.IntIterator;

public class IntLinkedList extends ReinforcementIntLinearList implements IntIterable{

	private static class Node{
		int val;
		Node next;
		Node before;
	}
	
	private Node head=new Node();
	
	public IntLinkedList() {
		super();
		head.next=head;
		head.before=head;
	}

	private int size=0;
	
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}
	
	private Node goIndex(int index){
		if(index<0||index>=size)
			throw new ArrayIndexOutOfBoundsException(index);
		
		Node node=head.next;
		
		while(index--!=0)
			node=node.next;
		
		return node;
	}

	@Override
	public int get(int index) {
		Node node=goIndex(index);
		return node.val;
	}

	@Override
	public void set(int element, int index) {
		Node node=goIndex(index);
		node.val=element;
	}

	@Override
	public int indexOf(int element) {
		Node node=head.next;
		
		int index=0;
		
		while(node.val!=element&&node!=head){
			node=node.next;
			++index;
		}
		
		if(node==head)
			return -1;
		
		return index;
	}

	@Override
	public boolean delete(int element) {
		Node node=head.next;
		
		while(node.val!=element&&node!=head)
			node=node.next;
		
		if(node==head)
			return false;
		
		node.next.before=node.before;
		node.before.next=node.next;
		
		--size;
		
		return true;
	}

	@Override
	public void deleteIndex(int index) {
		Node node=goIndex(index);
		
		node.next.before=node.before;
		node.before.next=node.next;
		
		--size;
	}

	@Override
	public void insert(int element, int index) {
		Node node;
		if(size!=index)
			node=goIndex(index);
		else
			node=head;
		
		Node newNode=new Node();
		
		newNode.val=element;
		newNode.next=node;
		newNode.before=node.before;
		
		node.before.next=newNode;
		node.before=newNode;
		
		++size;
	}

	@Override
	public void clear() {
		head.next=head;
		head.before=head;
		
		size=0;
	}
	
	@Override
	public void add(int element) {
		Node node=head;
		
		Node newNode=new Node();
		
		newNode.val=element;
		newNode.next=node;
		newNode.before=node.before;
		
		node.before.next=newNode;
		node.before=newNode;
		
		++size;
	}

	@Override
	public void deleteLast() {
		Node node=head.before;
		
		node.next.before=node.before;
		node.before.next=node.next;
		
		--size;
	}

	@Override
	public int getLast() {
		if(size==0)
			throw new ArrayIndexOutOfBoundsException(0);
		return head.before.val;
	}
	
	@Override
	public IntIterator iterator() {
		return new Iterator(this);
	}

	public static class Iterator implements IntIterator{

		private Node node;
		
		private IntLinkedList linkedList;
		
		public Iterator(IntLinkedList linkedList) {
			super();
			this.node = linkedList.head;
			this.linkedList = linkedList;
		}

		@Override
		public boolean hasNext() {
			return node.next!=linkedList.head;
		}

		@Override
		public int next() {
			node=node.next;
			return node.val;
		}
		
	}
	
}
