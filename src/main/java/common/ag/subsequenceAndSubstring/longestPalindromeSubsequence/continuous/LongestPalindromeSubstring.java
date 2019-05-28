package common.ag.subsequenceAndSubstring.longestPalindromeSubsequence.continuous;

import java.util.Arrays;

/**
 * 用马拉车算法实现的最长回文字串计算
 * 
 * @see Introducing
 * 
 * @see https://www.cnblogs.com/grandyang/p/4475985.html
 * @see https://cloud.tencent.com/developer/news/312855
 * @see https://blog.csdn.net/qq_32354501/article/details/80084325
 * 
 * @author 87663
 *
 */
public final class LongestPalindromeSubstring {
	
	/**
	 * @param array
	 * @return			[0]为回文中心，[1]为回文长度（对于增加了分隔符和首尾的数组）
	 */
	private static int[] manacherHelper(int[] array, final int HEAD, final int TAIL,
			final int SEPARATOR) {
		int[] nodes = new int[2 * array.length + 3];
		
		nodes[0] = HEAD;
		nodes[nodes.length - 1] = TAIL;
		nodes[1] = SEPARATOR;
		
		for (int i = 0; i < array.length; ++i) {
			nodes[2 + i * 2] = array[i];
			nodes[3 + i * 2] = SEPARATOR;
		}
		
		int mx = 0, id = 0, resLen = 0, resCenter = 0;
		
		int[] p = new int[nodes.length];
		
		for (int i = 1; i < nodes.length - 1; ++i) {//首尾是特殊符号，无需计算
			p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
			
			while (nodes[i + p[i]] == nodes[i - p[i]])
				++p[i];
			
			if (mx < i + p[i]) {
				mx = i + p[i];
				id = i;
			}
			
			if (resLen < p[i]) {
				resLen = p[i];
				resCenter = i;
			}
		}
		
		return new int[] {
				resCenter, resLen
		};
	}
	
	/**
	 * @param array		求回文的数组
	 * @return			回文长度
	 */
	public static int manacherLen(int[] array, int head, int tail, int separator) {
		return manacherHelper(array, head, tail, separator)[1] - 1;
	}
	
	/**
	 * @param array		求回文的数组
	 * @return			回文内容
	 */
	public static int[] manacher(int[] array, int head, int tail, int separator) {
		int[] ans = manacherHelper(array, head, tail, separator);
		int start = (ans[0] - ans[1]) / 2;
		int len = ans[1] - 1;
		return Arrays.copyOfRange(array, start, start + len);
	}
	
	/**
	 * @param array
	 * @return			[0]为回文中心，[1]为回文长度（对于增加了分隔符和首尾的数组）
	 */
	private static int[] manacherHelper(int[] array) {
		ManacherNode[] nodes = new ManacherNode[2 * array.length + 3];
		
		nodes[0] = ManacherNode.HEAD;
		nodes[nodes.length - 1] = ManacherNode.TAIL;
		nodes[1] = ManacherNode.SEPARATOR;
		
		for (int i = 0; i < array.length; ++i) {
			nodes[2 + i * 2] = ManacherNode.of(array[i]);
			nodes[3 + i * 2] = ManacherNode.SEPARATOR;
		}
		
		int mx = 0, id = 0, resLen = 0, resCenter = 0;
		
		int[] p = new int[nodes.length];
		
		for (int i = 1; i < nodes.length - 1; ++i) {//首尾是特殊符号，无需计算
			p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
			
			while (nodes[i + p[i]].equals(nodes[i - p[i]]))
				++p[i];
			
			if (mx < i + p[i]) {
				mx = i + p[i];
				id = i;
			}
			
			if (resLen < p[i]) {
				resLen = p[i];
				resCenter = i;
			}
		}
		
		return new int[] {
				resCenter, resLen
		};
	}
	
	/**
	 * @param array		求回文的数组
	 * @return			回文长度
	 */
	public static int manacherLen(int[] array) {
		return manacherHelper(array)[1] - 1;
	}
	
	/**
	 * @param array		求回文的数组
	 * @return			回文内容
	 */
	public static int[] manacher(int[] array) {
		int[] ans = manacherHelper(array);
		int start = (ans[0] - ans[1]) / 2;
		int len = ans[1] - 1;
		return Arrays.copyOfRange(array, start, start + len);
	}
	
	/**
	 * 为了覆盖int所有范围做的特殊处理
	 * 若是取不到所有范围，那么使用特殊值作为头尾和分隔符即可
	 * 对于char来说同理
	 * 
	 * @author 87663
	 */
	private static class ManacherNode {
		
		final static ManacherNode HEAD = new ManacherNode(0, true, false, false);
		final static ManacherNode TAIL = new ManacherNode(0, false, true, false);
		final static ManacherNode SEPARATOR = new ManacherNode(0, false, false, true);
		
		int value;
		
		boolean isHead;
		boolean isTail;
		boolean isSeparator;
		
		private ManacherNode(int value, boolean isHead, boolean isTail, boolean isSeparator) {
			super();
			this.value = value;
			this.isHead = isHead;
			this.isTail = isTail;
			this.isSeparator = isSeparator;
		}
		
		public static ManacherNode of(int value) {
			return new ManacherNode(value, false, false, false);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (isHead ? 1231 : 1237);
			result = prime * result + (isSeparator ? 1231 : 1237);
			result = prime * result + (isTail ? 1231 : 1237);
			result = prime * result + value;
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ManacherNode other = (ManacherNode) obj;
			if (isHead != other.isHead)
				return false;
			if (isSeparator != other.isSeparator)
				return false;
			if (isTail != other.isTail)
				return false;
			if (value != other.value)
				return false;
			return true;
		}
	}
	
}
