package ds.set;

import java.util.HashSet;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import ds.set.sortedSet.IntAVLTree;
import ds.set.sortedSet.IntSkipList;

public class SkipListAndAVL {

	@Test
	public void test() {
		IntSkipList sl = new IntSkipList();
		IntAVLTree avlTree = new IntAVLTree();
		HashSet<Integer> set = new HashSet<>();

		Random r = new Random();

		for (int i = 0; i < 1000; ++i) {
			int intVal = r.nextInt(10000);

			boolean s = set.add(intVal);

			Assert.assertEquals(s, sl.add(intVal));
			Assert.assertEquals(s, avlTree.add(intVal));
			Assert.assertEquals(sl.size(), avlTree.size());

			Assert.assertEquals(true, sl.contain(intVal));
			Assert.assertEquals(true, avlTree.contain(intVal));
		}

		Assert.assertEquals(set.size(), sl.size());
		Assert.assertEquals(set.size(), avlTree.size());

		for (int i = 0; i < 100000; ++i) {
			int intVal = r.nextInt(10000);

			System.out.println(sl.size());

			boolean c = set.remove(intVal);

			// Assert.assertEquals(c, sl.contain(intVal));
			// Assert.assertEquals(c, avlTree.contain(intVal));

			Assert.assertEquals(c, sl.remove(intVal));
			// Assert.assertEquals(c, avlTree.remove(intVal));
			// Assert.assertEquals(sl.size(), avlTree.size());
		}

		// for (int i = 0; i < 100000; ++i) {
		// int intVal = i;// r.nextInt(10000);
		//
		// System.out.println(sl.size());
		//
		// boolean c = set.remove(intVal);
		//
		// // Assert.assertEquals(c, sl.contain(intVal));
		// // Assert.assertEquals(c, avlTree.contain(intVal));
		//
		// Assert.assertEquals(c, sl.remove(intVal));
		// // Assert.assertEquals(c, avlTree.remove(intVal));
		// // Assert.assertEquals(sl.size(), avlTree.size());
		// }

	}

}
