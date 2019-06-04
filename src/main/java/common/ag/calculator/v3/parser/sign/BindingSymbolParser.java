package common.ag.calculator.v3.parser.sign;

import common.ag.calculator.v3.element.sign.BindingSymbol;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.exception.ParseException;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

public abstract class BindingSymbolParser extends AbstractElementParser {
	
	/**
	 * 用于检查内部表达式的括号是否合法，即某一刻出现的右括号小于左括号
	 * 
	 * 最终整个表达式的左括号数量应该等于右括号数量
	 * 
	 * @author 87663
	 *
	 */
	public static class ExperssionInteriorParenthsisCheck {
		public int numberOfLeftParenthesis = 0;
		public int numberOfRightParenthesis = 0;
	}
	
	public final static BindingSymbolParser LEFT_PARENTHESIS_PARSER = new BindingSymbolParser() {
		
		@Override
		protected boolean checkBeforeParse(ParserShared shared) {
			return ParserChecker.EXPRESSION_LIKE_CHECKER.check(shared);
		}
		
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("(", BindingSymbol.LEFT_PARENTHESIS, shared);
		}
		
		@Override
		protected void setSharedAfterParse(ParserShared shared) {
			if (!shared.parenthsisCheckers.isEmpty())
				++shared.parenthsisCheckers
						.get(shared.parenthsisCheckers.size() - 1).numberOfLeftParenthesis;
			
			++shared.numberOfLeftParenthesis;
			shared.leftParenthesisCheck[shared.now] = true;
		}
	};
	
	public final static BindingSymbolParser RIGHT_PARENTHESIS_PARSER = new BindingSymbolParser() {
		
		@Override
		protected boolean checkBeforeParse(ParserShared shared) {
			return ParserChecker.SYMBOL_LIKE_CHECKER.check(shared);
		}
		
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser(")", BindingSymbol.RIGHT_PARENTHESIS,
					shared);
		}
		
		@Override
		protected void checkAfterParse(ParserShared shared) {
			if (shared.numberOfLeftParenthesis < shared.numberOfRightParenthesis)
				throw new ParseException(
						"right parenthesis is more than left parenthsis", shared,
						shared.from[shared.now ^ 1]);
			if (!shared.parenthsisCheckers.isEmpty()
					&& shared.parenthsisCheckers.get(shared.parenthsisCheckers.size()
							- 1).numberOfLeftParenthesis < shared.parenthsisCheckers
									.get(shared.parenthsisCheckers.size()
											- 1).numberOfRightParenthesis)
				throw new ParseException(
						"right parenthesis is more than left parenthsis", shared,
						shared.from[shared.now ^ 1]);
		}
		
		@Override
		protected void setSharedAfterParse(ParserShared shared) {
			if (!shared.parenthsisCheckers.isEmpty())
				++shared.parenthsisCheckers
						.get(shared.parenthsisCheckers.size() - 1).numberOfLeftParenthesis;
			++shared.numberOfRightParenthesis;
			shared.rightParenthesisCheck[shared.now] = true;
		}
		
		@Override
		public void finalCheck(ParserShared shared) {
			if (shared.parenthsisCheckers.size() != 0)
				throw new ParseException("interior experssion imcomplete");
			if (shared.numberOfLeftParenthesis != shared.numberOfRightParenthesis)
				throw new ParseException(
						"number of left parenthsis is not equal to right parenthsis's");
		}
	};
	
}
