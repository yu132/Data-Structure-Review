package common.ag.discretization;

import java.util.Arrays;

import common.ag.cppstl.BinarySearchBound;
import common.ag.cppstl.UniqueArray;

/**
 * @see Introducing
 * 
 * @author 87663
 *
 */
public final class Discretization {

	private static class Node {
		int	val;
		int	id;

		public Node(int val, int id) {
			super();
			this.val = val;
			this.id = id;
		}
	}

	/**
	 * 离散化数组，离散化后相同的值按离散化出来的值不同
	 * 
	 * @param array
	 * @return
	 */
	public static int[] discretize(int[] array) {

		Node[] nodes = new Node[array.length];

		Arrays.setAll(nodes, (i) -> new Node(array[i], i));

		Arrays.sort(nodes, (a, b) -> Integer.compare(a.val, b.val));

		int[] discretizedArray = new int[array.length];

		for (int i = 0; i < discretizedArray.length; ++i)
			discretizedArray[nodes[i].id] = i;

		return discretizedArray;
	}

	public static void discretizeInplace(int[] array) {

		Node[] nodes = new Node[array.length];

		Arrays.setAll(nodes, (i) -> new Node(array[i], i));

		Arrays.sort(nodes, (a, b) -> Integer.compare(a.val, b.val));

		for (int i = 0; i < array.length; ++i)
			array[nodes[i].id] = i;
	}

	/**
	 * 离散化数组，对于相同的数值，离散化后是相同的结果
	 * 
	 * @param array
	 * @return
	 */
	public static int[] discretize2(int[] array) {

		int[] copy = Arrays.copyOf(array, array.length);

		Arrays.sort(copy);

		int uniqueLen = UniqueArray.unique(copy);

		int[] discretizedArray = new int[array.length];

		for (int i = 0; i < array.length; ++i)
			discretizedArray[i] = BinarySearchBound.lowerBound(array, 0, uniqueLen, array[i]);

		return discretizedArray;
	}

	public static void discretize2Inplace(int[] array) {

		int[] copy = Arrays.copyOf(array, array.length);

		Arrays.sort(copy);

		int uniqueLen = UniqueArray.unique(copy);

		for (int i = 0; i < array.length; ++i)
			array[i] = BinarySearchBound.lowerBound(array, 0, uniqueLen, array[i]);
	}


}
