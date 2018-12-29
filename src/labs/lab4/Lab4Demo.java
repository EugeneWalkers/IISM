package labs.lab4;

import java.util.Arrays;

public final class Lab4Demo {

    private Lab4Demo() {

    }

    public static void launch() {
        System.out.println(calculateTask1());
        System.out.println(calculateTask2());
        //System.out.println(Arrays.toString(calculateTask3()));
    }

    private static double calculateTask1() {
        double sum1 = 0;

        for (int i = 4; i < 100000; i++) {
            sum1 += Lab4.getF1(i);
        }

        return sum1;
    }

    private static double calculateTask2() {
        double sum2 = 0;

        for (double y = Math.E; y < Math.PI; y += 0.001) {
            for (double x = Math.pow(Math.E, 3); x < Math.pow(Math.PI, 3); x += 0.001) {
                sum2 += Lab4.getF2(x, y) * 0.000001;
            }
        }

        return sum2;
    }

    private static double[] calculateTask3() {
        final double[][] a = {
                {0.6, -0.3},
                {0.4, 0.2}
        };

        final double[] b = {1.0, -2.0};

        return Lab4.calculate(a, b);
    }

}
