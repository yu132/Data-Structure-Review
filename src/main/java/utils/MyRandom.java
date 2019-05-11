package utils;

import java.util.Arrays;
import java.util.Random;

public class MyRandom {

	private Random r;

	public MyRandom() {
		super();
		r = new Random();
	}

	public MyRandom(long seed) {
		super();
		r = new Random(seed);
	}

	public MyRandom(Random r) {
		super();
		this.r = r;
	}

	public int[] randomIntArray(int rangeFrom, int rangeTo, int length) {
		int[] array = new int[length];

		long min = rangeFrom;
		long max = rangeTo;

		Arrays.setAll(array, (i) -> {
			int num = (int) (min + (int) (r.nextDouble() * (max - min)));
			return num;
		});

		return array;
	}

}
