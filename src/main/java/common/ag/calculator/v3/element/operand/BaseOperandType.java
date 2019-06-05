package common.ag.calculator.v3.element.operand;

/**
 * 基本操作数类型
 * 
 * @author 876633022
 */
public enum BaseOperandType implements OperandType {
	
	INTEGER_OPERAND("Integer"), // 整形操作数
	DOUBLE_OPERAND("Double"),	// 浮点操作数
	BOOLEAN_OPERAND("Boolean");	// 浮点操作数
	
	private String type;
	
	private BaseOperandType(String type) {
		this.type = type;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
}
