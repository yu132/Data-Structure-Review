package common.ag.calculator.v3.element.sign.priority;

import common.ag.calculator.v3.element.sign.OperationalSymbol;

/**
 * 运算符号优先级
 * 
 * @author 87663
 */
public interface OperationalSymbolPriority {
	
	/**
	 * @return	优先级的整数表示
	 */
	int intValue();
	
	/**
	 * 两个优先级的比较器
	 * @param o1	第一个优先级
	 * @param o2	第二个优先级
	 * @return		若第一个优先级高，返回1，第二个优先级高，返回-1，两者优先级相等，返回0
	 */
	public static int compare(OperationalSymbol o1, OperationalSymbol o2) {
		return Integer.compare(o1.getOperationalSymbolPriority().intValue(),
				o2.getOperationalSymbolPriority().intValue());
	}
}
