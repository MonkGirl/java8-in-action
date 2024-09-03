package com.monkgirl.java8inaction.chapter8;

import lombok.Setter;

/**
 * 练习.
 *
 * @param <T> 泛型类
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Setter
public abstract class ProcessingObject<T> {
    /**
     * 处理对象.
     */
    private ProcessingObject<T> successor;

    /**
     * 处理方法.
     *
     * @param input 输入
     * @return 处理结果
     */
    public T handle(final T input) {
        T r = handWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    /**
     * 处理.
     *
     * @param input 输入
     * @return 处理结果
     */
    abstract T handWork(T input);
}
