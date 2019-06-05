package common.ag.calculator.v3;

/**
 * 简易计算器，就是调用语法解析器解析出元素后
 * 
 * 再调用计算器，对解析后的元素进行计算
 * 
 * @author 87663
 *
 */
public final class Calculator {
	
	public static String calculate(String experssion) {
		return Calculate.calculate(Parser.parse(experssion));
	}
	
}
