package ds.stack;

import org.junit.Assert;
import org.junit.Test;

public class Stack {

	public void test(IntStack s){
		s.push(1);
		s.push(2);
		
		Assert.assertEquals(s.top(), 2);
		
		s.pop();
		
		System.out.println(s.size());
		
		Assert.assertEquals(s.pop(), 1);
		
		s.push(1);
		s.push(2);
		
		Assert.assertEquals(s.size(), 2);
		
		s.clear();
		
		Assert.assertEquals(s.size(), 0);
		
	}
	
	@Test
	public void test1(){
		test(new IntArrayStack());
	}
	
	@Test
	public void test2(){
		test(new IntLinkedStack());
	}
	
	@Test
	public void test3(){
		test(new FastIntArrayStack());
	}
	
}
