package common.ag.calculator.v1;

public interface Symbol extends Element {
	
	int getPriority();
	
	int isSymbol(String expression, int from);
	
}
