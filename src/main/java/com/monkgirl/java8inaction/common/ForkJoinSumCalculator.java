package com.monkgirl.java8inaction.common;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin并发，求和计算器.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@AllArgsConstructor
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    /**
     * 数值数组.
     */
    private final long[] numbers;
    /**
     * 起始标志.
     */
    private final int start;
    /**
     * 终止标志.
     */
    private final int end;

    /**
     * 阈值.
     */
    public static final long THRESHOLD = 100;

    /**
     * 对数组进行分组求和.
     *
     * @param newNumbers 数组
     */
    public ForkJoinSumCalculator(final long[] newNumbers) {
        this(newNumbers, 0, newNumbers.length);
    }

    /**
     * 计算结果.
     *
     * @return 求和结果
     */
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return rightResult + leftResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
