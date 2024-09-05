package com.monkgirl.java8inaction.common;


import lombok.AllArgsConstructor;

/**
 * 链表列表.
 *
 * @param <T> 泛型
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@AllArgsConstructor
public final class MyLinkedList<T> implements MyList<T> {
    /**
     * 表头.
     */
    private final T head;

    /**
     * 表尾.
     */
    private final MyList<T> tail;

    /**
     * 获取表头.
     *
     * @return 表头
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
        return tail;
    }

    /**
     * 是否为空.
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return false;
    }
}
