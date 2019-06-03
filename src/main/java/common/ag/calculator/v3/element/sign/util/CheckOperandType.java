package common.ag.calculator.v3.element.sign.util;

import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.operand.OperandType;

public final class CheckOperandType {
	
	public static boolean checkOperandType(OperandType requiredType, Operand operand) {
		return requiredType == operand.getOperandType();
	}
	
}
