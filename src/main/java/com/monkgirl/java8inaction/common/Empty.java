package com.monkgirl.java8inaction.common;

/**
 * 空链表.
 *
 * @param <T> 泛型类
 */
public class Empty<T> implements MyList<T> {
    /**
     * 不支持head操作.
     *
     * @return UnsupportedOperationException.
     */
    public T head() {
        throw new UnsupportedOperationException();
    }

    /**
     * 不支持tail操作.
     *
     * @return UnsupportedOperationException.
     */
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }
}
