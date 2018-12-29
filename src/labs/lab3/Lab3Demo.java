package labs.lab3;

import labs.lab3.utilities.concreteDistributions.NormalDistribution;
import labs.lab3.utilities.concreteDistributions.UniformDistribution;
import labs.lab3.utilities.concreteDistributions.XSquareDistribution;
import labs.utilities.*;

import java.util.Arrays;

public final class Lab3Demo {

    private final static int errorsSize = 2;

    private final static double m = 0;
    private final static double sSquare = 64;

    private final static double level = 0.05;

    public static void launch(final int n, final double eps) {

        Lab3.N = n;

        final ContinuousDistribution[] functions = new ContinuousDistribution[2];

        functions[0] = new NormalDistribution(m, sSquare, n);
        functions[1] = new XSquareDistribution(4);
        //functions[2] = new UniformDistribution(0, 1);

        for (final ContinuousDistribution function : functions) {

            final Pair<Double, Double>[] result = Lab3.doTask1(function);
            System.out.println(function.getDescription());
            System.out.println("Значение функции распределения:");
            System.out.println(Arrays.toString(result));
            System.out.println("Математическое ожидание (табличное значение): " + Lab3.doTask2TableExpectedValue(function));
            System.out.println("Математическое ожидание (полученное значение): " + Lab3.doTask2RealExpectedValue(function, result));
            System.out.println("Дисперсия (табличное значение): " + Lab3.doTask2TableVariance(function));
            System.out.println("Дисперсия (полученное значение): " + Lab3.doTask2RealVariance(function, result));
            System.out.println("Критерий Пирсона: " + Pearson.isFulfilled(result, function, eps));
            System.out.println("Критерий Колмогорова: " + Kolmogorov.isFulfilled(result, function, level));
            System.out.println("Значения ошибок 1 рода:");
            System.out.println(Arrays.toString(Pearson.getErrorsMass(function, errorsSize, n)));
            System.out.println("-------------------------------------------------------");

        }


    }

}
