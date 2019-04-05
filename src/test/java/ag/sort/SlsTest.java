package ag.sort;

import org.junit.Test;

public class SlsTest {

	@Test
	public void test() {
		new TestSort().test(new SkipListSort(), 100000);
	}

}
