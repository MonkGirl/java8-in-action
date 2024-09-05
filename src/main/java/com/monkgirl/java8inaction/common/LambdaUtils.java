package com.monkgirl.java8inaction.common;

import com.monkgirl.java8inaction.chapter1.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lambda工具类.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class LambdaUtils {

    private LambdaUtils() {

    }

    /**
     * 过滤器.
     *
     * @param list      待处理链表数据
     * @param predicate 处理动作谓词
     * @param <T>       泛型类
     * @return 满足过滤条件的结果
     */
    public static <T> List<T> filter(final List<T> list,
                                     final Predicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 处理文件.
     *
     * @param p 处理器
     * @return 处理的结果字符串
     * @throws IOException 异常信息
     */
    public static String processFile(final BufferedReaderProcessor p) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("e:/Java8/Java8InAction/data.txt"));
        return p.process(br);
    }
}
