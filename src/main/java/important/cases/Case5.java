package important.cases;

/**
 * 多参数类型传递的数组不会为空
 * 
 * @author 87663
 */
public class Case5 {
	
	public static void a(int... nums) {
		System.out.println(nums);
		System.out.println(nums.length);
	}
	
	public static void main(String[] args) {
		a();
	}
	
}
