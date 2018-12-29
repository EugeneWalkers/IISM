package labs.lab3;

import labs.lab3.utilities.concreteDistributions.NormalDistribution;
import labs.lab3.utilities.concreteDistributions.UniformDistribution;
import labs.lab3.utilities.concreteDistributions.XSquareDistribution;
import labs.utilities.ContinuousDistribution;
import labs.utilities.Pair;

class Lab3 {
    static int N = 1000;

    private Lab3() {

    }

    static Pair<Double, Double>[] doTask1(final ContinuousDistribution function) {

        @SuppressWarnings("unchecked")
        Pair<Double, Double>[] result = new Pair[N]; // result[0] = function(0), result[1] = function(1), ... result[i] = function(i)

        for (int i = 0; i < N; i++) {

            result[i] = function.generateValue();
        }

        return result;
    }

    static double doTask2TableExpectedValue(final ContinuousDistribution function) {
        return function.getExpectedValue();
    }

    static double doTask2TableVariance(final ContinuousDistribution function) {
        return function.getVariance();
    }

    static double doTask2RealExpectedValue(final ContinuousDistribution function, final Pair<Double, Double>[] values) {
        double sum = 0.0;
        double sum1 = 0.0;

        if (function instanceof XSquareDistribution) {
            for (int i = 0; i < N; i++) {
                sum += values[i].getKey();
            }

            sum /= N;

            return sum;
        }

        if (function instanceof NormalDistribution) {
            for (int i = 0; i < N; i++) {
                sum1 += values[i].getKey();
                sum += values[i].getKey() * ((NormalDistribution) function).getP(values[i].getKey());
            }

            return sum;
        }

        return 0;

    }

    static double doTask2RealVariance(final ContinuousDistribution function, final Pair<Double, Double>[] values) {
        double sum = 0.0;

        final double M = doTask2RealExpectedValue(function, values);

        if (function instanceof XSquareDistribution || function instanceof UniformDistribution) {

            for (int i = 0; i < values.length; i++) {
                sum += (values[i].getKey() - M) * (values[i].getKey() - M);
            }

            sum /= ((double) N - 1);

            return sum;
        }

        if (function instanceof NormalDistribution) {

            for (int i = function.getMin(); i < function.getMax(); i++) {
                sum += ((NormalDistribution)function).getP(i) * Math.pow(i - M, 2);
            }
        }

//        if (function instanceof UniformDistribution) {
//
//            for (int i = function.getMin(); i < function.getMax(); i++) {
//                sum += ((NormalDistribution)function).getP(i) * Math.pow(i - M, 2);
//            }
//        }

        return sum;
    }

}
