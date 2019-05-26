package common.ag.countInversion;

import org.junit.Assert;
import org.junit.Test;

public class Test1 {

	@Test
	public void test() {
		int[] array = {
				5, 3, 4, 2, 1
		};
		int ans = 9;

		Assert.assertEquals(ans, CountInversionUsingBinaryIndexingArray.countInversion(array));
	}

}
