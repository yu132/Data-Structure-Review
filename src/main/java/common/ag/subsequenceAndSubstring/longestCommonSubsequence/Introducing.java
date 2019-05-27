package common.ag.subsequenceAndSubstring.longestCommonSubsequence;

public class Introducing {
	
	/**
	 * 最长公共子序列和是一种非常常见的问题
	 * 
	 * 其将一个大问题，通过两种分类，降解成3个小问题的求解
	 * 
	 * 再使用dp的方法，从而防止了子问题的重复求解
	 * 
	 * 动态转移方程为：
	 * 
	 * 			{	0							if	i==0 || j==0
	 * 	c[i,j]=	{	c[i-1,j-1]+1				if	i>0 && j>0 && a[i]=b[j]
	 * 			{	max{c[i-1,j], c[i,j-1]}		if  i>0 && j>0 && a[i]!=b[j]
	 * 
	 */
	
}
