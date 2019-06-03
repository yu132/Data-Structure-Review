package common.ag.calculator.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * 数学运算解析器
 * 
 * 能够将表达式解析出来，包括符号 + - * / % ^ ** ( )
 * 
 * 能够去除中间的空格
 * 
 * @author 87663
 *
 */
public class ExpressionParser {
	
	private final static OperationalSymbol[] operationalSymbols = {
			CommonSymbol.POWER, CommonSymbol.ADD, CommonSymbol.SUBTRACT, CommonSymbol.MULTIPLY,
			CommonSymbol.DIVIDE, CommonSymbol.MOD
	};
	
	//	private final static BindingSymbol[] bindingSymbols = {
	//			CommonSymbol.LEFT_PARENTHESIS, CommonSymbol.RIGHT_PARENTHESIS
	//	};
	
	private String expression;
	
	public ExpressionParser(String expression) {
		super();
		this.expression = expression;
	}
	
	private Element[] ans;
	
	private Element next;
	private int[] from = new int[2];
	
	private int numberOfLeftParenthesis = 0;
	private int numberOfRightParenthesis = 0;
	
	private int now = 1;
	
	private boolean[] numberCheck = new boolean[2];
	private boolean[] leftParenthesisCheck = new boolean[2];
	private boolean[] rightParenthesisCheck = new boolean[2];
	private boolean[] operationalSymbolCheck = new boolean[2];
	
	/**
	 * 首先，一个表达式开头的一定是一个数字或者括号，一个运算符号连接两个表达式
	 * 
	 * 括号中也是一个表达式
	 * 
	 * 左括号一定在数字前面，右括号一定在数字后面
	 * 
	 * 总结：
	 * 数字前面要么什么都没有，要么是左括号，要么是运算符
	 * 
	 * 左括号前面要么什么都没有，要么是运算符，要么是左括号
	 * 
	 * 运算符前面要么是数字，要么是右括号
	 * 
	 * 右括号前面要么是右括号，要么是数字
	 * 
	 * 结尾只能是数字或右括号
	 * 
	 * 当前的右括号的数量应该小于等于左括号
	 * 
	 * 最后右括号的数量应该等于左括号
	 * 
	 * @param expression
	 * @return
	 */
	public Element[] parseExpression() {
		
		if (ans != null)
			return ans;
		
		List<Element> elements = new ArrayList<>();
		
		while (from[now] < expression.length())
			elements.add(next());
		
		Element last = elements.get(elements.size() - 1);
		
		if (!(last instanceof Number) && !(last == CommonSymbol.RIGHT_PARENTHESIS))//结尾必须是数字或者右括号
			throw new IllegalArgumentException(
					"Expression illegal from: " + expression.substring(from[now ^ 1]));
		
		if (numberOfLeftParenthesis != numberOfRightParenthesis)//左右括号的数量必须相等
			throw new IllegalArgumentException(
					"Expression illegal : number of left parenthesis is not equal to right parenthesis");
		
		ans = elements.toArray(new Element[elements.size()]);
		
		return ans;
	}
	
	private void jumpSpace() {
		while (true) {
			char c = expression.charAt(from[now ^ 1]);
			if (c != ' ' || c != '\t')
				break;
			++from[now ^ 1];
		}
	}
	
	private Element next() {
		
		now ^= 1;
		
		jumpSpace();
		
		if (!numberCheck[now ^ 1] && !rightParenthesisCheck[now ^ 1]) {//数字和左括号前面不可能是数字和右括号，其余均有可能
			if (numberCheck[now] = hasNextNumber()) {
				leftParenthesisCheck[now] = false;
				rightParenthesisCheck[now] = false;
				operationalSymbolCheck[now] = false;
				return next;
			}
			if (leftParenthesisCheck[now] = hasNextLeftParenthesis()) {
				rightParenthesisCheck[now] = false;
				operationalSymbolCheck[now] = false;
				return next;
			}
		} else {
			numberCheck[now] = false;
			leftParenthesisCheck[now] = false;
		}
		
		if (numberCheck[now ^ 1] || rightParenthesisCheck[now ^ 1]) {//运算符的前面要么是数字，要么是右括号，其余均不对，也不能为空
			if (rightParenthesisCheck[now] = hasNextRightParenthesis()) {
				operationalSymbolCheck[now] = false;
				return next;
			}
			if (operationalSymbolCheck[now] = hasNextOperationalSymbol())
				return next;
		} else {
			rightParenthesisCheck[now] = false;
			operationalSymbolCheck[now] = false;
		}
		
		throw new IllegalArgumentException(
				"Expression illegal from: " + expression.substring(from[now]));
	}
	
	private boolean hasNextNumber() {
		int numberLength = 0;
		boolean hasSign = false;
		int isMinus = 1;
		
		int num = 0;
		
		for (int i = from[now ^ 1]; i < expression.length(); ++i) {
			char c = expression.charAt(i);
			if (c >= '0' && c <= '9') {
				++numberLength;
				num = num * 10 + (c - '0');
			} else if (c == '+' || c == '-') {
				if (i == from[now ^ 1]) {
					++numberLength;
					hasSign = true;
					if (c == '-')
						isMinus = -1;
				} else
					break;
			} else
				break;
		}
		
		if (numberLength == 0)
			return false;
		if (numberLength == 1 && hasSign)
			return false;
		
		from[now] = from[now ^ 1] + numberLength;
		next = Number.of(num * isMinus);
		
		return true;
	}
	
	private boolean hasNextLeftParenthesis() {
		if (CommonSymbol.LEFT_PARENTHESIS.isSymbol(expression, from[now ^ 1]) > 0) {
			next = CommonSymbol.LEFT_PARENTHESIS;
			from[now] = from[now ^ 1] + 1;
			++numberOfLeftParenthesis;
			return true;
		}
		return false;
	}
	
	private boolean hasNextRightParenthesis() {
		if (CommonSymbol.RIGHT_PARENTHESIS.isSymbol(expression, from[now ^ 1]) > 0) {
			next = CommonSymbol.RIGHT_PARENTHESIS;
			from[now] = from[now ^ 1] + 1;
			++numberOfRightParenthesis;
			
			if (numberOfLeftParenthesis > numberOfLeftParenthesis)
				throw new IllegalArgumentException(
						"Expression illegal :  number of right parenthesis is greater than left parenthesis :"
								+ expression.substring(0, from[now]));
			
			return true;
		}
		return false;
	}
	
	private boolean hasNextOperationalSymbol() {
		for (OperationalSymbol sym : operationalSymbols) {
			int len = sym.isSymbol(expression, from[now ^ 1]);
			if (len != -1) {
				next = sym;
				from[now] = from[now ^ 1] + len;
				return true;
			}
		}
		return false;
	}
	
}
