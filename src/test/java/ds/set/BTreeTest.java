package ds.set;

import java.util.HashSet;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import ds.IntIterable;
import ds.IntIterator;
import ds.set.sortedSet.IntBTree;

public class BTreeTest {

	@Test
	public void test() {

		for (int j = 0; j < 1; ++j) {
			IntBTree sl = new IntBTree(10);
			HashSet<Integer> set = new HashSet<>();

			Random r = new Random(4642345645643163l);

			for (int i = 0; i < 1000; ++i) {

				//	System.out.println(i);
				int intVal = r.nextInt(10000);

				System.out.println("INSERT:" + intVal);

				boolean s = set.add(intVal);

				Assert.assertEquals(s, sl.add(intVal));

				Assert.assertEquals(set.size(), sl.size());

				Assert.assertEquals(true, sl.contain(intVal));

				Assert.assertTrue(sl.check());
			}

			int count = 0;

			for (int a : set)
				Assert.assertTrue(sl.contain(a));

			for (IntIterator it = ((IntIterable) sl).iterator(); it.hasNext();) {
				Assert.assertTrue(set.contains(it.next()));
				++count;
			}

			Assert.assertEquals(set.size(), sl.size());
			Assert.assertEquals(count, sl.size());

			for (int i = 0; i < 100000; ++i) {
				int intVal = r.nextInt(10000);

				System.out.println(sl.size());

				boolean c = set.remove(intVal);

				Assert.assertTrue(sl.check());

				boolean b = sl.remove(intVal);

				Assert.assertFalse(sl.contain(intVal));

				Assert.assertEquals(c, b);

				for (int a : set)
					Assert.assertTrue(sl.contain(a));
			}
		}
	}

}
