package com.monkgirl.java8inaction.chapter13;

import java.util.stream.LongStream;

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
     * @param args 命令行参数
     */
    public static void main(final String... args) {
        run1();
    }

    /**
     * run1.
     */
    public static void run1() {
        long startTime = System.nanoTime();
        long result1 = factorialIterative(100);
        long time1 = System.nanoTime();
        System.out.println((time1 - startTime) / 1_000 + " msecs. " + result1);
        long result2 = factorialRecursive(100);
        long time2 = System.nanoTime();
        System.out.println((time2 - time1) / 1_000 + " msecs. " + result2);
        long result3 = factorialStream(100);
        long time3 = System.nanoTime();
        System.out.println((time3 - time2) / 1_000 + " msecs. " + result3);
        long result4 = factorialTailRecursive(100);
        System.out.println((System.nanoTime() - time3) / 1_000 + " msecs. " + result4);
    }

    /**
     * 迭代.
     *
     * @param n 迭代次数
     * @return d迭代结果
     */
    public static long factorialIterative(final int n) {
        long r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    /**
     * 阶乘递归.
     *
     * @param n 迭代深度
     * @return 迭代结果
     */
    public static long factorialRecursive(final long n) {
        return n == 1 ? 1 : factorialRecursive(n - 1);
    }

    /**
     * stream， range.
     *
     * @param n 循环深度。
     * @return 列表求和结果
     */
    public static long factorialStream(final long n) {
        return LongStream.rangeClosed(1, n).reduce(1, (long a, long b) -> a * b);
    }

    /**
     * 阶乘尾递归.
     *
     * @param n 递归深度
     * @return 阶乘结果
     */
    public static long factorialTailRecursive(final long n) {
        return factorialHelper(1, n);
    }

    /**
     * 阶乘帮助方法.
     *
     * @param acc 起始
     * @param n   阶乘深度
     * @return 计算结果
     */
    private static long factorialHelper(final long acc, final long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }
}
