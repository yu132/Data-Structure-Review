package common.ag.calculator.v3;

import common.ag.calculator.v3.element.Element;
import common.ag.calculator.v3.parser.ElementParser;
import common.ag.calculator.v3.parser.InitializeParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.exception.ParseException;
import common.ag.calculator.v3.setting.Setting;

public final class Parser {
	
	static {
		InitializeParser.initializeParser();
	}
	
	public static Element[] parse(String experssion) {
		
		ParserShared shared = new ParserShared(experssion);
		
		while (shared.from[shared.now] < experssion.length()) {
			
			shared.now ^= 1;
			
			shared.leftParenthesisCheck[shared.now] = false;
			shared.rightParenthesisCheck[shared.now] = false;
			shared.numberCheck[shared.now] = false;
			shared.operationalSymbolCheck[shared.now] = false;
			
			boolean flag = false;
			for (ElementParser parser : Setting.parserMaping.values()) {
				if (parser.parse(shared)) {
					flag = true;
					break;
				}
			}
			
			if (!flag)
				throw new ParseException(
						"Unknown string", shared, shared.from[shared.now ^ 1]);
		}
		
		for (ElementParser parser : Setting.parserMaping.values())
			parser.finalCheck(shared);
		
		//结尾不是数字或者括号
		if (!(shared.numberCheck[shared.now] || shared.rightParenthesisCheck[shared.now]))
			throw new ParseException("experssion is not end with number or right parenthsis");
		
		return shared.elements.toArray(new Element[shared.elements.size()]);
	}
	
}
