package ag.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import performanceTest.PerformanceTest;
import performanceTest.Tester;

public class PerformanceTesting {

	public static void one(IntArraySort list, int[] array) {
		list.sort(array);
	}

	public static void main(String[] args) {

		int[] array = new int[1000000];

		Random r = new Random();

		Arrays.setAll(array, (i) -> r.nextInt() * (r.nextBoolean() ? -1 : 1));

		int times = 1;

		PerformanceTest pt1 = new PerformanceTest(1, times, new Runnable() {

			@Override
			public void run() {
				one(new InsertionSort(), Arrays.copyOf(array, array.length));
			}
		}, "InsertionSort");

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
		}, "SystemDeafultSort");

		Tester t = new Tester(new ArrayList<>(Arrays.asList(pt1, pt2, pt3, pt4)));

		t.doTests();
	}

}
