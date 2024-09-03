package com.monkgirl.java8inaction.chapter10;

import lombok.Data;

import java.util.Optional;

/**
 * Car.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Data
public class Car {
    /**
     * 保险.
     */
    private Optional<Insurance> insurance;
}
