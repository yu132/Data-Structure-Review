package important.cases;

public class Case4 {
	
	/**
	 * 不是所有10位的数乘10都小于0的
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//		for (int i = 1000000000; i < Integer.MAX_VALUE; ++i)
		//			if (i * 10 >= 0)
		//				System.out.println(i + " " + i * 10);
		
		for (int i = Integer.MAX_VALUE / 10; i < 1000000000; ++i)
			if (i * 10 >= 0)
				System.out.println(i + " " + i * 10);
	}
	
}
