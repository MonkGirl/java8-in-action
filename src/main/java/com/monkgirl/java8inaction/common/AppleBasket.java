package com.monkgirl.java8inaction.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Apple Basket.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class AppleBasket {
    private AppleBasket() {

    }

    /**
     * 颜色.
     */
    private static final String[] COLOR = {"RED", "GREEN", "PURPLE"};

    /**
     * 随机器.
     */
    private static final Random RAND = new Random(47);

    /**
     * 获取苹果.
     *
     * @param capacity 容量
     * @return 苹果
     */
    public static List<Apple> getApples(final int capacity) {
        List<Apple> result = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            result.add(new Apple(COLOR[RAND.nextInt(COLOR.length)], RAND.nextInt(50000) / 100d));
        }
        return result;
    }

    /**
     * 筛选绿苹果.
     *
     * @param inventory 库存
     * @return 绿苹果
     */
    public static List<Apple> filterGreenApple(final List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equalsIgnoreCase(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 筛选重苹果.
     *
     * @param inventory 库存
     * @return 重苹果
     */
    public static List<Apple> filterHeavyApple(final List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 是否是绿苹果.
     *
     * @param apple 苹果
     * @return 是否是绿苹果
     */
    public static boolean isGreen(final Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }

    /**
     * 是否是重苹果.
     *
     * @param apple 苹果
     * @return 是或否
     */
    public static boolean isHeavy(final Apple apple) {
        return apple.getWeight() > 150;
    }

    /**
     * 函数动作谓词.
     *
     * @param <T> 泛型
     */
    public interface Predicate<T> {
        /**
         * 测试.
         *
         * @param t 被检测对象.
         * @return 是或否
         */
        boolean test(T t);
    }

    /**
     * 筛选苹果.
     *
     * @param inventory 库存
     * @param p         函数谓词
     * @return 筛选后的苹果
     */
    public static List<Apple> filterApple(final List<Apple> inventory,
                                          final Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
