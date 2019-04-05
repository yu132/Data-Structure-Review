package ag.sort;

import org.junit.Test;

public class MsTest {

	@Test
	public void test() {
		new TestSort().test(new MergeSort(), 100000);
	}

}
