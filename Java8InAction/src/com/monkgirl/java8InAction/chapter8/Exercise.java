package com.monkgirl.java8InAction.chapter8;

import com.monkgirl.java8InAction.common.CaloricLevel;
import java.util.stream.Collectors;
import com.monkgirl.java8InAction.common.Dish;
import java.util.Arrays;
import com.monkgirl.java8InAction.common.Dish.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Exercise{

    private static List<Dish> menu = new ArrayList<>();
    
    static{
	menu = Arrays.asList(new Dish(800, "pork", Dish.Type.MEAT, false),
			     new Dish(900, "beef", Dish.Type.MEAT, false),
			     new Dish(500, "chicken", Dish.Type.MEAT, false),
			     new Dish(530, "french fries", Dish.Type.OTHER, true),
			     new Dish(350, "rice", Dish.Type.OTHER, true),
			     new Dish(120, "season fruit", Dish.Type.OTHER, true),
			     new Dish(550, "pizza", Dish.Type.OTHER, true),
			     new Dish(300, "prawns", Dish.Type.FISH, true),
			     new Dish(450, "salmon", Dish.Type.FISH, true));
    }

    
    public static void main(String...args){
	run1();
    }

    public static void doSomething(Runnable r){
	r.run();
    }

    public static void doSomething(Task t){
	t.execute();
    }

    public static void run1(){
	//doSomething((Task)()->System.out.println("doSomething..."));
	Map<CaloricLevel, List<Dish>> dishesByCaloricLevel
	    = menu.stream().collect(Collectors.groupingBy(dish->{
			if(dish.getCalories()<=400){
			    return CaloricLevel.DIET;
			}else if(dish.getCalories()<=700){
			    return CaloricLevel.NORMAL;
			}else{
			    return CaloricLevel.FAT;
			}
		    }));
	System.out.println(dishesByCaloricLevel);

	Map<CaloricLevel, List<Dish>> dishesByCalorieLevel1
	    = menu.stream().collect(Collectors.groupingBy(Dish::getCaloricLevel));
	System.out.println(dishesByCalorieLevel1);

	List<String> list = menu.parallelStream().filter(dish->dish.getCalories()>300).map(Dish::getName).collect(Collectors.toList());
	System.out.println(list);
    }
}
