package labs.lab1.utilities;

public class FunctionProvider {
    private static double getI(final double x, final double right) { // реализация функции I<A>(x) = x включено в A? 1 : 0
        return x <= right ? 1 : 0;
    }

    public static double getEmpiricFunc(final double xBound, final double[] xi) { // функция распределения, xBound - точка, в которой мы хотим вычислить значение функции
        final int n = xi.length;
        double count = 0;

        for (double aX : xi) {
            count += getI(aX, xBound);
        }

        count /= n;

        return count;
    }
}
