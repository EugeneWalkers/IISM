package labs.lab2.utilities.concreteDistributions;

import labs.lab2.utilities.CumulativeDistribution;

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
    public double getDistributionFunctionValue(double i) {
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
    public double getP(int i) {
        return getBinomialKoeff(D, i) * getBinomialKoeff(N - D, n - i) / getBinomialKoeff(N, n);
    }

    @Override
    public String getDescription() {
        return "Гипергеометрическое распределение.";
    }

    private double getBinomialKoeff(final int n, final int k) {
        double result = 1;

        for (double i = 1; i <= n - k; i++) {
            result *= (i + k) / (i);
        }

        return result;
    }
}
