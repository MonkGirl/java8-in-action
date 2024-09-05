package com.monkgirl.java8inaction.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Apple.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Data
@AllArgsConstructor
public class Apple {

    /**
     * the color of apple.
     */
    private String color;

    /**
     * the weight of apple.
     */
    private double weight;

    /**
     * 默认构造.
     */
    public Apple() {
        this("green", 302);
    }

    /**
     * 重量构造.
     *
     * @param newWeight 重量
     */
    public Apple(final double newWeight) {
        this("green", newWeight);
    }

    /**
     * toString.
     *
     * @return toString
     */
    public String toString() {
        return "[color : " + color + ", weight: " + weight + "g]";
    }
}
