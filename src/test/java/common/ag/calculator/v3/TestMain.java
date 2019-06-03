package common.ag.calculator.v3;

import java.util.Arrays;

public class TestMain {
	
	public static void main(String[] args) {
		
		System.out.println(
				Arrays.toString(Parser.parse("1+100-(123+1?123123+123123?12312:132:123)")));
	}
	
}
