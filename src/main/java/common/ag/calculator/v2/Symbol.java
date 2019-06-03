package common.ag.calculator.v2;

public abstract class Symbol implements Element {
	
	public final static java.util.Comparator<Symbol> Comparator = new java.util.Comparator<Symbol>() {
		
		@Override
		public int compare(Symbol o1, Symbol o2) {
			return o1.getPriority().compareTo(o2.getPriority());
		}
	};
	
	public abstract SymbolPriority getPriority();
	
	@Override
	public boolean getElement(ParserShared shared) {
		return getSymbol(shared);
	}
	
	public boolean getSymbol(ParserShared shared) {
		
		if (check(shared))
			if (getSymbol(shared))
				return true;
			
		return false;
	};
	
	protected abstract boolean check(ParserShared shared);
	
	protected abstract boolean symbol(ParserShared shared);
	
	/**
	 * 对于多目运算符，中间需要进行检查时使用
	 * 或者检查括号的数量
	 * 
	 * 检查到错误将抛出异常
	 */
	public void insideCkeck(ParserShared shared) {}
	
	/**
	 * 对于被操作数或是表达式分割开的运算符，可能会只出现一半
	 * 这种情况是非法的，因此最终需要检查有没有这种情况
	 * 
	 * 检查到错误将抛出异常
	 */
	public void finalCheck(ParserShared shared) {}
	
}
