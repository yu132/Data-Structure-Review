package ag.common.lis;

import org.junit.Assert;
import org.junit.Test;

import common.ag.subsequenceAndSubstring.longestIncreasingSubsequence.LongestIncreasingSubsequence;

public class LISTest {

	@Test
	public void test() {
		Assert.assertEquals(7, LongestIncreasingSubsequence.lIS(new int[] {
				1, 5, 2, 6, 3, 7, 1, 2, 3, 4, 2, 6, 24, 47, 23, 1, 3, 2
		}));
	}

}
