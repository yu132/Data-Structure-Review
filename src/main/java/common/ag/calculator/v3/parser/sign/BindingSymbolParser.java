package common.ag.calculator.v3.parser.sign;

import common.ag.calculator.v3.element.sign.BindingSymbol;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.exception.ParseException;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

/**
 * 结合符号解析器，即左右括号
 * 
 * @author 87663
 */
public abstract class BindingSymbolParser extends AbstractElementParser {
	
	/**
	 * 用于检查内部表达式的括号是否合法，即某一刻出现的右括号小于左括号
	 * 
	 * 最终整个表达式的左括号数量应该等于右括号数量
	 * 
	 * @author 87663
	 */
	public static class ExperssionInteriorParenthsisCheck {
		public int numberOfLeftParenthesis = 0;
		public int numberOfRightParenthesis = 0;
	}
	
	/**
	 * 左括号解析器 (
	 */
	public final static BindingSymbolParser LEFT_PARENTHESIS_PARSER = new BindingSymbolParser() {
		
		/**
		 * 左括号是一个表达式的开头，故应该使用表达式的检查法则
		 */
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
			if (!shared.parenthsisCheckers.isEmpty())//对栈顶的符号检查添加上一个左括号计数
				++shared.parenthsisCheckers
						.get(shared.parenthsisCheckers.size() - 1).numberOfLeftParenthesis;
			
			++shared.numberOfLeftParenthesis;//总括号检查加上一个左括号计数
			shared.leftParenthesisCheck[shared.now] = true;//当前解析的是左括号
		}
	};
	
	/**
	 * 右括号解析器 )
	 */
	public final static BindingSymbolParser RIGHT_PARENTHESIS_PARSER = new BindingSymbolParser() {
		
		/**
		 * 右括号前面只能是右括号和操作数，和符号一致
		 */
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
			if (shared.numberOfLeftParenthesis < shared.numberOfRightParenthesis)//检查总的表达式是否合法
				throw new ParseException(
						"right parenthesis is more than left parenthsis", shared,
						shared.from[shared.now ^ 1]);
			
			//需要检查栈顶的内部表达式是否合法
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
			if (!shared.parenthsisCheckers.isEmpty())//栈顶的符号检查加一
				++shared.parenthsisCheckers
						.get(shared.parenthsisCheckers.size() - 1).numberOfLeftParenthesis;
			++shared.numberOfRightParenthesis;//总的符号检查加一
			shared.rightParenthesisCheck[shared.now] = true;//当前是右括号
		}
		
		@Override
		public void finalCheck(ParserShared shared) {
			if (shared.parenthsisCheckers.size() != 0)//没有不完成的内部表达式
				throw new ParseException("interior experssion imcomplete");
			if (shared.numberOfLeftParenthesis != shared.numberOfRightParenthesis)//总的左右括号数量相等
				throw new ParseException(
						"number of left parenthsis is not equal to right parenthsis's");
		}
	};
	
}
