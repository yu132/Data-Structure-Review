package ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import ds.set.sortedSet.IntSkipList;
import performanceTest.PerformanceTest;
import performanceTest.Tester;

public class PerformanceTesting {
	
	public static void one(int x){
		int b=x*2;
	}
	
	public static void one2(int y){
		int b=y<<1;
	}
	
	public static void main(String[] args) {
		
		int times=10000;
		
		PerformanceTest pt1=new PerformanceTest(10000, times, new Runnable() {
			@Override
			public void run() {
				one(10000);
			}
		}, "*");
		
		PerformanceTest pt2=new PerformanceTest(10000, times, new Runnable() {
			@Override
			public void run() {
				one2(10000);
			}
		}, "<<");
		
		Tester t=new Tester(new ArrayList<>(Arrays.asList(pt1,pt2)));
		
		t.doTests();
	}
	
}
