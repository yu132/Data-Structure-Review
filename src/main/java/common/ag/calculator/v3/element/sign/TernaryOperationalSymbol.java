package common.ag.calculator.v3.element.sign;

import static common.ag.calculator.v3.element.operand.BaseOperandType.BOOLEAN_OPERAND;
import static common.ag.calculator.v3.element.operand.BaseOperandType.DOUBLE_OPERAND;
import static common.ag.calculator.v3.element.operand.BaseOperandType.INTEGER_OPERAND;
import static common.ag.calculator.v3.element.operand.util.GetValue.booleanValue;
import static common.ag.calculator.v3.element.sign.util.PeCheck.preCheck;

import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.sign.priority.BaseOperationalSymbolPriority;
import common.ag.calculator.v3.element.sign.priority.OperationalSymbolPriority;

/**
 * 三目运算符
 * 
 * @author 87663
 *
 */
public abstract class TernaryOperationalSymbol extends OperationalSymbol {
	
	public final static int OPERAND_NUMBER = 3;//操作数有3个
	
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}
	
	/**
	 * 状态选择运算符（?:），根据第一个操作数的真假，返回值，若为真，返回第二个操作数，若为假，返回第三个操作数
	 */
	public final static TernaryOperationalSymbol CONDITIONAL_SIGN = new TernaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.TERNARY_OPERATION_PRIORITY;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("conditional sign", operands, 3, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			if (booleanValue(operands[0]))
				return operands[1];
			else
				return operands[2];
		}
		
		@Override
		public String toString() {
			return "?:";
		}
	};
	
}
