import Labs.Lab1;

import java.util.Arrays;


public class Main {

    private static final int a0Star = 24389; // входные данные
    private static final int b = 24389;
    private static final int k = 32;

    private static final double delta = 1.36; // табличное знаение

    private static final double eps = 0.05; // уровень значимости


    public static void main(String[] args) {

        double[] mkm = Lab1.doTask1(a0Star, b); // МКМ реализация
        double[] maklarenMarsalia = Lab1.doTask2(a0Star, b, k); // реализация Макларена-Марсалии

        System.out.println("Реализация БСВ с помощью мультипликативного конгруэнтного метода:\n");
        System.out.println(Arrays.toString(mkm));
        System.out.println();
        System.out.println(Lab1.doTask3(mkm, delta, eps));

        System.out.println("\n----\n");

        System.out.println("Реализация БСВ с помощью метода Макларена-Марсальи:\n"); // оба датчика МКМ
        System.out.println(Arrays.toString(maklarenMarsalia));
        System.out.println();
        System.out.println(Lab1.doTask3(maklarenMarsalia, delta, eps));


    }


}