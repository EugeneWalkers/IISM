package labs.utilities;

public final class StandartDistributionGenerator {

    private static final double[] array;

    static {
        array = new double[6000];

        final double[] alpha = new double[12000];
        double alphaWithAsterisc1 = 161051;
        double beta1 = 161051;

        final int M1 = (int) Math.pow(2, 31);

        for (int i = 0; i < alpha.length; i++) {
            double value = alphaWithAsterisc1 / M1;
            alpha[i] = value;
            alphaWithAsterisc1 = (beta1 * alphaWithAsterisc1) - M1 * Math.floor(beta1 * alphaWithAsterisc1 / M1);
        }

        for (int i = 0; i < array.length; i++) {
            double value = Math.sqrt((-2) * Math.log(alpha[i])) * Math.cos(2 * Math.PI * alpha[4000 + i]);
            array[i] = value;
        }
    }

    private StandartDistributionGenerator() {

    }

    public static double[] getArray() {
        return array;
    }


}
