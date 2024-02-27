package lab07;

public class BetterRandomNumberGenerator implements RandomNumberGenerator {
    private long multiplier;
    private long increment;
    private long seed;
    private long modulus;

    public BetterRandomNumberGenerator() {
        this.modulus = (long) Math.pow(2,32);
        this.multiplier = 1664525;
        this.increment = 1013904223;
    }

    @Override
    public int nextInt(int max) {
        seed = (multiplier * seed + increment) % modulus;
        return (int) (Math.abs(seed) % max);
    }

    @Override
    public void setSeed(long seed) {
        this.seed = seed;
    }

    @Override
    public void setConstants(long const1, long const2) {
        this.multiplier = const1;
        this.increment = const2;
    }
}
