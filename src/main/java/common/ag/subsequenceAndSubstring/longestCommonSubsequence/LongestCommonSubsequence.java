package common.ag.subsequenceAndSubstring.longestCommonSubsequence;

/**
 * 最长公共子序列
 * 
 * @see Introducing
 * 
 * @author 87663
 *
 */
public final class LongestCommonSubsequence {
	
	/**
	 * 空间优化，降成O(n)
	 */
	public static int lcsLen(int[] a, int[] b) {
		int[][] dp = new int[2][b.length + 1];
		
		int cur = 0;
		
		for (int i = 1; i <= a.length; ++i) {//i,j从1开始隐含了i,j某一个长度等于0时的情况
			cur ^= 1;
			for (int j = 1; j <= b.length; ++j) {
				if (a[i - 1] == b[j - 1])//如两个位置相等，则长度比两者小1
					dp[cur][j] = dp[cur ^ 1][j - 1] + 1;
				else//两个位置不想等，则是两个分别长度减一中，长度的最大的那种
					dp[cur][j] = Math.max(dp[cur ^ 1][j], dp[cur][j - 1]);
			}
		}
		
		return dp[cur][b.length];
	}
	
	public static int lcsLen2(int[] a, int[] b) {
		return lcsDpHelper(a, b)[a.length][b.length];
	}
	
	private static int[][] lcsDpHelper(int[] a, int[] b) {
		int[][] dp = new int[a.length + 1][b.length + 1];
		
		for (int i = 1; i <= a.length; ++i) {//i,j从1开始隐含了i,j某一个长度等于0时的情况
			for (int j = 1; j <= b.length; ++j) {
				if (a[i - 1] == b[j - 1])//如两个位置相等，则长度比两者小1
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else//两个位置不想等，则是两个分别长度减一中，长度的最大的那种
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		
		return dp;
	}
	
	public static int[] lcs(int[] a, int[] b) {
		int[][] dp = lcsDpHelper(a, b);
		
		int[] stack = new int[Math.min(a.length, b.length)];
		int len = 0;
		
		int i = a.length, j = b.length;
		
		while (i != 0 && j != 0) {
			if (a[i - 1] == b[j - 1]) {
				stack[len++] = a[--i];
				--j;
			} else if (dp[i - 1][j] < dp[i][j - 1])
				--j;
			else if (dp[i][j - 1] <= dp[i - 1][j])//dp[i][j - 1] == dp[i - 1][j] 的时候，就有两个合理的
				--i;								//需要任选其一，这里选的是从i方向下降
		}
		
		int[] ans = new int[len];
		
		for (i = len - 1; i >= 0; --i)
			ans[len - i - 1] = stack[i];
		
		return ans;
	}
	
}
