package com.monkgirl.java8inaction.chapter8;

/**
 * 校验策略.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public interface ValidationStrategy {
    /**
     * 校验处理.
     *
     * @param str 待处理字符串
     * @return 处理结果
     */
    boolean execute(String str);
}
