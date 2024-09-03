package com.monkgirl.java8inaction.chapter8;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Getter
@AllArgsConstructor
public final class Point {
    /**
     * 横向坐标.
     */
    private final int x;
    /**
     * 纵向坐标.
     */
    private final int y;

    /**
     * 向右横移.
     *
     * @param xOffset 横移量
     * @return 右移
     */
    public Point moveRightBy(final int xOffset) {
        return new Point(this.x + xOffset, this.y);
    }

    /**
     * 比较.
     */
    public static final Comparator<Point> COMPARE_BY_X_AND_THEN_Y = Comparator.comparing(Point::getX).thenComparing(Point::getY);

    /**
     * 向右移动所有.
     *
     * @param points 所有数据点
     * @param x      偏移量
     * @return 移动后的数据点
     */
    public static List<Point> moveAllPointsRightBy(final List<Point> points,
                                                   final int x) {
        return points.stream()
                .map(p -> new Point(p.getX() + x, p.getY()))
                .collect(Collectors.toList());
    }

    /**
     * 比较方法.
     *
     * @param others 待比较的数据点
     * @return 比较结果
     */
    @Override
    public boolean equals(final Object others) {
        if (others instanceof Point) {
            return this.x == ((Point) others).getX() && this.y == ((Point) others).getY();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.x + this.y;
    }
}
