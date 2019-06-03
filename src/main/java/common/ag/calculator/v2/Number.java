package common.ag.calculator.v2;

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
	
	public final static int rangeFrom = Integer.MAX_VALUE / 10;
	
	/**
	 * @see Integer.praseInt(String str)
	 * 
	 * 没处理int越界问题
	 */
	@Override
	public boolean getElement(ParserShared shared) {
		
		int numberLength = 0;
		boolean hasSign = false;
		int isMinus = 1;
		
		int num = 0;
		
		for (int i = shared.from[shared.now ^ 1]; i < shared.expression.length(); ++i) {
			char c = shared.expression.charAt(i);
			if (c >= '0' && c <= '9') {
				++numberLength;
				num = num * 10 + (c - '0');
			} else if (c == '+' || c == '-') {
				if (i == shared.from[shared.now ^ 1]) {
					++numberLength;
					hasSign = true;
					if (c == '-')
						isMinus = -1;
				} else
					break;
			} else
				break;
		}
		
		if (numberLength == 0)
			return false;
		if (numberLength == 1 && hasSign)
			return false;
		
		shared.from[shared.now] = shared.from[shared.now ^ 1] + numberLength;
		
		shared.elements.add(Number.of(num * isMinus));
		
		return true;
	}
	
}
