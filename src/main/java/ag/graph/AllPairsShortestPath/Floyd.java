package ag.graph.AllPairsShortestPath;

/**
 * 弗洛伊德算法是一种求多元最短路径的算法，
 * 其也可以求任意两点之间最短路径，不过就有点浪费了
 * 
 * 其复杂度比较高，是O(n^3)，对于比较大的图就不适用了
 * 
 * 代码表示非常简单，但是其中的思想却还是有些难理解的
 * 
 * 其思想就是一种动态规划的思想，两个点之间如果有最短路径
 * 那么有可能是直接路径，有可能是通过点中转的路径
 * 通过三层嵌套的for循环，能够将所有情况求出来
 * 利用dis[i][j]=min{dis[i][j],dis[i][k]+dis[k][j]}
 * 即可求出所有的最短路径
 * 
 * 还有很多限制性条件
 * @see https://en.m.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm
 * 
 * @author 87663
 *
 */
public final class Floyd {

	/**
	 * @param distance
	 * 
	 * 			这个参数表示初始的邻接矩阵，这个函数将就地修改
	 * 			有一个要求，首先distance中表示INF不推荐使用Integer.MAX_VALUE
	 * 			而是用一个比较大的数，因为加上这个数有可能会越界，然后就变成负值
	 * 			然后就会被当成最短路径，这明显是不合理的
	 * 			故应该使用一个较大值，比当前图中单源最短路径中选取一个取不到的值即可
	 * 
	 * 			不能有负环（即形成的一个回路中，整个回路所加的值不能是负数），因为
	 * 			如果存在这样的一个环，就会使路径无穷小，因为可以无限次走这条路
	 * 			因而是不对的
	 * 
	 * @param maxLength 	最大长度，超过这个长度的长度被认为是无穷大
	 */
	public static void floyd(int[][] distance, final int MAX_LENGTH) {

		int n = distance.length;

		for (int k = 0; k < n; ++k)
			for (int i = 0; i < n; ++i)
				for (int j = 0; j < n; ++j) {
					int len = distance[i][k] + distance[k][j];		//松弛操作
					if (len < MAX_LENGTH && distance[i][j] > len)	//即选择一条更短的路径，代替之前长的路径
						distance[i][j] = len;
				}
	}

}
