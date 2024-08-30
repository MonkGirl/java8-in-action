package com.monkgirl.java8inaction.chapter1;

/**
 * meaning of this.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public class MeaningOfThis {
    /**
     * value.
     */
    private final int value = 4;

    /**
     * thread.
     */
    public void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;

            public void run() {
                int value = 5;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    /**
     * 测试方法.
     *
     * @param args 入参
     */
    public static void main(final String... args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}
