package common.ag.subsequenceAndSubstring.longestPalindromeSubsequence.continuous;

public class Introducing {
	
	/**
	 * 最长回文字串比最长回文序列看起来简单，
	 * 
	 * 实际上确实有O(n)的算法，那就是Manacher's Algorithm "马拉车"算法
	 * @see https://www.cnblogs.com/grandyang/p/4475985.html
	 * 
	 * 一个更好理解的算法是中心扩展算法，就是对于每种中心都进行扩展，
	 * 奇中心，即最后回文串的长度为奇数，中心只有一个字符
	 * 偶中心，即最后回文串的长度为偶数，中心有两个一样的字符
	 * 
	 * 这样的扩展方法很好理解，但是其中有很多浪费，有不少重复计算的部分
	 * 故其时间复杂度为O(n^2)
	 * 
	 * 而"马拉车"算法则将这种回文的计算简化到了极致，
	 * 
	 * 1.首先是将奇偶情况统一，其用的方法是在每个字符旁边加上分隔符号，
	 * 并且在首尾加上异于其余符号的符号，并且首尾的符号也要相异，
	 * 这样，奇偶的情况就统一了，因为对于奇中心，则还是从字符出发
	 * 对于偶中心，则就是从分隔符出发，并且对于任何情况，以下结论都成立：
	 * 最长子串的长度是半径减1，起始位置是中间位置减去半径再除以2
	 * 
	 * 2.快速计算，首先引入两个变量，id是能延长到最右端的回文串的中心
	 * mx是能延迟到最右端的回文串的最右端的位置
	 * 
	 * 则根据mx和i的大小关系有以下两种数组情况
	 * (1).j(i的对称点)	......  mx对称点  ......	 id  .......  mx  .......  i(当前位置)
	 * (2).mx对称点	......	j(i的对称点) ......	 id  .......  i(当前位置)  .......  mx
	 * 
	 * 根据经验，我们能得到以下的一种快速计算的方法，能利用之前计算的结果，快速扩展
	 * p[i] = mx > i ? min(p[2 * id - i], mx - i) : 1;
	 * 
	 * 注：2 * id - i == j; mx - i == j - mx对称点
	 * 
	 * 首先对于情况1，i的位置在我们已知的回文串外面，所以我们只能认为i本身是一个长为1的回文串
	 * 然后再进行扩展，这种情况没法利用前面计算的结果
	 * 
	 * 对于情况2，i的位置在我们已知的回文串内，由回文的性质，我们可知以id为中心的回文串是对称的
	 * 故p[i]==p[j]，但前提是p[j]得在id这个回文串的范围内，超出mx和mx对称点范围内的情况我们无法得知
	 * 故实际p[i] = min(p[j], j - mx对称点) = min(p[2 * id - i], mx - i)
	 * 对于超出mx范围的情况我们无法得知，也只能老老实实匹配
	 * 
	 * 其实仔细观察，其本质还是中心扩展算法，只不过后面部分的计算，利用了前面计算的结果，
	 * 简化了后面的计算，使得重复计算的情况不再存在，从而降低了时间复杂度
	 */
	
}
