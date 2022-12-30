package com.monkgirl.java8inaction.chapter1;

import com.monkgirl.java8inaction.common.AppleBasket;
import com.monkgirl.java8inaction.common.Apple;
import java.util.List;
import java.util.ArrayList;
import com.monkgirl.java8inaction.common.LambdaUtils;
import java.io.BufferedReader;

public class Exercise{
    public static void main(String... args){
	List<Apple> list = AppleBasket.getApples(5);
	System.out.println(list);
	List<Apple> resultGreen = AppleBasket.filterGreenApple(list);
	System.out.println(resultGreen);
	List<Apple> resultHeavy = AppleBasket.filterHeavyApple(list);
	System.out.println(resultHeavy);

	System.out.println(AppleBasket.filterApple(list, AppleBasket::isGreen));
	System.out.println(AppleBasket.filterApple(list, AppleBasket::isHeavy));

	System.out.println(AppleBasket.filterApple(list, a->"green".equalsIgnoreCase(a.getColor())));
	System.out.println(AppleBasket.filterApple(list, (Apple a)->a.getWeight()>150));

	System.out.println(LambdaUtils.filter(list, (Apple a)->"red".equalsIgnoreCase(a.getColor())));

	List<Integer> numbers = new ArrayList<Integer>();
	for(int i=0;i<10;){
	    numbers.add(i++);
	}
	System.out.println(LambdaUtils.filter(numbers, (Integer i)->i%2==0));

	list.sort((Apple a1, Apple a2) -> Double.valueOf(a1.getWeight()).compareTo(Double.valueOf(a2.getWeight())));
	System.out.println(list);

	Thread t = new Thread(() -> System.out.println("Hello World"));
	t.start();

	Runnable r1 = () -> System.out.println("Hello World");

	r1.run();

	try{
	    System.out.println(LambdaUtils.processFile((BufferedReader br) -> br.readLine()));
	    System.out.println(LambdaUtils.processFile((BufferedReader br) -> br.readLine()+br.readLine()));
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
}
