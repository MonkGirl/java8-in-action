package com.monkgirl.java8InAction.chapter1;

import com.monkgirl.java8InAction.common.AppleBasket;
import com.monkgirl.java8InAction.common.Apple;
import java.util.List;
import java.util.ArrayList;

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

	System.out.println(AppleBasket.filterApple(list, (Apple a)->"green".equalsIgnoreCase(a.getColor())));
	System.out.println(AppleBasket.filterApple(list, (Apple a)->a.getWeight()>150));
	
    }
}
