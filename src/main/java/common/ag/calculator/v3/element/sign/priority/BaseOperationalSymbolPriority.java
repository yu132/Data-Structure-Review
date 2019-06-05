package common.ag.calculator.v3.element.sign.priority;

/**
 * 基本的运算优先级定义
 * 
 * @author 87663
 */
public enum BaseOperationalSymbolPriority implements OperationalSymbolPriority {
	
	// **Ternary operators**	three operand	三元运算
	TERNARY_OPERATION_PRIORITY(1000000),
	/*		包括
	 * 
	 * CONDITIONAL_SIGN		// ?: 状态选择
	 */
	
	// **Binary operation**		two operand		二元运算
	BINARY_OPERATION_PRIORITY_CCOMPARE(2000000),
	/*		包括
	 * 
	 * GREATER_SIGN			//大于号
	 * GREATER_EQUAL_SIGN	//大于等于号
	 * LITTLE_SIGN			//小于号
	 * LITTLE_EQUAL_SIGN	//小于等于号
	 * EQUAL_SIGN			//等于号
	 * NOT_EQUAL_SIGN		//不等于号
	 */
	
	BINARY_OPERATION_PRIORITY_1(3000000),
	/*		包括
	 * 
	 * BITWISE_OR_SIGN,		//按位或
	 * BITWISE_AND_SIGN,	//按位与
	 * BITWISE_XOR_SIGN,	//按位异或
	 * 
	 * BITWISE_LEFT_SHIFT_SIGN		//按位左移
	 * BITWISE_RIGHT_SHIFT_SIGN		//按位右移
	 * 
	 * LOGICAL_OR_SIGN,		//逻辑或
	 * LOGICAL_AND_SIGN,	//逻辑与
	 */
	BINARY_OPERATION_PRIORITY_2(4000000),
	/*		包括
	 * 
	 * ADD_SIGN,			//加号
	 * SUBTRACT_SIGN,		//减号
	 */
	BINARY_OPERATION_PRIORITY_3(5000000),
	/*		包括
	 * 
	 * MULTIPLY_SIGN,		//乘号
	 * DIVIDE_SIGN,			//除号
	 * FLOOR_DIVIDE_SIGN,	//取整除法
	 * MODULO_SIGN,			//求模（余数）
	 */
	BINARY_OPERATION_PRIORITY_4(6000000),
	/*		包括
	 * 
	 * POWER_SIGN,			//乘方号
	 * LOGARITHM_SIGN		//对数
	 */
	
	// **Unary operation**	one operand		一元运算
	UNARY_OPERATION_PRIORITY(7000000);
	/*		包括
	 * 
	 * NEGATIVE_SIGN,		//负号
	 * POSITIVE_SIGN,		//正号
	 * BITWISE_NOT_SIGN,	//按位取反
	 * LOGICAL_NOT_SIGN,	//逻辑取反
	 * TO_INT_SIGN,			//强制转换INT
	 * TO_DOUBLE_SIGN,		//强制转换DOUBLE
	 * TO_BOOLEAN_SIGN,		//强制转换BOOLEAN
	 */
	
	private int priority;
	
	private BaseOperationalSymbolPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public int intValue() {
		return priority;
	}
}
