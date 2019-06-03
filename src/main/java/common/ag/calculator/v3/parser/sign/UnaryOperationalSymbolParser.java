package common.ag.calculator.v3.parser.sign;

import common.ag.calculator.v3.element.sign.UnaryOperationalSymbol;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

public abstract class UnaryOperationalSymbolParser extends AbstractElementParser {
	
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.operationalSymbolCheck[shared.now] = true;
	}
	
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.EXPRESSION_LIKE_CHECKER.check(shared);
	}
	
	public final static UnaryOperationalSymbolParser NEGATIVE_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("-", UnaryOperationalSymbol.NEGATIVE_SIGN,
					shared);
		}
	};
	
	public final static UnaryOperationalSymbolParser POSITIVE_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("+", UnaryOperationalSymbol.POSITIVE_SIGN,
					shared);
		}
	};
	
	public final static UnaryOperationalSymbolParser BITWISE_NOT_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("~", UnaryOperationalSymbol.BITWISE_NOT_SIGN,
					shared);
		}
	};
	
	public final static UnaryOperationalSymbolParser LOGICAL_NOT_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("!", UnaryOperationalSymbol.LOGICAL_NOT_SIGN,
					shared);
		}
	};
	
	public final static UnaryOperationalSymbolParser TO_INT_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("(int)", UnaryOperationalSymbol.TO_INT_SIGN,
					shared);
		}
	};
	
	public final static UnaryOperationalSymbolParser TO_DOUBLE_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("(double)", UnaryOperationalSymbol.TO_DOUBLE_SIGN,
					shared);
		}
	};
	
	public final static UnaryOperationalSymbolParser TO_BOOLEAN_SIGN_PARSER = new UnaryOperationalSymbolParser() {
		@Override
		protected boolean parseHelper(ParserShared shared) {
			return FixedLengthElementParser.parser("(boolean)",
					UnaryOperationalSymbol.TO_BOOLEAN_SIGN, shared);
		}
	};
	
}
