package com.monkgirl.java8inaction.chapter5;

import com.monkgirl.java8inaction.common.Dish;
import com.monkgirl.java8inaction.common.ExerciseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Exercises {
    /**
     * 限制数量.
     */
    private static final int LIMIT = 5;

    private Exercises() {

    }

    /**
     * 菜单.
     */
    private static final List<Dish> MENU = Arrays.asList(
            new Dish(800, "pork", Dish.Type.MEAT, false),
            new Dish(900, "beef", Dish.Type.MEAT, false),
            new Dish(500, "chicken", Dish.Type.MEAT, false),
            new Dish(530, "french fries", Dish.Type.OTHER, true),
            new Dish(350, "rice", Dish.Type.OTHER, true),
            new Dish(120, "season fruit", Dish.Type.OTHER, true),
            new Dish(550, "pizza", Dish.Type.OTHER, true),
            new Dish(300, "prawns", Dish.Type.FISH, true),
            new Dish(450, "salmon", Dish.Type.FISH, true)
    );

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
        //run8();
        //run9();
        //run10();
        //run11();
        run12();
    }

    /**
     * run1.
     */
    public static void run1() {
        List<Dish> vegetarianDishes = MENU.parallelStream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        System.out.println(vegetarianDishes);
    }

    /**
     * run2.
     */
    public static void run2() {
        List<Integer> numbers = Arrays.asList(7, 1, 2, 3, 5, 8, 10, 1, 7, 5);
        numbers.stream()
                .filter(i -> i % 2 == 1)
                .distinct()
                //.limit(3)
                .sorted(Integer::compareTo)
                //.limit(3)
                .skip(3)
                .forEach(System.out::println);
    }

    /**
     * run3.
     */
    public static void run3() {
        List<String> dishNames = MENU.parallelStream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);

        List<Integer> wordLengths = dishNames.parallelStream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

        List<String> words = dishNames.parallelStream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                //.map(Arrays::stream)
                .distinct()
                //.filter(str->str.trim().length()>0)
                .collect(toList());
        System.out.println(words);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> square = numbers.parallelStream()
                .map(n -> n * n)
                .collect(toList());
        System.out.println(square);

        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);
        List<int[]> result = num1.stream()
                .flatMap(i -> num2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(toList());
        for (int[] i : result) {
            System.out.print(Arrays.toString(i));
        }
    }

    /**
     * run4.
     */
    public static void run4() {
        if (MENU.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The MENU is (somewhat) vegetarian friendly!!");
        }
        if (MENU.parallelStream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("Cool!");
        }
        if (MENU.parallelStream().noneMatch(d -> d.getCalories() > 1000)) {
            System.out.println("Very cool!!");
        }

        Optional<Dish> dish = MENU.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(dish);

        MENU.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByTwo = someNumbers.parallelStream()
                .map(i -> i * i)
                .filter(i -> i % 2 == 0)
                .findFirst();
        System.out.println(firstSquareDivisibleByTwo);
    }

    /**
     * run5.
     */
    public static void run5() {
        List<Integer> numbers = Arrays.asList(2, 4, 7, 1, 2, 3, -4);
        //int result1 = numbers.parallelStream().reduce(0,(a, b)->a+b);
        int result1 = numbers.parallelStream().reduce(0, Integer::sum);
        System.out.println(result1);

        int result2 = numbers.parallelStream().reduce(1, (a, b) -> a * b);
        System.out.println(result2);

        Optional<Integer> result3 = numbers.parallelStream().reduce(Integer::sum);
        result3.ifPresent(System.out::println);

        //Optional<Integer> result4 = numbers.parallelStream().reduce(Integer::max);
        Optional<Integer> result4 = numbers.parallelStream().reduce(Integer::min);
        result4.ifPresent(System.out::println);

        int count = MENU.parallelStream().map(d -> 1).reduce(0, Integer::sum);
        System.out.println(count);
    }

    /**
     * run6.
     */
    public static void run6() {
        double calories = MENU.parallelStream()
                .mapToDouble(Dish::getCalories)
                .sum();
        System.out.println(calories);

        OptionalDouble max = MENU.parallelStream()
                .mapToDouble(Dish::getCalories)
                .max();
        max.ifPresent(System.out::println);

        DoubleStream doubleStream = MENU.parallelStream()
                .mapToDouble(Dish::getCalories);

        Stream<Double> stream = doubleStream.boxed();

        IntStream evenNumber = IntStream.rangeClosed(1, 100);
        //System.out.println(evenNumber.sum());
        List<Integer> list = evenNumber.boxed().collect(toList());
        System.out.println(list);
    }

    /**
     * run7.
     */
    public static void run7() {
        IntStream number = IntStream.rangeClosed(1, 100);
        Stream<int[]> pythagoreanTriples = number.boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.forEach(a -> System.out.println(a[0] + "," + a[1] + "," + a[2]));
    }

    /**
     * run8.
     */
    public static void run8() {
        Stream<String> stream = Stream.of("Java8", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        int[] numbers = {1, 2, 3, 4, 5, 6, 7};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

    }

    /**
     * run9.
     */
    public static void run9() {
        Path path = Paths.get("E:/Java8/Java8InAction/data.txt");
        long startTime1 = System.nanoTime();
        try (Stream<String> lines = Files.lines(path, Charset.defaultCharset())) {
            long uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);
        } catch (IOException e) {
            throw new ExerciseException(e);
        }
        System.out.println((System.nanoTime() - startTime1) / 1000.0);

        long startTime2 = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("E:/java8/Java8InAction/data.txt")))) {
            String str;
            Set<String> container = new HashSet<>();
            while ((str = reader.readLine()) != null) {
                container.addAll(Arrays.asList(str.split(" ")));
            }
            System.out.println(container.size());
        } catch (IOException e) {
            throw new ExerciseException(e);
        }
        System.out.println((System.nanoTime() - startTime2) / 1000.0);
    }

    /**
     * run10.
     */
    public static void run10() {
        //iterate
        Stream.iterate(0, n -> n + 2)
                .limit(2 * LIMIT)
                .forEach(System.out::println);

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(2 * LIMIT)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    /**
     * run11.
     */
    public static void run11() {
        Stream.generate(Math::random)
                .limit(LIMIT)
                .forEach(System.out::println);

        IntStream.generate(() -> 1)
                .limit(LIMIT)
                .forEach(System.out::println);

        IntStream.generate(() -> 3).limit(LIMIT)
                .forEach(System.out::println);
    }

    /**
     * run12.
     */
    public static void run12() {
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib)
                .limit(LIMIT)
                .forEach(System.out::println);
    }
}
