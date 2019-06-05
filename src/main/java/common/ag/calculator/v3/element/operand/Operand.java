package common.ag.calculator.v3.element.operand;

import common.ag.calculator.v3.element.Element;

/**
 * 表示运算表达式中的操作数
 * 
 * @author 87663
 */
public interface Operand extends Element {
	
	/**
	 * @return 操作数的字符串表示
	 */
	String value();
	
	/**
	 * @return 操作数的类型
	 */
	OperandType getOperandType();
	
}
