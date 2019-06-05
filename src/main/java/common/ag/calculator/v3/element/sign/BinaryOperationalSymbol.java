package common.ag.calculator.v3.element.sign;

import static common.ag.calculator.v3.element.operand.BaseOperandType.BOOLEAN_OPERAND;
import static common.ag.calculator.v3.element.operand.BaseOperandType.DOUBLE_OPERAND;
import static common.ag.calculator.v3.element.operand.BaseOperandType.INTEGER_OPERAND;
import static common.ag.calculator.v3.element.operand.util.GetValue.booleanValue;
import static common.ag.calculator.v3.element.operand.util.GetValue.doubleValue;
import static common.ag.calculator.v3.element.operand.util.GetValue.intValue;
import static common.ag.calculator.v3.element.sign.util.CheckOperandRange.checkBigInteger;
import static common.ag.calculator.v3.element.sign.util.PeCheck.preCheck;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import common.ag.calculator.v3.element.operand.BooleanNumber;
import common.ag.calculator.v3.element.operand.DoubleNumber;
import common.ag.calculator.v3.element.operand.IntNumber;
import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.operand.OperandType;
import common.ag.calculator.v3.element.sign.exception.IllegalOperationException;
import common.ag.calculator.v3.element.sign.exception.OperandOutOfRangeException;
import common.ag.calculator.v3.element.sign.exception.OperandTypeMismatchException;
import common.ag.calculator.v3.element.sign.priority.BaseOperationalSymbolPriority;
import common.ag.calculator.v3.element.sign.priority.OperationalSymbolPriority;
import common.ag.calculator.v3.setting.Setting;
import important.cases.Case8;

/**
 * 双目运算符号
 * 
 * @author 87663
 *
 */
public abstract class BinaryOperationalSymbol extends OperationalSymbol {
	
