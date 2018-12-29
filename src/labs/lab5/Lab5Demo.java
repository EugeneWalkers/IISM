package labs.lab5;

import java.math.BigDecimal;

public final class Lab5Demo {
    private Lab5Demo() {

    }

    public static void launch() {
        final double[][] a = new double[2][2];

        a[0][0] = 0.6d;
        a[0][1] = -0.3d;
        a[1][0] = 0.4d;
        a[1][1] = 0.2d;

        final double[] b = new double[2];

        b[0] = 1.0d;
        b[1] = -2.0d;

        double[] result = Lab5.calculate(a, b);

        System.out.print("x = ");

        System.out.println(new BigDecimal(result[0]).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.print("y = ");
        System.out.println(new BigDecimal(result[1]).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }


}
