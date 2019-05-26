package common.ag.math.avg;

public final class IntAvgCalculator {

	private int	avg	= 0;

	private int	res	= 0;

	private int	n;

	public IntAvgCalculator(int n) {
		super();
		this.n = n;
	}

	public void offer(int num) {
		avg += num / n;
		res += num % n;
		avg += res / n;
		res = res % n;
	}

	public int getAvg() {
		return avg;
	}

}
