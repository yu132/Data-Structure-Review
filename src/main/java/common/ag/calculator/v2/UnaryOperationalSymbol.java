package common.ag.calculator.v2;

/**
 * 单目运算符
 * 
 * @author 87663
 *
 */
public abstract class UnaryOperationalSymbol extends OperationalSymbol {
	
	public final static int OPERAND_NUMBER = 1;
	
	public final static int SYMBOL_LENGTH = 1;
	
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}
	
	@Override
	public int operationalSymbolLength() {
		return SYMBOL_LENGTH;
	}
	
	@Override
	public OperationalSymbolPriority getOperationalSymbolPriority() {
		return OperationalSymbolPriority.UNARY_OPERATION_PRIORITY;
	}
	
	/**
	 * 单目运算符的检查，和数字一模一样
	 * 
	 * 即前面不能是数字和右括号
	 */
	@Override
	protected boolean check(ParserShared shared) {
		return !shared.numberCheck[shared.now ^ 1] && !shared.rightParenthesisCheck[shared.now ^ 1];
	}
	
	public final static UnaryOperationalSymbol NEGATIVE_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 1 for negative sign");
			return -nums[0];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '-')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static UnaryOperationalSymbol POSITIVE_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 1 for positive sign");
			return nums[0];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '+')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
		
	};
	
	public final static UnaryOperationalSymbol BITWISE_NOT_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 1 for bitwise not sign");
			return ~nums[0];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '~')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static UnaryOperationalSymbol LOGICAL_NOT_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 1 for logical not sign");
			
			if (nums[0] != 0)
				return 0;
			else
				return 1;
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '!')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
}
