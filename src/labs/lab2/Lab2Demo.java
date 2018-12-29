package labs.lab2;

import labs.utilities.CumulativeDistribution;
import labs.lab2.utilities.concreteDistributions.*;
import labs.utilities.Pair;
import labs.utilities.Pearson;

import java.util.Arrays;

public final class Lab2Demo {

    private final static int rNegativeBinomial = 4;
    private final static double pNegativeBinomial = 0.2;
    private final static int errorsSize = 2;

    private final static double pGeometric = 0.25;

    private final static double pBinomial = 0.4;

    private final static double lambda = 3;

    private final static int D = 80;
    private final static int n = 45;

    private Lab2Demo() {

    }

    public static void launch(final int n, final double eps) {

        Lab2.N = n;

        final CumulativeDistribution[] functions = new CumulativeDistribution[5];
        functions[0] = new NegativeBinomialDistribution(rNegativeBinomial, pNegativeBinomial);
        functions[1] = new GeometricDistribution(pGeometric);
        functions[2] = new BinomialDistribution(pBinomial, n);
        functions[3] = new PoissonDistribution(lambda);
        functions[4] = new HypergeometricDistribution(n, D, Lab2Demo.n);

        for (final CumulativeDistribution function : functions) {

            final Pair<Double, Double>[] result = Lab2.doTask1(function);
            System.out.println(function.getDescription());
            System.out.println("Значение функции распределения:");
            System.out.println(Arrays.asList(result).subList(0, 6).toString());
            System.out.println("Математическое ожидание (табличное значение): " + Lab2.doTask2TableExpectedValue(function));
            System.out.println("Математическое ожидание (полученное значение): " + Lab2.doTask2RealExpectedValue(function, result));
            System.out.println("Дисперсия (табличное значение): " + Lab2.doTask2TableVariance(function));
            System.out.println("Дисперсия (полученное значение): " + Lab2.doTask2RealVariance(function, result));
            System.out.println("Критерий Пирсона: " + Pearson.isFulfilled(result, function, eps));
            System.out.println("Значения ошибок 1 рода:");
            System.out.println(Arrays.toString(Pearson.getErrorsMass(function, errorsSize, n)));
            System.out.println("-------------------------------------------------------");

        }


    }

}
