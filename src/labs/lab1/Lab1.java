package labs.lab1;

import labs.lab1.utilities.FunctionProvider;
import labs.utilities.Kolmogorov;
import labs.utilities.Pair;
import labs.utilities.Pearson;

import java.util.stream.IntStream;

final class Lab1 {

    static int N = 1000; // 1000 реализаций
    private static final double M = Integer.MAX_VALUE; // 2^31

    private Lab1() {

    }

    static Pair<Double, Double>[] doTask1(final double a0Star, final double b) {
        @SuppressWarnings("unchecked") final Pair<Double, Double>[] output = new Pair[N]; // выходной массив реализаций БСВ
        final double[] temp = new double[N]; // вспомогательный массив для расчетов
        temp[0] = a0Star;
        output[0] = new Pair<>(0.0, a0Star / M);

        IntStream.range(1, N).forEach(i -> { // представляет собой for (int i = 1; i < N; i++) { }, остальное по формулам
            temp[i] = (b * temp[i - 1]) % M;
            output[i] = new Pair<>((double) i, temp[i] / M);
        });

        return output;
    }

    static Pair<Double, Double>[] doTask2(final double a0Star, final double b, final int k) {
        @SuppressWarnings("unchecked") final Pair<Double, Double>[] output = new Pair[N]; // выходной массив реализаций БСВ
        @SuppressWarnings("unchecked") final Pair<Double, Double>[] vi = new Pair[k];

        final Pair<Double, Double>[] bi = doTask1(a0Star, b); // исходя из условия один из датчиков МКМ
        final Pair<Double, Double>[] ci = doTask1(a0Star, b); // почему бы и нет?

        for (int i = 0; i < k; i++) {
            vi[i] = new Pair<>(bi[i].getKey(), bi[i].getValue());
        }

        IntStream.range(0, N).forEach(i -> { // представляет собой for (int i = 0; i < N; i++){ ... }, остальное - по формулам
            final int s = (int) Math.abs(ci[i].getValue() * k);
            output[i] = new Pair((double) i, vi[s].getValue());
            vi[s] = i + k >= N ? bi[(i + k) % N] : bi[i + k];
        });

        return output;
    }

    static String doTask3(final Pair<Double, Double>[] mas, final double delta, final double eps) {
        final StringBuilder builder = new StringBuilder();

        if (Kolmogorov.isFulfilled(mas, delta)) {
            builder.append("Критерий Колмогорова выполнился");
        } else {
            builder.append("Критерий Колмогорова не выполнился");
        }

        builder.append("\n");

        if (Pearson.isFulfilledWithEmpiricFunc(mas, eps)) {
            builder.append("Критерий Пирсона выполнился");
        } else {
            builder.append("Критерий Пирсона не выполнился");
        }

        return builder.toString();
    }




}
