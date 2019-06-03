package common.ag.calculator.v3.element.operand;

import common.ag.calculator.v3.element.Element;

public interface Operand extends Element {
	
	String value();
	
	OperandType getOperandType();
	
}
