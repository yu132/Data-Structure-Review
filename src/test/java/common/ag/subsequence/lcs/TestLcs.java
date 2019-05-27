package common.ag.subsequence.lcs;

import org.junit.Assert;
import org.junit.Test;

import common.ag.subsequenceAndSubstring.longestCommonSubsequence.LongestCommonSubsequence;

public class TestLcs {
	
	@Test
	public void test() {
		int[] a = {
				1, 123123, 45732457, 2, 3, 24562436, 4, 5, 53685678, 6, 7, 8, 23412412, 223423, 9,
				10, 345324523, 11, 12, 23453453, 13, 14, 12341234, 15
		};
		int[] b = {
				1, 3, 2341234, 5, 234234, 7, 123423421, 9, 2342341, 11, 1234234, 13, 65786578, 15
		};
		
		int ans = 8;
		
		Assert.assertEquals(ans, LongestCommonSubsequence.lcsLen(a, b));
	}
	
}
