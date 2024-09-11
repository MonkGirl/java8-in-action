package com.monkgirl.java8inaction.chapter7;

import com.monkgirl.java8inaction.common.Accumulator;
import com.monkgirl.java8inaction.common.ForkJoinSumCalculator;
import com.monkgirl.java8inaction.common.WordCounter;
import com.monkgirl.java8inaction.common.WordCounterSpliterator;

import java.math.BigDecimal;
import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class ParallelStreams {
    private ParallelStreams() {

    }

    /**
     * sentence.
     */
    private static final String SENTENCE = "Nel mezzo del camin di nostra vita mi ritrovai in una selva oscura "
            + "che la dritta via era smarrita";

    /**
     * 数据量.
     */
    private static final long NUM = 10_000_000L;

    /**
     * 主方法.
     *
     * @param args 命令行入参
     */
    public static void main(final String... args) {
        //run1();
        run2();
    }

    /**
     * run1.
     */
    public static void run1() {
        System.out.println("Sequential sum done in: "
                + measureSumPerf(ParallelStreams::sequentialSum, NUM)
                + " msecs");
        System.out.println("Parallel sum done in: "
                + measureSumPerf(ParallelStreams::parallelSum, NUM)
                + " msecs");
        System.out.println("Iterative sum done in: "
                + measureSumPerf(ParallelStreams::iterativeSum, NUM)
                + " msecs");
        System.out.println("LongStream sum done in: "
                + measureSumPerf(ParallelStreams::rangedSum, NUM)
                + " msecs");
        System.out.println("ParallelLongStream sum done in: "
                + measureSumPerf(ParallelStreams::parallelRangedSum, NUM)
                + " msecs");
        System.out.println("SideEffectSum sum done in: "
                + measureSumPerf(ParallelStreams::sideEffectSum, NUM)
                + " msecs");
        System.out.println("ParallelSideEffectSum sum done in: "
                + measureSumPerf(ParallelStreams::parallelSideEffectSum, NUM)
                + " msecs");
        System.out.println("ForkJoinCalculator sum done in: "
                + measureSumPerf(ParallelStreams::forkJoinSum, NUM)
                + " msecs");
    }

    /**
     * 测试求和性能.
     *
     * @param adder 函数
     * @param num   数量
     * @return 性能时间
     */
    public static long measureSumPerf(final Function<Long, Long> adder,
                                      final long num) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(num);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result : " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    /**
     * 顺序求和.
     *
     * @param num 序列长度
     * @return 求和结果
     */
    public static long sequentialSum(final long num) {
        return Stream.iterate(1L, i -> i + 1).limit(num).reduce(0L, Long::sum);
    }

    /**
     * 并行求和.
     *
     * @param num 序列长度
     * @return 求和结果
     */
    public static long parallelSum(final long num) {
        return Stream.iterate(1L, i -> i + 1).limit(num).parallel().reduce(0L, Long::sum);
    }

    /**
     * 迭代求和.
     *
     * @param num 序列长度
     * @return 求和结果
     */
    public static long iterativeSum(final long num) {
        BigDecimal result = BigDecimal.valueOf(1L);
        for (long i = 0; i < num; i++) {
            result = result.add(BigDecimal.valueOf(i));
        }
        return result.longValue();
    }

    /**
     * 范围求和.
     *
     * @param num 序列长度
     * @return 求和结果
     */
    public static long rangedSum(final long num) {
        return LongStream.rangeClosed(1, num).reduce(0L, Long::sum);
    }

    /**
     * 并行范围求和.
     *
     * @param num 序列长度
     * @return 求和结果
     */
    public static long parallelRangedSum(final long num) {
        return LongStream.rangeClosed(1, num).parallel().reduce(0L, Long::sum);
    }

    /**
     * 副作用求和.
     *
     * @param num 序列长度
     * @return 求和结果
     */
    public static long sideEffectSum(final long num) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, num).forEach(accumulator::add);
        return accumulator.getTotal();
    }

    /**
     * 并行副作用求和.
     *
     * @param num 序列长度
     * @return 求和结果
     */
    public static long parallelSideEffectSum(final long num) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, num).parallel().forEach(accumulator::add);
        return accumulator.getTotal();
    }

    /**
     * 并行语句块求和.
     *
     * @param num 序列长度
     * @return 求和结果
     */
    public static long forkJoinSum(final long num) {
        long[] numbers = LongStream.rangeClosed(1, num).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        ForkJoinPool forkJoinPool = null;
        try {
            forkJoinPool = new ForkJoinPool();
            return forkJoinPool.invoke(task);
        } finally {
            if (forkJoinPool != null) {
                forkJoinPool.shutdown();
            }
        }
    }

    private static int countWordsIteratively() {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : ParallelStreams.SENTENCE.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    /**
     * run2.
     */
    public static void run2() {
        System.out.println("Found " + countWordsIteratively() + " words");
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        //System.out.println("Found " + countWords(stream) + " words");
        //System.out.println("Found " + countWords(stream.parallel()) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);

        System.out.println("Found " + countWords(stream1) + " words");
    }

    private static int countWords(final Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
}
