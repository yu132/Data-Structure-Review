package common.ag.calculator.v3.parser.sign;

import common.ag.calculator.v3.element.sign.BindingSymbol;
import common.ag.calculator.v3.element.sign.TernaryOperationalSymbol;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.exception.ParseException;
import common.ag.calculator.v3.parser.sign.BindingSymbolParser.ExperssionInteriorParenthsisCheck;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

public abstract class TernaryOperationalSymbolParser extends AbstractElementParser {
	
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.SYMBOL_LIKE_CHECKER.check(shared);
	}
	
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.operationalSymbolCheck[shared.now] = true;
	}
	
	/**
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
	 * 那么根据运算法则，我们将1压入，然后+压入，然后23压入，遇到CONDITIONAL_SIGN，比+的优先级低
	 * 
	 * 然后是“2和3”做加法，而不是1和2，这是错误的
	 * 
	 * 但是我们加上括号之后，e1，e2，就会被括号限制先算出来，这样应该就没有问题了
	 * 
	 * 还有一点需要特别注意的是e2是内部表达式，不能在e2内部有更多的右括号先出现
	 */
	public final static TernaryOperationalSymbolParser CONDITIONAL_SIGN_PARSER = new TernaryOperationalSymbolParser() {
		
		@Override
		protected boolean parseHelper(ParserShared shared) {
			if (FixedLengthElementParser.parser("?", BindingSymbol.RIGHT_PARENTHESIS,
					shared)) {
				
				++shared.condidtionalSignCount;
				
				boolean flag = false;
				
				for (int i = shared.elements.size() - 1; i >= 0; --i) {
					if (shared.elements.get(i) == BindingSymbol.LEFT_PARENTHESIS) {
						shared.elements.add(i, BindingSymbol.LEFT_PARENTHESIS);
						flag = true;
						break;
					}
				}
				
				if (!flag) {
					shared.elements.add(0, BindingSymbol.LEFT_PARENTHESIS);
				}
				
				shared.elements.add(BindingSymbol.LEFT_PARENTHESIS);
				
				shared.parenthsisCheckers.add(new ExperssionInteriorParenthsisCheck());
				
				return true;
				
			} else if (FixedLengthElementParser.parser(":",
					BindingSymbol.RIGHT_PARENTHESIS, shared)) {
				
				shared.elements.add(TernaryOperationalSymbol.CONDITIONAL_SIGN);
				
				--shared.condidtionalSignCount;
				
				if (shared.condidtionalSignCount < 0)
					throw new ParseException(": is more than ?", shared,
							shared.from[shared.now ^ 1]);
				
				ExperssionInteriorParenthsisCheck checker = shared.parenthsisCheckers
						.remove(shared.parenthsisCheckers.size() - 1);
				
				if (checker.numberOfLeftParenthesis != checker.numberOfRightParenthesis)
					throw new ParseException(
							"right parenthesis is not equal to left parenthsis", shared,
							shared.from[shared.now ^ 1]);
				
				return true;
			}
			
			return false;
		}
		
		@Override
		public void finalCheck(ParserShared shared) {
			if (shared.condidtionalSignCount != 0)
				throw new ParseException("conditional sign imcomplete");
		}
		
	};
	
}
