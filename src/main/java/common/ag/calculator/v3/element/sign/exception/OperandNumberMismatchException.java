package common.ag.calculator.v3.element.sign.exception;

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
