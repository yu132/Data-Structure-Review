package common.ag.subsequenceAndSubstring.longestPalindromeSubsequence;

public class Introducing {
	
	/**
	 * 最长回文子序列是一类动态规划问题
	 * 
	 * 和最长公共子序列很像，都是将一个大的问题降解成小问题
	 * 
	 * 本问题的状态转移方程为：
	 * 
	 * 			{	0							if	i>j(对称部分无需重新求)
	 * dp[i][j=	{	1							if	i==j
	 * 			{	dp[i+1][j-1]				if	i<j && a[i]==a[j]
	 * 			{	max{dp[i][j-1],dp[i+1][j]}	if	i<j && a[i]==a[j]	
	 */
	
}
