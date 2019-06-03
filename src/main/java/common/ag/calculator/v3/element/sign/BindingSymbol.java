package common.ag.calculator.v3.element.sign;

public abstract class BindingSymbol implements Symbol {
	
	public final static BindingSymbol LEFT_PARENTHESIS = new BindingSymbol() {
		
		@Override
		public String toString() {
			return "(";
		}
		
	};
	
	public final static BindingSymbol RIGHT_PARENTHESIS = new BindingSymbol() {
		
		@Override
		public String toString() {
			return ")";
		}
		
	};
	
}
