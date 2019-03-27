package ds.pq;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import ds.priorityQueue.BinaryHeap;
import ds.priorityQueue.IntPriorityQueue;

public class BinaryHeapTest {

	public static void one(BinaryHeap pq){
		
	//	System.out.println(Arrays.toString(pq.heap));
		
		Assert.assertEquals(12, pq.size());
		
		Assert.assertEquals(false, pq.isEmpty());
		
		Assert.assertEquals(100, pq.top());
		
		pq.push(101);
		
		Assert.assertEquals(101, pq.pop());
		
		Assert.assertEquals(100, pq.top());
		
		pq.clear();
		
		Assert.assertEquals(0, pq.size());
		
		Assert.assertEquals(true, pq.isEmpty());
		
		for(int i=0;i<1000;++i){
			pq.push(i);
		}
		
		Assert.assertEquals(1000, pq.size());
		
		Assert.assertEquals(false, pq.isEmpty());
		
		Assert.assertEquals(999, pq.top());
		
	}
	
	@Test
	public void test(){
		one(new BinaryHeap(new int[]{1,54,32,13,22,76,43,23,2,100,23,100}));
	}
	
	
}
