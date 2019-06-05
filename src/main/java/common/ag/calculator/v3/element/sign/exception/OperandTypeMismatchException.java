package common.ag.calculator.v3.element.sign.exception;

import common.ag.calculator.v3.element.operand.OperandType;
import common.ag.math.IntToOrdinalNumbers;

/**
 * 操作数类型错误异常
 * 
 * @author 87663
 */
public class OperandTypeMismatchException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 4918858786244539344L;
	
	public OperandTypeMismatchException(String signName, int typeExceptionOperandIndex,
			OperandType offeredType, OperandType... types) {
		super(stringBuildeHelper(signName, typeExceptionOperandIndex, offeredType, types));
	}
	
	private static String stringBuildeHelper(String signName, int typeExceptionOperandIndex,
			OperandType offeredType, OperandType... types) {
		StringBuilder sb = new StringBuilder(100);
		
		sb.append("The ").append(IntToOrdinalNumbers.convert(typeExceptionOperandIndex))
				.append(" operand type is " + offeredType + " which is mismatch.");
		
		if (types.length > 0) {
			sb.append("\nSign ").append(signName).append(" need operand whose type like: ");
			
			for (int i = 0; i < types.length - 1; ++i) {
				sb.append(types[i]).append(",");
			}
			
			sb.append(types[types.length - 1]).append(".");
		}
		
		return sb.toString();
	}
	
}
