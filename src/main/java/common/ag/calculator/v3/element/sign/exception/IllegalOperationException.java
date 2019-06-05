package common.ag.calculator.v3.element.sign.exception;

/**
 * 常见算数异常
 * 
 * @author 87663
 */
public class IllegalOperationException {
	
	/**
	 * 除0异常
	 */
	public final static RuntimeException DIVIDE_ZERO_EXCEPTION = new RuntimeException(
			"Divide Zero");
	
}
