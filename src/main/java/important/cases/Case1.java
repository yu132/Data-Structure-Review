package important.cases;

public class Case1 {

	public static void main(String[] args) {

		int[] array = new int[10];

		array[f1(2)] = array[f1(3)] = f2(array[4], array[5]);
	}

	public static int f1(int i) {
		System.out.println("f1 " + i);
		return i;
	}

	public static int f2(int a, int b) {
		System.out.println("f2");
		return a + b;
	}

}
