package ds.linearList;

import java.util.ArrayList;
import java.util.Arrays;

import performanceTest.PerformanceTest;
import performanceTest.Tester;

public class PerformanceTesting {
	
	public static void one(ReinforcementIntLinearList list){
		for(int i=0;i<1000;++i)
			list.add(i);
		
		for(int i=300;i<600;++i)
			list.get(i);
		
		for(int i=0;i<100;++i)
			list.addFirst(100);
			
		for(int i=0;i<100;++i)
			list.addLast(100);
		
		for(int i=0;i<100;++i)
			list.deleteFirst();
		
		for(int i=0;i<100;++i)
			list.deleteLast();
		
		for(int i=0;i<100;++i)
			list.insert(100,1);
		
		for(int i=0;i<100;++i)
			list.deleteIndex(100);
		
		for(int i=0;i<100;++i)
			list.indexOf(i+100);
		
		list.clear();
	}
	
	public static void main(String[] args) {
		
		PerformanceTest pt1=new PerformanceTest(1, 100000, new Runnable() {
			ReinforcementIntLinearList list=new IntArrayList();
			@Override
			public void run() {
				one(list);
			}
		}, "IntArrayList");
		
		PerformanceTest pt2=new PerformanceTest(1, 100000, new Runnable() {
			ReinforcementIntLinearList list=new IntLinkedList();
			@Override
			public void run() {
				one(list);
			}
		}, "IntLinkedList");
		
		PerformanceTest pt3=new PerformanceTest(1, 100000, new Runnable() {
			ReinforcementIntLinearList list=new SystemIntArrayList();
			@Override
			public void run() {
				one(list);
			}
		}, "SystemIntArrayList");
		
		PerformanceTest pt4=new PerformanceTest(1, 100000, new Runnable() {
			ReinforcementIntLinearList list=new SystemIntLinkedList();
			@Override
			public void run() {
				one(list);
			}
		}, "SystemIntLinkedList");
		
		Tester t=new Tester(new ArrayList<>(Arrays.asList(pt1,pt2,pt3,pt4)));
		
		t.doTests();
	}
	
}
