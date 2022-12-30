package com.monkgirl.java8inaction.chapter14;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;
import com.monkgirl.java8inaction.common.MyMathUtils;
import com.monkgirl.java8inaction.common.MyList;
import com.monkgirl.java8inaction.common.LazyList;

public class Exercise{
    public static void main(String...args){
	//run1();
	//run2();
	run3();
    }

    public static void run1(){
	DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
	DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
	DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

	double temp = convertCtoF.applyAsDouble(37.5);
	System.out.println(temp);

	double gbp = convertUSDtoGBP.applyAsDouble(1000);
	System.out.println(gbp);
    }

    public static DoubleUnaryOperator curriedConverter(double f, double d){
	return (double x) -> x*f + d;
    }

    public static Stream<Integer> primes(int n){
	return Stream.iterate(2, i->i+1)
	    .filter(MyMathUtils::isPrime)
	    .limit(n);
    }

    public static MyList<Integer> primes(MyList<Integer> numbers){
	return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n->n%numbers.head() != 0)));
    }

    public static LazyList<Integer> from(int n){
	return new LazyList<Integer>(n, ()->from(n+1));
    }

    public static <T> void printAll(MyList<T> list){
	while(!list.isEmpty()){
	    System.out.println(list.head());
	    list = list.tail();
	}
    }
    
    public static void run2(){
	//MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
	LazyList<Integer> numbers = from(2);
	int two = numbers.head();
	int three = numbers.tail().head();
	int four = numbers.tail().tail().head();
	System.out.println(two + " " + three + " " + four);

	LazyList<Integer> numbers1 = from(2);
	int two1 = primes(numbers1).head();
	int three1 = primes(numbers1).tail().head();
	int four1 = primes(numbers1).tail().tail().head();
	System.out.println(two1 + " " + three1 + " " + four1);
    }

    public static void run3(){
	printAll(from(2));
    }
}
