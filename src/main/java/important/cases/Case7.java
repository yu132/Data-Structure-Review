package important.cases;

import java.math.BigDecimal;

import common.ag.math.DoubleMath;

public class Case7 {
	
	/**
	 * double 很危险，要慎用
	 * 
	 * @see https://blog.csdn.net/z69183787/article/details/53286492
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(1.0 / 0.0);
		
		System.out.println(Double.doubleToLongBits(Double.NaN));
		
		System.out.println(Double.doubleToLongBits(Double.POSITIVE_INFINITY));
		System.out.println(Double.doubleToLongBits(Double.NEGATIVE_INFINITY));
		
		System.out.println(Double.doubleToLongBits(0.0));
		System.out.println(Double.doubleToLongBits(-0.0));
		
		System.out.println(DoubleMath.equals(1.0f, 1.0d, 0.00001));
		
		double a = 1.2;
		double b = 1.3;
		
		System.out.println(a + b == 2.5);
		System.out.println(b - a == 0.01);
		
		System.out.println(1.3 - 1.2 == 0.01);
		
		System.out.println(new BigDecimal("0.0").equals(new BigDecimal("-0.0")));
		
		System.out.println(1.0f == 1.0d);//现在java优化过了这种情况了？
	}
	
}
