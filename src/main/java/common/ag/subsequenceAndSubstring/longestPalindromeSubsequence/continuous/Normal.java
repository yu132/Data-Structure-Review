package common.ag.subsequenceAndSubstring.longestPalindromeSubsequence.continuous;

public class Normal {
	
	public static String longestPalindrome(String s) {
		if (s.equals("") || s == null)
			return s;
		
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int l1 = expand(s, i, i);
			int l2 = expand(s, i, i + 1);
			int len = Math.max(l1, l2);
			
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}
	
	private static int expand(String s, int l, int r) {
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return r - l - 1;
	}
	
}
