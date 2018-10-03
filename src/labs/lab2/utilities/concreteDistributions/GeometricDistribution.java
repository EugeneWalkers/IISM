package labs.lab2.utilities.concreteDistributions;

import labs.lab2.utilities.CumulativeDistribution;

public class GeometricDistribution implements CumulativeDistribution {

    private final double p;

    public GeometricDistribution(double p) {
        this.p = p;
    }

    @Override
    public double getDistributionFunctionValue(final double i) {

        return 1 - Math.pow(1 - p, i + 1);
    }

    @Override
    public double getExpectedValue() {
        return (1 - p) / p;
    }

    @Override
    public double getVariance() {
        return (1 - p) / (p * p);
    }

    @Override
    public double getP(final int i) {
        return p * Math.pow(1 - p, i);
    }

    @Override
    public String getDescription() {
        return "Геометрическое распределение.";
    }
}
