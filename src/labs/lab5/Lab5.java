package labs.lab5;

import java.util.Arrays;
import java.util.Random;

final class Lab5 {

    private Lab5(){

    }

    private static int N = 1000;
    private static int m = 10000;

    private static int[] i = new int[N + 1];

    private static double[] Qx = new double[N + 1];
    private static double[] Qy = new double[N + 1];

    private static double[] ksix = new double[m];
    private static double[] ksiy = new double[m];

    private static int n = 2;
    private static double[] hx = new double[n];
    private static double[] hy = new double[n];
    private static double[] pi = new double[n];
    private static double[][] p = new double[n][n];

    private static Random random = new Random();

    private static void initKsi(){
        for (int j = 0; j < m; j++) {
            ksix[j] = 0;
            ksiy[j] = 0;
        }
    }

    private static void initHxy(){
        hx[1] = 1.0;
        hx[0] = 0.0;
        hy[1] = 0.0;
        hy[0] = 1.0;
    }

    private static void initPi(){
        pi[1] = 0.5;
        pi[0] = 0.5;
    }

    private static void initP(){
        p[0][0] = 0.5;
        p[0][1] = 0.5;
        p[1][0] = 0.5;
        p[1][1] = 0.5;
    }

    private static void recalculateI(){
        double alpha =  ((double)random.nextInt(1000))/1000.0;

        if (alpha < pi[0]) {
            i[0] = 0;
        } else {
            i[0] = 1;
        }

        for (int k = 1; k <= N; k++) {
            alpha =  ((double)random.nextInt(1000))/1000.0;

            if (alpha < 0.5) {
                i[k] = 0;
            } else {
                i[k] = 1;
            }

        }
    }

    private static void recalculateQxy(final double[][] a){
        if (pi[i[0]] > 0) {
            Qx[0] = hx[i[0]] / pi[i[0]];
            Qy[0] = hy[i[0]] / pi[i[0]];
        } else {
            Qx[0] = 0;
            Qy[0] = 0;
        }

        for (int k = 1; k <= N; k++) {
            if (p[i[k - 1]][i[k]] > 0) {
                Qx[k] = Qx[k - 1] * a[i[k - 1]][i[k]] / p[i[k - 1]][i[k]];
                Qy[k] = Qy[k - 1] * a[i[k - 1]][i[k]] / p[i[k - 1]][i[k]];
            } else {
                Qx[k] = 0;
                Qy[k] = 0;
            }
        }
    }

    private static void recalculateKsi(final double[] b, final int j){
        for (int k = 0; k <= N; k++) {
            ksix[j] += Qx[k] * b[i[k]];
            ksiy[j] += Qy[k] * b[i[k]];
        }
    }

    static double[] calculate(final double[][] a, final double[] b){
        double[] result = new double[2];

        double x = 0;
        double y = 0;

        initKsi();
        initHxy();
        initPi();
        initP();

        for (int j = 0; j < m; j++) {
            recalculateI();
            recalculateQxy(a);
            recalculateKsi(b, j);
        }

        for (int k = 0; k < m; k++) {
            x += ksix[k];
            y += ksiy[k];
        }

        x /= (double)m;
        y /= (double)m;

        result[0] = x;
        result[1] = y;

        return result;
    }

}
