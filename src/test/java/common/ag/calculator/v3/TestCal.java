package common.ag.calculator.v3;

import org.junit.Assert;
import org.junit.Test;

public class TestCal {
	
	@Test
	public void test1() {
		Assert.assertEquals("2", Calculator.calculate("1+1"));
	}
	
	@Test
	public void test2() {
		Assert.assertEquals("0", Calculator.calculate("1-1"));
	}
	
	@Test
	public void test3() {
		Assert.assertEquals("4", Calculator.calculate("2*2"));
	}
	
	@Test
	public void test4() {
		Assert.assertEquals("1.5", Calculator.calculate("3/2"));
	}
	
	@Test
	public void test5() {
		Assert.assertEquals("1", Calculator.calculate("3//2"));
	}
	
	@Test
	public void test6() {
		Assert.assertEquals("1", Calculator.calculate("3//2"));
	}
	
	@Test
	public void test7() {
		Assert.assertEquals("1", Calculator.calculate("3%2"));
	}
	
	@Test
	public void test8() {
		Assert.assertEquals("16", Calculator.calculate("2**4"));
	}
	
	@Test
	public void test9() {
		Assert.assertEquals("4", Calculator.calculate("2LOG16"));
	}
	
	@Test
	public void test10() {
		Assert.assertEquals("-2", Calculator.calculate("-(1+1)"));
	}
	
	@Test
	public void test11() {
		Assert.assertEquals("126", Calculator.calculate("100+20*4//(1+2)"));
	}
	
	@Test
	public void test12() {
		Assert.assertEquals("true", Calculator.calculate("true||false"));
	}
	
	@Test
	public void test13() {
		Assert.assertEquals("false", Calculator.calculate("true&&false"));
	}
	
	@Test
	public void test14() {
		Assert.assertEquals("true", Calculator.calculate("true&&true"));
	}
	
	@Test
	public void test15() {
		Assert.assertEquals("false", Calculator.calculate("false&&false"));
	}
	
	@Test
	public void test16() {
		Assert.assertEquals("true", Calculator.calculate("true||true"));
	}
	
	@Test
	public void test17() {
		Assert.assertEquals("false", Calculator.calculate("false||false"));
	}
	
	@Test
	public void test18() {
		Assert.assertEquals("2", Calculator.calculate("1+true"));
	}
	
	@Test
	public void test19() {
		Assert.assertEquals("2.5", Calculator.calculate("1.5+true"));
	}
	
	@Test
	public void test20() {
		Assert.assertEquals("1", Calculator.calculate("true?1:2"));
	}
	
	@Test
	public void test21() {
		Assert.assertEquals("2", Calculator.calculate("false?1:2"));
	}
	
	@Test
	public void test22() {
		Assert.assertEquals(Integer.toString((int) (10 + (100 * (234 + 12 + (213 * 123)
				+ (213 + 1 + (Math.pow(123, 2)) + (213 + (323 / 2 + (434 * 1234)))))))),
				Calculator.calculate(
						"10+(100*(234+12+(213*123)+(213+1+(123**2)+(213+(323//2+(434*1234))))))"));
	}
	
	@Test
	public void test23() {
		Assert.assertEquals("3.3333333333333333333333333333333333333333",
				Calculator.calculate("10/3"));
	}
	
}
