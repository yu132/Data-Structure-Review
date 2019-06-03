package common.ag.calculator.v2;

public abstract class BindingSymbol extends Symbol {
	
	public final static int SYMBOL_LENGTH = 1;
	
	@Override
	public SymbolPriority getPriority() {
		return SymbolPriority.BINDING_SYMBOL;
	}
	
	public final static BindingSymbol LEFT_PARENTHESIS = new BindingSymbol() {
		
		@Override
		public boolean getSymbol(ParserShared shared) {
			
			if (check(shared))
				if (symbol(shared)) {
					shared.leftParenthesisCheck[shared.now] = true;
					return true;
				}
			
			return false;
		};
		
		@Override
		protected boolean check(ParserShared shared) {
			return !shared.numberCheck[shared.now ^ 1]
					&& !shared.rightParenthesisCheck[shared.now ^ 1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != '(')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			++shared.numberOfLeftParenthesis;
			
			return true;
		}
		
	};
	
	public final static BindingSymbol RIGHT_PARENTHESIS = new BindingSymbol() {
		
		@Override
		public boolean getSymbol(ParserShared shared) {
			
			if (check(shared))
				if (getSymbol(shared)) {
					shared.rightParenthesisCheck[shared.now] = true;
					return true;
				}
			return false;
		};
		
		@Override
		protected boolean check(ParserShared shared) {
			return shared.numberCheck[shared.now ^ 1]
					|| shared.rightParenthesisCheck[shared.now ^ 1];
		}
		
		@Override
		protected boolean symbol(ParserShared shared) {
			
			char c = shared.expression.charAt(shared.from[shared.now ^ 1]);
			if (c != ')')
				return false;
			
			shared.from[shared.now] = shared.from[shared.now ^ 1] + SYMBOL_LENGTH;
			shared.elements.add(this);
			
			++shared.numberOfRightParenthesis;
			
			if (shared.numberOfLeftParenthesis <= shared.numberOfRightParenthesis)
				throw new IllegalArgumentException(
						"Expression illegal :  number of right parenthesis is greater than left parenthesis :"
								+ shared.expression.substring(0, shared.from[shared.now]));
			
			return false;
		}
		
		@Override
		public void finalCheck(ParserShared shared) {
			if (shared.numberOfLeftParenthesis != shared.numberOfRightParenthesis)//左右括号的数量必须相等
				throw new IllegalArgumentException(
						"Expression illegal : number of left parenthesis is not equal to right parenthesis");
		}
		
	};
	
}
