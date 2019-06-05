package common.ag.calculator.v3.parser.sign;

import common.ag.calculator.v3.element.sign.UnaryOperationalSymbol;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

/**
 * 一目运算符解析器，就是运算的操作数只有1个的符号
 * 
 * 有结合性，总是靠近右边操作数的先算
 * 
 * @author 87663
 */
public abstract class UnaryOperationalSymbolParser extends AbstractElementParser {
	
	/**
	 * 一目运算符还是运算符
	 */
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.operationalSymbolCheck[shared.now] = true;
	}
	
	/**
	 * 一目运算符应该可以和后面结合成一整个表达式，所以用表达式的检查法
	 */
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.EXPRESSION_LIKE_CHECKER.check(shared);
	}
	
	/**
	 * 负号解析器 -
	 */
	public final static UnaryOperationalSymbolParser NEGATIVE_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("-", UnaryOperationalSymbol.NEGATIVE_SIGN,
					shared);
		}
	};
	
	/**
	 * 正号解析器 +
	 */
	public final static UnaryOperationalSymbolParser POSITIVE_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("+", UnaryOperationalSymbol.POSITIVE_SIGN,
					shared);
		}
	};
	
	/**
	 * 按位取反号解析器 ~
	 */
	public final static UnaryOperationalSymbolParser BITWISE_NOT_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("~", UnaryOperationalSymbol.BITWISE_NOT_SIGN,
					shared);
		}
	};
	
	/**
	 * 逻辑取反号解析器 !
	 */
	public final static UnaryOperationalSymbolParser LOGICAL_NOT_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("!", UnaryOperationalSymbol.LOGICAL_NOT_SIGN,
					shared);
		}
	};
	
	/**
	 * 强制转化类型为整形号解析器 (int)
	 */
	public final static UnaryOperationalSymbolParser TO_INT_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("(int)", UnaryOperationalSymbol.TO_INT_SIGN,
					shared);
		}
	};
	
	/**
	 * 强制转化类型为浮点型号解析器 (double)
	 */
	public final static UnaryOperationalSymbolParser TO_DOUBLE_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("(double)",
					UnaryOperationalSymbol.TO_DOUBLE_SIGN,
					shared);
		}
	};
	
	/**
	 * 强制转化类型为布尔型号解析器 (boolean)
	 */
	public final static UnaryOperationalSymbolParser TO_BOOLEAN_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("(boolean)",
					UnaryOperationalSymbol.TO_BOOLEAN_SIGN, shared);
		}
	};
	
}
