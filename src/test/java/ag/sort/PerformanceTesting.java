package ag.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import ag.sort.QuickSort.RandomQuickSort;
import performanceTest.PerformanceTest;
import performanceTest.Tester;

public class PerformanceTesting {

	public static void one(IntArraySort list, int[] array) {
		list.sort(array);
	}

	public static void main(String[] args) {

		//*********   参数    ********* 

		int arrayLength = 5000000;

		int lowArrayLength = 100000;

		int times = 1;

		int timesLOW = 1;

		//*********   测试代码        *********

		int[] array = new int[arrayLength];

		int[] arrayLOW = new int[lowArrayLength];

		Random r = new Random();

		Arrays.setAll(array, (i) -> r.nextInt() * (r.nextBoolean() ? -1 : 1));//随机数组

		//Arrays.setAll(array, (i) -> i);//顺序数组

		System.arraycopy(array, 0, arrayLOW, 0, lowArrayLength);

		PerformanceTest pt1 = new PerformanceTest(1, timesLOW, new Runnable() {

			@Override
			public void run() {
				one(new InsertionSort(), Arrays.copyOf(arrayLOW, arrayLOW.length));
			}
		}, "InsertionSort-LOW_LENGTH_ARRAY");

		PerformanceTest pt2 = new PerformanceTest(1, times, new Runnable() {

			@Override
			public void run() {
				one(new HeapSort(), Arrays.copyOf(array, array.length));
			}
		}, "HeapSort");

		PerformanceTest pt3 = new PerformanceTest(1, times, new Runnable() {

			@Override
			public void run() {
				one(new WinnerSort(), Arrays.copyOf(array, array.length));
			}
		}, "WinnerSort");

		PerformanceTest pt4 = new PerformanceTest(1, times, new Runnable() {

			@Override
			public void run() {
				one(new IntArraySort() {
					@Override
					public void sort(int[] array) {
						Arrays.sort(array);
					}
				}, Arrays.copyOf(array, array.length));
			}
		}, "SystemDeafultSort_TimSort");

		PerformanceTest pt5 = new PerformanceTest(1, timesLOW, new Runnable() {

			@Override
			public void run() {
				one(new BubbleSort(), Arrays.copyOf(arrayLOW, arrayLOW.length));
			}
		}, "BubbleSort-LOW_LENGTH_ARRAY");

		PerformanceTest pt6 = new PerformanceTest(1, timesLOW, new Runnable() {

			@Override
			public void run() {
				//one(new SelectionSort(), Arrays.copyOf(arrayLOW, arrayLOW.length));
			}
		}, "SelectionSort-LOW_LENGTH_ARRAY-BANDONED");

		PerformanceTest pt7 = new PerformanceTest(1, times, new Runnable() {

			@Override
			public void run() {
				one(new QuickSort(), Arrays.copyOf(array, array.length));
			}
		}, "QuickSort");

		PerformanceTest pt8 = new PerformanceTest(1, times, new Runnable() {

			@Override
			public void run() {
				one(new RandomQuickSort(), Arrays.copyOf(array, array.length));
			}
		}, "RandomQuickSort");

		PerformanceTest pt9 = new PerformanceTest(1, times, new Runnable() {

			@Override
			public void run() {
				one(new MergeSort(), Arrays.copyOf(array, array.length));
			}
		}, "MergeSort");

		PerformanceTest pt10 = new PerformanceTest(1, timesLOW, new Runnable() {

			@Override
			public void run() {
				one(new SkipListSort(), Arrays.copyOf(arrayLOW, arrayLOW.length));
			}
		}, "SkipListSort-LOW_LENGTH_ARRAY");

		Tester t = new Tester(
				new ArrayList<>(Arrays.asList(pt1, pt5, pt6, pt2, pt3, pt4, pt7, pt8, pt9, pt10)));

		t.doTests();
	}

}
