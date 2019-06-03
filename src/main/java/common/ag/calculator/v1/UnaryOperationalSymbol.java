package common.ag.calculator.v1;

/**
 * 单目运算符
 * 
 * @author 87663
 *
 */
public interface UnaryOperationalSymbol extends OperationalSymbol {
	
	int calculate(int a);
	
}
