package common.ag.calculator.v3.parser.sign;

import common.ag.calculator.v3.element.sign.BindingSymbol;
import common.ag.calculator.v3.element.sign.TernaryOperationalSymbol;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.exception.ParseException;
import common.ag.calculator.v3.parser.sign.BindingSymbolParser.ExperssionInteriorParenthsisCheck;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

/**
 * 三目运算符解析器，目前只有状态选择运算符
 * 
 * @author 87663
 *
 */
public abstract class TernaryOperationalSymbolParser extends AbstractElementParser {
	
	/**
	 * 三目运算符形如e1?e2:e3
	 * 
	 * 符号前面都应该是表达式样式的
	 * 
	 * 故使用符号样式的检查
	 */
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.SYMBOL_LIKE_CHECKER.check(shared);
	}
	
	/**
	 * 三目运算符是运算符号
	 */
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.operationalSymbolCheck[shared.now] = true;
	}
	
	/**
	 * 状态选择运算符解析器  ?:  
	 * 
	 * 对于运算表达式  (e1?e2:e3) 若e1==true  则运算结果为e2  若e1==false  则运算结果为e3
	 * 
	 * 另注意：
	 * 
	 * 为什么要在问号的时候插入3个括号，而遇到冒号的时候插入右括号和CONDITIONAL_SIGN呢？
	 * 
	 * 是因为原理的表达式形如    (e1?e2:e3)
	 * 
	 * 最后翻译完是((e1) (e2) CONDITIONAL_SIGN e3)
	 * 
	 * 为什么必须在e1和e2上加上括号呢
	 * 
	 * 我举个例子 e1=1+2  e2=3
	 * 
	 * 那么根据运算法则，我们将1压入，然后+压入，然后2、3压入，遇到CONDITIONAL_SIGN，比+的优先级低
	 * 
	 * 然后是“2和3”做加法，而不是1和2，这是错误的
	 * 
	 * 但是我们加上括号之后，e1，e2，就会被括号结合，分别计算，这样应该就没有问题了
	 * 
	 * 还有一点需要特别注意的是e2是内部表达式，不能在e2内部有更多的右括号先出现
	 */
	public final static TernaryOperationalSymbolParser CONDITIONAL_SIGN_PARSER = new TernaryOperationalSymbolParser() {
		
		@Override
		protected boolean parseHelper(ParserShared shared) {
			if (FixedLengthElementParser.parser("?", BindingSymbol.RIGHT_PARENTHESIS,
					shared)) {//如果能解析成?，则添加一个右括号
				
				++shared.condidtionalSignCount;//不完整计数加一
				
				boolean flag = false;//是否补到了括号
				
				for (int i = shared.elements.size() - 1; i >= 0; --i) {
					if (shared.elements.get(i) == BindingSymbol.LEFT_PARENTHESIS) {//想前找到第一个左括号
						shared.elements.add(i, BindingSymbol.LEFT_PARENTHESIS);//再添加一个左括号
						flag = true;//补到了左括号
						break;
					}
				}
				
				if (!flag) {//如果没补到左括号
					shared.elements.add(0, BindingSymbol.LEFT_PARENTHESIS);//就在表达式开头补上左括号
				}
				
				//在右括号后面加上一个左括号 目前是((e1)(... 或 (e1)(... ; 
				//...应该是e2) CONDITION_SIGN e3) 或 e2) CONDITION_SIGN e3
				//但当前还没解析那么多
				shared.elements.add(BindingSymbol.LEFT_PARENTHESIS);
				
				//因为e2是内部表达式，故添加一个括号检查
				shared.parenthsisCheckers.add(new ExperssionInteriorParenthsisCheck());
				
				return true;
				
			} else if (FixedLengthElementParser.parser(":",
					BindingSymbol.RIGHT_PARENTHESIS, shared)) {//如果匹配到了:,先加入一个右括号
				
				//然后再加入一个CONDITIONAL_SIGN
				//此时是((e1)(e2) CONDITION_SIGN 或 (e1)(e2) CONDITION_SIGN
				//后面的e3其实和这个符号就没啥关系了
				shared.elements.add(TernaryOperationalSymbol.CONDITIONAL_SIGN);
				
				--shared.condidtionalSignCount;//不完整计数减一
				
				if (shared.condidtionalSignCount < 0)//如果当前:比?多
					throw new ParseException(": is more than ?", shared,
							shared.from[shared.now ^ 1]);
				
				//e2已经完整，故需要去除一个内部表达式的括号检查
				ExperssionInteriorParenthsisCheck checker = shared.parenthsisCheckers
						.remove(shared.parenthsisCheckers.size() - 1);
				
				//但是还需要对内部表达式内的左右括号检查一下数量是否相等
				if (checker.numberOfLeftParenthesis != checker.numberOfRightParenthesis)
					throw new ParseException(
							"right parenthesis is not equal to left parenthsis", shared,
							shared.from[shared.now ^ 1]);
				
				return true;
			}
			
			return false;
		}
		
		/**
		 * 最终检查需要保证?和:一样多
		 */
		@Override
		public void finalCheck(ParserShared shared) {
			if (shared.condidtionalSignCount != 0)
				throw new ParseException("conditional sign imcomplete");
		}
		
	};
	
}
