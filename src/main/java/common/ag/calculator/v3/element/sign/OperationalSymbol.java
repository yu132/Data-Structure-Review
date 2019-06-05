package common.ag.calculator.v3.element.sign;

import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.sign.priority.OperationalSymbolPriority;

/**
 * 表示符号中的运算符号
 * 
 * @author 87663
 */
public abstract class OperationalSymbol implements Symbol {
	
	/**
	 * @return	符号在运算符号中的优先级
	 */
	public abstract OperationalSymbolPriority getOperationalSymbolPriority();
	
	/**
	 * @return	符号的操作数个数
	 */
	public abstract int getOperandNumber();
	
	/**
	 * @param operands	传入的操作数
	 * @return	运算结果
	 */
	public abstract Operand calculate(Operand[] operands);
	
}
