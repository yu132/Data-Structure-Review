package ds.pq;

import java.util.PriorityQueue;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import ds.priorityQueue.BinaryHeap;

public class PQ {

	@Test
	public void test() {

		BinaryHeap bh = new BinaryHeap();
		PriorityQueue<Integer> pq = new PriorityQueue<>(
				(x, y) -> (x > y) ? -1 : ((x == y) ? 0 : 1));

		Random r = new Random(43245353);

		for (int i = 0; i < 1000000; ++i) {
			if (pq.size() == 0 || r.nextDouble() < 0.7) {
				int num = r.nextInt() * (r.nextBoolean() ? -1 : 1);
				pq.add(num);
				bh.push(num);
				//Assert.assertTrue(bh.check());
			} else {
				int b = bh.pop();
				//Assert.assertTrue(bh.check());
				Assert.assertEquals((int) pq.poll(), b);
			}
		}

		while (pq.size() != 0) {
			Assert.assertEquals((int) pq.poll(), bh.pop());
		}
	}

}
