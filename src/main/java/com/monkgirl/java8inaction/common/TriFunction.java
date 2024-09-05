package com.monkgirl.java8inaction.common;

/**
 * 三元功能函数.
 *
 * @param <T> 泛型入参类型
 * @param <U> 泛型入参类型
 * @param <V> 泛型value类型
 * @param <R> 泛型返回类型
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    /**
     * 函数实现方法.
     *
     * @param t 入参类型1
     * @param u 入参类型2
     * @param v value入参
     * @return 返回类型
     */
    R apply(T t, U u, V v);
}
