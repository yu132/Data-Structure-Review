package ds.set;

import java.util.HashSet;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import ds.IntIterable;
import ds.IntIterator;
import ds.set.sortedSet.IntRedBlackTree;

public class SetTest {

	@Test
	public void test() {

		for (int j = 0; j < 1; ++j) {
			IntRedBlackTree sl = new IntRedBlackTree();
			HashSet<Integer> set = new HashSet<>();

			Random r = new Random();

			for (int i = 0; i < 1000; ++i) {
				int intVal = r.nextInt(10000);

				boolean s = set.add(intVal);

				Assert.assertEquals(s, sl.add(intVal));

				//	Assert.assertTrue(sl.check());

				Assert.assertEquals(set.size(), sl.size());

				Assert.assertEquals(true, sl.contain(intVal));
			}

			int count = 0;

			for (IntIterator it = ((IntIterable) sl).iterator(); it.hasNext(); it.next())
				++count;

			for (int a : set)
				Assert.assertTrue(sl.contain(a));

			Assert.assertEquals(count, sl.size());

			for (int i = 0; i < 100000; ++i) {
				int intVal = i;
				//r.nextInt(10000);

				System.out.println(sl.size());

				boolean c = set.remove(intVal);

				boolean b = sl.remove(intVal);

				//	Assert.assertTrue(sl.check());

				Assert.assertEquals(c, b);

			}
		}
	}

}
