package common.ag.calculator;

import org.junit.Test;

import common.ag.calculator.v1.Element;
import common.ag.calculator.v1.ExpressionParser;

public class TestParser {
	
	@Test
	public void test() {
		String expression = "-1+(-2*2/3)-100+6*8^2";
		for (Element e : new ExpressionParser(expression).parseExpression()) {
			System.out.println(e);
		}
	}
	
}
