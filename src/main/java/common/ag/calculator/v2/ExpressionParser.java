package common.ag.calculator.v2;

import java.util.Arrays;

public class ExpressionParser {
	
	private ParserShared shared;
	
	private Element[] ans;
	
	private final static Element[] parsers = {
			Number.of(0),//TODO
	};
	
	static {
		Arrays.sort(parsers, (a, b) -> {
			return Element.getPriority(a) - Element.getPriority(b);
		});
	}
	
	public ExpressionParser(String expression) {
		super();
		this.shared = new ParserShared(expression);
	}
	
	public Element[] parseExpression() {
		if (ans == null) {
			
			while (true) {
				if (shared.from[shared.now] >= shared.expression.length())
					break;
				
				shared.now ^= 1;
				
				for (Element parser : parsers)
					if (parser.getElement(shared))
						break;
					
				ans = shared.elements.toArray(new Element[shared.elements.size()]);
			}
		}
		return ans;
	}
	
}
