package com.monkgirl.java8inaction.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * 示例.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Demo {
    private Demo() {

    }

    /**
     * 主方法.
     *
     * @param args 命令行入参
     */
    public static void main(final String... args) {
        run();
    }

    /**
     * 运行方法.
     */
    public static void run() {
        List<String> list = Arrays.asList("just a test".split(" "));
        List<String> result = filter(list, (String s) -> !s.isEmpty());
        List<String> result1 = filter(list, (String s) -> s.length() > 2);
        System.out.println(result);
        System.out.println(result1);

        forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);

        System.out.println(map(Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"), String::length));

        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println(evenNumbers.test(1000));

        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
        System.out.println(oddNumbers.test(1000));

        int portNumber = 1337;
        int finalPortNumber = portNumber;
        Runnable r1 = () -> System.out.println(finalPortNumber);
        r1.run();
        portNumber = 1338;
    }

    /**
     * 过滤.
     *
     * @param list      待处理的链表
     * @param predicate 操作谓词
     * @param <T>       泛型
     * @return 操作结果
     */
    public static <T> List<T> filter(final List<T> list,
                                     final Predicate<T> predicate) {
        List<T> results = new ArrayList<T>();
        for (T t : list) {
            if (predicate.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * 循环遍历.
     *
     * @param list     待处理的链表
     * @param consumer 消费者
     * @param <T>      泛型
     */
    public static <T> void forEach(final List<T> list,
                                   final Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    /**
     * 映射.
     *
     * @param list     待处理链表
     * @param function 函数
     * @param <T>      类型泛型
     * @param <R>      参数泛型
     * @return 处理后结果
     */
    public static <T, R> List<R> map(final List<T> list,
                                     final Function<T, R> function) {
        List<R> result = new ArrayList<R>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

}
