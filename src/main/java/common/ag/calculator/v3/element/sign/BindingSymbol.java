package common.ag.calculator.v3.element.sign;

/**
 * 结合符号
 * 
 * @author 87663
 *
 */
public abstract class BindingSymbol implements Symbol {
	
	/**
	 * 左括号
	 */
	public final static BindingSymbol LEFT_PARENTHESIS = new BindingSymbol() {
		
		@Override
		public String toString() {
			return "(";
		}
		
	};
	
	/**
	 * 右括号
	 */
	public final static BindingSymbol RIGHT_PARENTHESIS = new BindingSymbol() {
		
		@Override
		public String toString() {
			return ")";
		}
		
	};
	
}
