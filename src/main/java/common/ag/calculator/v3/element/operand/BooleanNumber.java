package common.ag.calculator.v3.element.operand;

/**
 * 布尔操作数
 * 
 * @author 876633022
 */
public class BooleanNumber implements Number {
	
	public final static BooleanNumber TRUE = new BooleanNumber(true);
	public final static BooleanNumber FALSE = new BooleanNumber(false);
	
	private boolean number;
	
	public boolean getNumber() {
		return number;
	}
	
	private BooleanNumber(boolean number) {
		super();
		this.number = number;
	}
	
	public static BooleanNumber valueOf(boolean num) {
		return num ? TRUE : FALSE;
	}
	
	@Override
	public String value() {
		return this == TRUE ? "true" : "false";
	}
	
	@Override
	public String toString() {
		return value();
	}
	
	@Override
	public OperandType getOperandType() {
		return BaseOperandType.BOOLEAN_OPERAND;
	}
	
}
