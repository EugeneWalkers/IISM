package labs.lab3.utilities.concreteDistributions;

import labs.utilities.ContinuousDistribution;
import labs.utilities.CumulativeDistribution;
import labs.utilities.LaplaceFunction;
import labs.utilities.Pair;

import java.util.Random;

public class NormalDistribution implements ContinuousDistribution {

    private final double m, sSquare;
    private final int n;
    private double i = 0;
    private Random rand = new Random();

    public NormalDistribution(final double m, final double sSquare, final int n) {
        this.m = m;
        this.sSquare = sSquare;
        this.n = n;
    }

    @Override
    public double getDistributionFunctionValue(final double i) {
        double f = getP(i);

        double F = 1 - f * (LaplaceFunction.getValue(i));

        return 1.0 / 2 + F / 2;
    }

    @Override
    public double getExpectedValue() {
        return m;
    }

    @Override
    public double getVariance() {
        return sSquare;
    }

//    @Override
    public double getP(final double i) {
        double result;

        result = Math.exp(-(i - m) * (i - m) / (2 * sSquare)) / Math.sqrt(2 * Math.PI * sSquare);

        return result;
    }

    @Override
    public Pair<Double, Double> generateValue() {
        double value = Math.sqrt(sSquare) * Math.cos((rand.nextDouble() % Math.PI) / Math.PI) * Math.sqrt(-2 * Math.log((rand.nextDouble() % 100000.0) / 100000.0)) + m;

        return new Pair<>(value, getDistributionFunctionValue(value));
    }

    @Override
    public String getDescription() {
        return "Нормальное распределение.";
    }

    @Override
    public int getMin() {
        return -n / 2;
    }

    @Override
    public int getMax() {
        return n / 2;
    }
}
