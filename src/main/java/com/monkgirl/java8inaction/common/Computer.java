package com.monkgirl.java8inaction.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 电脑.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Computer {
    /**
     * cpu.
     */
    private String cpu;
    /**
     * disk.
     */
    private String disk;
    /**
     * memory.
     */
    private String memory;

    /**
     * toString.
     *
     * @return The description of the computer.
     */
    public String toString() {
        return "Cpu: " + cpu + " Disk: " + disk + " Memory: " + memory;
    }
}
