package common.ag.calculator.v3.element.sign.exception;

/**
 * 操作数数量不符合异常（如果编程者不出错误的话，应该是不会抛出这个异常的）
 * 
 * @author 87663
 */
public class OperandNumberMismatchException extends IllegalArgumentException {
	
	private static final long serialVersionUID = -7257802760410690443L;
	
	public OperandNumberMismatchException(String signName, int needOprandNumber,
			int nowOprandNumber) {
		super("Sign " + signName + " need " + needOprandNumber + "operand"
				+ (needOprandNumber == 1 ? "" : "s") + ", but "
				+ needOprandNumber + " operand" + (nowOprandNumber == 1 ? "" : "s")
				+ " is offered");
	}
	
}
