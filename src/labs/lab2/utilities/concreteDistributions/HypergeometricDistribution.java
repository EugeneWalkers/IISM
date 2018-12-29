package labs.lab2.utilities.concreteDistributions;

import labs.utilities.CumulativeDistribution;
import labs.utilities.Pair;

public class HypergeometricDistribution implements CumulativeDistribution {

    private final int N;
    private final int D;
    private final int n;

    public HypergeometricDistribution(final int N, final int D, final int n) {
        this.N = N;
        this.D = D;
        this.n = n;
    }

    @Override
    public double getDistributionFunctionValue(final double i) {
        double result = 0;

        for (int k = 0; k <= i; k++) {
            result += getP(k);
        }

        return result;
    }

    @Override
    public double getExpectedValue() {
        return n * D / (double) N;
    }

    @Override
    public double getVariance() {
        return getExpectedValue() * (1 - (double) D / N) * (N - n) / (N - 1);
    }

    @Override
    public double getP(final double i) {
        return getBinomialKoeff(D, i) * getBinomialKoeff(N - D, n - i) / getBinomialKoeff(N, n);
    }

    @Override
    public Pair<Double, Double> generateValue() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Гипергеометрическое распределение.";
    }

    private double getBinomialKoeff(final int n, final double k) {
        double result = 1;

        for (double i = 1; i <= n - k; i++) {
            result *= (i + k) / (i);
        }

        return result;
    }
}
