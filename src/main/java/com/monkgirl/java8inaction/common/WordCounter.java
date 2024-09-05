package com.monkgirl.java8inaction.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 单词计数器.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
@AllArgsConstructor
public class WordCounter {
    /**
     * 计数器.
     */
    @Getter
    private final int counter;
    /**
     * 是否是最后一个空格.
     */
    private final boolean lastSpace;

    /**
     * 累加器.
     *
     * @param character 字符
     * @return 单词计数器
     */
    public WordCounter accumulate(final Character character) {
        if (Character.isWhitespace(character)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    /**
     * 组合.
     *
     * @param wordCounter 单词计数器
     * @return 单词计数器
     */
    public WordCounter combine(final WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

}
