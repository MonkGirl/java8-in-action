package com.monkgirl.java8inaction.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Debugging {

    private Debugging() {

    }

    /**
     * 主方法.
     *
     * @param args 命令行参数
     */
    public static void main(final String... args) {
        List<Point> points = Arrays.asList(new Point(2, 2), null);
        //points.stream().map(p -> p.getX()).forEach(System.out::println);
        //points.stream().map(Point::getX).forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        //numbers.stream().map(Debugging::divideByZero).forEach(System.out::println);

        numbers.stream().map(x -> x + 17).filter(x -> x % 2 == 0).limit(3).forEach(System.out::println);

        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from Stream : " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("from map : " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("from filter : " + x))
                .limit(3)
                .peek(x -> System.out.println("from limit : " + x))
                .collect(Collectors.toList());
    }

    /**
     * 除零测试.
     *
     * @param i 被除数
     * @return 除零
     */
    public static int divideByZero(final int i) {
        return i / 0;
    }
}
