package common.ag.calculator.v3.setting;

import java.util.LinkedHashMap;
import java.util.Map;

import common.ag.calculator.v3.parser.ElementParser;
import common.ag.calculator.v3.parser.StringType;

public class Setting {
	
	/**
	 * 由于浮点运算总会损失精度
	 * 
	 * 那么如果大数类中不支持的运算符
	 * 
	 * 例如对数运算或者小数乘方，那么我们就不得不将其降成普通的数进行运算
	 * 
	 * 那么就会在转换中和运算中都损失精度
	 * 
	 * 对于不允许精度损失的情况，就关闭这个选项，
	 * 
	 * 那么那些运算符在损失精度的情况下都不能使用
	 * 
	 * 而是抛出一个异常
	 * 
	 * 为了防止在不想丢失精度的时候，错误使用会丢失精度的符号
	 */
	public static boolean permissibleLossOfAccuracy = true;
	
	/**
	 * 可插拔的元素解析器，每种符号对应一种解析器，遍历的顺序即解析的优先顺序
	 */
	public static Map<StringType, ElementParser> parserMaping = new LinkedHashMap<>();
	
	/**
	 * 默认的浮点保留精度，如果需要更加精确的计算，请将本值设大，同时将permissibleLossOfAccuracy设为false
	 */
	public static int doubleScale = 10;
	
}
