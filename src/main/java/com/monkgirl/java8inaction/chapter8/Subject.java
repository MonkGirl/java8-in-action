package com.monkgirl.java8inaction.chapter8;

/**
 * 观察对象.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public interface Subject {
    /**
     * 注册观察者.
     *
     * @param o 观察者
     */
    void registerObserver(Observer o);

    /**
     * 通知观察者.
     *
     * @param tweet 待处理推特
     */
    void notifyObservers(String tweet);
}
