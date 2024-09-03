package com.monkgirl.java8inaction.chapter8;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public interface Observer {
    /**
     * 通知.
     *
     * @param tweet 待处理内容
     */
    void notify(String tweet);
}
