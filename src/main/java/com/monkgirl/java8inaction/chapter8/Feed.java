package com.monkgirl.java8inaction.chapter8;

import java.util.ArrayList;
import java.util.List;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public class Feed implements Subject {
    /**
     * 观察者列表.
     */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * 注册观察者.
     *
     * @param observer 观察者
     */
    public void registerObserver(final Observer observer) {
        observers.add(observer);
    }

    /**
     * 通知观察者.
     *
     * @param tweet 观察内容
     */
    public void notifyObservers(final String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
