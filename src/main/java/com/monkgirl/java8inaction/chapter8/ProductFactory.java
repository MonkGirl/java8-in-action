package com.monkgirl.java8inaction.chapter8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 产品工厂.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class ProductFactory {

    private ProductFactory() {
    }

    /**
     * 类图.
     */
    private static final Map<String, Supplier<Product>> MAP = new HashMap<>();

    static {
        MAP.put("loan", Loan::new);
        MAP.put("stock", Stock::new);
        MAP.put("bond", Bond::new);
    }

    /**
     * 创建产品.
     *
     * @param name 产品名称
     * @return 产品
     */
    public static Product createProduct(final String name) {

        Supplier<Product> p = MAP.get(name);
        if (p != null) {
            return p.get();
        }
        throw new RuntimeException("No such product " + name);
    }
}
