package common.ag.calculator.v3.parser;

/**
 * 元素解析器，包含解析功能和最后的检查功能
 * 
 * @author 87663
 *
 */
public interface ElementParser {
	
	boolean parse(ParserShared shared);
	
	void finalCheck(ParserShared shared);
	
}
