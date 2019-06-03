package important.cases;

public class Case3 {
	
	/**
	 * 转型中的进位精度
	 * 
	 * 精度到了一定标准就会一样
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println((int) 0.9999999999999999d);
		System.out.println((int) 0.99999999999999999d);
		
		System.out.println(0.9999999999999999d == 0.99999999999999999d);
		System.out.println(0.99999999999999999d == 0.999999999999999999d);
		
		System.out.println(0.19999999999999999d == 0.199999999999999999d);
		System.out.println(0.199999999999999999d == 0.1999999999999999999d);
		
		System.out.println(0.19999999999999999d < 0.199999999999999999d);
	}
	
}
