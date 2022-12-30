package com.monkgirl.java8inaction.chapter4;

import com.monkgirl.java8inaction.common.Dish;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Exercises{
    public static void main(String...args){
	//run1();
	//run2();
	//run3();
	run4();
    }

    private static List<Dish> menu = new ArrayList<>();

    private static long time1;

    private static long time2;
    
    static{
	/*Function<Double, Dish> fun = Dish::new;
	Random rand = new Random(47);
	for(int i=0;i<5;i++){
	    menu.add(fun.apply(Math.round(rand.nextDouble()*50000)/100.00));
	}
	menu.get(0).setName("Fish");
	menu.get(1).setName("Beef");
	menu.get(2).setName("Pork");
	menu.get(3).setName("Cabbage");
	menu.get(4).setName("Onion");*/

	menu = Arrays.asList(new Dish(800, "pork", Dish.Type.MEAT, false),
			     new Dish(900, "beef", Dish.Type.MEAT, false),
			     new Dish(500, "chicken", Dish.Type.MEAT, false),
			     new Dish(530, "french fries", Dish.Type.OTHER, true),
			     new Dish(350, "rice", Dish.Type.OTHER, true),
			     new Dish(120, "season fruit", Dish.Type.OTHER, true),
			     new Dish(550, "pizza", Dish.Type.OTHER, true),
			     new Dish(300, "prawns", Dish.Type.FISH, true),
			     new Dish(450, "salmon", Dish.Type.FISH, true));
	System.out.println(menu);
    }
    
    public static void run1(){
	System.out.println(menu);
	List<Dish> lowerCalories = new ArrayList<>();
	for(Dish dish : menu){
	    if(dish.getCalories()<150){
		lowerCalories.add(dish);
	    }
	}
	Collections.sort(lowerCalories, new Comparator<Dish>(){
		public int compare(Dish d1, Dish d2){
		    return Double.compare(d1.getCalories(), d2.getCalories());
		}
	    });
	System.out.println(lowerCalories);
    }

    public static void run2(){
	long startTime = System.nanoTime();
	//System.out.println(menu);
	List<String> lowerCaloriesName = menu.stream()
	    .filter(d->d.getCalories()<150)
	    .sorted(comparing(Dish::getCalories))
	    .map(Dish::getName)
	    .collect(toList());
	System.out.println(lowerCaloriesName);
	time1 = System.nanoTime()-startTime;
 	System.out.println("Time: " + time1);
    }

    public static void run3(){
	long startTime = System.nanoTime();
	//System.out.println(menu);
	List<String> lowerCaloriesName = menu.parallelStream()
	    .filter(d->d.getCalories()<150)
	    .sorted(comparing(Dish::getCalories))
	    .map(Dish::getName)
	    .collect(toList());
	System.out.println(lowerCaloriesName);
	time2 = System.nanoTime()-startTime;
	System.out.println("Time: " + time2);
	System.out.println(Math.round((time1-time2)/(time2*1.0)*100)+"%");
    }

    public static void run4(){
	List<String> threeHighCaloricDishNames = menu.stream()
	    .filter(d->d.getCalories()>400)
	    .map(Dish::getName)
	    .limit(3)
	    .sorted((a, b)->a.compareTo(b))
	    .collect(toList());
	System.out.println(threeHighCaloricDishNames);
    }

    public static void run5(){
	List<String> threeHighCaloricDishNames = menu.stream()
	    .filter(d->{
		    System.out.println("filtering: " + d.getName());
		    return d.getCalories()>400;})
	    .map(d->{
		    System.out.println("mapping: " + d.getName());
		    return d.getName();
		})
	    .limit(3)
	    .collect(toList());
	System.out.println(threeHighCaloricDishNames);
    }

}
