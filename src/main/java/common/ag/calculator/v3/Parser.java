package common.ag.calculator.v3;

import common.ag.calculator.v3.element.Element;
import common.ag.calculator.v3.parser.ElementParser;
import common.ag.calculator.v3.parser.InitializeParser;
import common.ag.calculator.v3.parser.ParserShared;
import common.ag.calculator.v3.parser.exception.ParseException;
import common.ag.calculator.v3.setting.Setting;

/**
 * 元素解析器，负责使用更低层的解析器来解析给定的运算表达式字符串
 * 
 * @author 87663
 *
 */
public final class Parser {
	
	/**
	 * 初始化更低层的解析器
	 */
	static {
		InitializeParser.initializeParser();
	}
	
	/**
	 * 解析表达式字符串，返回表达式的元素表示
	 * 
	 * @param experssion
	 * @return
	 */
	public static Element[] parse(String experssion) {
		
		//将表达式中的空白字符去除
		experssion = experssion.replaceAll("\\s", "");
		
		//初始化一个解析器共享资源，里面包含了一些解析时需要的数据
		ParserShared shared = new ParserShared(experssion);
		
		//解析到最后为止
		while (shared.from[shared.now] < experssion.length()) {
			
			//表示移动到下一个位置，由于存储检查的数组是复用的，而不是有多少个元素就开多长
			//而且也不知道有多少个元素，因此使用这个方法节省空间
			shared.now ^= 1;
			
			//每次首先将这些标记清空
			shared.leftParenthesisCheck[shared.now] = false;
			shared.rightParenthesisCheck[shared.now] = false;
			shared.numberCheck[shared.now] = false;
			shared.operationalSymbolCheck[shared.now] = false;
			
			boolean flag = false;//是否有解析器解析成功
			
			//调用更低层的解析器进行解析，解析符号的优先顺序根据Setting里面的map的存储顺序
			for (ElementParser parser : Setting.parserMaping.values()) {
				if (parser.parse(shared)) {//如果解析成功
					flag = true;
					break;//就跳出循环
				}
			}
			
			if (!flag)//没有解析器解析成功，证明这个表达式中存在不能解析的元素，故需要抛出异常
				throw new ParseException(
						"Unknown string", shared, shared.from[shared.now ^ 1]);
		}
		
		//进行解析器的结束检查，要保证一些符号完整和左右括号相等
		for (ElementParser parser : Setting.parserMaping.values())
			parser.finalCheck(shared);
		
		//结尾不是数字或者右括号，证明这个表达式肯定是不完整的
		if (!(shared.numberCheck[shared.now] || shared.rightParenthesisCheck[shared.now]))
			throw new ParseException("experssion is not end with number or right parenthsis");
		
		return shared.elements.toArray(new Element[shared.elements.size()]);
	}
	
}
