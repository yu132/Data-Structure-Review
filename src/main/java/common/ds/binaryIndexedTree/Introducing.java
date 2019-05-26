package common.ds.binaryIndexedTree;

public class Introducing {

	/**
	 * 树状数组是数据结构中比较有技巧性的结构之一，
	 * 而且一般用在在ACM比赛中
	 * 其在书上是没有的，因此我单独列出
	 * 
	 * 这种数据结构一般用于求区间和(积)，区间最大值
	 * 
	 * 树状数组是一个长成树形式的数组，其查询区间值
	 * 最多只需要访问log(n)个位置的值
	 * 单点修改也最多需要修改log(n)个位置的值
	 * 
	 * 其高效性不是由于算法的特殊性，而是由于其结构上
	 * 的特殊性，其每个位置上的值不一定存储的是这个位置上的值
	 * 可能是一段区间和(积)
	 * 
	 * 原理详解见
	 * https://blog.csdn.net/bestsort/article/details/80796531#%E5%8C%BA%E9%97%B4%E4%BF%AE%E6%94%B9-%E5%8C%BA%E9%97%B4%E6%9F%A5%E8%AF%A2
	 * 
	 * 树状数组求逆序对
	 * https://blog.csdn.net/m0_38033475/article/details/80330157
	 * 
	 */

}
