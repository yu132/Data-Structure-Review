package common.ag.math;

public final class IntToOrdinalNumbers {
	
	public static String convert(int num) {
		if (num <= 0)
			throw new IllegalArgumentException("num can't be smaller than 1");
		int length = IntMath.length(num);
		
		int low1 = num % 10;
		int low2 = num % 100 / 10;
		
		if (length >= 2)
			if (low2 == 1)// 11到13都是th
				return num + "th";
			
		switch (low1) {
			case 1:
				return num + "st";
			case 2:
				return num + "nd";
			case 3:
				return num + "rd";
			default:
				return num + "th";
		}
	}
	
}
