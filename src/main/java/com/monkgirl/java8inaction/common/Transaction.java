package com.monkgirl.java8inaction.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Currency;
import java.util.Locale;

/**
 * 交易类.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Data
@AllArgsConstructor
public class Transaction {
    /**
     * 交易者.
     */
    private Trader trader;
    /**
     * 年份.
     */
    private int year;
    /**
     * 价值.
     */
    private int value;
    /**
     * 货币.
     */
    private Currency currency;

    /**
     * 默认货币构造器.
     *
     * @param newTrader 交易者
     * @param newYear   年份
     * @param newValue  价值
     */
    public Transaction(final Trader newTrader,
                       final int newYear,
                       final int newValue) {
        this.trader = newTrader;
        this.year = newYear;
        this.value = newValue;
        this.currency = Currency.getInstance(Locale.CHINA);
    }

    /**
     * The description of the transaction.
     *
     * @return The description of the transaction
     */
    public String toString() {
        return "{" + this.trader + ", year: " + this.year + ", " + "value: " + this.value + " , currency: " + this.currency.getDisplayName() + "}";
    }
}
