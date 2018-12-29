package labs.lab2;

import labs.utilities.CumulativeDistribution;
import labs.utilities.Pair;

final class Lab2 {

    static int N = 1000;

    private Lab2() {

    }

    static Pair<Double, Double>[] doTask1(final CumulativeDistribution function) {

        @SuppressWarnings("unchecked")
        Pair<Double, Double>[] result = new Pair[N]; // result[0] = function(1), result[1] = function(2), ... result[i] = function(i+1)

        for (int i = 0; i < N; i++) {
            result[i] = new Pair<>((double) i, function.getDistributionFunctionValue(i));
        }

        return result;
    }

    static double doTask2TableExpectedValue(final CumulativeDistribution function) {
        return function.getExpectedValue();
    }

    static double doTask2TableVariance(final CumulativeDistribution function) {
        return function.getVariance();
    }

    static double doTask2RealExpectedValue(final CumulativeDistribution function, final Pair<Double, Double>[] values) {
        double sum = 0.0;

        for (int i = 0; i < N; i++) {
            sum += values[i].getKey() * function.getP(i);
        }

        return sum;
    }

    static double doTask2RealVariance(final CumulativeDistribution function, final Pair<Double, Double>[] values) {
        double sum = 0.0;

        final double M = doTask2RealExpectedValue(function, values);

        for (int i = 0; i < N; i++) {
            sum += function.getP(i) * Math.pow(i - M, 2);
        }

        return sum;
    }


}
