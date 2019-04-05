package ag.sort;

import org.junit.Test;

import ag.sort.QuickSort.RandomQuickSort;

public class QsTest {

	@Test
	public void test() {
		new TestSort().test(new QuickSort(), 10000);
	}

	@Test
	public void test1() {
		new TestSort().test(new RandomQuickSort(), 10000);
	}

}
