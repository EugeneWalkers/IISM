package labs.utilities;

import labs.lab1.utilities.FunctionProvider;
import labs.lab2.utilities.CumulativeDistribution;

public class Pearson {

    private static final int K = 40; // разбиение для критерия Пирсона
    private static int N;

    private Pearson() {

    }

    public static boolean isFulfilled(final Pair<Double, Double>[] mass, final CumulativeDistribution function, final double eps) {
        N = mass.length;
        final double[] v = getV(mass); // частоты
        final double[] p = getP(function); // вероятности
        final double x2 = getX2(v, p); // значение X2, рассчитанное по формуле
        final double delta = getDelta(eps); // псевдосгенерированное значение

        return x2 < delta;
    }

    public static double[] getErrorsMass(final CumulativeDistribution function, final int n) {

        double[] errors = new double[n];

        for (int k = 0; k < n; k++) {

            @SuppressWarnings("unchecked") final Pair<Double, Double>[] result = new Pair[N]; // result[0] = function(1), result[1] = function(2), ... result[i] = function(i+1)

            for (int i = 0; i < N; i++) {
                double someValue = i * (k + 1); // можно сгенерировать случайно
                result[i] = new Pair<>(someValue, function.getDistributionFunctionValue(someValue));
            }

            final double[] v = getV(result); // частоты
            final double[] p = getP(function); // вероятности
            errors[k] = getX2(v, p);

        }

        return errors;
    }

    private static double[] getP(final CumulativeDistribution function) {
        final double[] p = new double[K]; // вероятность считается с помощью функции распределения

        for (int i = 0; i < K; i++) {
            p[i] = function.getDistributionFunctionValue(i + 1) - function.getDistributionFunctionValue(i);
        }

        return p;
    }

    public static boolean isFulfilledWithEmpiricFunc(final Pair<Double, Double>[] mas, final double eps) { // для lab1
        N = mas.length;

        final double[] v = getV(mas); // частоты
        final double[] p = getPWithEmpiricFunc(mas); // вероятности
        final double x2 = getX2(v, p); // значение X2, рассчитанное по формуле
        final double delta = getDelta(eps); // псевдосгенерированное значение

        return x2 < delta;
    }

    private static double[] getV(final Pair<Double, Double>[] mass) {
        final double[] v = new double[K]; // частота вхождения в каждый промежуток заносится в этот массив, K промежутков

        double max = Double.MIN_VALUE; // промежуток от 0 до max

        for (Pair<Double, Double> mas : mass) {
            max = Math.max(max, mas.getValue());
        }

        final double interval = max / K;
        int pos = 0;

        for (Pair<Double, Double> value : mass) {
            for (int j = 0; j < K; j++) {
                if (value.getValue() >= j * interval && value.getValue() < (j + 1) * interval) {
                    pos = j;
                    break;
                }
            }


//            pos = (int) Math.floor(value.getValue() / interval);
//            if (pos == K) {
//                pos = K - 1;
//            }
            v[pos]++;
        }

        return v;
    }

    private static double[] getPWithEmpiricFunc(final Pair<Double, Double>[] mass) {
        final double[] p = new double[K]; // вероятность считается с помощью функции распределения

        final double[] massOfValues = Pair.Converter.getValuesFromPair(mass);

        for (int i = 0; i < K; i++) {
            p[i] = FunctionProvider.getEmpiricFunc(((double) i + 1) / K, massOfValues)
                    - FunctionProvider.getEmpiricFunc((double) i / K, massOfValues);
        }

        return p;
    }

    private static double getX2(final double[] v, final double[] p) {
        double resultSum = 0; // применяем формулу

        for (int i = 0; i < K; i++) {
            resultSum += (v[i] - N * p[i]) * (v[i] - N * p[i]) / (N * p[i]);
        }

        return resultSum;
    }

    private static double getDelta(@SuppressWarnings("unused") final double eps) {
        // используем таблицу, считая число степеней свободы k = 40 - 10 - 1 = 29
        // получили 42.6
        return 42.6;
    }

}