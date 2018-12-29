package labs.lab3.utilities.concreteDistributions;

import labs.utilities.ContinuousDistribution;
import labs.utilities.CumulativeDistribution;
import labs.utilities.Pair;

public class UniformDistribution implements ContinuousDistribution {

    private final double a;
    private final double b;

    private double step = 0.001;

    public UniformDistribution(final double a, final double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getDistributionFunctionValue(final double i) {
        if (i < a) {
            return 0;
        } else if (i > b) {
            return 1;
        } else {
            return (i - a) / (b - a);
        }
    }

    @Override
    public double getExpectedValue() {
        return (a + b) / 2;
    }

    @Override
    public double getVariance() {
        return (b - a) * (b - a) / 12;
    }

    //@Override
    public double getP(double i) {
        return 0;
    }

    @Override
    public Pair<Double, Double> generateValue() {
        final double value = a + (b-a)*step;
        step+=0.010;
        return new Pair<>(value, getDistributionFunctionValue(value));
    }

    @Override
    public String getDescription() {
        return "Равномерное распределение.";
    }
}
