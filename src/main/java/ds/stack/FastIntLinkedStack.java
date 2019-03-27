package ds.stack;

public class FastIntLinkedStack implements IntStack{

	private static class Node{
		int val;
		Node next;
	}
	
	private Node head;
	
	private int size;

	public FastIntLinkedStack() {
		super();
		
		this.head = new Node();
		head.next=head;
	}
	
	private void emptyStackCheck(){
		if(head.next==head)
			throw new EmptyStackException();
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int top() {
		emptyStackCheck();
		return head.next.val;
	}

	@Override
	public int pop() {
		emptyStackCheck();
		int element=head.next.val;
		
		head.next=head.next.next;
		
		--size;
		
		return element;
	}

	@Override
	public void push(int element) {
		Node newNode=new Node();
		
		newNode.val=element;
		newNode.next=head.next;
		
		head.next=newNode;
		
		++size;
	}

	@Override
	public void clear() {
		head.next=head;
		size=0;
	}
}
