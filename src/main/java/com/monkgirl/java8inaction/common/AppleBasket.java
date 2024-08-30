package com.monkgirl.java8inaction.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppleBasket {
    private static String[] color = {"RED", "GREEN", "PURPLE"};
    private static Random rand = new Random(47);

    public static List<Apple> getApples(int capacity) {
        List<Apple> result = new ArrayList<Apple>();
        for (int i = 0; i < capacity; i++) {
            result.add(new Apple(color[rand.nextInt(color.length)], rand.nextInt(50000) / 100d));
        }
        return result;
    }

    public static List<Apple> filterGreenApple(List<Apple> inventory) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if ("green".equalsIgnoreCase(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApple(List<Apple> inventory) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreen(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }

    public static boolean isHeavy(Apple apple) {
        return apple.getWeight() > 150;
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