	public final static int OPERAND_NUMBER = 2;//有两个操作数
	
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}
	
	/**
	 * 大于号（>），返回第一个操作数大于第二个操作数的真假性
	 */
	public final static BinaryOperationalSymbol GREATER_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_CCOMPARE;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("greater sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) > 0);
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) > 0);
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return ">";
		}
	};
	
	/**
	 * 大于等于号（>=），返回第一个操作数大于等于第二个操作数的真假性
	 */
	public final static BinaryOperationalSymbol GREATER_EQUAL_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_CCOMPARE;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("greater equal sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) >= 0);
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) >= 0);
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return ">=";
		}
	};
	
	/**
	 * 小于号（<=），返回第一个操作数小于第二个操作数的真假性
	 */
	public final static BinaryOperationalSymbol LITTLE_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_CCOMPARE;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("little sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) < 0);
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) < 0);
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "<";
		}
	};
	
	/**
	 * 小于等于号（<=），返回第一个操作数小于等于第二个操作数的真假性
	 */
	public final static BinaryOperationalSymbol LITTLE_EQUAL_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_CCOMPARE;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("little equal sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) <= 0);
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) <= 0);
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "<=";
		}
	};
	
	/**
	 * 等于号（==），返回第一个操作数等于第二个操作数的真假性
	 */
	public final static BinaryOperationalSymbol EQUAL_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_CCOMPARE;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("equal sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) == 0);
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) == 0);
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "==";
		}
	};
	
	/**
	 * 不等于号（!= <>），返回第一个操作数不等于第二个操作数的真假性
	 */
	public final static BinaryOperationalSymbol NOT_EQUAL_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_CCOMPARE;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("not equal sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) != 0);
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return BooleanNumber.valueOf(num.compareTo(num2) != 0);
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "!=";
		}
	};
	
	/**
	 * 按位或号（|），将两个操作数中的每一位进行或操作
	 */
	public final static BinaryOperationalSymbol BITWISE_OR_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_1;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("bitwise or sign", operands, 2, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return IntNumber.valueOf(num.or(num2));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "|";
		}
	};
	
	/**
	 * 按位与号（&），将两个操作数中的每一位进行与操作
	 */
	public final static BinaryOperationalSymbol BITWISE_AND_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_1;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("bitwise and sign", operands, 2, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return IntNumber.valueOf(num.and(num2));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "&";
		}
	};
	
	/**
	 * 按位异或号（^），将两个操作数中的每一位进行异或操作
	 */
	public final static BinaryOperationalSymbol BITWISE_XOR_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_1;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("bitwise xor sign", operands, 2, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return IntNumber.valueOf(num.xor(num2));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "^";
		}
	};
	
	/**
	 * 按位左移号（<<），将第一个操作数向左移动第二个操作数位
	 */
	public final static BinaryOperationalSymbol BITWISE_LEFT_SHIFT_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_1;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("bitwise left shift sign", operands, 2,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return IntNumber.valueOf(num.shiftLeft(num2.intValue()));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "<<";
		}
	};
	
	/**
	 * 按位右移号（>>），将第一个操作数向右移动第二个操作数位
	 */
	public final static BinaryOperationalSymbol BITWISE_RIGHT_SHIFT_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_1;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("bitwise right shift sign", operands, 2,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return IntNumber.valueOf(num.shiftRight(num2.intValue()));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return ">>";
		}
	};
	
	/**
	 * 逻辑或号（||），返回第一个操作数或第二个操作数的真假性
	 */
	public final static BinaryOperationalSymbol LOGICAL_OR_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_1;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("logical or sign", operands, 2, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			boolean num = booleanValue(operands[0]);
			boolean num2 = booleanValue(operands[1]);
			
			return BooleanNumber.valueOf(num || num2);
		}
		
		@Override
		public String toString() {
			return "||";
		}
	};
	
	/**
	 * 逻辑与号（&&），返回第一个操作数与第二个操作数的真假性
	 */
	public final static BinaryOperationalSymbol LOGICAL_AND_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_1;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("logical and sign", operands, 2, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			boolean num = booleanValue(operands[0]);
			boolean num2 = booleanValue(operands[1]);
			
			return BooleanNumber.valueOf(num && num2);
		}
		
		@Override
		public String toString() {
			return "&&";
		}
	};
	
	/**
	 * 加号（+），返回第一个操作数加第二个操作数的数值
	 */
	public final static BinaryOperationalSymbol ADD_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_2;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("add sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return DoubleNumber.valueOf(num.add(num2));
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return IntNumber.valueOf(num.add(num2));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "+";
		}
	};
	
	/**
	 * 减号（-），返回第一个操作数减第二个操作数的数值
	 */
	public final static BinaryOperationalSymbol SUBTRACT_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_2;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("subtract sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return DoubleNumber.valueOf(num.subtract(num2));
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return IntNumber.valueOf(num.subtract(num2));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "-";
		}
	};
	
	/**
	 * 乘号（*），返回第一个操作数乘第二个操作数的数值
	 */
	public final static BinaryOperationalSymbol MULTIPLY_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("multiply sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				return DoubleNumber.valueOf(num.multiply(num2));
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				return IntNumber.valueOf(num.multiply(num2));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "*";
		}
	};
	
	/**
	 * 除号（/），返回第一个操作数除以第二个操作数的数值（非整除）
	 */
	public final static BinaryOperationalSymbol DIVIDE_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			preCheck("divide sign", operands, 2, DOUBLE_OPERAND, INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			BigDecimal num = doubleValue(operands[0]);
			BigDecimal num2 = doubleValue(operands[1]);
			
			if (num2.equals(BigDecimal.ZERO))
				throw IllegalOperationException.DIVIDE_ZERO_EXCEPTION;
			
			return DoubleNumber.valueOf(num.divide(num2, Setting.doubleScale, RoundingMode.DOWN));
		}
		
		@Override
		public String toString() {
			return "/";
		}
	};
	
	/**
	 * 整除号（//），返回第一个操作数除以第二个操作数后向下取整的数值
	 */
	public final static BinaryOperationalSymbol FLOOR_DIVIDE_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("divide sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				if (num2.equals(BigDecimal.ZERO))
					throw IllegalOperationException.DIVIDE_ZERO_EXCEPTION;
				
				return DoubleNumber.valueOf(num.divide(num2, 0, RoundingMode.DOWN));
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				if (num2.equals(BigInteger.ZERO))
					throw IllegalOperationException.DIVIDE_ZERO_EXCEPTION;
				
				return IntNumber.valueOf(num.divide(num2));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "//";
		}
	};
	
	/**
	 * 取模号（%），返回第一个操作数除以第二个操作数剩下的余数
	 */
	public final static BinaryOperationalSymbol MODULO_SIGN = new BinaryOperationalSymbol() {
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("modulo sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			if (operandType == DOUBLE_OPERAND) {
				
				BigDecimal num = doubleValue(operands[0]);
				BigDecimal num2 = doubleValue(operands[1]);
				
				if (num2.equals(BigDecimal.ZERO))
					throw IllegalOperationException.DIVIDE_ZERO_EXCEPTION;
				
				return DoubleNumber.valueOf(num.divideAndRemainder(num2)[1]);
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				BigInteger num = intValue(operands[0]);
				BigInteger num2 = intValue(operands[1]);
				
				if (num2.equals(BigInteger.ZERO))
					throw IllegalOperationException.DIVIDE_ZERO_EXCEPTION;
				
				return IntNumber.valueOf(num.mod(num2));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "%";
		}
	};
	
	/**
	 * 乘方号（**），返回以第一个操作数为底数，第二个操作数为指数的乘方运算的结果
	 * 
	 * 注意本运算若指数为浮点数，则会损失精度，而且比较严重，请注意
	 */
	public final static BinaryOperationalSymbol POWER_SIGN = new BinaryOperationalSymbol() {
		
		/**
		 * f^n = (f1 * 10^len)^n=f1^n * 10^(len * n)
		 * 
		 * @see Case8 由于乘方数量级比较大，因此误差也比较大，不可接受的时候关闭选项即可
		 * 
		 * @param base
		 * @param exponent
		 * @return
		 */
		private BigDecimal simplePow(BigDecimal base, double exponent) {
			
			int len = base.toBigInteger().toString(10).length() - 1;//两位数除10的一次方  1一位数除10的0次方，故要减一
			
			double value = base.divide(new BigDecimal(10).pow(len)).doubleValue();//压缩到1-10之间
			
			value = Math.pow(value, exponent) * Math.pow(10, len * exponent);
			
			return new BigDecimal(value);
		}
		
		private final BigInteger maxExponent = BigInteger.valueOf(Integer.MAX_VALUE);
		
		private final BigInteger maxDoubleExponent = BigInteger.valueOf(999999999);
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			OperandType operandType = preCheck("power sign", operands, 2, DOUBLE_OPERAND,
					INTEGER_OPERAND, BOOLEAN_OPERAND);
			
			
			if (operands[1].getOperandType() == DOUBLE_OPERAND)
				if (!Setting.permissibleLossOfAccuracy)
					throw new OperandTypeMismatchException("power sign", 2, DOUBLE_OPERAND,
							INTEGER_OPERAND, BOOLEAN_OPERAND);
				else {//有损失的小数乘方
					BigDecimal base = doubleValue(operands[0]);
					
					BigDecimal exp = doubleValue(operands[1]);
					
					BigInteger floorExp = exp.toBigInteger();
					
					if (!checkBigInteger(floorExp, BigInteger.ZERO, maxExponent))
						throw new OperandOutOfRangeException("power sign", 2, floorExp,
								BigInteger.ZERO, maxDoubleExponent);
					
					double floatExp = exp.subtract(new BigDecimal(floorExp)).doubleValue();
					
					BigDecimal part = simplePow(base, floatExp);
					
					return DoubleNumber.valueOf(part.add(base.pow(floorExp.intValue())));
				}
			
			BigInteger num2 = intValue(operands[1]);
			
			if (operandType == DOUBLE_OPERAND) {
				
				if (!checkBigInteger(num2, BigInteger.ZERO, maxExponent))
					throw new OperandOutOfRangeException("power sign", 2, num2, BigInteger.ZERO,
							maxDoubleExponent);
				
				BigDecimal num = doubleValue(operands[0]);
				
				return DoubleNumber.valueOf(num.pow(num2.intValue()));
			}
			
			if (operandType == INTEGER_OPERAND || operandType == BOOLEAN_OPERAND) {
				
				if (!checkBigInteger(num2, BigInteger.ZERO, maxExponent))
					throw new OperandOutOfRangeException("power sign", 2, num2, BigInteger.ZERO,
							maxExponent);
				
				BigInteger num = intValue(operands[0]);
				
				return IntNumber.valueOf(num.pow(num2.intValue()));
			}
			
			throw new RuntimeException("impossible to reach here");
		}
		
		@Override
		public String toString() {
			return "**";
		}
	};
	
	/**
	 * 对数号（**），返回以第一个操作数为底数，第二个操作数为真数的对数运算的结果
	 * 
	 * 注意本运算会损失精度，不过损失的比较小
	 */
	public final static BinaryOperationalSymbol LOGARITHM_SIGN = new BinaryOperationalSymbol() {
		
		private final BigDecimal LOG10 = new BigDecimal(Math.log(10));
		
		private final BigDecimal bigDouble = new BigDecimal(999999999999999.0);
		
		/**
		 * 有精度损失的求对数，不知道会损失多少
		 * 
		 * 就是由于大数没有直接求对数的方法，才使用这种有损失的方法作为替代
		 * 
		 * 就是将大数f压成1-10的数f1
		 * 
		 * log(f)=log(f1*10^len)=log(f1)+len*log(10)
		 * 
		 * 使用@see Case8 证明了利用这个公式计算实际上损失很小或者几乎没有
		 * 损失主要是在从大数压缩到1-10之间的时候，但是由于log(f1)非常小
		 * 而后面len*log(10)比较大，故损失应该不是很大
		 * 
		 * @param number
		 * @return
		 */
		private BigDecimal simpleLog1(BigDecimal number) {
			
			int len = number.toBigInteger().toString(10).length() - 1;//两位数除10的一次方  1一位数除10的0次方，故要减一
			
			double value = number.divide(new BigDecimal(10).pow(len)).doubleValue();//压缩到1-10之间
			
			return new BigDecimal(Math.log(value)).add(LOG10.multiply(new BigDecimal(len)));
		}
		
		private BigDecimal simpleLog2(BigDecimal number) {
			return new BigDecimal(Math.log(number.doubleValue()));
		}
		
		private BigDecimal simpleLog(BigDecimal number) {
			if (number.compareTo(bigDouble) > 0)
				return simpleLog1(number);
			else
				return simpleLog2(number);
		}
		
		@Override
		public OperationalSymbolPriority getOperationalSymbolPriority() {
			return BaseOperationalSymbolPriority.BINARY_OPERATION_PRIORITY_3;
		}
		
		@Override
		public Operand calculate(Operand[] operands) {
			
			if (!Setting.permissibleLossOfAccuracy)
				throw new IllegalStateException("No permissible loss of accuracy");
			
			preCheck("logarithm sign", operands, 2, DOUBLE_OPERAND, INTEGER_OPERAND,
					BOOLEAN_OPERAND);
			
			BigDecimal num = doubleValue(operands[1]);
			BigDecimal num2 = doubleValue(operands[0]);
			
			return DoubleNumber
					.valueOf(simpleLog(num).divide(simpleLog(num2), Setting.doubleScale,
							RoundingMode.DOWN));
		}
		
		@Override
		public String toString() {
			return "LOG";
		}
	};
	
}
