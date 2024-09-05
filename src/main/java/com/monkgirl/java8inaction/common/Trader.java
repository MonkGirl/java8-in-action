package com.monkgirl.java8inaction.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 商人.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Data
@AllArgsConstructor
public class Trader {
    /**
     * 姓名.
     */
    private String name;
    /**
     * 城市.
     */
    private String city;

    /**
     * The description of the trader.
     *
     * @return the description of the trader
     */
    public String toString() {
        return "Trader: " + this.name + " in " + this.city;
    }
}
