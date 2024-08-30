package com.monkgirl.java8inaction.common;

/**
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30
 */
public class ExerciseException extends RuntimeException {
    /**
     * 默认异常.
     */
    public ExerciseException() {
        super();
    }

    /**
     * 带异常信息的ExerciseException.
     *
     * @param e 异常信息
     */
    public ExerciseException(final Exception e) {
        super(e);
    }
}
