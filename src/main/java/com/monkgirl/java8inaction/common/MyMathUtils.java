package com.monkgirl.java8inaction.common;

import java.util.stream.IntStream;

public class MyMathUtils{
    public static boolean isPrime(int candidate){
	int candidateRoot = (int)Math.sqrt((double) candidate);
	return IntStream.rangeClosed(2, candidateRoot)
	    .noneMatch(i -> candidate % i == 0);
    }
}
