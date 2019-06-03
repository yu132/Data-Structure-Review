package common.ag.calculator.v2;


public interface Element {
	
	boolean getElement(ParserShared shared);
	
	static int getPriority(Element e) {
		if (e instanceof Number)
			return 0;
		if (e instanceof BindingSymbol)
			return 1;
		if (e instanceof OperationalSymbol)
			return 2 + OperationalSymbolPriority.values().length
					- ((OperationalSymbol) e).getOperationalSymbolPriority().ordinal();
		return 100;
	}
	
}
