package lab07;

public class PoorRandomNumberGenerator implements RandomNumberGenerator {

	// NOTE: None of these variables are used in this generator.
	private long seed;
	private long const1;
	private long const2;

	public int nextInt(int max) {
		return 1;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public void setConstants(long const1, long const2) {
		this.const1 = const1;
		this.const2 = const2;
	}
}
