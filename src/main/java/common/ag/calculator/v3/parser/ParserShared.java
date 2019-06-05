package common.ag.calculator.v3.parser;

import java.util.ArrayList;
import java.util.List;

import common.ag.calculator.v3.element.Element;
import common.ag.calculator.v3.parser.sign.BindingSymbolParser.ExperssionInteriorParenthsisCheck;

/**
 * 解析器共享资源，用于在解析器之间传递信息
 * 
 * 同时也只保留了上一次解析的情况，即只有两个位置
 * 
 * @author 87663
 */
public class ParserShared {
	
	public String expression;//需要解析的运算表达式
	
	public List<Element> elements = new ArrayList<>();//解析得到的元素
	
	public int now = 1;//当前的数组位置
	
	public int numberOfLeftParenthesis = 0;//左括号的数量
	public int numberOfRightParenthesis = 0;//右括号的数量
	
	//[now]表示当前情况的存储位置，[now^1]表示上一次的情况
	public boolean[] numberCheck = new boolean[2];//是不是数字
	public boolean[] leftParenthesisCheck = new boolean[2];//是不是左括号
	public boolean[] rightParenthesisCheck = new boolean[2];//是不是右括号
	public boolean[] operationalSymbolCheck = new boolean[2];//是不是运算符号
	
	public int[] from = new int[2];//当前开始的位置
	
	public int condidtionalSignCount = 0;//三目状态运算符的完整性检查，几表示当前还有几个运算符不完整
	
	//内部表达式的括号检查，由于内部表达式和最外面的表达式有一样的需要检查的括号情况，其实可以和上面的左右括号合并在一起
	//但是其实是最后再移动过来的，故保留了没有在此修改
	public List<ExperssionInteriorParenthsisCheck> parenthsisCheckers = new ArrayList<>();
	
	public ParserShared(String expression) {
		super();
		this.expression = expression;
	}
}
