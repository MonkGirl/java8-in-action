
package com.monkgirl.java8inaction.chapter11;

import com.monkgirl.java8inaction.common.ExerciseException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * 折扣.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Discount {
    private Discount() {

    }

    /**
     * 会员级别.
     */
    public enum Code {
        /**
         * 普通会员.
         */
        NONE(0),
        /**
         * 银牌会员.
         */
        SILVER(5),
        /**
         * 金牌会员.
         */
        GOLD(10),
        /**
         * 白金会员.
         */
        PLATINUM(15),
        /**
         * 钻石会员.
         */
        DIAMOND(20);

        /**
         * 打折率.
         */
        private final int percentage;

        /**
         * 构造.
         *
         * @param newPercentage 打折率
         */
        Code(final int newPercentage) {
            this.percentage = newPercentage;
        }
    }

    /**
     * 使用折扣.
     *
     * @param quote 引用
     * @return 折扣使用情况
     */
    public static String applyDiscount(final Quote quote) {
        return quote.getShopName()
                + " price is "
                + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(final double price, final Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }

    private static void delay() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            throw new ExerciseException(e);
        }
    }

    /**
     * 格式化.
     *
     * @param d 数据对象
     * @return 格式化后数据
     */
    public static double format(final double d) {
        BigDecimal bd = BigDecimal.valueOf(d);
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
