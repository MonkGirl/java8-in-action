package com.monkgirl.java8inaction.chapter8;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class HeaderTextProcessing extends ProcessingObject<String> {
    /**
     * 处理工作.
     *
     * @param text 文本
     * @return 处理结果
     */
    @Override
    public String handWork(final String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}
