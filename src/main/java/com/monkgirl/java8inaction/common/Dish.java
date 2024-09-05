package com.monkgirl.java8inaction.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    /**
     * 计数器.
     */
    private static int counter;
    /**
     * id.
     */
    private final int id = ++counter;
    /**
     * 卡路里.
     */
    private double calories;
    /**
     * 名称.
     */
    private String name;
    /**
     * 类型.
     */
    private Type type;
    /**
     * 是否素食.
     */
    private boolean vegetarian;

    /**
     * The description of the dish.
     *
     * @return the description of the dish
     */
    public String toString() {
        return "Dish " + id + ": Name " + name + ", Calories " + calories;
    }

    /**
     * 饮食类型.
     */
    public enum Type {
        /**
         * 肉类.
         */
        MEAT,
        /**
         * 鱼类.
         */
        FISH,
        /**
         * 其他.
         */
        OTHER
    }

    /**
     * 比较.
     *
     * @param dish 被比较的对象
     * @return 比较结果
     */
    public int compareTo(final Dish dish) {
        return dish.getName().compareTo(this.getName());
    }

    /**
     * 获取卡路里水平.
     *
     * @return 卡路里水平
     */
    public CaloricLevel getCaloricLevel() {
        if (this.getCalories() <= 400) {
            return CaloricLevel.DIET;
        } else if (this.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
        } else {
            return CaloricLevel.FAT;
        }
    }
}
