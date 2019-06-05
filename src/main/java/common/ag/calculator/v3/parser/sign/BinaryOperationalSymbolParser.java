package common.ag.calculator.v3.parser.sign;

import common.ag.calculator.v3.element.sign.BinaryOperationalSymbol;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

/**
 * 二目运算符解析器
 * 
 * @author 87663
 *
 */
public abstract class BinaryOperationalSymbolParser extends AbstractElementParser {
	
	/**
	 * 一般的二目运算符夹在两个操作数之间，所以是符号检查
	 * 
	 * 当然可以定义如同操作数一般的二目符号，不过数量少，需要的时候再重载就可以了
	 */
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.SYMBOL_LIKE_CHECKER.check(shared);
	}
	
	/**
	 * 当前是运算符号
	 */
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.operationalSymbolCheck[shared.now] = true;
	}
	
	/**
	 * 大于号解析器 >
	 */
	public final static BinaryOperationalSymbolParser GREATER_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser(">", BinaryOperationalSymbol.GREATER_SIGN,
					shared);
		}
	};
	
	/**
	 * 大于等于号解析器 >=
	 */
	public final static BinaryOperationalSymbolParser GREATER_EQUAL_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser(">=", BinaryOperationalSymbol.GREATER_EQUAL_SIGN,
					shared);
		}
	};
	
	/**
	 * 小于号解析器 <
	 */
	public final static BinaryOperationalSymbolParser LITTLE_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<", BinaryOperationalSymbol.LITTLE_SIGN,
					shared);
		}
	};
	
	/**
	 * 小于等于号解析器 <=
	 */
	public final static BinaryOperationalSymbolParser LITTLE_EQUAL_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<=", BinaryOperationalSymbol.LITTLE_EQUAL_SIGN,
					shared);
		}
	};
	
	/**
	 * 等于号解析器 ==
	 */
	public final static BinaryOperationalSymbolParser EQUAL_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("==", BinaryOperationalSymbol.EQUAL_SIGN,
					shared);
		}
	};
	
	/**
	 * 不等于号解析器 !=
	 */
	public final static BinaryOperationalSymbolParser NOT_EQUAL_SIGN_PARSER_1 = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("!=", BinaryOperationalSymbol.NOT_EQUAL_SIGN,
					shared);
		}
	};
	
	/**
	 * 不等于号解析器 <>
	 */
	public final static BinaryOperationalSymbolParser NOT_EQUAL_SIGN_PARSER_2 = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<>", BinaryOperationalSymbol.NOT_EQUAL_SIGN,
					shared);
		}
	};
	
	/**
	 * 按位或号解析器 |
	 */
	public final static BinaryOperationalSymbolParser BITWISE_OR_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("|", BinaryOperationalSymbol.BITWISE_OR_SIGN,
					shared);
		}
	};
	
	/**
	 * 按位与号解析器 &
	 */
	public final static BinaryOperationalSymbolParser BITWISE_AND_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("&", BinaryOperationalSymbol.BITWISE_AND_SIGN,
					shared);
		}
	};
	
	/**
	 * 按位异或号解析器 ^
	 */
	public final static BinaryOperationalSymbolParser BITWISE_XOR_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("^", BinaryOperationalSymbol.BITWISE_XOR_SIGN,
					shared);
		}
	};
	
	/**
	 * 按位左移号解析器 <<
	 */
	public final static BinaryOperationalSymbolParser BITWISE_LEFT_SHIFT_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<<",
					BinaryOperationalSymbol.BITWISE_LEFT_SHIFT_SIGN, shared);
		}
	};
	
	/**
	 * 按位右移号解析器 >>
	 */
	public final static BinaryOperationalSymbolParser BITWISE_RIGHT_SHIFT_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser(">>",
					BinaryOperationalSymbol.BITWISE_RIGHT_SHIFT_SIGN, shared);
		}
	};
	
	/**
	 * 逻辑或号解析器 ||
	 */
	public final static BinaryOperationalSymbolParser LOGICAL_OR_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("||",
					BinaryOperationalSymbol.LOGICAL_OR_SIGN, shared);
		}
	};
	
	/**
	 * 逻辑与号解析器 &&
	 */
	public final static BinaryOperationalSymbolParser LOGICAL_AND_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("&&",
					BinaryOperationalSymbol.LOGICAL_AND_SIGN, shared);
		}
	};
	
	/**
	 * 加号解析器 +
	 */
	public final static BinaryOperationalSymbolParser ADD_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("+",
					BinaryOperationalSymbol.ADD_SIGN, shared);
		}
	};
	
	/**
	 * 减号解析器 -
	 */
	public final static BinaryOperationalSymbolParser SUBTRACT_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("-",
					BinaryOperationalSymbol.SUBTRACT_SIGN, shared);
		}
	};
	
	/**
	 * 乘号解析器 *
	 */
	public final static BinaryOperationalSymbolParser MULTIPLY_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("*",
					BinaryOperationalSymbol.MULTIPLY_SIGN, shared);
		}
	};
	
	/**
	 * 除号解析器 /
	 */
	public final static BinaryOperationalSymbolParser DIVIDE_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("/",
					BinaryOperationalSymbol.DIVIDE_SIGN, shared);
		}
	};
	
	/**
	 * 整除号解析器 //
	 */
	public final static BinaryOperationalSymbolParser FLOOR_DIVIDE_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("//",
					BinaryOperationalSymbol.FLOOR_DIVIDE_SIGN, shared);
		}
	};
	
	/**
	 * 求模号解析器 %
	 */
	public final static BinaryOperationalSymbolParser MODULO_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("%",
					BinaryOperationalSymbol.MODULO_SIGN, shared);
		}
	};
	
	/**
	 * 乘方号解析器 **
	 */
	public final static BinaryOperationalSymbolParser POWER_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("**",
					BinaryOperationalSymbol.POWER_SIGN, shared);
		}
	};
	
	/**
	 * 对数号解析器 LOG
	 */
	public final static BinaryOperationalSymbolParser LOGARITHM_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("LOG",
					BinaryOperationalSymbol.LOGARITHM_SIGN, shared);
		}
	};
	
}
