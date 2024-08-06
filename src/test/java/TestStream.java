import com.monkgirl.java8inaction.chapter11.Shop;
import com.monkgirl.java8inaction.common.Dish;
import com.monkgirl.java8inaction.common.Utils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author MissYoung
 * @version 0.1
 * @since 2021-04-01 10:05:41
 */
class TestStream {
    @Test
    void testFilter() {
        List<String> list = Utils.menus.stream().filter(dish -> dish.getCalories() > 300).sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).limit(3).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    void testFlatMap() {
        String[] arraysOfWorlds = {"Thinking", "in", "java"};
        List<String> result = Arrays.stream(arraysOfWorlds).map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    void testReduce() {
        IntStream intStream = IntStream.rangeClosed(0, 100);
        System.out.println(intStream.reduce(0, Integer::max));
        System.out.println(Utils.menus.stream().mapToDouble(Dish::getCalories).sum());
    }

    @Test
    void testStream() {

//        Stream<int[]> result =
//                IntStream.rangeClosed(1, 100).boxed()
//                        .flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
//                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        Stream<double[]> result =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0));

        result.forEach(a -> System.out.println("[" + a[0] + ", " + a[1] + ", " + a[2] + "]"));

        System.out.println(8.9 % 1);
    }

    @Test
    void testFileStream() {
        long start = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - start) / 1000.0 + "s");
    }

    @Test
    void testFileReader() throws IOException {
        long start = System.currentTimeMillis();
        File file = new File("data.txt");
        FileReader reader = new FileReader(file);

        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();

        System.out.println((System.currentTimeMillis() - start) / 1000.0 + "s");
    }

    @Test
    void testNoLimitStream() {
        Stream.iterate(0, n -> n + 2).limit(5).forEach(System.out::println);
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("[" + t[0] + ", " + t[1] + "]"));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .mapToInt(t -> t[0] + t[1])
                .forEach(System.out::println);

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .flatMapToInt(Arrays::stream).distinct()
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    void testFibonacci() {
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;

                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    @Test
    void testCollect() {
        System.out.println(Utils.menus.stream().count());
        //System.out.println(Menu.menuList.stream().collect(summingInt(Dish::getCalories)));
        System.out.println((Double) Utils.menus.stream().mapToDouble(Dish::getCalories).sum());
        Utils.menus.stream().mapToDouble(Dish::getCalories).reduce(Double::sum).ifPresent(System.out::println);
        System.out.println(Utils.menus.stream().collect(averagingDouble(Dish::getCalories)));
        DoubleSummaryStatistics intSummaryStatistics = Utils.menus.stream().collect(summarizingDouble(Dish::getCalories));
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(Utils.menus.stream().map(Dish::getName).collect(joining(", ")));
    }

    @Test
    void testCompletableFuture() {
        Shop shop = new Shop("Best Shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        //Future<Double> futurePrice = shop.getPriceAsyncNew("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        shop.doSomethingElse();

        try {
            double price = futurePrice.get(30, TimeUnit.SECONDS);
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    @Test
    void testAsync() {
        System.out.println(findPrices("Thinkpad"));
    }

    private List<String> findPrices(String product) {
        List<CompletableFuture<String>> futures = Utils.shops.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor)
        ).collect(Collectors.toList());

        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private final Executor executor = Executors.newFixedThreadPool(Math.min(Utils.shops.size(), 100),
            runnable -> {
                Thread t = new Thread(runnable);
                //设置守护线程，这种方式不会阻止程序关停
                t.setDaemon(true);
                return t;
            });
}
