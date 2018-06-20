package com.monkgirl.java8InAction.chapter3;

import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;

public class Demo{
    public static void main(String...args){
	run();
    }

    public static void run(){
	List<String> list = Arrays.asList("just a test".split(" "));
	List<String> result = filter(list, (String s)->!s.isEmpty());
	List<String> result1 = filter(list, (String s)->s.length()>2);
	System.out.println(result);
	System.out.println(result1);

	forEach(Arrays.asList(1,2,3,4,5), (Integer i)->System.out.println(i));

	System.out.println(map(Arrays.asList("monday","tuesday","wednesday","thursday", "friday", "saturday", "sunday"), (String s)->s.length()));

	IntPredicate evenNumbers = (int i)->i%2==0;
	System.out.println(evenNumbers.test(1000));

	Predicate<Integer> oddNumbers = (Integer i)->i%2==1;
	System.out.println(oddNumbers.test(1000));

	int portNumber = 1337;
	Runnable r1 = ()->System.out.println(portNumber);
	r1.run();
	portNumber = 1338;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
	List<T> results = new ArrayList<T>();
	for(T t : list){
	    if(p.test(t)){
		results.add(t);
	    }
	}
	return results;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
	for(T t : list){
	    c.accept(t);
	}
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f){
	List<R> result = new ArrayList<R>();
	for(T t : list){
	    result.add(f.apply(t));
	}
	return result;
    }

}
