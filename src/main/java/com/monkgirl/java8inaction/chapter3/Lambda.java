package com.monkgirl.java8inaction.chapter3;

import com.monkgirl.java8inaction.chapter1.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Lambda processor.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2022-04-08 09:43:04
 */
public final class Lambda {
    private Lambda() {

    }

    /**
     * 处理方法.
     *
     * @param runnable 可运行线程
     */
    public static void process(final Runnable runnable) {
        runnable.run();
    }

    /**
     * 处理文件.
     *
     * @param processor 处理器
     * @return 处理结果
     * @throws IOException IO异常
     */
    public static String processFile(final BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(reader);
        }
    }

    /**
     * 排序.
     *
     * @param list 待处理链表
     */
    public static void sort(final List<String> list) {
    }

    /**
     * 方法引用.
     */
    public static void methodReference() {
    }
}
