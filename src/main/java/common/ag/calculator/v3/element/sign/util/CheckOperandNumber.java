package common.ag.calculator.v3.element.sign.util;

public final class CheckOperandNumber {
	
	/**
	 * 检查操作数的数量是否满足邀请
	 * @param requiredNumber		需要的操作数数量
	 * @param now					当前的操作数数量
	 * @return						两者是否相等
	 */
	public static boolean checkOperandNumber(int requiredNumber, int now) {
		return requiredNumber == now;
	}
	
}
