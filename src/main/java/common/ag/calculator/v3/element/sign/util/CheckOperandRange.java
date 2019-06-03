package common.ag.calculator.v3.element.sign.util;

import java.math.BigInteger;

public final class CheckOperandRange {
	
	public static boolean checkBigInteger(BigInteger needCheck, BigInteger min, BigInteger max) {
		return needCheck.compareTo(min) >= 0 && needCheck.compareTo(max) <= 0;
	}
	
}
