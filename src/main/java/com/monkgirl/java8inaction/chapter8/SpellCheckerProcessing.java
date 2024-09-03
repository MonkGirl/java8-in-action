package com.monkgirl.java8inaction.chapter8;

/**
 * 拼写检查.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {
    /**
     * 处理拼写错误.
     * @param text 输入
     * @return
     */
    @Override
    public String handWork(final String text) {
        return text.replaceAll("labda", "lambda");
    }
}
