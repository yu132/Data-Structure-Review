package ds.set.sortedSet;

import java.util.Random;

import ds.IntIterable;
import ds.IntIterator;
import ds.linearList.UnchekedIntArrayList;
import ds.set.IntSet;

public final class IntSkipList implements IntSet, IntIterable {

	private static class SkipNode {
		int			val;
		SkipNode[]	nextNodes;

		public SkipNode(int val, int level) {
			super();
			this.val = val;
			this.nextNodes = new SkipNode[level];
		}
	}

	private int					maxLevel;

	private int					size;

	private SkipNode			head;

	private final static Random	RANDOM					= new Random();

	private final static float	NEXT_LEVEL_PROBABILITY	= 0.25f;

	private final static int	DEFAULT_CAPACITY		= 16;

	private int					rebuildThreshold;

	public IntSkipList() {
		this(DEFAULT_CAPACITY);
	}

	public IntSkipList(int probablyCapacity) {
		this(probablyCapacity, false);
	}

	public IntSkipList(int probablyCapacity, boolean needRebuild) {
		super();

		if (probablyCapacity < DEFAULT_CAPACITY)
			probablyCapacity = DEFAULT_CAPACITY;

		probablyCapacity = initializeCapacity(probablyCapacity);

		maxLevel = (int) (Math.log(probablyCapacity + 1) / Math.log(2));
		this.head = new SkipNode(0, maxLevel);

		if (!needRebuild)
			rebuildThreshold = Integer.MAX_VALUE;
		else {
			rebuildThreshold = probablyCapacity << 1;

			if (rebuildThreshold < 0)
				rebuildThreshold = Integer.MAX_VALUE;
		}
	}

	private int initializeCapacity(int requiredCapacity) {

		int initialCapacity = DEFAULT_CAPACITY;

		if (requiredCapacity > initialCapacity) {
			initialCapacity = requiredCapacity - 1;

			initialCapacity |= (initialCapacity >>> 1);
			initialCapacity |= (initialCapacity >>> 2);
			initialCapacity |= (initialCapacity >>> 4);
			initialCapacity |= (initialCapacity >>> 8);
			initialCapacity |= (initialCapacity >>> 16);
			initialCapacity++;

			if (initialCapacity < 0)// 0x80000000 -> Integer.MIN_VALUE
				initialCapacity >>>= 1;// 0x40000000 -> 2^30
		}

		return initialCapacity;
	}

	private void rebuild() {
		UnchekedIntArrayList list = new UnchekedIntArrayList();

		SkipNode node = head.nextNodes[0];

		while (node != null) {
			list.add(node.val);
			node = node.nextNodes[0];
		}

		maxLevel = (int) (Math.log(rebuildThreshold + 1) / Math.log(2));

		head = new SkipNode(0, maxLevel);

		rebuildThreshold <<= 1;

		size = 0;

		if (rebuildThreshold < 0)
			rebuildThreshold = Integer.MAX_VALUE;

		for (int i = 0; i < list.size(); ++i)
			this.add(list.get(i));
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
	public boolean add(int element) {

		SkipNode node = head;

		SkipNode[] nodeBefore = new SkipNode[maxLevel];

		int level = maxLevel - 1;

		while (level >= 0) {

			SkipNode next = node.nextNodes[level];

			while (next != null && next.val < element) {
				node = next;
				next = node.nextNodes[level];
			}

			if (next != null && next.val == element)// 有相等的元素出现，插入失败，直接返回
				return false;
			// else(next.val>element)
			nodeBefore[level--] = node;
		}

		SkipNode newNode = new SkipNode(element, maxLevel);

		level = 0;

		// 每次都有NEXT_LEVEL_PROBABILITY的概率升一层，
		// 当层数为0时必定添加，到达最高层时结束
		while ((level == 0 || RANDOM.nextFloat() < NEXT_LEVEL_PROBABILITY) && level < maxLevel) {
			SkipNode beforeNode = nodeBefore[level];
			newNode.nextNodes[level] = beforeNode.nextNodes[level];
			beforeNode.nextNodes[level] = newNode;
			++level;
		}

		++size;

		if (size > rebuildThreshold)
			rebuild();

		return true;
	}

	@Override
	public boolean contain(int element) {
		SkipNode node = head;

		int level = maxLevel - 1;

		while (level >= 0) {

			SkipNode next = node.nextNodes[level];

			while (next != null && next.val < element) {
				node = next;
				next = node.nextNodes[level];
			}

			if (next != null && next.val == element)// 找到该元素，直接返回
				return true;

			--level;
		}

		return false;
	}

	@Override
	public boolean remove(int element) {// @bug
		SkipNode node = head;

		SkipNode[] nodeBefore = new SkipNode[maxLevel];

		int level = maxLevel - 1;

		while (level >= 0) {

			SkipNode next = node.nextNodes[level];

			while (next != null && next.val < element) {
				node = next;
				next = node.nextNodes[level];
			}

			nodeBefore[level--] = node;
		}

		// 如果这个节点并不存在，那么我们直接返回
		if (node.nextNodes[0] == null || node.nextNodes[0].val != element)
			return false;

		node = node.nextNodes[0];

		for (level = 0; level < maxLevel; ++level)
			if (nodeBefore[level].nextNodes[level] == node)
				nodeBefore[level].nextNodes[level] = node.nextNodes[level];
			else
				break;
		--size;

		return true;
	}

	@Override
	public void clear() {
		head = new SkipNode(0, maxLevel);
		size = 0;
	}

	@Override
	public IntIterator iterator() {
		return new SkipListIterator(head.nextNodes[0]);
	}

	private static class SkipListIterator implements IntIterator {

		private SkipNode node;

		public SkipListIterator(SkipNode node) {
			super();
			this.node = node;
		}

		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public int next() {
			int val = node.val;
			node = node.nextNodes[0];
			return val;
		}

	}

}
