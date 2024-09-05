package com.monkgirl.java8inaction.common;

/**
 * 链表.
 *
 * @param <T> 泛型
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public interface MyList<T> {
    /**
     * 获取表头.
     *
     * @return 表头
     */
    T head();

    /**
     * 查看队尾.
     *
     * @return 队尾
     */
    MyList<T> tail();

    /**
     * 是否空值.
     *
     * @return 是否空值
     */
    default boolean isEmpty() {
        return false;
    }

    /**
     * 过滤器.
     *
     * @param predicate 断言
     * @return 过滤后的列表
     */
    default MyList<T> filter(Predicate<T> predicate) {
        return null;
    }
}
