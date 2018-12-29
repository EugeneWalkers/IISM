package labs.utilities;

public final class LaplaceFunction {

    private static double p = 0.2316419;
    private static double b1 = 0.319381530;
    private static double b2 = -0.356563782;
    private static double b3 = 1.781477937;
    private static double b4 = -1.821255978;
    private static double b5 = 1.330274429;

    private LaplaceFunction(){

    }

    public static double getValue(final double x){


        double t = 1 / (1 + p * x);

        return b1 * t
                + b2 * Math.pow(t, 2)
                + b3 * Math.pow(t, 3)
                + b4 * Math.pow(t, 4)
                + b5 * Math.pow(t, 5);
    }

}
