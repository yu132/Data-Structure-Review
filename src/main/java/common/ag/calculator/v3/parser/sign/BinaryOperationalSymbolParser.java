package common.ag.calculator.v3.parser.sign;

import common.ag.calculator.v3.element.sign.BinaryOperationalSymbol;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

public abstract class BinaryOperationalSymbolParser extends AbstractElementParser {
	
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.SYMBOL_LIKE_CHECKER.check(shared);
	}
	
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.operationalSymbolCheck[shared.now] = true;
	}
	
	public final static BinaryOperationalSymbolParser GREATER_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser(">", BinaryOperationalSymbol.GREATER_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser GREATER_EQUAL_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser(">=", BinaryOperationalSymbol.GREATER_EQUAL_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser LITTLE_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<", BinaryOperationalSymbol.LITTLE_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser LITTLE_EQUAL_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<=", BinaryOperationalSymbol.LITTLE_EQUAL_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser EQUAL_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("==", BinaryOperationalSymbol.EQUAL_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser NOT_EQUAL_SIGN_PARSER_1 = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("!=", BinaryOperationalSymbol.NOT_EQUAL_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser NOT_EQUAL_SIGN_PARSER_2 = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<>", BinaryOperationalSymbol.NOT_EQUAL_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser BITWISE_OR_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("|", BinaryOperationalSymbol.BITWISE_OR_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser BITWISE_AND_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("&", BinaryOperationalSymbol.BITWISE_AND_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser BITWISE_XOR_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("^", BinaryOperationalSymbol.BITWISE_XOR_SIGN,
					shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser BITWISE_LEFT_SHIFT_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<<",
					BinaryOperationalSymbol.BITWISE_LEFT_SHIFT_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser BITWISE_RIGHT_SHIFT_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("<<",
					BinaryOperationalSymbol.BITWISE_RIGHT_SHIFT_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser LOGICAL_OR_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("||",
					BinaryOperationalSymbol.LOGICAL_OR_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser LOGICAL_AND_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("&&",
					BinaryOperationalSymbol.LOGICAL_AND_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser ADD_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("+",
					BinaryOperationalSymbol.ADD_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser SUBTRACT_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("-",
					BinaryOperationalSymbol.SUBTRACT_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser MULTIPLY_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("*",
					BinaryOperationalSymbol.MULTIPLY_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser DIVIDE_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("/",
					BinaryOperationalSymbol.DIVIDE_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser FLOOR_DIVIDE_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("//",
					BinaryOperationalSymbol.FLOOR_DIVIDE_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser MODULO_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("%",
					BinaryOperationalSymbol.MODULO_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser POWER_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("**",
					BinaryOperationalSymbol.POWER_SIGN, shared);
		}
	};
	
	public final static BinaryOperationalSymbolParser LOGARITHM_SIGN_PARSER = new BinaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("LOG",
					BinaryOperationalSymbol.LOGARITHM_SIGN, shared);
		}
	};
	
}
