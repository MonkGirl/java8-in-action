package com.monkgirl.java8inaction.chapter1;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 缓存读取处理器.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    /**
     * 处理方法.
     *
     * @param reader 读取器
     * @return 读取内容
     * @throws IOException IO异常信息
     */
    String process(BufferedReader reader) throws IOException;
}
