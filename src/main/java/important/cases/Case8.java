package important.cases;

import java.math.BigDecimal;

public class Case8 {
	
	public static void main(String[] args) {
		//		double num1 = 464513654.4646515;
		//		double num2 = 5441.4541212;
		//		
		//		double num3 = 4.645136544646515;
		//		double num4 = 5.4414541212;
		//		
		//		int len1 = 8;
		//		int len2 = 3;
		//		
		//		System.out.println(Math.log(num1) / Math.log(num2));
		//		
		//		System.out.println(
		//				(Math.log(num3) + len1 * Math.log(10)) / (Math.log(num4) + len2 * Math.log(10)));
		
		
		//		double num1 = 4645141644844564646464213189798516518494561.46465147445;
		//		double num2 = 5441.4541212;
		//		
		//		double num3 = 4.64514164484456464646421318979851651849456146465147445;
		//		double num4 = 5.4414541212;
		//		
		//		int len1 = 42;
		//		int len2 = 3;
		//		
		//		System.out.println(Math.log(num1) / Math.log(num2));
		//		
		//		System.out.println(
		//				(Math.log(num3) + len1 * Math.log(10)) / (Math.log(num4) + len2 * Math.log(10)));
		//		
		//		System.out.println();
		//		
		//		System.out.println(Math.log(10));
		//		System.out.println(Math.log(1.1));
		
		double num1 = 4645141644844564646464213189798516518494561.46465147445;
		double num2 = 2.4541212;
		
		double num3 = 4.64514164484456464646421318979851651849456146465147445;
		double num4 = 2;
		double num5 = 0.4541212;
		
		int len = 42;
		
		System.out.println(Math.pow(num1, num2));
		System.out.println();
		System.out.println(Math.pow(num1, num4) * Math.pow(num3, num5) * Math.pow(10, len * num5));
		
		System.out.println(new BigDecimal(".1"));
	}
	
}
