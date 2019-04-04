package ds;

public class test {

	public static void getMin2Pow(int x) {
		x |= (x >>> 1);
		x |= (x >>> 2);
		x |= (x >>> 4);
		x |= (x >>> 8);
		x |= (x >>> 16);
		x++;

		System.out.println(x);
	}

	public static void main(String[] args) {

		//	System.out.println(Integer.MAX_VALUE<<2>>>10>>10>>10);
		//
		//		int x = 123123;
		//		int y = 31233;
		//
		//		System.out.println(x / y + " " + (x % y));
		//		System.out.println(-x / y + " " + (-x % y));
		//		System.out.println(x / -y + " " + (x % -y));
		//		System.out.println(-x / -y + " " + (-x % -y));

		System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);

		//		TreeMap<Integer,Integer> map=new TreeMap<>();
		//		
		//		map.put(12, 1);
		//		map.put(12123, 1);
		//		map.put(123, 1);
		//		map.put(126, 1);
		//		map.put(1534, 1);
		//		map.put(1, 1);
		//		map.put(11322, 1);
		//		map.put(16452, 1);
		//		
		//		for(Integer key:map.keySet()){
		//			System.out.println(key);
		//		}
		//		
		/*		HashMap
				Set
		System.out.println(-2&31);*/

		/*double df=Integer.MAX_VALUE;
		
		for(int i=0;i<100;++i){
			System.out.println((int)(df+i)==Integer.MAX_VALUE);
		}*/

		//System.out.println(Math.log(32)/Math.log(2));

		//		getMin2Pow(5674345);

		//		System.out.println((Integer.MAX_VALUE/2+100)*2);
		//		
		//		ArrayDeque a=new ArrayDeque();

		//		int[] array=new int[1000000];
		//		
		//		int loc=1312;
		//		
		//		int l=0;
		//		
		//		System.arraycopy(array, loc-1, array, loc, l);
		//		
		//		{
		//			
		//			int[] array=new int[1000000];
		//			
		//			int loc=1312;
		//			
		//			int l=19231;
		//			
		//			ShowTimeOfExecution s=new ShowTimeOfExecution("A");
		//			
		//			for(int i=0;i<10000;++i){
		//				for(int j=loc,len=loc+l;j<len;++j)
		//					array[j]=array[j+1];
		//			}
		//			
		//			s.showTimeInCmd();
		//		}
		//		{
		//			int[] array=new int[1000000];
		//			
		//			int loc=1312;
		//			
		//			int l=19231;
		//			
		//			ShowTimeOfExecution s=new ShowTimeOfExecution("B");
		//
		//			for(int i=0;i<10000;++i){
		//				System.arraycopy(array, loc-1, array, loc, l);
		//			}
		//			
		//			s.showTimeInCmd();
		//		}
		//		
		//		{
		//			
		//			int[] array=new int[1000000];
		//			
		//			int loc=1312;
		//			
		//			int l=19231;
		//			
		//			ShowTimeOfExecution s=new ShowTimeOfExecution("B");
		//	
		//			for(int i=0;i<1;++i)
		//				for(int j=0;j<10000000;++j){
		//					System.arraycopy(array, loc-1, array, loc, 0);
		//				}
		//			
		//			s.showTimeInCmd();
		//		}
		//		{
		//			
		//			
		//			ShowTimeOfExecution s=new ShowTimeOfExecution("A");
		//			
		//			for(int i=0;i<1;++i)
		//				for(int j=0;j<10000000;++j);
		//			
		//			s.showTimeInCmd();
		//		}
		//		
		//		{
		//			
		//			int[] array=new int[1000000];
		//			
		//			int loc=1312;
		//			
		//			int l=19231;
		//			
		//			ShowTimeOfExecution s=new ShowTimeOfExecution("B");
		//	
		//			for(int i=0;i<1;++i)
		//				for(int j=0;j<10000000;++j){
		//					System.arraycopy(array, loc-1, array, loc, 0);
		//				}
		//			
		//			s.showTimeInCmd();
		//		}
		//		{
		//			
		//			
		//			ShowTimeOfExecution s=new ShowTimeOfExecution("A");
		//			
		//			for(int i=0;i<1;++i)
		//				for(int j=0;j<10000000;++j);
		//			
		//			s.showTimeInCmd();
		//		}

	}
}
