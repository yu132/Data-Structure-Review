package ds.priorityQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import ds.IntComparator;
import utils.GetNext2Pow;
import utils.MyIntArrays;

/**
 * 这个实现里面heap[0]是没有使用的，浪费了一个空间
 * 其实可以使用0作为根 child1=2*parent+1 child2=2*(parent+1)即可
 * 
 * 这里1为根 child1=2*parent child2=2*parent+1
 * 
 * 最大堆和最小堆的区别就在于comparator的不同
 * 
 * @author 87663
 *
 */
public class BinaryHeap implements IntPriorityQueue {

	public final static IntComparator	MAX_COMPARATOR		= IntComparator.GREATER;

	public final static IntComparator	MIN_COMPARATOR		= IntComparator.SMALLER;

	private final static IntComparator	DEFAULT_COMPARATOR	= MAX_COMPARATOR;

	private final static int			DEFAULT_CAPACITY	= 16;

	private final static int			MAX_CAPACITY		= Integer.MAX_VALUE - 8;

	private IntComparator				comparator;

	private int[]						heap;

	private int							size				= 0;

	public BinaryHeap() {
		super();
		this.comparator = DEFAULT_COMPARATOR;
		this.heap = new int[DEFAULT_CAPACITY];
	}

	public BinaryHeap(int capacity) {
		this(null, capacity, DEFAULT_COMPARATOR);
	}

	public BinaryHeap(int[] initArray) {
		this(initArray, initArray.length, DEFAULT_COMPARATOR);
	}

	public BinaryHeap(IntComparator comparator) {
		this(null, DEFAULT_CAPACITY, comparator);
	}

	public BinaryHeap(int capacity, IntComparator comparator) {
		this(null, capacity, comparator);
	}

	public BinaryHeap(int[] initArray, int capacity) {
		this(initArray, capacity, DEFAULT_COMPARATOR);
	}

	public BinaryHeap(int[] initArray, IntComparator comparator) {
		this(initArray, initArray.length, comparator);
	}

	private BinaryHeap(int[] initArray, int capacity, IntComparator comparator) {
		super();

		this.comparator = comparator;

		if (initArray == null) {

			if (capacity <= 0)
				throw new IllegalArgumentException("Capacity should be positive");

			if (capacity < DEFAULT_CAPACITY)
				capacity = DEFAULT_CAPACITY;

			this.heap = new int[capacity];

		} else {

			this.heap = initArray;

			size = initArray.length;

			heapify();
		}
	}

	private void grow(int capacity) {
		if (capacity < 0) {
			if (heap.length == MAX_CAPACITY)
				throw new IllegalStateException("Heap is too big");
			else
				capacity = MAX_CAPACITY;
		}

		heap = Arrays.copyOf(heap, capacity);
	}

	private void grow() {
		grow(heap.length << 1);
	}

	private void fastGrow(int capacity) {
		if (capacity <= 0 || capacity > MAX_CAPACITY)
			throw new IllegalStateException("Heap is too big");
		grow(GetNext2Pow.get(capacity));
	}

	/**
	 * 自上向下检查一颗子树是否符合堆的要求
	 * @param father
	 */
	private void checkDown(int father) {

		int child = (father << 1) + 1;// father*2

		while (child < size) {

			//选择子节点中大的那个节点
			if (child + 1 < size && comparator.compare(heap[child + 1], heap[child]) > 0)
				++child;

			//子节点小于等于父节点,证明这颗树符合堆的要求
			if (comparator.compare(heap[child], heap[father]) <= 0)
				break;

			//否则将两者置换，并且检查那颗子树是否符合，
			//另一颗子树由于没有改变，所以无需处理
			MyIntArrays.swap(heap, child, father);

			father = child;
			child = (father << 1) + 1;
		}
	}

	/**
	 * 自下向上检查一颗子树是否符合要求
	 * @param child
	 */
	private void checkUp(int child) {

		int father = (child - 1) >> 1;// child/2

		while (father >= 0) {

			//如果这个堆中的父亲节点比子节点大，那么符合要求
			if (comparator.compare(heap[father], heap[child]) >= 0)
				break;

			//否则将两者置换，然后我们需要继续检查这个父节点
			//和其祖先节点是否符合规范
			MyIntArrays.swap(heap, child, father);

			child = father;
			father = (child - 1) >> 1;
		}
	}

	/**
	 * 将数组堆化
	 */
	private void heapify() {
		//对于每个非叶子节点，都从上向下检查以其为根节点的子树是否合法
		for (int root = size; root >= 0; --root)
			checkDown(root);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int top() {
		if (size == 0)
			throw new NoSuchElementException("Heap is empty");
		return heap[0];
	}

	@Override
	public int pop() {
		if (size == 0)
			throw new NoSuchElementException("Heap is empty");

		int element = heap[0];

		//将最后一个节点拿到第一个节点上
		heap[0] = heap[--size];

		//然后自上向下检查是否合法
		checkDown(0);

		return element;
	}

	@Override
	public void push(int element) {
		if (size >= heap.length)
			grow();

		//把新的节点放到堆的最后一个位置
		heap[size] = element;

		//自下向上检查是否合法
		checkUp(size++);
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public void pushAll(int[] array) {

		if (array.length < size / 100)
			for (int i = 0; i < array.length; ++i)
				this.push(array[i]);
		else {
			if (size + array.length >= heap.length - 1)
				fastGrow(size + array.length);

			System.arraycopy(array, 0, heap, size + 1, array.length);

			size += array.length;

			heapify();
		}
	}

	public boolean check() {
		return check(0);
	}

	private boolean check(int node) {

		if (node >= size)
			return true;

		int child1 = node * 2 + 1;
		int child2 = node * 2 + 2;

		if (child1 < size && heap[node] < heap[child1]) {
			System.out.println("e1");
			System.out.println("node:" + node);
			return false;
		}

		if (child2 < size && heap[node] < heap[child2]) {
			System.out.println("e2");
			System.out.println("node:" + node);
			return false;
		}

		if (!check(child1))
			return false;

		if (!check(child2))
			return false;

		return true;
	}

}
