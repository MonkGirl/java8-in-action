package com.monkgirl.java8inaction.chapter10;

import lombok.Data;

import java.util.Optional;

/**
 * 人员.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Data
public class Person {
    /**
     * 车辆.
     */
    private Optional<Car> car;
}
