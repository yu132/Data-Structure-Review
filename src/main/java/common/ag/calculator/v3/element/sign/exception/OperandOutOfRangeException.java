package common.ag.calculator.v3.element.sign.exception;

import java.math.BigInteger;

import common.ag.math.IntToOrdinalNumbers;

public class OperandOutOfRangeException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 4918858786244539344L;
	
	public OperandOutOfRangeException(String signName, int operandIndex,
			BigInteger operand, BigInteger min, BigInteger max) {
		super("The " + IntToOrdinalNumbers.convert(operandIndex) + " operand of " + signName
				+ " should be betwwen " + min + " and " + max + ",but it's " + operand);
	}
}
