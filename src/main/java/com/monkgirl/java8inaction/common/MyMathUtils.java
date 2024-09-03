package com.monkgirl.java8inaction.common;

import java.util.stream.IntStream;

/**
 * math工具类.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class MyMathUtils {
    private MyMathUtils() {

    }

    /**
     * 是否是素数.
     *
     * @param candidate 被检测的数值
     * @return 是否是素数
     */
    public static boolean isPrime(final int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
