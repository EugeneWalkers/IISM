package labs.utilities;

import java.util.ArrayList;

public final class GammaFunction {


    private static final double eps = 0.005;

    private static ArrayList<Double> mas;

    static {
        mas = new ArrayList<>();
    }

    private GammaFunction() {

    }

    public static double getEilerValue(double z) {

        if (mas.size() == z) {

        }

        double result = 1 / z;

        double oldValue = 0;
        double i = 1;

        while (Math.abs(result - oldValue) > eps) {
            oldValue = result;
            result *= Math.pow(1 + 1 / i, z) / (1 + z / i);
            i++;
        }

        return result;
    }

}
