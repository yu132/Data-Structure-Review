package utils;

public class GetNext2Pow {

	public static int get(int num){
			
        num |= (num >>>  1);
        num |= (num >>>  2);
        num |= (num >>>  4);
        num |= (num >>>  8);
        num |= (num >>> 16);
        num++;
        
        if (num < 0)//0x80000000 -> Integer.MIN_VALUE
            num >>>= 1;//0x40000000 -> 2^30
		
		return num;
	}
	
	
}
