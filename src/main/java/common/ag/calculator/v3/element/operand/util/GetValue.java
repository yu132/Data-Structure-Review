package common.ag.calculator.v3.element.operand.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import common.ag.calculator.v3.element.operand.BaseOperandType;
import common.ag.calculator.v3.element.operand.BooleanNumber;
import common.ag.calculator.v3.element.operand.DoubleNumber;
import common.ag.calculator.v3.element.operand.IntNumber;
import common.ag.calculator.v3.element.operand.Operand;

public final class GetValue {
	
	public static boolean booleanValue(Operand operand) {
		if (operand.getOperandType() == BaseOperandType.INTEGER_OPERAND)
			return !((IntNumber) operand).getNumber().equals(BigInteger.ZERO);
		
		if (operand.getOperandType() == BaseOperandType.DOUBLE_OPERAND)
			return !((DoubleNumber) operand).getNumber().toBigInteger().equals(BigInteger.ZERO);
		
		if (operand.getOperandType() == BaseOperandType.BOOLEAN_OPERAND)
			return ((BooleanNumber) operand).getNumber();
		
		throw new IllegalArgumentException("Unkonwn operand type");
	}
	
	public static BigInteger intValue(Operand operand) {
		if (operand.getOperandType() == BaseOperandType.INTEGER_OPERAND)
			return ((IntNumber) operand).getNumber();
		
		if (operand.getOperandType() == BaseOperandType.DOUBLE_OPERAND)
			return ((DoubleNumber) operand).getNumber().toBigInteger();
		
		if (operand.getOperandType() == BaseOperandType.BOOLEAN_OPERAND)
			return ((BooleanNumber) operand).getNumber() ? BigInteger.ONE : BigInteger.ZERO;
		
		throw new IllegalArgumentException("Unkonwn operand type");
	}
	
	public static BigDecimal doubleValue(Operand operand) {
		if (operand.getOperandType() == BaseOperandType.INTEGER_OPERAND)
			return new BigDecimal(((IntNumber) operand).getNumber());
		
		if (operand.getOperandType() == BaseOperandType.DOUBLE_OPERAND)
			return ((DoubleNumber) operand).getNumber();
		
		if (operand.getOperandType() == BaseOperandType.BOOLEAN_OPERAND)
			return ((BooleanNumber) operand).getNumber() ? BigDecimal.ONE : BigDecimal.ZERO;
		
		throw new IllegalArgumentException("Unkonwn operand type");
	}
	
}
