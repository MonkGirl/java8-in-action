package com.monkgirl.java8inaction.chapter14;

import com.monkgirl.java8inaction.common.LazyList;
import com.monkgirl.java8inaction.common.MyList;
import com.monkgirl.java8inaction.common.MyMathUtils;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Exercise {

    private Exercise() {

    }

    /**
     * 主方法.
     *
     * @param args 命令行入参
     */
    public static void main(final String... args) {
        //run1();
        //run2();
        run3();
    }

    /**
     * run1.
     */
    public static void run1() {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        double temp = convertCtoF.applyAsDouble(37.5);
        System.out.println(temp);

        double gbp = convertUSDtoGBP.applyAsDouble(1000);
        System.out.println(gbp);
    }

    /**
     * 双元运算符.
     *
     * @param f 数值1
     * @param d 数值2
     * @return 双元运算
     */
    public static DoubleUnaryOperator curriedConverter(double f, double d) {
        return (double x) -> x * f + d;
    }

    /**
     * 质数列出n以内的列表.
     *
     * @param n 长度
     * @return 质数列表
     */
    public static Stream<Integer> primes(final int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(MyMathUtils::isPrime)
                .limit(n);
    }

    /**
     * 查找质数.
     *
     * @param numbers 数字列表
     * @return 质数列表
     */
    public static MyList<Integer> primes(final MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    /**
     * lazyList.
     *
     * @param n 列表长度
     * @return LazyList
     */
    public static LazyList<Integer> from(final int n) {
        return new LazyList<Integer>(n, () -> from(n + 1));
    }

    /**
     * 打印所有.
     *
     * @param list 待打印列表
     * @param <T>  泛型
     */

    public static <T> void printAll(MyList<T> list) {
        while (!list.isEmpty()) {
            System.out.println(list.head());
            list = list.tail();
        }
    }

    /**
     * run2.
     */
    public static void run2() {
        //MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);

        LazyList<Integer> numbers1 = from(2);
        int two1 = primes(numbers1).head();
        int three1 = primes(numbers1).tail().head();
        int four1 = primes(numbers1).tail().tail().head();
        System.out.println(two1 + " " + three1 + " " + four1);
    }

    /**
     * run3.
     */
    public static void run3() {
        printAll(from(2));
    }
}
