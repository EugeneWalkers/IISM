package labs.lab2.utilities.concreteDistributions;

import labs.lab2.utilities.CumulativeDistribution;

public class BinomialDistribution implements CumulativeDistribution {

    private final double p;
    private final int n;

    public BinomialDistribution(final double p, final int n) {
        this.p = p;
        this.n = n;
    }

    @Override
    public double getDistributionFunctionValue(final double i) {
//        if (i <= 0) {
//            return 0;
//        } else if (i < 1) {
//            return 1 - p;
//        } else {
//            return 1;
//        }

        final int I = (int) Math.floor(i);
        double result = 0;

        for (int j = 0; j < I; j++) {
            result += getBinomialKoeff(j) * Math.pow(p, j) * Math.pow(1 - p, n - j);
        }

        return result;
    }

    @Override
    public double getExpectedValue() {
        return p * n;
    }

    @Override
    public double getVariance() {
        return p * (1 - p) * n;
    }

    @Override
    public double getP(final int i) {
        return getBinomialKoeff(i) * Math.pow(p, i) * Math.pow(1 - p, n - i);
    }

    @Override
    public String getDescription() {
        return "Биномиальное распределение.";
    }

    private double getBinomialKoeff(final int k) {
        double result = 1;

        for (double i = 1; i <= n - k; i++) {
            result *= (i + k) / (i);
        }

        return result;
    }

}
