package common.ag.calculator.v3.parser.util;

import common.ag.calculator.v3.parser.ParserShared;

public interface ParserChecker {
	
	boolean check(ParserShared shared);
	
	/**
	 * 形如表达式的检查，类似于表达式的符号有
	 * 
	 * 操作数（本身就是表达式），左括号（以左括号为起点到右括号是一个完整的表达式）
	 * 单目运算符（单目运算符和后面的表达式结合成一个大表达式，表示在这个表达式上做完单目运算后的结果）
	 * 后置操作数的双目运算符和多目运算符（类似于函数表达式，例如POW(x,y)这样的，整体表示一个数，即表达式）
	 * 
	 * 只需要保证前面不是数字和右括号即可
	 */
	public final static ParserChecker EXPRESSION_LIKE_CHECKER = new ParserChecker() {
		
		@Override
		public boolean check(ParserShared shared) {
			return !shared.numberCheck[shared.now ^ 1]
					&& !shared.rightParenthesisCheck[shared.now ^ 1];
		}
		
	};
	
	/**
	 * 形如一般运算符号的检查
	 * 
	 * 只能用于一般的运算符号（一般的双目和多目运算符）和右括号
	 * 
	 * 需要保证前面是操作数或者右括号
	 */
	public final static ParserChecker SYMBOL_LIKE_CHECKER = new ParserChecker() {
		
		@Override
		public boolean check(ParserShared shared) {
			return shared.numberCheck[shared.now ^ 1]
					|| shared.rightParenthesisCheck[shared.now ^ 1];
		}
		
	};
	
}
