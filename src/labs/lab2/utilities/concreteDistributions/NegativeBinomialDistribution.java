package labs.lab2.utilities.concreteDistributions;

import labs.lab2.utilities.CumulativeDistribution;

public class NegativeBinomialDistribution implements CumulativeDistribution {

    private final int r;
    private final double p;

    public NegativeBinomialDistribution(final int r, final double p) {
        this.r = r;
        this.p = p;
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
        return r * (1 - p) / p;
    }

    @Override
    public double getVariance() {
        return r * (1 - p) / (p * p);
    }

    @Override
    public double getP(final int i) {
        double result = r;

        for (int j = 1; j <= i; j++) {
            result *= (1 - p) / j;
        }

        for (int j = 1; j <= r; j++) {
            result *= p / j;
        }

        for (int j = 1; j < i + r; j++) {
            result *= j;
        }

        return result;
    }

    @Override
    public String getDescription() {
        return "Отрицательное биномиальное распределение.";
    }

}
