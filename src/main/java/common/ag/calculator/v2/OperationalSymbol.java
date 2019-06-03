package common.ag.calculator.v2;

public abstract class OperationalSymbol extends Symbol {
	
	// Arity @see https://en.wikipedia.org/wiki/Arity
	public abstract int getOperandNumber();
	
	public abstract int operationalSymbolLength();
	
	public abstract OperationalSymbolPriority getOperationalSymbolPriority();
	
	public abstract int culculate(int... nums);
	
	@Override
	public SymbolPriority getPriority() {
		return SymbolPriority.OPERATIONAL_SYMBOL;
	}
	
	@Override
	public boolean getSymbol(ParserShared shared) {
		
		if (check(shared))
			if (symbol(shared)) {
				shared.operationalSymbolCheck[shared.now] = true;
				return true;
			}
		
		return false;
	};
}
