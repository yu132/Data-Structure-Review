package common.ag.calculator.v3.element.sign.util;

import java.math.BigInteger;

public final class CheckOperandRange {
	
	/**
	 * 检查整形的数是否满足某个范围
	 * 
	 * @param needCheck		需要检查的整形
	 * @param min			范围的下界（包含）
	 * @param max			范围的上界（包含）
	 * @return				是否在这个范围内
	 */
	public static boolean checkBigInteger(BigInteger needCheck, BigInteger min, BigInteger max) {
		return needCheck.compareTo(min) >= 0 && needCheck.compareTo(max) <= 0;
	}
	
}
