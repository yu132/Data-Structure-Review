package ag.sort;

import org.junit.Test;

public class BsTest {
	@Test
	public void test() {
		new TestSort().test(new BubbleSort(), 10000);
	}

}
