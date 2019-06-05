package common.ag.calculator.v3.element.operand.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import common.ag.calculator.v3.element.operand.BaseOperandType;
import common.ag.calculator.v3.element.operand.BooleanNumber;
import common.ag.calculator.v3.element.operand.DoubleNumber;
import common.ag.calculator.v3.element.operand.IntNumber;
import common.ag.calculator.v3.element.operand.Operand;

/**
 * 从操作数中获取真实数值的方法
 * 
 * 如果类型不一致，则会自动转换类型
 * 
 * @author 876633022
 *
 */
public final class GetValue {
	
	/**
	 * @param operand	操作数
	 * @return			其对应的布尔数值
	 */
	public static boolean booleanValue(Operand operand) {
		if (operand.getOperandType() == BaseOperandType.INTEGER_OPERAND)//对于整形来说，非0就是真
			return !((IntNumber) operand).getNumber().equals(BigInteger.ZERO);
		
		if (operand.getOperandType() == BaseOperandType.DOUBLE_OPERAND)//对于浮点来说，非0就是真
			return !((DoubleNumber) operand).getNumber().toBigInteger().equals(BigInteger.ZERO);
		
		if (operand.getOperandType() == BaseOperandType.BOOLEAN_OPERAND)//布尔类型则返回内部变量
			return ((BooleanNumber) operand).getNumber();
		
		throw new IllegalArgumentException("Unkonwn operand type");
	}
	
	/**
	 * @param operand	操作数
	 * @return			其对应的整形数值
	 */
	public static BigInteger intValue(Operand operand) {
		if (operand.getOperandType() == BaseOperandType.INTEGER_OPERAND)//整形返回内部变量
			return ((IntNumber) operand).getNumber();
		
		if (operand.getOperandType() == BaseOperandType.DOUBLE_OPERAND)//浮点进行向下取整
			return ((DoubleNumber) operand).getNumber().toBigInteger();
		
		if (operand.getOperandType() == BaseOperandType.BOOLEAN_OPERAND)//布尔真对应1，布尔假对应0
			return ((BooleanNumber) operand).getNumber() ? BigInteger.ONE : BigInteger.ZERO;
		
		throw new IllegalArgumentException("Unkonwn operand type");
	}
	
	/**
	 * @param operand	操作数
	 * @return			其对应的浮点数值
	 */
	public static BigDecimal doubleValue(Operand operand) {
		if (operand.getOperandType() == BaseOperandType.INTEGER_OPERAND)//整形返回对应小数为0的浮点
			return new BigDecimal(((IntNumber) operand).getNumber());
		
		if (operand.getOperandType() == BaseOperandType.DOUBLE_OPERAND)//浮点返回内部变量
			return ((DoubleNumber) operand).getNumber();
		
		if (operand.getOperandType() == BaseOperandType.BOOLEAN_OPERAND)//布尔真对应1.0，布尔假对应0
			return ((BooleanNumber) operand).getNumber() ? BigDecimal.ONE : BigDecimal.ZERO;
		
		throw new IllegalArgumentException("Unkonwn operand type");
	}
	
}
