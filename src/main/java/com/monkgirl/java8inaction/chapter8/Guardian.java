package com.monkgirl.java8inaction.chapter8;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Guardian implements Observer {
    /**
     * 通知.
     *
     * @param tweet 推特内容，
     */
    @Override
    public void notify(final String tweet) {
        if (tweet != null && tweet.contains("queue")) {
            System.out.println("Yet another news in London..." + tweet);
        }
    }
}
