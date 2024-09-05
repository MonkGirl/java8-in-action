package com.monkgirl.java8inaction.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 累加器.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Getter
@Setter
public class Accumulator {
    /**
     * 总数.
     */
    private long total = 0;

    /**
     * 累加.
     *
     * @param value 数值
     */
    public void add(final long value) {
        total += value;
    }
}
