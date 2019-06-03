package common.ag.calculator.v3.parser.operand;

import common.ag.calculator.v3.element.operand.BooleanNumber;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

public class BooleanNumberParser extends AbstractElementParser {
	
	public final static ElementParser BOOLEAN_NUMBER_PARSER = new BooleanNumberParser();
	
	private BooleanNumberParser() {
		super();
	}
	
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.EXPRESSION_LIKE_CHECKER.check(shared);
	}
	
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.numberCheck[shared.now] = true;
	}
	
	@Override
	protected boolean parseHelper(ParserShared shared) {
		return FixedLengthElementParser.parser("true", BooleanNumber.TRUE, shared)
				|| FixedLengthElementParser.parser("false", BooleanNumber.FALSE, shared);
		
	}
	
}
