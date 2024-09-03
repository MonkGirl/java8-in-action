package com.monkgirl.java8inaction.review;

import com.monkgirl.java8inaction.common.CaloricLevel;
import com.monkgirl.java8inaction.common.Dish;
import com.monkgirl.java8inaction.common.Menu;
import com.monkgirl.java8inaction.common.MyMathUtils;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 复习回顾.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Review {
    private Review() {

    }

    /**
     * 处理链表.
     */
    private static final List<String> LIST = new ArrayList<>();

    static {
        LIST.add("Icarus");
        LIST.add("Aurora");
        LIST.add("Adam");
        LIST.add("Cynthia");
        LIST.add("Zeus");
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
        //run7();
        run8();
    }

    /**
     * run1.
     */
    public static void run1() {
        List<String> result = filter((String str) -> str.length() > 4);
        System.out.println(result);

        filter((String str) -> System.out.println(str + ": " + str.length()));

        List<Integer> resultList = filter(String::length);
        System.out.println(resultList);
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> filter(final Predicate<T> p) {
        List<T> resultList = new ArrayList<>();
        for (T t : (List<T>) Review.LIST) {
            if (p.test(t)) {
                resultList.add(t);
            }
        }
        return resultList;
    }

    @SuppressWarnings("unchecked")
    private static <T> void filter(final Consumer<T> c) {
        for (T t : (List<T>) Review.LIST) {
            c.accept(t);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T, R> List<R> filter(final Function<T, R> f) {
        List<R> resultList = new ArrayList<>();
        for (T t : (List<T>) Review.LIST) {
            resultList.add(f.apply(t));
        }
        return resultList;
    }

    /**
     * run2.
     */
    public static void run2() {
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println(evenNumbers.test(1000));

        Predicate<Integer> oddNumbers = i -> i % 2 != 0;
        System.out.println(oddNumbers.test(1000));
    }

    /**
     * run3.
     */
    public static void run3() {
        LIST.sort(String::compareTo);
        System.out.println(LIST);

        LIST.sort(Comparator.comparing(String::length));
        System.out.println(LIST);

        LIST.sort(Comparator.comparing(String::toString));
        System.out.println(LIST);

        LIST.sort(Comparator.comparing(String::length).thenComparing(String::toString).reversed());
        System.out.println(LIST);

        Predicate<String> startA = str -> str.startsWith("A");
        List<String> newList = LIST.stream().filter(startA.negate()).collect(Collectors.toList());
        System.out.println(newList);
    }

    /**
     * run4.
     */
    public static void run4() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> numbers2 = Arrays.asList(2, 3);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        for (int[] i : pairs) {
            System.out.println(Arrays.toString(i));
        }

        List<int[]> pairs1 = numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 2 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        for (int[] i : pairs1) {
            System.out.println(Arrays.toString(i));
        }
    }

    /**
     * run5.
     */

    public static void run5() {
        List<Dish> dishes = Menu.menu;
        System.out.println(dishes);
        long num = dishes.size();
        System.out.println(num);

        Map<Dish.Type, List<Dish>> dishes1 = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishes1);

        Optional<Dish> mostCalorieDish = dishes.stream()
                .max(Comparator.comparingDouble(Dish::getCalories));
        System.out.println(mostCalorieDish);

        Optional<Dish> leastCalorieDish = dishes.stream()
                .min(Comparator.comparingDouble(Dish::getCalories));
        System.out.println(leastCalorieDish);

        double sumCalories = dishes.stream()
                .mapToDouble(Dish::getCalories)
                .sum();
        System.out.println(sumCalories);

        double averagingCalories = dishes.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories));
        System.out.println(averagingCalories);

        DoubleSummaryStatistics menuStatistics = dishes.stream()
                .collect(Collectors.summarizingDouble(Dish::getCalories));
        System.out.println(menuStatistics);

        String shortMenu = dishes.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        System.out.println(shortMenu);

        double sumCalories1 = dishes.stream()
                .map(Dish::getCalories)
                .reduce(0.0, Double::sum);
        System.out.println(sumCalories1);

        Optional<Dish> mostCalories1 = dishes.stream()
                .reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        System.out.println(mostCalories1);

        double sumCalories2 = dishes.stream()
                .mapToDouble(Dish::getCalories)
                .sum();
        System.out.println(sumCalories2);
    }

    /**
     * run6.
     */
    public static void run6() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = Menu.menu
                .stream()
                .collect(Collectors
                        .groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        })));
        System.out.println(dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> dishesByTypeNum = Menu.menu
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(dishesByTypeNum);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = Menu.menu
                .stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparingDouble(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByType1 = Menu.menu
                .stream()
                .collect(Collectors.toMap(
                        Dish::getType,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparingDouble(Dish::getCalories)))
                );
        System.out.println(mostCaloricByType1);

        Map<Dish.Type, Double> sumCaloricByType = Menu.menu.stream()
                .collect(Collectors
                        .groupingBy(Dish::getType,
                                Collectors.summingDouble(Dish::getCalories)));
        System.out.println(sumCaloricByType);
    }

    /**
     * run7.
     */
    public static void run7() {
        Map<Boolean, List<Dish>> map = Menu.menu
                .stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(map);

        Map<Boolean, Map<Dish.Type, List<Dish>>> map1 = Menu.menu
                .stream()
                .collect(Collectors.partitioningBy(
                        Dish::isVegetarian,
                        Collectors.groupingBy(Dish::getType))
                );
        System.out.println(map1);

        System.out.println(isPrime(85));

        System.out.println(partitionPrimes(25));
    }

    private static boolean isPrime(final int candidate) {
        return MyMathUtils.isPrime(candidate);
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(final int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(Collectors.partitioningBy(Review::isPrime));
    }

    private static long parallelSum(final long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    private static long sequentSum(final long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * run8.
     */
    public static void run8() {
        long startTime1 = System.nanoTime();
        System.out.println(parallelSum(100));
        System.out.println((System.nanoTime() - startTime1) / 1_000 + " ms");

        long startTime2 = System.nanoTime();
        System.out.println(sequentSum(100));
        System.out.println((System.nanoTime() - startTime2) / 1_000 + " ms");
    }
}
