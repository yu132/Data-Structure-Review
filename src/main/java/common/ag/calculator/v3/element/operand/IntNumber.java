package common.ag.calculator.v3.element.operand;

import java.math.BigInteger;

/**
 * 整形操作数
 * 
 * @author 876633022
 */
public class IntNumber implements Number {
	
	private final static IntNumber[] pool = new IntNumber[256];// 常量池 -128 to 127
	
	private BigInteger number;
	
	public BigInteger getNumber() {
		return number;
	}
	
	private IntNumber(int num) {
		super();
		this.number = BigInteger.valueOf(num);
	}
	
	public IntNumber(BigInteger number) {
		super();
		this.number = number;
	}
	
	static {
		for (int i = 0; i < pool.length; ++i) {
			pool[i] = new IntNumber(i - 128);
		}
	}
	
	private final static BigInteger min = BigInteger.valueOf(-128);
	private final static BigInteger max = BigInteger.valueOf(127);
	
	public static IntNumber valueOf(String number) {
		if (number.length() < 3) {
			int num = Integer.parseInt(number);
			if (num >= -128 && num <= 127)
				return pool[num + 128];
		}
		return new IntNumber(new BigInteger(number));
	}
	
	public static IntNumber valueOf(BigInteger number) {
		if (number.compareTo(min) >= 0 && number.compareTo(max) <= 0)
			return pool[number.intValue() + 128];
		else
			return new IntNumber(number);
	}
	
	@Override
	public String value() {
		return number.toString();
	}
	
	@Override
	public String toString() {
		return value();
	}
	
	@Override
	public OperandType getOperandType() {
		return BaseOperandType.INTEGER_OPERAND;
	}
}
