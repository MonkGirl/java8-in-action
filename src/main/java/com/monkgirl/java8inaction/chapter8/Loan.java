package com.monkgirl.java8inaction.chapter8;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public class Loan extends Product {
    /**
     * toString.
     *
     * @return simple name
     */
    @Override
    public String toString() {
        return Loan.class.getSimpleName();
    }
}
