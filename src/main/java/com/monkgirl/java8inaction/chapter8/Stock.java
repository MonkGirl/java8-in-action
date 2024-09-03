package com.monkgirl.java8inaction.chapter8;

/**
 * 股票工厂.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public class Stock extends Product {
    /**
     * toString方法.
     *
     * @return simple name
     */
    public String toString() {
        return Stock.class.getSimpleName();
    }
}
