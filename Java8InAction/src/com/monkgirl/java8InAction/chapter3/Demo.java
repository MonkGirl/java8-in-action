package com.monkgirl.java8InAction.chapter3;

import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
}
