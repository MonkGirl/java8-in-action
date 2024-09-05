package com.monkgirl.java8inaction.common;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

/**
 * 懒惰链表.
 *
 * @param <T> 泛型
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@AllArgsConstructor
public class LazyList<T> implements MyList<T> {

    /**
     * 表头元素.
     */
    private final T head;

    /**
     * 链表提供者.
     */
    private final Supplier<MyList<T>> tail;

    /**
     * 获取表头元素.
     *
     * @return 表头元素
     */
    public T head() {
        return head;
    }

    /**
     * 获取表尾.
     *
     * @return 表尾
     */
    public MyList<T> tail() {
        return tail.get();
    }

    /**
     * 是否为空.
     *
     * @return 是否为空
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * 过滤.
     *
     * @param predicate 断言
     * @return 过滤的数据
     */
    public MyList<T> filter(final Predicate<T> predicate) {
        return isEmpty() ? this
                : predicate.test(head()) ? new LazyList<>(head(), () -> tail().filter(predicate))
                : tail().filter(predicate);
    }
}
