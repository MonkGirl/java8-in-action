package com.monkgirl.java8InAction.chapter3;

import com.monkgirl.java8InAction.common.Apple;
import java.util.List;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import com.monkgirl.java8InAction.common.Computer;
import com.monkgirl.java8InAction.common.TriFunction;
import java.util.Collections;
import java.util.function.Predicate;
import static java.util.Comparator.*;

public class Exercises{
    public static void main(String...args){
	//run1();
	//run2();
	//run3();
	//run4();
	run5();
    }

    public static void run1(){
	BiFunction<String, Integer, Apple> bf = Apple::new;
	List<Apple> inventory = new ArrayList<>();
	inventory.add(bf.apply("green", 149));
	inventory.add(bf.apply("yellow", 90));
	inventory.add(bf.apply("red", 298));
	System.out.println(inventory);
	inventory.sort(comparing(Apple::getWeight));
	System.out.println(inventory);
    }

    public static void run2(){
	String s = "10";
	Function<String, Double> f = Double::parseDouble;
	System.out.println(f.apply(s));

	Function<String, Integer> fu = String::length;
	System.out.println(fu.apply(s));

	BiFunction<String, Integer, Apple> bf = Apple::new;
	Apple apple = bf.apply("pink", 706);

	Function<Apple, Double> fun = Apple::getWeight;
	System.out.println(fun.apply(apple));

	TriFunction<String, String, String, Computer> tf = Computer::new;
	System.out.println(tf.apply("amd","kingston","westdata"));
    }

    public static void run3(){
	List<Apple> inventory = new ArrayList<>();
	inventory.add(new Apple("green", 405));
	inventory.add(new Apple("red", 382));
	inventory.add(new Apple("purple", 430));
	inventory.add(new Apple("red", 430));
	System.out.println("before: "+inventory);

	//method 1
	inventory.sort(new AppleComparator());
	System.out.println("method1: "+inventory);
	Collections.shuffle(inventory);
	System.out.println("restore: "+inventory);

	//method 2
	inventory.sort(new Comparator<Apple>(){
		public int compare(Apple a1, Apple a2){
		    return Double.valueOf(a1.getWeight()).compareTo(Double.valueOf(a2.getWeight()));
		}
	    });
	System.out.println("method2: " + inventory);
	Collections.shuffle(inventory);
	System.out.println("restore: " + inventory);
	
	//method 3
	inventory.sort((a1, a2)->Double.valueOf(a1.getWeight()).compareTo(Double.valueOf(a2.getWeight())));
	System.out.println("method3: " + inventory);
	Collections.shuffle(inventory);
	System.out.println("restore: " + inventory);

	//method 4
	inventory.sort(comparing(Apple::getWeight));
	System.out.println("method4: " + inventory);
	Collections.shuffle(inventory);
	System.out.println("restore: " + inventory);

	//thenComparing
	inventory.sort(comparing(Apple::getWeight).thenComparing(Apple::getColor));
	System.out.println("thenComparing: " + inventory);
    }

    public static void run4(){
	Predicate<Apple> redApple = (a)->"red".equals(a.getColor());
	Apple a = new Apple("red", 302);
	Apple b = new Apple("green", 159);
	System.out.println(redApple.test(a));

	Predicate<Apple> noRedApple = redApple.negate();
	System.out.println(noRedApple.test(b));

	Predicate<Apple> redAndHeavyApple = redApple.and(apple->apple.getWeight()>310);
	System.out.println(redAndHeavyApple.test(a));
    }

    public static void run5(){
	Function<Integer, Integer> f = x -> x+1;
	Function<Integer, Integer> g = x -> x*2;
	Function<Integer, Integer> h = f.andThen(g);
	Function<Integer, Integer> p = f.compose(g);
	System.out.println(h.apply(1));
	System.out.println(p.apply(1));
    }
}

class AppleComparator implements Comparator<Apple>{
    public int compare(Apple a1, Apple a2){
	return Double.valueOf(a1.getWeight()).compareTo(Double.valueOf(a2.getWeight()));
    }
}
