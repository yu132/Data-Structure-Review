package common.ag.calculator.v3.parser.util;

import common.ag.calculator.v3.element.Element;
import common.ag.calculator.v3.parser.ParserShared;

public class FixedLengthElementParser {
	
	public static boolean parser(String element, Element elementObj, ParserShared shared) {
		if (shared.expression.length() < shared.from[shared.now ^ 1] + element.length())
			return false;
		
		for (int i = 0; i < element.length(); ++i)
			if (shared.expression.charAt(shared.from[shared.now ^ 1] + i) != element.charAt(i))
				return false;
			
		shared.from[shared.now] = shared.from[shared.now ^ 1] + element.length();
		shared.elements.add(elementObj);
		
		return true;
	}
	
}
