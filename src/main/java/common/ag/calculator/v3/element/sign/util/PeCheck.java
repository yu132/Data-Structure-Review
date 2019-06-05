package common.ag.calculator.v3.element.sign.util;

import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.operand.OperandType;
import common.ag.calculator.v3.element.sign.exception.OperandNumberMismatchException;
import common.ag.calculator.v3.element.sign.exception.OperandTypeMismatchException;

public final class PeCheck {
	
	/**
	 * 进行预检查，返回最高的运算类型
	 * 
	 * @param signName				符号名称
	 * @param operands				操作数
	 * @param operandNumber			应该的操作数数量
	 * @param requiredOperandType	允许的操作数类型，前面的等级高
	 * @return						等级最高的操作数类型
	 */
	public static OperandType preCheck(String signName, Operand[] operands, int operandNumber,
			OperandType... requiredOperandType) {
		
		assert requiredOperandType.length != 0;
		
		//检查操作数数量是否符号要求
		if (!CheckOperandNumber.checkOperandNumber(operandNumber, operands.length))
			throw new OperandNumberMismatchException(signName, operandNumber, operands.length);
		
		OperandType greatestOperandType = null;
		int minIndex = requiredOperandType.length;
		
		//	检查当前运算数的类型是否符合要求，并且检测最高的运算数等级
		for (int i = 1; i <= operands.length; ++i) {
			
			boolean flag = false;
			Operand now = operands[i - 1];
			
			for (int j = 0; j < requiredOperandType.length; ++j) {
				OperandType type = requiredOperandType[j];
				
				if (now.getOperandType() == type) {
					flag = true;
					
					if (j < minIndex) {
						minIndex = j;
						greatestOperandType = type;
					}
					
					break;
				}
			}
			
			if (!flag)
				throw new OperandTypeMismatchException(signName, i, now.getOperandType(),
						requiredOperandType);
		}
		
		return greatestOperandType;
	}
	
}
