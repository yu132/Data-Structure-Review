package common.ag.calculator.v2;

/**
 * 双目运算符
 * @author 87663
 *
 */
public abstract class BinaryOperationalSymbol extends OperationalSymbol {
	
	public final static int OPERAND_NUMBER = 2;
	
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
		return OperationalSymbolPriority.BINARY_OPERATION_PRIORITY_1;
	}
	
	/**
	 * 大多数双目运算符的检查，前面必须是数字或者右括号
	 * 
	 * 特殊的双目运算符特殊处理
	 */
	@Override
	protected boolean check(ParserShared shared) {
		return shared.numberCheck[shared.now ^ 1] || shared.rightParenthesisCheck[shared.now ^ 1];
	}
	
	public final static BinaryOperationalSymbol BITWISE_OR_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for bitwise or sign");
			return nums[0] | nums[1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '|')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol BITWISE_AND_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for bitwise and sign");
			return nums[0] & nums[1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '&')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol BITWISE_XOR_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for bitwise xor sign");
			return nums[0] ^ nums[1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '^')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol BITWISE_LEFT_SHIFT_SIGN = new BinaryOperationalSymbol() {
		
		public final static int SYMBOL_LENGTH = 2;
		
		@Override
		public int operationalSymbolLength() {
			return SYMBOL_LENGTH;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException(
						"Operand number is not 2 for bitwise left shift sign");
			return nums[0] << nums[1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			if (shared.expression.length() < shared.from[shared.now ^ 1] + SYMBOL_LENGTH)
				return false;
			
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			char c2 = shared.expression.charAt(shared.from[shared.now ^ 1] + 1);
			
			if (c != '<' || c2 != '<')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol BITWISE_RIGHT_SHIFT_SIGN = new BinaryOperationalSymbol() {
		
		public final static int SYMBOL_LENGTH = 2;
		
		@Override
		public int operationalSymbolLength() {
			return SYMBOL_LENGTH;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException(
						"Operand number is not 2 for bitwise right shift sign");
			return nums[0] << nums[1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			if (shared.expression.length() < shared.from[shared.now ^ 1] + SYMBOL_LENGTH)
				return false;
			
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			char c2 = shared.expression.charAt(shared.from[shared.now ^ 1] + 1);
			
			if (c != '<' || c2 != '<')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol LOGICAL_OR_SIGN = new BinaryOperationalSymbol() {
		
		public final static int SYMBOL_LENGTH = 2;
		
		@Override
		public int operationalSymbolLength() {
			return SYMBOL_LENGTH;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for logical or sign");
			
			return (nums[0] != 0 || nums[1] != 0) ? 1 : 0;
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			if (shared.expression.length() < shared.from[shared.now ^ 1] + SYMBOL_LENGTH)
				return false;
			
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			char c2 = shared.expression.charAt(shared.from[shared.now ^ 1] + 1);
			
			if (c != '|' || c2 != '|')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol LOGICAL_AND_SIGN = new BinaryOperationalSymbol() {
		
		public final static int SYMBOL_LENGTH = 2;
		
		@Override
		public int operationalSymbolLength() {
			return SYMBOL_LENGTH;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for logical and sign");
			
			
			return (nums[0] != 0 && nums[1] != 0) ? 1 : 0;
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			if (shared.expression.length() < shared.from[shared.now ^ 1] + SYMBOL_LENGTH)
				return false;
			
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			char c2 = shared.expression.charAt(shared.from[shared.now ^ 1] + 1);
			
			if (c != '&' || c2 != '&')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol ADD_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return OperationalSymbolPriority.BINARY_OPERATION_PRIORITY_2;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for add sign");
			
			return nums[0] + nums[1];
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
	
	public final static BinaryOperationalSymbol SUBTRACT_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return OperationalSymbolPriority.BINARY_OPERATION_PRIORITY_2;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for subtract sign");
			
			return nums[0] - nums[1];
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
	
	public final static BinaryOperationalSymbol MULTIPLY_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return OperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for multiply sign");
			
			return nums[0] * nums[1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '*')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol DIVIDE_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return OperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for divide sign");
			
			return nums[0] / nums[1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '/')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol MODULO_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return OperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for modulo sign");
			
			return nums[0] % nums[1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '%')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol POWER_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return OperationalSymbolPriority.BINARY_OPERATION_PRIORITY_4;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for power sign");
			
			return (int) Math.pow(nums[0], nums[1]);
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '%')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
	
	public final static BinaryOperationalSymbol LOGARITHM_SIGN = new BinaryOperationalSymbol() {
		
		public final static int SYMBOL_LENGTH = 3;
		
		@Override
		public int operationalSymbolLength() {
			return SYMBOL_LENGTH;
		}
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return OperationalSymbolPriority.BINARY_OPERATION_PRIORITY_4;
		}
		
		@Override
		public int culculate(int... nums) {
			if (nums.length != OPERAND_NUMBER)
				throw new IllegalArgumentException("Operand number is not 2 for logarithm sign");
			
			return (int) (Math.log(nums[1]) / Math.log(nums[0]));
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			if (shared.expression.length() < shared.from[shared.now ^ 1] + SYMBOL_LENGTH)
				return false;
			
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			char c2 = shared.expression.charAt(shared.from[shared.now ^ 1] + 1);
			char c3 = shared.expression.charAt(shared.from[shared.now ^ 1] + 2);
			
			if (c != 'l' || c2 != 'o' || c3 != 'g')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			return true;
		}
	};
}
