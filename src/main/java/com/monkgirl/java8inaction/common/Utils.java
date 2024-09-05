package com.monkgirl.java8inaction.common;

import com.monkgirl.java8inaction.chapter11.Shop;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2022-04-08 09:36:28
 */
@Data
public final class Utils {
    private Utils() {

    }

    /**
     * 菜单.
     */
    public static final List<Dish> MENUS = new ArrayList<>();

    /**
     * 商店.
     */
    public static final List<Shop> SHOPS = new ArrayList<>();
}
