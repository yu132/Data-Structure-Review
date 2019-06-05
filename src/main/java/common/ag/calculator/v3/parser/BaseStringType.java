package common.ag.calculator.v3.parser;

/**
 * 常见的字符串符号类型
 * 
 * @author 87663
 */
public enum BaseStringType implements StringType {
	
	//操作数
	NUMBER("1 1.0"),
	BOOLEAN_NUMBER("true false"),
	
	//操作符
	GREATER_SIGN(">"),
	GREATER_EQUAL_SIGN(">="),
	LITTLE_SIGN("<"),
	LITTLE_EQUAL_SIGN("<="),
	EQUAL_SIGN("=="),
	NOT_EQUAL_SIGN_1("!="),
	NOT_EQUAL_SIGN_2("<>"),
	BITWISE_OR_SIGN("|"),
	BITWISE_AND_SIGN("&"),
	BITWISE_XOR_SIGN("^"),
	BITWISE_LEFT_SHIFT_SIGN("<<"),
	BITWISE_RIGHT_SHIFT_SIGN(">>"),
	LOGICAL_OR_SIGN("||"),
	LOGICAL_AND_SIGN("&&"),
	
	ADD_SIGN("+"),
	SUBTRACT_SIGN("-"),
	MULTIPLY_SIGN("*"),
	DIVIDE_SIGN("/"),
	FLOOR_DIVIDE_SIGN("//"),
	MODULO_SIGN("%"),
	POWER_SIGN("**"),
	LOGARITHM_SIGN("LOG"),
	
	NEGATIVE_SIGN("-"),
	POSITIVE_SIGN("+"),
	BITWISE_NOT_SIGN("~"),
	LOGICAL_NOT_SIGN("!"),
	TO_INT_SIGN("(int)"),
	TO_DOUBLE_SIGN("(double)"),
	TO_BOOLEAN_SIGN("(boolean)"),
	
	//分段操作符
	CONDITIONAL_SIGN("? :"),
	
	//通用操作符
	RIGHT_PARENTHESIS(")"),
	
	//左括号
	LEFT_PARENTHESIS("(");
	
	public final String DESCRIPTION;
	
	private BaseStringType(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	
}
