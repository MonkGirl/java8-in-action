package com.monkgirl.java8InAction.chapter1;

import java.io.IOException;
import java.io.BufferedReader;

@FunctionalInterface
public interface BufferedReaderProcessor{
    String process(BufferedReader b) throws IOException;
}
