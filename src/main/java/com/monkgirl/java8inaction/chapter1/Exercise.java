package com.monkgirl.java8inaction.chapter1;

import com.monkgirl.java8inaction.common.Apple;
import com.monkgirl.java8inaction.common.AppleBasket;
import com.monkgirl.java8inaction.common.ExerciseException;
import com.monkgirl.java8inaction.common.LambdaUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 练习内容.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Slf4j
public final class Exercise {

    /**
     * 篮子容量.
     */
    private static final int BASKET_SIZE = 5;
    /**
     * 重量阈值.
     */
    private static final int WEIGHT_THRESHOLD = 150;
    /**
     * 数目大小.
     */
    private static final int NUM_SIZE = 10;

    private Exercise() {

    }

    /**
     * 测试主方法.
     *
     * @param args 入参
     */
    public static void main(final String... args) {
        List<Apple> list = AppleBasket.getApples(BASKET_SIZE);
        System.out.println(list);

        List<Apple> resultGreen = AppleBasket.filterGreenApple(list);
        System.out.println(resultGreen);

        List<Apple> resultHeavy = AppleBasket.filterHeavyApple(list);
        System.out.println(resultHeavy);

        System.out.println(AppleBasket.filterApple(list, AppleBasket::isGreen));
        System.out.println(AppleBasket.filterApple(list, AppleBasket::isHeavy));

        System.out.println(AppleBasket.filterApple(list, a -> "green".equalsIgnoreCase(a.getColor())));
        System.out.println(AppleBasket.filterApple(list, (Apple a) -> a.getWeight() > WEIGHT_THRESHOLD));

        System.out.println(LambdaUtils.filter(list, (Apple a) -> "red".equalsIgnoreCase(a.getColor())));

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < NUM_SIZE; i++) {
            numbers.add(i);
        }
        System.out.println(LambdaUtils.filter(numbers, (Integer i) -> i % 2 == 0));

        list.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(list);

        Thread t = new Thread(() -> System.out.println("Hello World"));
        t.start();

        Runnable r1 = () -> System.out.println("Hello World");

        r1.run();

        try {
            System.out.println(LambdaUtils.processFile(BufferedReader::readLine));
            System.out.println(LambdaUtils.processFile((BufferedReader br) -> br.readLine() + br.readLine()));
        } catch (Exception e) {
            throw new ExerciseException(e);
        }
    }
}
