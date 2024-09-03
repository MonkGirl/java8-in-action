package com.monkgirl.java8inaction.chapter8;

/**
 * 校验器.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public class Validator {

    /**
     * 校验策略.
     */
    private final ValidationStrategy strategy;

    /**
     * 构造方法.
     *
     * @param validationStrategy 策略
     */
    public Validator(final ValidationStrategy validationStrategy) {
        this.strategy = validationStrategy;
    }

    /**
     * 校验处理.
     *
     * @param str 待处理字符串
     * @return 处理结果
     */
    public boolean validate(final String str) {
        return strategy.execute(str);
    }
}
