package common.ag.calculator.v3;

public final class Calculator {
	
	public static String calculate(String experssion) {
		return Calculate.calculate(Parser.parse(experssion));
	}
	
}
