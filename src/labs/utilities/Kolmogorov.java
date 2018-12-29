package labs.utilities;

import labs.lab1.utilities.FunctionProvider;

public final class Kolmogorov {

    private Kolmogorov() {

    }

    public static boolean isFulfilled(final Pair<Double, Double>[] mass, final double delta) { // критерий выполняется, если sqrt(n)*Dn <= delta
        final double dn = getDn(Pair.Converter.getValuesFromPair(mass));
        final int n = mass.length;
        final double result = Math.sqrt(n) * dn;

        return result <= delta;
    }

    public static boolean isFulfilled(final  Pair<Double, Double>[] mass, final Distribution function, final double delta){
        final double dn = getDn(Pair.Converter.getValuesFromPair(mass), function);
        final int n = mass.length;
        final double result = Math.sqrt(n) * dn;

        return result <= delta;
    }

    private static double getDn(final double[] mas) { // Dn - это максимум из Dn- и Dn+, каждая считается по своей формуле, используя функцию распределения
        final double dnPlus = getDnPlus(mas);
        final double dnMinus = getDnMinus(mas);

        return Math.max(dnPlus, dnMinus);
    }

    private static double getDn(final double[] mas, final Distribution function) { // Dn - это максимум из Dn- и Dn+, каждая считается по своей формуле, используя функцию распределения
        final double dnPlus = getDnPlus(mas, function);
        final double dnMinus = getDnMinus(mas, function);

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

    private static double getDnPlus(final double[] mas, final Distribution function) { // посчитали Dn+ исходя из формулы
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

    private static double getDnMinus(final double[] mas, final Distribution function) { // посчитали Dn- исходя из формулы
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