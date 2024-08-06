package com.monkgirl.java8inaction.chapter3;

import com.monkgirl.java8inaction.chapter1.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author MissYoung
 * @version 0.1
 * @since 2022-04-08 09:43:04
 */
public class Lambda {
    public static void process(Runnable runnable) {
        runnable.run();
    }

    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader("data.txt"))){
            return processor.process(reader);
        }
    }

    public static void sort(List<String> list) {
    }

    public static void methodReference() {
    }
}
