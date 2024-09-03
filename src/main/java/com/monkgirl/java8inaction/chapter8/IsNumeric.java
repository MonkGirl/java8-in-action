package com.monkgirl.java8inaction.chapter8;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public class IsNumeric implements ValidationStrategy {
    /**
     * 处理方法.
     *
     * @param str 待处理字符串
     * @return 处理结果
     */
    @Override
    public boolean execute(final String str) {
        return str.matches("\\d+");
    }
}
