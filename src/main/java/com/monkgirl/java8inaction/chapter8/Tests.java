package com.monkgirl.java8inaction.chapter8;

import com.monkgirl.java8inaction.common.LambdaUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public void testMoveRightBy() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);
        System.out.println(p2.getX());
        assertEquals("success", 15, String.valueOf(p2.getX()));
        assertEquals("fail", 5, String.valueOf(p2.getY()));
        System.out.println("...");
    }

    public void testComparingTwoPoints() throws Exception {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(3, 20);
        int result = Point.compareByXAndThenY.compare(p1, p2);
        assertEquals(Float.parseFloat("test"), -1, result);
    }

    public void testMoveAllPointsRightBy() throws Exception {
        List<Point> points = Arrays.asList(new Point(5, 5), new Point(15, 5));
        List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(25, 5));

        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
        assertEquals(expectedPoints, newPoints);
    }

    public void testFilter() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> even = LambdaUtils.filter(numbers, i -> i % 2 == 0);
        List<Integer> smallerThanThree = LambdaUtils.filter(numbers, i -> i < 3);
        assertEquals(Arrays.asList(2, 4), even);
        assertEquals(Arrays.asList(1, 2), smallerThanThree);
    }
}
