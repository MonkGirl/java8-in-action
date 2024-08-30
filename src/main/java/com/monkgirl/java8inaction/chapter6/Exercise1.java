package com.monkgirl.java8inaction.chapter6;

import com.monkgirl.java8inaction.common.CaloricLevel;
import com.monkgirl.java8inaction.common.Dish;
import com.monkgirl.java8inaction.common.Trader;
import com.monkgirl.java8inaction.common.Transaction;

import java.util.*;
import java.util.stream.Collectors;

import static com.monkgirl.java8inaction.common.Menu.menu;
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

    public static void main(final String... args) {
        //run1();
        //run2();
        //run3();
        //run4();
        //run5();
        run6();
    }

    public static void run1() {
        Map<Currency, List<Transaction>> transactionsByCurrency = new HashMap<>();
        for (Transaction transaction : TRANSACTIONS) {
            Currency currency = transaction.getCurrency();
            List<Transaction> list = transactionsByCurrency.get(currency);
            if (list == null) {
                list = new ArrayList<>();
                transactionsByCurrency.put(currency, list);
            }
            list.add(transaction);
        }
        System.out.println(transactionsByCurrency);

        Map<Currency, List<Transaction>> transactionsByCurrency1 = TRANSACTIONS.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrency1);

        System.out.println(TRANSACTIONS.stream().collect(Collectors.toList()));
    }

    public static void run2() {
        System.out.println(menu.stream().count());
        System.out.println(menu.stream().collect(Collectors.counting()));
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingDouble(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish.get());

        System.out.println(menu.stream().collect(Collectors.minBy(Comparator.comparingDouble(Dish::getCalories))).get());
        System.out.println(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)));
        System.out.println(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)));
        System.out.println(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)));

        System.out.println(menu.stream().map(Dish::getName).collect(Collectors.joining(", ")));
        System.out.println(menu.stream().collect(Collectors.reducing(0.0, Dish::getCalories, (i, j) -> i + j)));
        System.out.println(menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
        System.out.println(menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() < d2.getCalories() ? d1 : d2)));
    }

    public static void run3() {
        System.out.println(menu.stream().collect(Collectors.reducing(0.0, Dish::getCalories, Double::sum)));
        System.out.println(menu.stream().mapToDouble(Dish::getCalories).sum());
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType)));
    }

    public static void run4() {
        System.out.println(menu.stream()
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

    public static void run5() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> map = menu.stream()
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

        Map<Dish.Type, Long> map1 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(map1);

        Map<Dish.Type, Optional<Dish>> map2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.minBy(Comparator
                        .comparingDouble(Dish::getCalories))));
        System.out.println(map2);
    }

    public static void run6() {
        Map<Dish.Type, Dish> map1 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Dish::getCalories)), Optional::get)));
        System.out.println(map1);

        Map<Dish.Type, Double> map2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.summingDouble(Dish::getCalories)));
        System.out.println(map2);

        Map<Dish.Type, Set<CaloricLevel>> map3 = menu.stream()
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

        Map<Dish.Type, Set<CaloricLevel>> map4 = menu.stream()
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
