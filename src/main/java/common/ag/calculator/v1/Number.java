package common.ag.calculator.v1;

public class Number implements Element {
	
	private final static Number[] pool = new Number[256];// 常量池 -128 to 127
	
	int number;
	
	public Number(int number) {
		super();
		this.number = number;
	}
	
	static {
		for (int i = 0; i < pool.length; ++i) {
			pool[i] = new Number(i - 128);
		}
	}
	
	public static Number of(int num) {
		if (num >= -128 && num <= 127)
			return pool[num + 128];
		else
			return new Number(num);
	}
	
	@Override
	public String toString() {
		return Integer.toString(number);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Number other = (Number) obj;
		if (number != other.number)
			return false;
		return true;
	}
	
}
