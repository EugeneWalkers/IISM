package labs.lab1;

import labs.utilities.Pair;

import java.util.Arrays;

public final class Lab1Demo {

    private static final int a0Star = 24389; // входные данные
    private static final int b = 24389;
    private static final int k = 32;

    private static final double delta = 1.36; // табличное значение

    private Lab1Demo() {

    }

    public static void launch(final int n, final double eps) {

        Lab1.N = n;

        final Pair<Double, Double>[] mkm = Lab1.doTask1(a0Star, b); // МКМ реализация
        final Pair<Double, Double>[] maklarenMarsalia = Lab1.doTask2(a0Star, b, k); // реализация Макларена-Марсалии

        System.out.println("Реализация БСВ с помощью мультипликативного конгруэнтного метода:");
        System.out.println(Arrays.asList(mkm).subList(0, 5).toString());
        System.out.println(Lab1.doTask3(mkm, delta, eps));
        System.out.println("----");
        System.out.println("Реализация БСВ с помощью метода Макларена-Марсальи:"); // оба датчика МКМ
        System.out.println(Arrays.asList(maklarenMarsalia).subList(0, 5).toString());
        System.out.println(Lab1.doTask3(maklarenMarsalia, delta, eps));

    }

}
