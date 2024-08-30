package com.monkgirl.java8inaction.chapter3;

import com.monkgirl.java8inaction.common.Apple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用示例.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2022-04-08 09:43:04
 */
public final class MethodsReferenceDemo {

    private MethodsReferenceDemo() {
    }

    /**
     * map.
     */
    private static final Map<String, Function<Integer, Apple>> MAP = new HashMap<>();

    static {
        MAP.put("green", Apple::new);
        MAP.put("purple", Apple::new);
    }

    /**
     * 主方法.
     *
     * @param args 命令行入参
     */
    public static void main(final String... args) {
        List<String> str = Arrays.asList("a", "b", "A", "B");
        // str.sort((s1, s2)->s1.compareToIgnoreCase(s2));
        System.out.println("Lambda: " + str);
        str.sort(String::compareToIgnoreCase);
        System.out.println("MethodReference: " + str);

        Supplier<Apple> s = Apple::new;
        Apple a1 = s.get();
        System.out.println(a1);

        Function<Integer, Apple> f = Apple::new;
        Apple a2 = f.apply(232);
        System.out.println(a2);

        BiFunction<String, Integer, Apple> b = Apple::new;
        Apple a3 = b.apply("yellow", 203);

        System.out.println(a3);

        System.out.println(giveMeApple("green", 204));
    }

    /**
     * give me apple.
     *
     * @param color  颜色
     * @param weight 重量
     * @return apple
     */
    public static Apple giveMeApple(final String color, final Integer weight) {
        return MAP.get(color.toLowerCase()).apply(weight);
    }
}
