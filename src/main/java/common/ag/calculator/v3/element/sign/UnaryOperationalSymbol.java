package common.ag.calculator.v3.element.sign;

import static common.ag.calculator.v3.element.operand.BaseOperandType.BOOLEAN_OPERAND;
import static common.ag.calculator.v3.element.operand.BaseOperandType.DOUBLE_OPERAND;
import static common.ag.calculator.v3.element.operand.BaseOperandType.INTEGER_OPERAND;
import static common.ag.calculator.v3.element.operand.util.GetValue.booleanValue;
import static common.ag.calculator.v3.element.operand.util.GetValue.doubleValue;
import static common.ag.calculator.v3.element.operand.util.GetValue.intValue;
import static common.ag.calculator.v3.element.sign.util.PeCheck.preCheck;

import java.math.BigDecimal;
import java.math.BigInteger;

import common.ag.calculator.v3.element.operand.BooleanNumber;
import common.ag.calculator.v3.element.operand.DoubleNumber;
import common.ag.calculator.v3.element.operand.IntNumber;
import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.operand.OperandType;
import common.ag.calculator.v3.element.sign.priority.BaseOperationalSymbolPriority;
import common.ag.calculator.v3.element.sign.priority.OperationalSymbolPriority;

/**
 * 一目运算符号
 * 
 * @author 87663
 */
public abstract class UnaryOperationalSymbol extends OperationalSymbol {
	
	public final static int OPERAND_NUMBER = 1;//操作数只有1个
	
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}
	
	@Override
	public OperationalSymbolPriority getOperationalSymbolPriority() {
		return BaseOperationalSymbolPriority.UNARY_OPERATION_PRIORITY;
	}
	
	/**
	 * 负号（-），结果返回一个操作数的负数
	 */
	public final static UnaryOperationalSymbol NEGATIVE_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("negative sign", operands, OPERAND_NUMBER,
					DOUBLE_OPERAND, INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				
				return DoubleNumber.valueOf(num.negate());
				
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				
				return IntNumber.valueOf(num.negate());
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "-";
		}
	};
	
	/**
	 * 正号（+），返回这个操作数本身
	 */
	public final static UnaryOperationalSymbol POSITIVE_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("positive sign", operands, OPERAND_NUMBER, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			return operands[0];
		}
		
		@Override
		public String toString() {
			return "+";
		}
	};
	
	/**
	 * 按位取反（~），使整形的每一位从0变成1，1变成0
	 */
	public final static UnaryOperationalSymbol BITWISE_NOT_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("bitwise not sign", operands, OPERAND_NUMBER, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			BigInteger num = intValue(operands[0]);
			
			return IntNumber.valueOf(num.not());
		}
		
		@Override
		public String toString() {
			return "~";
		}
	};
	
	/**
	 * 逻辑取反（!），将真变为假，将假变为真
	 */
	public final static UnaryOperationalSymbol LOGICAL_NOT_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("logical not sign", operands, OPERAND_NUMBER, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			boolean num = booleanValue(operands[0]);
			
			return BooleanNumber.valueOf(!num);
		}
		
		@Override
		public String toString() {
			return "!";
		}
	};
	
	/**
	 * 强制转整形（(int)），将一个操作数转换成整形
	 */
	public final static UnaryOperationalSymbol TO_INT_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("to int sign", operands, OPERAND_NUMBER, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			BigInteger num = intValue(operands[0]);
			
			return IntNumber.valueOf(num);
		}
		
		@Override
		public String toString() {
			return "(int)";
		}
	};
	
	/**
	 * 强制转浮点型（(double)），将一个操作数转换成浮点型
	 */
	public final static UnaryOperationalSymbol TO_DOUBLE_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("to double sign", operands, OPERAND_NUMBER, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			BigDecimal num = doubleValue(operands[0]);
			
			return DoubleNumber.valueOf(num);
		}
		
		@Override
		public String toString() {
			return "(double)";
		}
	};
	
	/**
	 * 强制转布尔型（(boolean)），将一个操作数转换成布尔型
	 */
	public final static UnaryOperationalSymbol TO_BOOLEAN_SIGN = new UnaryOperationalSymbol() {
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("to boolean sign", operands, OPERAND_NUMBER, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			boolean num = booleanValue(operands[0]);
			
			return BooleanNumber.valueOf(num);
		}
		
		@Override
		public String toString() {
			return "(boolean)";
		}
	};
	
}
