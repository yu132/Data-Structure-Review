package ds.set;

import java.util.Random;

import ds.IntIterator;
import ds.set.sortedSet.IntAVLTree;

public class AVLTreeTest {

	public static void main(String[] args) {
		Random r = new Random();

		IntAVLTree sk = new IntAVLTree();

		int count = 0;

		for (int i = 0; i < 1000000; ++i) {
			if (sk.add(r.nextInt(1000))) {
				++count;
			}
			/*
			 * if(!sk.check()){ System.out.println(i); System.exit(0); }
			 */
		}

		System.out.println(sk.contain(312));// TODO 有问题 有但是没发现

		System.out.println(sk.remove(312));

		// for (int i = 0; i < 1000; ++i) {
		// sk.remove(i);
		// }

		int count2 = 0;

		for (IntIterator it = sk.iterator(); it.hasNext();) {
			System.out.println(it.next());
			++count2;
		}

		System.out.println(sk.size() + " " + count + " " + count2);

		// IntAVLTree sk=new IntAVLTree();
		//
		// sk.add(0);
		//
		// sk.add(1000);
		//
		// for(int i=10;i<100;++i)
		// sk.add(i);

	}

}
