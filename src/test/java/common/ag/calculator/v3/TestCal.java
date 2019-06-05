package common.ag.calculator.v3;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import common.ag.calculator.v3.parser.exception.ParseException;
import common.ag.calculator.v3.setting.Setting;

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
		int before = Setting.doubleScale;
		Setting.doubleScale = 40;
		
		Assert.assertEquals("3.3333333333333333333333333333333333333333",
				Calculator.calculate("10/3"));
		
		Setting.doubleScale = before;
	}
	
	@Test
	public void test24() {
		Assert.assertEquals(Integer.toString(123 * 123),
				Calculator.calculate("1+100*2-200?456+123?123**2:234//2:123*123-4"));
	}
	
	@Test
	public void test25() {
		Assert.assertEquals(Integer.toString(123 * 124 - 4),
				Calculator.calculate("1+100*2-201?456+123?123**2:234//2:123*124-4"));
	}
	
	@Test
	public void test26() {
		Assert.assertEquals(Integer.toString(234 / 2),
				Calculator.calculate("1+100*2-200?1-1?123**2:234//2:123*123-4"));
	}
	
	@Test
	public void test27() {
		Assert.assertEquals(Integer.toString(1234 ^ 4321),
				Calculator.calculate("1234^4321"));
	}
	
	@Test
	public void test28() {
		Assert.assertEquals(Integer.toString(1234 | 4321),
				Calculator.calculate("1234|4321"));
	}
	
	@Test
	public void test29() {
		Assert.assertEquals(Integer.toString(1234 & 4321),
				Calculator.calculate("1234&4321"));
	}
	
	@Test
	public void test30() {
		Assert.assertEquals(BigInteger.TEN.shiftLeft(10).toString(),
				Calculator.calculate("10<<10"));
	}
	
	@Test
	public void test31() {
		Assert.assertEquals(new BigInteger("1111111111111").shiftRight(10).toString(),
				Calculator.calculate("1111111111111>>10"));
	}
	
	@Test
	public void test32() {
		Assert.assertEquals("false",
				Calculator.calculate("!true"));
	}
	
	@Test
	public void test33() {
		Assert.assertEquals("true",
				Calculator.calculate("!false"));
	}
	
	@Test
	public void test34() {
		Assert.assertEquals("true",
				Calculator.calculate("100>1"));
	}
	
	@Test
	public void test35() {
		Assert.assertEquals("true",
				Calculator.calculate("100>=50"));
	}
	
	@Test
	public void test36() {
		Assert.assertEquals("false",
				Calculator.calculate("100<50"));
	}
	
	@Test
	public void test37() {
		Assert.assertEquals("true",
				Calculator.calculate("100<=101"));
	}
	
	@Test
	public void test38() {
		Assert.assertEquals("true",
				Calculator.calculate("100<=100"));
	}
	
	@Test
	public void test39() {
		Assert.assertEquals("true",
				Calculator.calculate("200==200"));
	}
	
	@Test
	public void test40() {
		Assert.assertEquals("true",
				Calculator.calculate("200!=201"));
	}
	
	@Test
	public void test41() {
		Assert.assertEquals("1",
				Calculator.calculate("(int)1.5"));
	}
	
	@Test
	public void test42() {
		Assert.assertEquals("2",
				Calculator.calculate("(double)1+1"));
	}
	
	@Test
	public void test43() {
		Assert.assertEquals("true",
				Calculator.calculate("(boolean)101"));
	}
	
	@Test
	public void test44() {
		Assert.assertEquals("false",
				Calculator.calculate("(boolean)0"));
	}
	
	@Test
	public void test45() {
		try {
			Calculator.calculate("(1+1");//bug
			Assert.assertTrue(false);
		} catch (ParseException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test46() {
		try {
			Calculator.calculate("1+1)");
			Assert.assertTrue(false);
		} catch (ParseException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test47() {
		try {
			Calculator.calculate("()");
			Assert.assertTrue(false);
		} catch (ParseException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test48() {
		try {
			Calculator.calculate("1//");
			Assert.assertTrue(false);
		} catch (ParseException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test49() {
		try {
			Calculator.calculate("1?12");
			Assert.assertTrue(false);
		} catch (ParseException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test50() {
		try {
			Calculator.calculate("(1?12)+1:1");
			Assert.assertTrue(false);
		} catch (ParseException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test51() {
		try {
			Calculator.calculate("((1)?12)+1:1");
			Assert.assertTrue(false);
		} catch (ParseException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test52() {
		try {
			Calculator.calculate("1?(12+1:1)");
			Assert.assertTrue(false);
		} catch (ParseException e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void test53() {
		Assert.assertEquals("-1",
				Calculator.calculate("---------1"));
	}
	
	@Test
	public void test54() {
		Assert.assertEquals("1.5",
				Calculator.calculate("(1 + 1 *2 )/2"));
	}
	
	@Test
	public void test55() {
		Assert.assertEquals("-1",
				Calculator.calculate("-3//2"));
	}
}