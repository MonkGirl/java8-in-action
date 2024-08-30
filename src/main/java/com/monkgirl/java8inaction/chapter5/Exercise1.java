package com.monkgirl.java8inaction.chapter5;

import com.monkgirl.java8inaction.common.Trader;
import com.monkgirl.java8inaction.common.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

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
     * Raoul的交易.
     */
    private static final Trader RAOUL = new Trader("Raoul", "Cambridge");
    /**
     * Marion的交易.
     */
    private static final Trader MARIO = new Trader("Mario", "Milan");
    /**
     * Alan的交易.
     */
    private static final Trader ALAN = new Trader("Alan", "Cambridge");
    /**
     * Brian的交易.
     */
    private static final Trader BRIAN = new Trader("Brian", "Roma");

    /**
     * 交易列表.
     */
    private static final List<Transaction> TRANSACTIONS = Arrays.asList(
            new Transaction(BRIAN, 2011, 500),
            new Transaction(RAOUL, 2012, 1000),
            new Transaction(RAOUL, 2011, 400),
            new Transaction(MARIO, 2012, 710),
            new Transaction(MARIO, 2012, 700),
            new Transaction(ALAN, 2012, 950));

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
     * 找出2011年发生的所有交易，并按交易额排序（从低到高）.
     */
    public static void run1() {
        List<Transaction> trans = TRANSACTIONS
                .stream()
                .filter(t -> t.getYear() == 2011)
                //.sorted((a, b)->a.getValue()-b.getValue())
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(trans);
    }

    /**
     * 交易员都在哪些不同的地方工作过.
     */
    public static void run2() {
        List<String> cities = TRANSACTIONS.parallelStream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(cities);
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序.
     */
    public static void run3() {
        List<Trader> traders = TRANSACTIONS.parallelStream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(comparing(Trader::getName))
                .distinct()
                .collect(toList());
        System.out.println(traders);
    }

    /**
     * 返回交易员的姓名字符串，按字母顺序排序.
     */
    public static void run4() {
        List<String> names = TRANSACTIONS.parallelStream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .collect(toList());
        System.out.println(names);
    }

    /**
     * 有没有交易员是在米兰工作的.
     */
    public static void run5() {
        List<Trader> traders = TRANSACTIONS.parallelStream()
                .map(Transaction::getTrader)
                .filter(d -> "Milan".equals(d.getCity()))
                .distinct()
                .collect(toList());
        System.out.println(traders);
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额.
     */
    public static void run6() {
        List<Integer> values = TRANSACTIONS.parallelStream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(toList());
        System.out.println(values);
    }

    /**
     * 所有交易中，最高交易额是多少.
     */
    public static void run7() {
        Optional<Integer> max = TRANSACTIONS.parallelStream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        max.ifPresent(System.out::println);
    }

    /**
     * 找到交易额最小的交易.
     */
    public static void run8() {
        Optional<Transaction> trans = TRANSACTIONS.parallelStream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        trans.ifPresent(System.out::println);
    }
}
