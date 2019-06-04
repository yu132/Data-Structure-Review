package common.ag.calculator.v3.parser.exception;

import common.ag.calculator.v3.parser.ParserShared;

public class ParseException extends RuntimeException {
	
	private static final long serialVersionUID = -8314729961427444690L;
	
	public ParseException(String reason) {
		super("Parse exception occur because: " + reason);
	}
	
	public ParseException(String reason, ParserShared shared, int from) {
		super("Parse exception occur because: " + reason + "\n"
				+ "It's from : " + shared.expression.substring(from));
	}
	
	public ParseException(String reason, ParserShared shared, int from, int to) {
		super("Parse exception occur because: " + reason + "\n"
				+ "It's from : " + shared.expression.substring(from, to));
	}
	
}
