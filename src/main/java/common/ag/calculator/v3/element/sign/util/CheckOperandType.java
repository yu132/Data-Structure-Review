package common.ag.calculator.v3.element.sign.util;

import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.operand.OperandType;

public final class CheckOperandType {
	
	/**
	 * 检查运算数类型是否符合要求
	 * @param requiredType		需要的运算数类型
	 * @param operand			提供的操作数类型
	 * @return					两者是否类型是否相等
	 */
	public static boolean checkOperandType(OperandType requiredType, Operand operand) {
		return requiredType == operand.getOperandType();
	}
	
}
