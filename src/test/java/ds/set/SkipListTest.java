package ds.set;

import java.util.Random;

import ds.IntIterator;
import ds.set.sortedSet.IntSkipList;


public class SkipListTest {

	public static void main(String[] args) {
		Random r=new Random();
		
		IntSkipList sk=new IntSkipList(1000,true);
		
		int count=0;
		
		for(int i=0;i<100000;++i)
			if(sk.add(r.nextInt(100000)))
				++count;
		
		System.out.println(sk.contain(312));
		
		System.out.println(sk.remove(312));
		
		int count2=0;
		
		for(IntIterator it=sk.iterator();it.hasNext();){
			System.out.println(it.next());
			++count2;
		}
		
		System.out.println(sk.size()+" "+count+" "+count2);
	}
	
}
