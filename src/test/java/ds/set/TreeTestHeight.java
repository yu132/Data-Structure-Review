package ds.set;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import ds.IntIterable;
import ds.IntIterator;
import ds.set.sortedSet.IntRedBlackTree;

public class TreeTestHeight {

	@Test
	public void test() {

		for (int j = 0; j < 10; ++j) {
			IntRedBlackTree sl = new IntRedBlackTree();

			Random r = new Random();

			for (int i = 0; i < 1000; ++i) {
				int intVal = r.nextInt(10000);

				sl.add(intVal);

				Assert.assertEquals(true, sl.contain(intVal));
			}

			int count = 0;

			for (IntIterator it = ((IntIterable) sl).iterator(); it.hasNext(); it.next())
				++count;

			Assert.assertEquals(count, sl.size());

			Assert.assertTrue(sl.height() < 2 * Math.log(sl.size()) / Math.log(2));
		}
	}

}
