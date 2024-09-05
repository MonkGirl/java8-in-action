package com.monkgirl.java8inaction.chapter11;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 引用.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@Data
@AllArgsConstructor
public final class Quote {

    /**
     * 商店名称.
     */
    private final String shopName;

    /**
     * 价格.
     */
    private final double price;

    /**
     * 折扣.
     */
    private final Discount.Code discountCode;

    /**
     * 生成引用.
     *
     * @param s 商店、价格、折扣字符串
     * @return 引用
     */
    public static Quote parse(final String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code code = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, code);
    }
}
