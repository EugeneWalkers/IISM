package labs.lab4;

import java.util.Random;

public class Lab4 {

    private static final double[] pi = {
            0.5, 0.5
    };

    private static final double[][] p = {
            {0.5, 0.5},
            {0.5, 0.5}
    };

    private static double[] h;

    private static final int N = 1000;
    private static final int M = 10000;

    private static int[] i;

    private static double[] q = new double[N+1];

    private static double[] ksi = new double[N];
    private static void generateEmptyKsi(){
        for (int k=0; k<N; k++){
            ksi[k] = 0;
        }
    }

    static double getF1(final int x) {
        return Math.exp(-x) / ((double) x * Math.sqrt(1.0 + Math.pow(x, 3)));
    }

    static double getF2(final double x, final double y) {
        return Math.atan(x + y);
    }

    static double[] calculate(final double[][] a, final double[] b) {
        h = new double[]{1, 0};
        generateEmptyKsi();
        generateI();
        generateQ(a);

        generateEmptyKsi();

        for (int k=0; k<M; k++){
            generateKsi(b);
        }

        double resultX = 0.0;

        for (int k=0; k<N; k++){
            resultX += ksi[k];
        }

        h = new double[]{0, 1};

        generateQ(a);
        generateEmptyKsi();

        for (int k=0; k<M; k++){
            generateKsi(b);
        }

        double resultY = 0.0;

        for (int k=0; k<N; k++){
            resultY += ksi[k];
        }

        resultX/=(double)N;
        resultY/=(double)N;


        return new double[]{resultX, resultY};
    }

    private static double getQI0() {
        return pi[i[0]] == 0 ? 0 : h[i[0]] / pi[i[0]];
    }

    private static void generateQ(final double[][] a) {
        q[0] = getQI0();

        for (int k=1; k<=N; k++){
            if (p[i[k - 1]][i[k]] > 0) {
                q[k] = q[k-1]*a[i[k-1]][i[k]]/p[i[k-1]][i[k]];
            }
            else{
                q[k] = 0;
            }
        }
    }

    private static void generateKsi(final double[] b) {
        for (int k=0; k<N; k++){
            ksi[k] += q[k]*b[i[k]];
        }
    }

    private static void generateI() {
        final Random random = new Random();

        double alp = random.nextInt(N) / (double) N;

        i = new int[N + 1];

        if (alp < pi[0]) {
            i[0] = 0;
        } else {
            i[0] = 1;
        }

        for (int k = 0; k <= N; k++) {
            alp = random.nextInt(N) / (double) N;
            if (alp < 0.5) {
                i[k] = 0;
            } else {
                i[k] = 1;
            }
        }
    }


}
