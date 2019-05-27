package common.ag.subsequenceAndSubstring.longestPalindromeSubsequence;

/**
 * 最长回文子序列
 * 
 * 没有展示回文子序列内容是因为情况实在是太多了
 * 
 * 以这张图为例子
 * @see https://img-blog.csdn.net/20180619105538741?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N1Z2FyYmxpc3M=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70
 * 
 * 获取最长回文子序列的思路：
 * 从右上角开始，只要是相等的向左，向下都是2种长度相等的情况，可以向下向左任意选择，
 * 比左下角+2处的i和j相等，存a[i][j]，然后则向左下移动
 * 
 * @see Introducing
 * @see https://blog.csdn.net/sugarbliss/article/details/80730929
 * 
 * @author 87663
 *
 */
public final class LongestPalindromeSubsequence {
	
	public static int lpsLen(int[] a) {
		int[][] dp = new int[2][a.length];
		
		int cur = 0;
		
		for (int i = a.length - 1; i >= 0; --i) {
			cur ^= 1;
			dp[cur][i] = 1;
			for (int j = i + 1; j < a.length; ++j) {
				if (a[i] == a[j])
					dp[cur][j] = dp[cur ^ 1][j - 1] + 2;
				else
					dp[cur][j] = Math.max(dp[cur][j - 1], dp[cur ^ 1][j]);
			}
		}
		
		return dp[cur][a.length - 1];
	}
	
}
