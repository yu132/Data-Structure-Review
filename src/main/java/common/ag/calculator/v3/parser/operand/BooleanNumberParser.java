package common.ag.calculator.v3.parser.operand;

import common.ag.calculator.v3.element.operand.BooleanNumber;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.util.FixedLengthElementParser;
import common.ag.calculator.v3.parser.util.ParserChecker;

/**
 * 布尔类型解析器 true false
 * 
 * @author 87663
 */
public class BooleanNumberParser extends AbstractElementParser {
	
	public final static ElementParser BOOLEAN_NUMBER_PARSER = new BooleanNumberParser();
	
	private BooleanNumberParser() {
		super();
	}
	
	/**
	 * 布尔类型是一种表达式，故使用表达式的检查
	 */
	@Override
	protected boolean checkBeforeParse(ParserShared shared) {
		return ParserChecker.EXPRESSION_LIKE_CHECKER.check(shared);
	}
	
	@Override
	protected void setSharedAfterParse(ParserShared shared) {
		shared.numberCheck[shared.now] = true;//当前是操作数
	}
	
	@Override
	protected boolean parseHelper(ParserShared shared) {
		return FixedLengthElementParser.parser("true", BooleanNumber.TRUE, shared)
				|| FixedLengthElementParser.parser("false", BooleanNumber.FALSE, shared);
	}
	
}
