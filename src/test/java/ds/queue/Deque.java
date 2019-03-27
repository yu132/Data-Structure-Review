package ds.queue;

import org.junit.Assert;
import org.junit.Test;

public class Deque {

	public void test(IntDeque deque){
		
		deque.pushFirst(100);
		
		deque.pushFirst(102);
		
		deque.pushFirst(103);
		
		Assert.assertEquals(100, deque.topLast());
		
		Assert.assertEquals(103,deque.topFirst());
		
		Assert.assertEquals(3,deque.size());
		
		deque.clear();
		
		Assert.assertEquals(0,deque.size());
		
		deque.pushFirst(100);
		
		deque.pushLast(102);
		
		Assert.assertEquals(102, deque.topLast());
		
		Assert.assertEquals(100,deque.topFirst());
		
		for(int i=0;i<100;i++)
			deque.pushFirst(100);
		
		Assert.assertEquals(102, deque.size());
	}
	
	@Test
	public void test1(){
		test(new IntArrayDeque());
	}
	

	@Test
	public void test2(){
		test(new IntLinkedDeque());
	}
	
}
