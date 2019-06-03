package common.ag.calculator.v3.element.operand;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DoubleNumber implements Number {
	
	private BigDecimal number;
	
	public BigDecimal getNumber() {
		return number;
	}
	
	private DoubleNumber(BigDecimal number) {
		super();
		this.number = number;
	}
	
	private DoubleNumber(BigInteger number) {
		super();
		this.number = new BigDecimal(number);
	}
	
	private DoubleNumber(String number) {
		super();
		this.number = new BigDecimal(number);
	}
	
	public static DoubleNumber valueOf(String number) {
		return new DoubleNumber(number);
	}
	
	public static DoubleNumber valueOf(BigDecimal number) {
		return new DoubleNumber(number);
	}
	
	public static DoubleNumber valueOf(BigInteger number) {
		return new DoubleNumber(number);
	}
	
	@Override
	public String value() {
		return number.stripTrailingZeros().toPlainString();
	}
	
	@Override
	public String toString() {
		return value();
	}
	
	@Override
	public OperandType getOperandType() {
		return BaseOperandType.DOUBLE_OPERAND;
	}
	
}
