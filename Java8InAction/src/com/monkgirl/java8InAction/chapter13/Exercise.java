package com.monkgirl.java8InAction.chapter13;

import java.util.stream.LongStream;

public class Exercise{
    public static void main(String...args){
	run1();
    }

    public static void run1(){
	long startTime = System.nanoTime();
	factorialIterative(100);
	long time1 = System.nanoTime();
	System.out.println((time1 - startTime)/1_000 + " msecs.");
	factorialRecursive(100);
	long time2 = System.nanoTime();
	System.out.println((time2 - time1)/1_000 + " msecs.");
	factorialStream(100);
	long time3 = System.nanoTime();
	System.out.println((time3 - time2)/1_000 + " msecs");
	factorialTailRecursive(100);
	System.out.println((System.nanoTime() - time3)/1_000 + " msecs.");
    }

    public static long factorialIterative(int n){
	long r = 1;
	for(int i = 1; i<=n;i++){
	    r *=i;
	}
	return r;
    }

    public static long factorialRecursive(long n){
	return n == 1?1:factorialRecursive(n-1);
    }

    public static long factorialStream(long n){
	return LongStream.rangeClosed(1, n).reduce(1, (long a, long b) -> a*b);
    }

    public static long factorialTailRecursive(long n){
	return factorialHelper(1, n);
    }

    public static long factorialHelper(long acc, long n){
	return n==1?acc:factorialHelper(acc*n, n-1);
    }
}
