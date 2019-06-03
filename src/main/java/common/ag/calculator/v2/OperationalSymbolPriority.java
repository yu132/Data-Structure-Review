package common.ag.calculator.v2;

public enum OperationalSymbolPriority {
	
	// **Ternary operators**	three operand	三元运算
	TERNARY_OPERATION_PRIORITY,
	/*		包括
	 * 
	 * CONDITIONAL_SIGN		// ?: 状态选择
	 */
	
	// **Binary operation**		two operand		二元运算
	BINARY_OPERATION_PRIORITY_1,
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
	BINARY_OPERATION_PRIORITY_2,
	/*		包括
	 * 
	 * ADD_SIGN,			//加号
	 * SUBTRACT_SIGN,		//减号
	 */
	BINARY_OPERATION_PRIORITY_3,
	/*		包括
	 * 
	 * MULTIPLY_SIGN,		//乘号
	 * DIVIDE_SIGN,			//除号
	 * MODULO_SIGN,			//求模（余数）
	 */
	BINARY_OPERATION_PRIORITY_4,
	/*		包括
	 * 
	 * POWER_SIGN,			//乘方号
	 * LOGARITHM_SIGN		//对数
	 */
	
	// **Unary operation**	one operand		一元运算
	UNARY_OPERATION_PRIORITY,
	/*		包括
	 * 
	 * NEGATIVE_SIGN,		//负号
	 * POSITIVE_SIGN,		//正号
	 * BITWISE_NOT_SIGN,	//按位取反
	 * LOGICAL_NOT_SIGN,	//逻辑取反
	 */
}
