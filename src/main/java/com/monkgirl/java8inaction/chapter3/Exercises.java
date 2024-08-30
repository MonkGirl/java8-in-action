package com.monkgirl.java8inaction.chapter3;

import com.monkgirl.java8inaction.common.Apple;
import com.monkgirl.java8inaction.common.Computer;
import com.monkgirl.java8inaction.common.TriFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * 练习内容.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Exercises {
    private Exercises() {

    }

    /**
     * 主方法.
     *
     * @param args 命令行入参
     */
    public static void main(final String... args) {
        //run1();
        //run2();
        //run3();
        //run4();
        //run5();
        //run6();
        run7();
    }

    /**
     * run1.
     */
    public static void run1() {
        BiFunction<String, Integer, Apple> bf = Apple::new;
        List<Apple> inventory = new ArrayList<>();
        inventory.add(bf.apply("green", 149));
        inventory.add(bf.apply("yellow", 90));
        inventory.add(bf.apply("red", 298));
        System.out.println(inventory);
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);
    }

    /**
     * run2.
     */
    public static void run2() {
        String s = "10";
        Function<String, Double> f = Double::parseDouble;
        System.out.println(f.apply(s));

        Function<String, Integer> fu = String::length;
        System.out.println(fu.apply(s));

        BiFunction<String, Integer, Apple> bf = Apple::new;
        Apple apple = bf.apply("pink", 706);

        Function<Apple, Double> fun = Apple::getWeight;
        System.out.println(fun.apply(apple));

        TriFunction<String, String, String, Computer> tf = Computer::new;
        System.out.println(tf.apply("amd", "kingston", "westdata"));
    }

    /**
     * run3.
     */
    public static void run3() {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("green", 405));
        inventory.add(new Apple("red", 382));
        inventory.add(new Apple("purple", 430));
        inventory.add(new Apple("red", 430));
        System.out.println("before: " + inventory);

        //method 1
        inventory.sort(new AppleComparator());
        System.out.println("method1: " + inventory);
        Collections.shuffle(inventory);
        System.out.println("restore: " + inventory);

        //method 2
        inventory.sort(new Comparator<Apple>() {
            public int compare(final Apple a1, final Apple a2) {
                return Double.compare(a1.getWeight(), a2.getWeight());
            }
        });
        System.out.println("method2: " + inventory);
        Collections.shuffle(inventory);
        System.out.println("restore: " + inventory);

        //method 3
        inventory.sort((a1, a2) -> Double.compare(a1.getWeight(), a2.getWeight()));
        System.out.println("method3: " + inventory);
        Collections.shuffle(inventory);
        System.out.println("restore: " + inventory);

        //method 4
        inventory.sort(comparing(Apple::getWeight));
        System.out.println("method4: " + inventory);
        Collections.shuffle(inventory);
        System.out.println("restore: " + inventory);

        //thenComparing
        inventory.sort(comparing(Apple::getWeight).thenComparing(Apple::getColor));
        System.out.println("thenComparing: " + inventory);
    }

    /**
     * run4.
     */
    public static void run4() {
        Predicate<Apple> redApple = (a) -> "red".equals(a.getColor());
        Apple a = new Apple("red", 302);
        Apple b = new Apple("green", 159);
        System.out.println(redApple.test(a));

        Predicate<Apple> noRedApple = redApple.negate();
        System.out.println(noRedApple.test(b));

        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 310);
        System.out.println(redAndHeavyApple.test(a));
    }

    /**
     * run5.
     */
    public static void run5() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> p = f.compose(g);
        System.out.println(h.apply(1));
        System.out.println(p.apply(1));
    }

    /**
     * run6.
     */
    public static void run6() {
        String mailText = "Long time no see, labda.";
        Function<String, String> addHeader = Letter::addHeader;

        Function<String, String> transformationPipeline;

        //transformationPipeline = addHeader.andThen(Letter::checkSpelling);
        transformationPipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        System.out.println(transformationPipeline.apply(mailText));
    }

    /**
     * run7.
     */
    public static void run7() {
        DoubleFunction<Double> df = x -> x + 10;
        System.out.println(integrate(df, 3, 7));
    }

    private static double integrate(final DoubleFunction<Double> f,
                                    final double a,
                                    final double b) {
        return (f.apply(a) + f.apply(b)) * Math.abs(a - b) / 2;
    }
}

class AppleComparator implements Comparator<Apple> {
    public int compare(final Apple a1, final Apple a2) {
        return Double.compare(a1.getWeight(), a2.getWeight());
    }
}

final class Letter {
    private Letter() {

    }

    public static String addHeader(final String text) {
        return "From Raol, Mario and Alan: \n" + text;
    }

    public static String addFooter(final String text) {
        return text + "\nKind regards";
    }

    public static String checkSpelling(final String text) {
        return text.replaceAll("labda", "lambda");
    }
}
