package com.monkgirl.java8inaction.chapter8;

import com.monkgirl.java8inaction.common.CaloricLevel;
import com.monkgirl.java8inaction.common.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
     *
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
        run5();
    }

    /**
     * doSomething.
     * @param r 可运行线程
     */
    public static void doSomething(final Runnable r) {
        r.run();
    }

    /**
     * doSomething.
     * @param t 可运行任务
     */
    public static void doSomething(final Task t) {
        t.execute();
    }

    /**
     * run1.
     */
    public static void run1() {
        //doSomething((Task)()->System.out.println("doSomething..."));
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel
                = MENU.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));
        System.out.println(dishesByCaloricLevel);

        Map<CaloricLevel, List<Dish>> dishesByCalorieLevel1 = MENU
                .stream()
                .collect(Collectors.groupingBy(Dish::getCaloricLevel));
        System.out.println(dishesByCalorieLevel1);

        List<String> list = MENU.parallelStream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * run2.
     */
    public static void run2() {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbb");
        System.out.println("numericValidator: " + b1);
        System.out.println("lowerCaseValidator: " + b2);

        Validator numericValidatorLambda = new Validator((String str) -> str.matches("[a-z]+"));
        System.out.println(numericValidatorLambda.validate("ccc"));
        Validator lowerCaseValidatorLambda = new Validator((String str) -> str.matches("\\d+"));
        System.out.println(lowerCaseValidatorLambda.validate("ddd"));
    }

    /**
     * run3.
     */
    public static void run3() {
        Feed feed = new Feed();
        //feed.registerObserver(new NYTimes());
        //feed.registerObserver(new Guardian());
        //feed.registerObserver(new LeMonde());

        feed.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in London... " + tweet);
            }
        });

        feed.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("queue")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        });
        feed.notifyObservers("The queue said her favourite book is Java 8 in Action!");
    }

    /**
     * run4.
     */
    public static void run4() {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!! ");
        System.out.println(result);

        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        result = pipeline.apply("Aren't labdas really sexy?!!");

        System.out.println(result);
    }

    /**
     * run5.
     */
    public static void run5() {
        Product p = ProductFactory.createProduct("loan");
        System.out.println(p);
    }
}
