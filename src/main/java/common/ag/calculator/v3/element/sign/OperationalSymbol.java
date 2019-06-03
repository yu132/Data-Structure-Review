package common.ag.calculator.v3.element.sign;

import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.sign.priority.OperationalSymbolPriority;

public abstract class OperationalSymbol implements Symbol {
	
	public abstract OperationalSymbolPriority getOperationalSymbolPriority();
	
	public abstract int getOperandNumber();
	
	public abstract Operand calculate(Operand[] operands);
	
}
