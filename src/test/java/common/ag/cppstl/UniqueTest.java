package common.ag.cppstl;

import org.junit.Assert;
import org.junit.Test;

public class UniqueTest {

	@Test
	public void test() {
		int[] array = {
				1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6
		};
		int[] shouldbe = {
				1, 2, 3, 4, 5, 6
		};

		UniqueArray.unique(array);

		for (int i = 0; i < shouldbe.length; ++i)
			Assert.assertEquals(shouldbe[i], array[i]);
	}

}
