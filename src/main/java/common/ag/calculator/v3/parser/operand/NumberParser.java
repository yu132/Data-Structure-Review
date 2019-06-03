package common.ag.calculator.v3.parser.operand;

import common.ag.calculator.v3.element.operand.DoubleNumber;
import common.ag.calculator.v3.element.operand.IntNumber;
import common.ag.calculator.v3.parser.AbstractElementParser;
import common.ag.calculator.v3.parser.ElementParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.util.ParserChecker;

public class NumberParser extends AbstractElementParser {
	
	public final static ElementParser NUMBER_PARSER = new NumberParser();
	
	private NumberParser() {
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
		int numberLength = 0;
		boolean hasSign = false;
		int pointNumber = 0;
		
		int index = shared.from[shared.now ^ 1];
		
		if (shared.expression.length() <= index)
			return false;
		
		char first = shared.expression.charAt(index);
		
		if (first == '+' || first == '-') {
			++index;
			++numberLength;
			hasSign = true;
		}
		
		for (; index < shared.expression.length();) {
			char c = shared.expression.charAt(index);
			if (c == '.') {
				pointNumber++;
				if (pointNumber == 2)
					break;
			} else if (c >= '0' && c <= '9')
				++numberLength;
			else
				break;
			++index;
		}
		
		if (numberLength == 0)
			return false;
		if (numberLength == 1 && (hasSign || pointNumber == 1))
			return false;
		if (numberLength == 2 && hasSign && pointNumber == 1)
			return false;
		
		if (pointNumber == 0) {
			shared.elements.add(
					IntNumber.valueOf(
							shared.expression.substring(shared.from[shared.now ^ 1], index)));
		} else {
			shared.elements.add(
					DoubleNumber.valueOf(
							shared.expression.substring(shared.from[shared.now ^ 1], index)));
		}
		
		shared.from[shared.now] = index;
		
		return true;
	}
	
}
