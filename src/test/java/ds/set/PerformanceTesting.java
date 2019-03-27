package ds.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import ds.set.sortedSet.IntSkipList;
import performanceTest.PerformanceTest;
import performanceTest.Tester;

public class PerformanceTesting {
	
	public final static Random RANDOM=new Random();
	
	public final static int AMOUNT=1000;
	
	public final static int CONTAIN_TIMES=AMOUNT*10000;
	
	public final static int[] DATA=new int[AMOUNT];
	
	public final static int[] ORDER=new int[CONTAIN_TIMES];
	
	static{
		for(int i=0;i<AMOUNT;++i)
			DATA[i]=RANDOM.nextInt(AMOUNT*2);
		
		for(int i=0;i<CONTAIN_TIMES;++i)
			ORDER[i]=RANDOM.nextInt(AMOUNT);
	}
	
	public static void one(IntSkipList set){
		for(int i=0;i<AMOUNT;++i)
			set.add(DATA[i]);
		
		for(int i=0;i<CONTAIN_TIMES;++i)
			set.contain(ORDER[i]);
	}
	
	public static void one2(HashSet<Integer> set){
		for(int i=0;i<AMOUNT;++i)
			set.add(DATA[i]);
		
		for(int i=0;i<CONTAIN_TIMES;++i)
			set.contains(ORDER[i]);
	}
	
	public static void main(String[] args) {
		
		int times=10;
		
		PerformanceTest pt1=new PerformanceTest(1, times, new Runnable() {
			@Override
			public void run() {
				IntSkipList list=new IntSkipList(AMOUNT);
				one(list);
			}
		}, "SkipList");
		
		PerformanceTest pt2=new PerformanceTest(1, times, new Runnable() {
			@Override
			public void run() {
				HashSet<Integer> set=new HashSet<>(AMOUNT);
				one2(set);
			}
		}, "HashSet");
		
		Tester t=new Tester(new ArrayList<>(Arrays.asList(pt1,pt2)));
		
		t.doTests();
	}
	
}
