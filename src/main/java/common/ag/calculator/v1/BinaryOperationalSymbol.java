package common.ag.calculator.v1;

/**
 * 双目运算符
 * @author 87663
 *
 */
public interface BinaryOperationalSymbol extends OperationalSymbol {
	
	int calculate(int a, int b);
	
}
