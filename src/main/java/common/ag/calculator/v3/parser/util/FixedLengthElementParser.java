package common.ag.calculator.v3.parser.util;

import common.ag.calculator.v3.element.Element;
import common.ag.calculator.v3.parser.ParserShared;

/**
 * 固定长度元素解析器
 * 
 * 对于没有分段，且总是长得一样的符号，可以使用如下的解析方式
 * 
 * @author 87663
 *
 */
public class FixedLengthElementParser {
	
	public static boolean parser(String element, Element elementObj, ParserShared shared) {
		
		//剩下的运算表达式的长度比本符号的长度小，那么肯定解析失败
		if (shared.expression.length() < shared.from[shared.now ^ 1] + element.length())
			return false;
		
		//依次比较每个位置上的字符是否一样
		for (int i = 0; i < element.length(); ++i)
			if (shared.expression.charAt(shared.from[shared.now ^ 1] + i) != element.charAt(i))
				return false;
			
		//如果都一样，那么解析成功
		shared.from[shared.now] = shared.from[shared.now ^ 1] + element.length();//设置解析后长度
		shared.elements.add(elementObj);//将解析出来的元素加入
		
		return true;
	}
	
}
