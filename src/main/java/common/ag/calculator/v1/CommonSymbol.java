package common.ag.calculator.v1;

public class CommonSymbol {
	
	public final static OperationalSymbol ADD = new BinaryOperationalSymbol() {
		
		@Override
		public int getPriority() {
			return 0;
		}
		
		@Override
		public int calculate(int a, int b) {
			return a + b;
		}
		
		@Override
		public int isSymbol(String expression, int from) {
			return expression.charAt(from) == '+' ? 1 : -1;
		}
		
		@Override
		public String toString() {
			return "+";
		}
	};
	
	public final static OperationalSymbol SUBTRACT = new BinaryOperationalSymbol() {
		
		@Override
		public int getPriority() {
			return 0;
		}
		
		@Override
		public int calculate(int a, int b) {
			return a - b;
		}
		
		@Override
		public int isSymbol(String expression, int from) {
			return expression.charAt(from) == '-' ? 1 : -1;
		}
		
		@Override
		public String toString() {
			return "-";
		}
		
	};
	
	public final static OperationalSymbol MULTIPLY = new BinaryOperationalSymbol() {
		
		@Override
		public int getPriority() {
			return 1;
		}
		
		@Override
		public int calculate(int a, int b) {
			return a * b;
		}
		
		@Override
		public int isSymbol(String expression, int from) {
			return expression.charAt(from) == '*' ? 1 : -1;
		}
		
		@Override
		public String toString() {
			return "*";
		}
		
	};
	
	public final static OperationalSymbol DIVIDE = new BinaryOperationalSymbol() {
		
		@Override
		public int getPriority() {
			return 1;
		}
		
		@Override
		public int calculate(int a, int b) {
			return a / b;
		}
		
		@Override
		public int isSymbol(String expression, int from) {
			return expression.charAt(from) == '/' ? 1 : -1;
		}
		
		@Override
		public String toString() {
			return "/";
		}
		
	};
	
	public final static OperationalSymbol MOD = new BinaryOperationalSymbol() {
		
		@Override
		public int getPriority() {
			return 1;
		}
		
		@Override
		public int calculate(int a, int b) {
			return a % b;
		}
		
		@Override
		public int isSymbol(String expression, int from) {
			return expression.charAt(from) == '%' ? 1 : -1;
		}
		
		@Override
		public String toString() {
			return "%";
		}
	};
	
	public final static OperationalSymbol POWER = new BinaryOperationalSymbol() {
		
		@Override
		public int getPriority() {
			return 2;
		}
		
		@Override
		public int calculate(int a, int b) {
			return (int) Math.pow(a, b);
		}
		
		@Override
		public int isSymbol(String expression, int from) {
			if (expression.charAt(from) == '^')
				return 1;
			if (expression.length() > from + 1 && expression.charAt(from) == '*'
					&& expression.charAt(from + 1) == '*')// **
				return 2;
			return -1;
		}
		
		@Override
		public String toString() {
			return "^";
		}
	};
	
	public final static BindingSymbol LEFT_PARENTHESIS = new BindingSymbol() {
		
		@Override
		public int getPriority() {
			return 3;
		}
		
		@Override
		public int isSymbol(String expression, int from) {
			return expression.charAt(from) == '(' ? 1 : -1;
		}
		
		@Override
		public String toString() {
			return "(";
		}
		
	};
	
	public final static BindingSymbol RIGHT_PARENTHESIS = new BindingSymbol() {
		
		@Override
		public int getPriority() {
			return 3;
		}
		
		@Override
		public int isSymbol(String expression, int from) {
			return expression.charAt(from) == ')' ? 1 : -1;
		}
		
		@Override
		public String toString() {
			return ")";
		}
		
	};
	
}
