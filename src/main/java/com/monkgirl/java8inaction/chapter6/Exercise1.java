package com.monkgirl.java8inaction.chapter6;

import com.monkgirl.java8inaction.common.CaloricLevel;
import com.monkgirl.java8inaction.common.Dish;
import com.monkgirl.java8inaction.common.Trader;
import com.monkgirl.java8inaction.common.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.monkgirl.java8inaction.common.Menu.MENU;
import static java.util.Currency.getInstance;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Exercise1 {

    private Exercise1() {

    }

    /**
     * raoul.
     */
    private static final Trader RAOUL = new Trader("Raoul", "Cambridge");
    /**
     * mario.
     */
    private static final Trader MARIO = new Trader("Mario", "Milan");
    /**
     * alan.
     */
    private static final Trader ALAN = new Trader("Alan", "Cambridge");
    /**
     * brian.
     */
    private static final Trader BRIAN = new Trader("Brian", "Roma");

    /**
     * 交易.
     */
    private static final List<Transaction> TRANSACTIONS =
            Arrays.asList(
                    new Transaction(BRIAN, 2011, 500, getInstance(Locale.US)),
                    new Transaction(RAOUL, 2012, 1000, getInstance(Locale.UK)),
                    new Transaction(RAOUL, 2011, 400),
                    new Transaction(MARIO, 2012, 710, getInstance(Locale.JAPAN)),
                    new Transaction(MARIO, 2012, 700, getInstance(Locale.US)),
                    new Transaction(ALAN, 2012, 950)
            );

    /**
     * 运行主方法.
     *
     * @param args 命令行参数
     */
    public static void main(final String... args) {
        //run1();
        //run2();
        //run3();
        //run4();
        //run5();
        run6();
    }

    /**
     * run1.
     */
    public static void run1() {
        Map<Currency, List<Transaction>> transactionsByCurrency = new HashMap<>();
        for (Transaction transaction : TRANSACTIONS) {
            Currency currency = transaction.getCurrency();
            List<Transaction> list = transactionsByCurrency.computeIfAbsent(currency, k -> new ArrayList<>());
            list.add(transaction);
        }
        System.out.println(transactionsByCurrency);

        Map<Currency, List<Transaction>> transactionsByCurrency1 = TRANSACTIONS.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrency1);

        System.out.println(new ArrayList<>(TRANSACTIONS));
    }

    /**
     * run2.
     */
    public static void run2() {
        System.out.println(MENU.size());
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingDouble(Dish::getCalories);
        Optional<Dish> mostCalorieDish = MENU.stream().max(dishCaloriesComparator);
        mostCalorieDish.ifPresent(System.out::println);

        MENU.stream().min(Comparator.comparingDouble(Dish::getCalories)).ifPresent(System.out::println);
        System.out.println(MENU.stream().mapToDouble(Dish::getCalories).sum());
        System.out.println(MENU.stream().collect(Collectors.averagingDouble(Dish::getCalories)));
        System.out.println(MENU.stream().collect(Collectors.summarizingDouble(Dish::getCalories)));

        System.out.println(MENU.stream().map(Dish::getName).collect(Collectors.joining(", ")));
        System.out.println(MENU.stream().map(Dish::getCalories).reduce(0.0, Double::sum));
        System.out.println(MENU.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(MENU.stream().reduce((d1, d2) -> d1.getCalories() < d2.getCalories() ? d1 : d2));
    }

    /**
     * run3.
     */
    public static void run3() {
        System.out.println(MENU.stream().map(Dish::getCalories).reduce(0.0, Double::sum));
        System.out.println(MENU.stream().mapToDouble(Dish::getCalories).sum());
        System.out.println(MENU.stream().collect(Collectors.groupingBy(Dish::getType)));
    }

    /**
     * run4.
     */
    public static void run4() {
        System.out.println(MENU.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() < 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() > 700) {
                        return CaloricLevel.FAT;
                    } else {
                        return CaloricLevel.NORMAL;
                    }
                })));
    }

    /**
     * run5.
     */
    public static void run5() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> map = MENU.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
                    if (dish.getCalories() < 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() > 700) {
                        return CaloricLevel.FAT;
                    } else {
                        return CaloricLevel.NORMAL;
                    }
                })));
        System.out.println(map);

        Map<Dish.Type, Long> map1 = MENU.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(map1);

        Map<Dish.Type, Optional<Dish>> map2 = MENU.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.minBy(Comparator.comparingDouble(Dish::getCalories)))
                );
        System.out.println(map2);
    }

    /**
     * run6.
     */
    public static void run6() {
        Map<Dish.Type, Dish> map1 = MENU.stream()
                .collect(Collectors.toMap(
                                Dish::getType,
                                Function.identity(),
                                BinaryOperator.maxBy(Comparator.comparingDouble(Dish::getCalories))
                        )
                );
        System.out.println(map1);

        Map<Dish.Type, Double> map2 = MENU.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.summingDouble(Dish::getCalories)));
        System.out.println(map2);

        Map<Dish.Type, Set<CaloricLevel>> map3 = MENU.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
                    if (dish.getCalories() < 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() > 700) {
                        return CaloricLevel.FAT;
                    } else {
                        return CaloricLevel.NORMAL;
                    }
                }, Collectors.toSet())));
        System.out.println(map3);

        Map<Dish.Type, Set<CaloricLevel>> map4 = MENU.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
                    if (dish.getCalories() < 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() > 700) {
                        return CaloricLevel.FAT;
                    } else {
                        return CaloricLevel.NORMAL;
                    }
                }, Collectors.toCollection(HashSet::new))));
        System.out.println(map4);
    }
}
