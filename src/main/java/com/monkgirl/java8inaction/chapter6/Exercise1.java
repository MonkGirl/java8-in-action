package com.monkgirl.java8inaction.chapter6;

import java.util.Locale;
import java.util.Currency;
import java.util.Arrays;
import com.monkgirl.java8inaction.common.Transaction;
import static com.monkgirl.java8inaction.common.Menu.*;
import java.util.List;
import java.util.ArrayList;
import com.monkgirl.java8inaction.common.Trader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import com.monkgirl.java8inaction.common.Dish;
import java.util.Optional;

import com.monkgirl.java8inaction.common.CaloricLevel;
import java.util.Set;
import java.util.HashSet;

public class Exercise1{
    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan  = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Roma");

    
    private static List<Transaction> transactions =
	Arrays.asList(new Transaction(brian, 2011, 500, Currency.getInstance(Locale.US)),
		      new Transaction(raoul, 2012, 1000, Currency.getInstance(Locale.UK)),
		      new Transaction(raoul, 2011, 400),
		      new Transaction(mario, 2012, 710, Currency.getInstance(Locale.JAPAN)),
		      new Transaction(mario, 2012, 700, Currency.getInstance(Locale.US)),
		      new Transaction(alan, 2012, 950));

    public static void main(String...args){
	//run1();
	//run2();
	//run3();
	//run4();
	//run5();
	run6();
    }

    public static void run1(){
	Map<Currency, List<Transaction>> transactionsByCurrency = new HashMap<>();
	for(Transaction transaction : transactions){
	    Currency currency = transaction.getCurrency();
	    List<Transaction> list = transactionsByCurrency.get(currency);
	    if(list==null){
		list = new ArrayList<>();
		transactionsByCurrency.put(currency, list);
	    }
	    list.add(transaction);
	}
	System.out.println(transactionsByCurrency);

	Map<Currency, List<Transaction>> transactionsByCurrency1 = transactions.stream()
	    .collect(Collectors.groupingBy(Transaction::getCurrency));
	System.out.println(transactionsByCurrency1);

	System.out.println(transactions.stream().collect(Collectors.toList()));
    }

    public static void run2(){
	System.out.println(menu.stream().count());
	System.out.println(menu.stream().collect(Collectors.counting()));
	Comparator<Dish> dishCaloriesComparator = Comparator.comparingDouble(Dish::getCalories);
	Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
	System.out.println(mostCalorieDish.get());

	System.out.println(menu.stream().collect(Collectors.minBy(Comparator.comparingDouble(Dish::getCalories))).get());
	System.out.println(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)));
	System.out.println(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)));
	System.out.println(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)));

	System.out.println(menu.stream().map(Dish::getName).collect(Collectors.joining(", ")));
	System.out.println(menu.stream().collect(Collectors.reducing(0.0, Dish::getCalories, (i, j)->i+j)));
	System.out.println(menu.stream().collect(Collectors.reducing((d1, d2)->d1.getCalories()>d2.getCalories()?d1:d2)));
	System.out.println(menu.stream().collect(Collectors.reducing((d1, d2)->d1.getCalories()<d2.getCalories()?d1:d2)));
    }

    public static void run3(){
	System.out.println(menu.stream().collect(Collectors.reducing(0.0, Dish::getCalories, Double::sum)));
	System.out.println(menu.stream().mapToDouble(Dish::getCalories).sum());
	System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType)));
    }

    public static void run4(){
	System.out.println(menu.stream()
			   .collect(Collectors.groupingBy(dish->{
				       if(dish.getCalories()<400){
				           return CaloricLevel.DIET;
				       }else if(dish.getCalories()>700){
				           return CaloricLevel.FAT;
				       }else{
				           return CaloricLevel.NORMAL;
				       }})));
    }

    public static void run5(){
	Map<Dish.Type, Map<CaloricLevel, List<Dish>>> map = menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish->{
			    if(dish.getCalories()<400){
				           return CaloricLevel.DIET;
				       }else if(dish.getCalories()>700){
				           return CaloricLevel.FAT;
				       }else{
				           return CaloricLevel.NORMAL;
				       }
			})));
        System.out.println(map);

        Map<Dish.Type, Long> map1 = menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(map1);

        Map<Dish.Type, Optional<Dish>> map2 = menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType, Collectors.minBy(Comparator
									   .comparingDouble(Dish::getCalories))));
        System.out.println(map2);
    }

    public static void run6(){
	Map<Dish.Type, Dish> map1 = menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType,
					   Collectors.collectingAndThen(
									Collectors.maxBy(Comparator.comparingDouble(Dish::getCalories)), Optional::get)));
	System.out.println(map1);

	Map<Dish.Type, Double> map2 = menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType, Collectors.summingDouble(Dish::getCalories)));
	System.out.println(map2);

	Map<Dish.Type, Set<CaloricLevel>> map3 = menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish->{
			    if(dish.getCalories()<400){
				           return CaloricLevel.DIET;
				       }else if(dish.getCalories()>700){
				           return CaloricLevel.FAT;
				       }else{
				           return CaloricLevel.NORMAL;
				       }
			},Collectors.toSet())));
	System.out.println(map3);

	Map<Dish.Type, Set<CaloricLevel>> map4 = menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish->{
			    if(dish.getCalories()<400){
				           return CaloricLevel.DIET;
				       }else if(dish.getCalories()>700){
				           return CaloricLevel.FAT;
				       }else{
				           return CaloricLevel.NORMAL;
				       }
			},Collectors.toCollection(HashSet::new))));
	System.out.println(map4);
    }
}
