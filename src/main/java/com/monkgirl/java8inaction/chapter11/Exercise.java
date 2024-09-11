package com.monkgirl.java8inaction.chapter11;

import com.monkgirl.java8inaction.common.ExerciseException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
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
     * 商店列表.
     */
    private static final List<Shop> SHOPS = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"),
            new Shop("ShopToll"),
            new Shop("FruitShop"),
            new Shop("ComputerShop"),
            new Shop("OverseaShop")
    );

    /**
     * 线程池.
     */
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(
            SHOPS.size(),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });
    /**
     * 随机工具.
     */
    private static final Random RANDOM = new Random();

    /**
     * 主方法.
     *
     * @param args 命令行入参
     */
    public static void main(final String... args) {
        //run1();
        //run2();
        //run3();
        run4();
    }

    /**
     * 随机延迟.
     */
    public static void randomDelay() {
        try {
            int delay = 500 + RANDOM.nextInt(2000);
            TimeUnit.MICROSECONDS.sleep(delay);
        } catch (InterruptedException ie) {
            throw new ExerciseException(ie);
        }
    }

    /**
     * run1.
     */
    public static void run1() {
        ExecutorService executor = null;
        try {
            executor = Executors.newCachedThreadPool();
            Future<Double> future = executor.submit(Exercise::doSomeLongComputation);
            doSomethingElse();

            Double result = future.get(5, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new ExerciseException(e);
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }

    /**
     * 长时间计算.
     *
     * @return 2
     */
    public static Double doSomeLongComputation() {
        System.out.println("doSomeLongComputation...");
        return 2D;
    }

    /**
     * 其他任务.
     */
    public static void doSomethingElse() {
        System.out.println("doSomethingElse...");
    }

    /**
     * run2.
     */
    public static void run2() {
        try {
            Shop shop = new Shop("BestShop");
            long start = System.nanoTime();
            Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
            long invocationTime = ((System.nanoTime() - start) / 1_000_000);
            System.out.println("Invocation returned after " + invocationTime + " msecs");
            doSomethingElse();

            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);

            long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
            System.out.println("Price returned after " + retrievalTime + " msecs");
        } catch (Exception e) {
            throw new ExerciseException(e);
        }
    }

    /**
     * 根据产品查找各商店价格列表.
     *
     * @param product 产品
     * @return 价格信息列表
     */
    public static List<String> findPrices(final String product) {
        return SHOPS.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 并行查找价格.
     *
     * @param product 产品
     * @return 各商店价格列表
     */
    public static List<String> findPricesParallel(final String product) {
        return SHOPS.parallelStream()
                .map(shop -> String.format("%s price %s", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 异步查找价格.
     *
     * @param product 产品
     * @return 各商店价格列表
     */
    public static List<String> findPricesAsync(final String product) {
        List<CompletableFuture<String>> priceFutures = SHOPS.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), EXECUTOR))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), EXECUTOR)))
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * run3.
     */
    public static void run3() {
        long start = System.nanoTime();
        System.out.println(findPricesStream("myPhone27s"));
        CompletableFuture<?>[] futures = findPricesStream("myPhone")
                .map(f -> f.thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.anyOf(futures).join();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    private static Stream<CompletableFuture<String>> findPricesStream(final String product) {
        return SHOPS.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), EXECUTOR)
                )
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), EXECUTOR)));
    }

    /**
     * run4.
     */
    public static void run4() {
        long start = System.nanoTime();
        CompletableFuture<?>[] futures = findPricesStream("myPhone27S")
                .map(f -> f.thenAccept(
                                s -> System.out.println(s
                                        + " (done in " + (System.nanoTime() - start) / 1_000_000
                                        + " msecs)")
                        )
                )
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in "
                + (System.nanoTime() - start) / 1_000_000
                + " msecs");
    }
}
