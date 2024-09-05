package com.monkgirl.java8inaction.common;

/**
 * 断言.
 *
 * @param <T> 泛型
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public interface Predicate<T> {
    /**
     * 校验.
     *
     * @param t 校验器
     * @return 检验结果
     */
    boolean test(T t);
}
