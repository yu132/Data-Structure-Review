package common.ag.calculator.v3.element.sign.priority;

import common.ag.calculator.v3.element.sign.OperationalSymbol;

public interface OperationalSymbolPriority {
	
	int intValue();
	
	public static int compare(OperationalSymbol o1, OperationalSymbol o2) {
		return Integer.compare(o1.getOperationalSymbolPriority().intValue(),
				o2.getOperationalSymbolPriority().intValue());
	}
}
