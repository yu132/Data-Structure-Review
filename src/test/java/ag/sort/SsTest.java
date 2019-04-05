package ag.sort;

import org.junit.Test;

public class SsTest {

	@Test
	public void test() {
		new TestSort().test(new SelectionSort(), 10000);
	}

}
