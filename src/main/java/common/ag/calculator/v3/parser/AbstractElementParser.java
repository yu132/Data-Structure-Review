package common.ag.calculator.v3.parser;

/**
 * 抽象解析器，实现了部分功能
 * 
 * 同时将解析工作拆成了四部分
 * 
 * @author 87663
 *
 */
public abstract class AbstractElementParser implements ElementParser {
	
	/**
	 * 将解析拆成了前检查（检查该位置是否能够使用本解析器，如前面是运算符号，这里就不能使用*号的解析器）
	 * 解析（将元素解析出来），设置shared（将当前状况进行设置，如当前解析了一个数字，就将shared中对应位置设为true）
	 * 后检查（查看解析后是否违反了约束条件，例如出现的右括号比左括号多）
	 */
	@Override
	public boolean parse(ParserShared shared) {
		if (checkBeforeParse(shared) && parseHelper(shared)) {
			setSharedAfterParse(shared);
			checkAfterParse(shared);
			return true;
		}
		return false;
	}
	
	/**
	 * 前检查
	 */
	protected abstract boolean checkBeforeParse(ParserShared shared);
	
	/**
	 * 解析
	 */
	protected abstract boolean parseHelper(ParserShared shared);
	
	/**
	 * 后检查
	 */
	protected void checkAfterParse(ParserShared shared) {}
	
	/**
	 * 设置shared
	 */
	protected abstract void setSharedAfterParse(ParserShared shared);
	
	@Override
	public void finalCheck(ParserShared shared) {}
	
}
