package common.ag.calculator.v3;

import java.util.Arrays;

import common.ag.calculator.v3.element.Element;
import common.ag.calculator.v3.element.operand.Operand;
import common.ag.calculator.v3.element.sign.BindingSymbol;
import common.ag.calculator.v3.element.sign.OperationalSymbol;
import common.ag.calculator.v3.element.sign.Symbol;
import common.ag.calculator.v3.element.sign.priority.OperationalSymbolPriority;

public final class Calculate {
	
	/**
	 * @param operandStack
	 * @param size
	 * @param operationalSymbol
	 * @return		减少操作数的个数
	 */
	private static int helper(Operand[] operandStack, int size,
			OperationalSymbol operationalSymbol) {
		int operandNumber = operationalSymbol.getOperandNumber();
		if (size < operandNumber)
			throw new IllegalArgumentException("operand number not enough");
		
		Operand[] operands = Arrays.copyOfRange(operandStack, size - operandNumber, size);
		
		operandStack[size - operandNumber] = operationalSymbol.calculate(operands);
		
		return operandNumber - 1;
	}
	
	/**
	 * 计算，就是维护两个栈
	 * 
	 * 一个栈存储操作数
	 * 
	 * 一个栈存储符号
	 * 
	 * 左右括号比较特殊，他们不进行运算，但是将运算顺序进行了改变
	 * 
	 * （1）首先，我们每次遇到一个数，都将其压入操作数栈中
	 * 
	 * （2）当我们遇到一个符号时，需要分情况讨论，
	 * 
	 * 			如果是一个左括号，无条件将其压入符号栈
	 * 
	 * 			当遇到右括号时，直到遇到第一个左括号前，将其中的所有符号都弹出
	 * 
	 * 				每当我们弹出一个符号，就将这个符号和其所需要的操作数进行这个符号定义的运算
	 * 
	 * 				需要几个操作数，就从栈顶中取出几个操作数，结果压回栈中
	 * 
	 * 			当遇到一个运算符号时，如果栈为空，则直接压入栈
	 * 
	 * 				若不为空，就比较这个符号和栈顶的符号比较优先级，当是左括号的时候直接压入栈
	 * 
	 * 				如果运算符号比栈顶的符号优先级高的情况下，也压入栈
	 * 				
	 * 				否则直接弹出栈顶符号，将栈顶符号进行运算后比较下一个栈顶符号，直到将当前符号压入栈为止
	 * 
	 * （3）当我们遍历完所有元素后，将符号栈中元素依次弹出并进行计算，最后应该只剩下一个操作数，即结果
	 * 
	 * @param elements
	 * @return
	 */
	public static String calculate(Element[] elements) {
		
		Operand[] operandStack = new Operand[elements.length];
		int operandStackSize = 0;
		
		Symbol[] symbolStack = new Symbol[elements.length];
		int symbolStackSize = 0;
		
		for (Element ele : elements) {
			
			if (ele == null)
				throw new IllegalArgumentException("Element is null");
			
			if (ele instanceof Operand)//操作数直接压入栈
				operandStack[operandStackSize++] = (Operand) ele;
			
			else if (ele instanceof BindingSymbol) {//括号
				
				if (ele == BindingSymbol.LEFT_PARENTHESIS) //左括号直接入栈
					symbolStack[symbolStackSize++] = (Symbol) ele;
				
				else if (ele == BindingSymbol.RIGHT_PARENTHESIS) {//右括号
					while (true) {
						Symbol top = symbolStack[--symbolStackSize];//弹出栈顶符号
						
						if (top == BindingSymbol.LEFT_PARENTHESIS)//弹出第一个左括号时终止
							break;
						
						if (top instanceof OperationalSymbol)//如果是运算符
							operandStackSize -= helper(operandStack, operandStackSize,//就进行运算
									(OperationalSymbol) top);
					}
				} else
					throw new IllegalArgumentException("Unknown binding symbol type");
			}
			
			else if (ele instanceof OperationalSymbol) {//运算符号
				
				while (true) {
					if (symbolStackSize == 0) {//栈为空，直接压入
						symbolStack[symbolStackSize++] = (Symbol) ele;
						break;
					}
					
					Symbol top = symbolStack[symbolStackSize - 1];//获取栈顶符号
					
					if (!(top instanceof OperationalSymbol)) {//栈顶符号不是运算符（即左括号）
						
						symbolStack[symbolStackSize++] = (Symbol) ele;//将当前符号压入
						break;
						
					} else {//是运算符
						
						if (OperationalSymbolPriority.compare((OperationalSymbol) ele,
								(OperationalSymbol) top) > 0) {//如果当前符号比栈顶符号优先级大
							
							symbolStack[symbolStackSize++] = (Symbol) ele;//将当前符号压入
							break;
							
						} else {//如果优先级小或相等，那么栈顶的符号先运算
							
							--symbolStackSize;//弹出栈顶符号
							operandStackSize -= helper(operandStack, operandStackSize,//栈顶符号运算
									(OperationalSymbol) top);
							
							//没有终止循环，即继续基表下一个栈顶符号
						}
					}
				}
			} else
				throw new IllegalArgumentException("Unknown element type");
		}
		
		while (symbolStackSize != 0) {//栈中还有符号
			
			Symbol top = symbolStack[--symbolStackSize];//弹出栈顶符号
			
			if (top instanceof OperationalSymbol)//如果是运算符
				operandStackSize -= helper(operandStack, operandStackSize,//就进行运算
						(OperationalSymbol) top);
		}
		
		if (operandStackSize != 1)//结果应该是1个才对，否则操作数和符号不匹配
			throw new IllegalArgumentException(
					"Operand number isn't compatible with symbol number");
		
		return operandStack[0].value();//返回结果的字符串表示
	}
	
}
