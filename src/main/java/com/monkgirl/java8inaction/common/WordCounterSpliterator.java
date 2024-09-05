package com.monkgirl.java8inaction.common;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 单词计数分解器.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public class WordCounterSpliterator implements Spliterator<Character> {
    /**
     * 字符串.
     */
    private final String string;
    /**
     * 当前字符.
     */
    private int currentChar = 0;


    /**
     * 字符串构造.
     *
     * @param str 字符串
     */
    public WordCounterSpliterator(final String str) {
        string = str;
    }

    /**
     * 尝试推进.
     *
     * @param action The action whose operation is performed at-most once
     * @return 是否成功
     */
    @Override
    public boolean tryAdvance(final Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    /**
     * 尝试分离.
     *
     * @return 分离器
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    /**
     * 评估字符串长度.
     *
     * @return 所剩字符串长度
     */
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    /**
     * 返回字符串数值.
     *
     * @return 字符特定数值
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
