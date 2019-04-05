package ag.sort;

import org.junit.Test;

public class Istest {

	@Test
	public void test() {
		new TestSort().test(new InsertionSort(), 100000);
	}

}
