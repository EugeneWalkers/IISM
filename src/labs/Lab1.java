package labs;

import utilities.FunctionProvider;

import java.util.stream.IntStream;

public final class Lab1 {

    private static final int N = 1000; // 1000 реализаций
    private static final double M = Integer.MAX_VALUE; // 2^31
    private static final int K = 40; // разбиение для критерия Пирсона

    private Lab1() {

    }

    public static double[] doTask1(final double a0Star, final double b) {
        final double[] output = new double[N]; // выходной массив реализаций БСВ
        final double[] temp = new double[N]; // вспомогательный массив для расчетов
        temp[0] = a0Star;
        output[0] = a0Star / M;

        IntStream.range(1, N).forEach(i -> { // представляет собой for (int i = 1; i < N; i++) { }, остальное по формулам
            temp[i] = (b * temp[i - 1]) % M;
            output[i] = temp[i] / M;
        });

        return output;
    }

    public static double[] doTask2(final double a0Star, final double b, final int k) {
        final double[] output = new double[N]; // выходной массив реализаций БСВ
        final double[] vi = new double[k];

        final double[] bi = doTask1(a0Star, b); // исходя из условия один из датчиков МКМ
        final double[] ci = doTask1(a0Star, b); // почему бы и нет?

        /*
        Random random = new Random();
        for (int i =0; i<N; i++){
        ci[i] = random.nextInt(1000000)/1000000.0;
        }
        */

        System.arraycopy(bi, 0, vi, 0, k);

        IntStream.range(0, N).forEach(i -> { // представляет собой for (int i = 0; i < N; i++){ ... }, остальное - по формулам
            final int s = (int) Math.abs(ci[i] * k);
            output[i] = vi[s];
            vi[s] = i + k >= N ? bi[(i + k) % N] : bi[i + k];
        });

        return output;
    }

    public static String doTask3(final double[] mas, final double delta, final double eps) {
        final StringBuilder builder = new StringBuilder();

        if (Kolmogorov.isFulfilled(mas, delta)) {
            builder.append("Критерий Колмогорова выполнился");
        } else {
            builder.append("Критерий Колмогорова не выполнился");
        }

        builder.append("\n");

        if (Pirson.isFulfilled(mas, eps)) {
            builder.append("Критерий Пирсона выполнился");
        } else {
            builder.append("Критерий Пирсона не выполнился");
        }

        return builder.toString();
    }

    private final static class Kolmogorov {

        private Kolmogorov() {

        }

        static boolean isFulfilled(final double[] mas, final double delta) { // критерий выполняется, если sqrt(n)*Dn <= delta
            final double dn = getDn(mas);
            final int n = mas.length;
            final double result = Math.sqrt(n) * dn;

            return result <= delta;
        }

        private static double getDn(final double[] mas) { // Dn - это максимум из Dn- и Dn+, каждая считается по своей формуле, используя функцию распределения
            final double dnPlus = getDnPlus(mas);
            final double dnMinus = getDnMinus(mas);

            return Math.max(dnPlus, dnMinus);
        }

        private static double getDnPlus(final double[] mas) { // посчитали Dn+ исходя из формулы
            double dnPlus = Double.MIN_VALUE;
            final int n = mas.length;

            for (int i = 0; i < n; i++) {
                dnPlus = Math.max(dnPlus, ((double) i + 1) / n - FunctionProvider.getEmpiricFunc(mas[i], mas));
            }

            return dnPlus;
        }

        private static double getDnMinus(final double[] mas) { // посчитали Dn- исходя из формулы
            double dnMinus = Double.MIN_VALUE;
            final int n = mas.length;

            for (int i = 0; i < n; i++) {
                dnMinus = Math.max(dnMinus, FunctionProvider.getEmpiricFunc(mas[i], mas) - (double) i / n);
            }

            return dnMinus;
        }

        /*
        private double[] getRandomValues(final int n){
        double[] values = new double[n];
        Random random = new Random();
        for (int i=0; i<n; i++){
        values[i] = random.nextInt(100000)/100000.0;
        }
        return values;
        }
        */
    }

    private static class Pirson {

        private Pirson() {

        }

        static boolean isFulfilled(final double[] mas, final double eps) {
            final double[] v = getV(mas); // частоты
            final double[] p = getP(mas); // вероятности
            final double x2 = getX2(v, p); // значение X2, рассчитанное по формуле
            final double delta = getDelta(eps); // псевдосгенерированное значение

            return x2 < delta;
        }

        private static double[] getV(final double[] mas) {
            final double[] v = new double[K]; // частота вхождения в каждый промежуток заносится в этот массив, K промежутков
            final double interval = 1.0 / K;

            for (double value : mas) {
                int pos = (int) Math.floor(value / interval);
                v[pos]++;
            }

            return v;
        }

        private static double[] getP(final double[] mas) {
            final double[] p = new double[K]; // вероятность считается с помощью функции распределения

            for (int i = 0; i < K; i++) {
                p[i] = FunctionProvider.getEmpiricFunc(((double) i + 1) / K, mas)
                        - FunctionProvider.getEmpiricFunc((double) i / K, mas);
            }

            return p;
        }

        private static double getX2(final double[] v, final double[] p) {
            double resultSum = 0; // применяем формулу

            for (int i = 0; i < K; i++) {
                resultSum += Math.pow((v[i] - N * p[i]), 2) / (N * p[i]);
            }

            return resultSum;
        }

        private static double getDelta(@SuppressWarnings("unused") final double eps){
            // используем таблицу, считая число степеней свободы k = 40 - 10 - 1 = 29
            // получили 42.6
            return 42.6;
        }

    }

}
