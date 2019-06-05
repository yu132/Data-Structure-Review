package common.ag.calculator.v3.parser.operand;

import common.ag.calculator.v3.element.operand.DoubleNumber;
import common.ag.calculator.v3.element.operand.IntNumber;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.util.ParserChecker;

/**
 * 数字解析器，无论是整形还是浮点都是用这个解析器解析
 * 
 * @author 87663
 *
 */
public class NumberParser extends AbstractElementParser {
	
	public final static ElementParser NUMBER_PARSER = new NumberParser();
	
	private NumberParser() {
		super();
	}
	
	/**
	 * 数字是表达式，用表达式方法检查
	 */
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.EXPRESSION_LIKE_CHECKER.check(shared);
	}
	
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.numberCheck[shared.now] = true;//当前是操作数
	}
	
	/**
	 * 解析数字
	 */
	@Override
	protected boolean parseHelper(ParserShared shared) {
		int numberLength = 0;//数字总长，包括正负号（如果有的话）
		boolean hasSign = false;//是否有符号
		int pointNumber = 0;//小数点的个数
		
		int index = shared.from[shared.now ^ 1];//解析到的位置
		
		if (shared.expression.length() <= index)//没有可解析的内容
			return false;
		
		char first = shared.expression.charAt(index);
		
		if (first == '+' || first == '-') {//是正号或者负号
			++index;
			++numberLength;
			hasSign = true;
		}
		
		for (; index < shared.expression.length();) {
			char c = shared.expression.charAt(index);
			if (c == '.') {//如果是小数点
				pointNumber++;
				if (pointNumber == 2)//小数点等于两个的时候，认为其不是数字的部分
					break;
			} else if (c >= '0' && c <= '9')//是0-9
				++numberLength;
			else//否则不是数字
				break;
			++index;
		}
		
		if (numberLength == 0)//没有长度，肯定不是数字
			return false;
		if (numberLength == 1 && (hasSign || pointNumber == 1))//只有一个正负号或者小数点，不是数字
			return false;
		if (numberLength == 2 && hasSign && pointNumber == 1)//只有正负号和小数点，不是数字
			return false;
		
		if (pointNumber == 0) {//没有小数点，那么应该是整形
			shared.elements.add(
					IntNumber.valueOf(
							shared.expression.substring(shared.from[shared.now ^ 1], index)));
			
		} else {//有小数点，应该是浮点型
			shared.elements.add(
					DoubleNumber.valueOf(
							shared.expression.substring(shared.from[shared.now ^ 1], index)));
		}
		
		shared.from[shared.now] = index;
		
		return true;
	}
	
}
