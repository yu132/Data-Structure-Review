package common.ag.calculator.v3.parser;

public interface ElementParser {
	
	boolean parse(ParserShared shared);
	
	void finalCheck(ParserShared shared);
	
}
