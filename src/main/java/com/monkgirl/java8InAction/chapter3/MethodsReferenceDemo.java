package com.monkgirl.java8InAction.chapter3;

import java.util.Arrays;
import java.util.List;
import com.monkgirl.java8InAction.common.Apple;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.HashMap;
import java.util.Map;

public class MethodsReferenceDemo{
    public static void main(String...args){
	List<String> str = Arrays.asList("a","b","A","B");
	//	str.sort((s1, s2)->s1.compareToIgnoreCase(s2));
	System.out.println("Lambda: "+str);
	str.sort(String::compareToIgnoreCase);
	System.out.println("MethodReference: "+str);

	Supplier<Apple> s = Apple::new;
	Apple a1 = s.get();
	System.out.println(a1);

	Function<Integer, Apple> f = Apple::new;
	Apple a2 = f.apply(232);
	System.out.println(a2);
	
	BiFunction<String, Integer, Apple> b = Apple::new;
	Apple a3 = b.apply("yellow", 203);

	System.out.println(a3);

	System.out.println(giveMeApple("green", 204));
    }

    static Map<String, Function<Integer, Apple>> map = new HashMap<>();

    static{
	map.put("green", Apple::new);
	map.put("purple", Apple::new);
    }
    
    public static Apple giveMeApple(String color, Integer weight){
	return map.get(color.toLowerCase()).apply(weight);
    }
}
