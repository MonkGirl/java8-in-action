package com.monkgirl.java8inaction.review;

import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Arrays;
import com.monkgirl.java8inaction.common.Dish;
import com.monkgirl.java8inaction.common.Menu;
import java.util.Map;
import java.util.Optional;
import java.util.DoubleSummaryStatistics;
import com.monkgirl.java8inaction.common.CaloricLevel;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Review{
    private static List<String> list = new ArrayList<>();

    static{
	list.add("Icarus");
	list.add("Aurora");
	list.add("Adam");
	list.add("Cythina");
	list.add("Zeus");
    }
    
    public static void main(String...args){
	//run1();
	//run2();
	//run3();
	//run4();
	//run5();
	//run6();
	//run7();
	run8();
    }

    public static void run1(){
	List<String> result = filter(list, (String str) -> str.length()>4);
	System.out.println(result);

	filter(list, (String str) -> System.out.println(str + ": " + str.length()));

	List<Integer> resultList = filter(list, (String str) -> str.length());
	System.out.println(resultList);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
	List<T> resultList = new ArrayList<>();
	for(T t : list){
	    if(p.test(t)){
		resultList.add(t);
	    }
	}
	return resultList;
    }

    public static <T> void filter(List<T> list, Consumer<T> c){
	for(T t : list){
	    c.accept(t);
	}
    }

    public static <T, R> List<R> filter(List<T> list, Function<T, R> f){
	List<R> resultList = new ArrayList<>();
	for(T t : list){
	    resultList.add(f.apply(t));
	}
	return resultList;
    }

    public static void run2(){
	IntPredicate evenNumbers = (int i) -> i%2==0;
	System.out.println(evenNumbers.test(1000));

	Predicate<Integer> oddNumbers = i -> i%2 != 0;
	System.out.println(oddNumbers.test(1000));
    }

    public static void run3(){
	list.sort((s1, s2)->s1.compareTo(s2));
	System.out.println(list);

	list.sort((s1, s2)->Integer.valueOf(s1.length()).compareTo(Integer.valueOf(s2.length())));
	System.out.println(list);

	list.sort(Comparator.comparing(String::toString));
	System.out.println(list);

	list.sort(Comparator.comparing(String::length).thenComparing(String::toString).reversed());
	System.out.println(list);

	Predicate<String> startA = str -> str.startsWith("A");
	List<String> newList = list.stream().filter(startA.negate()).collect(Collectors.toList());
	System.out.println(newList);
    }

    public static void run4(){
	List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4);
	List<Integer> numbers2 = Arrays.asList(2, 3);
	List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
	    .collect(Collectors.toList());
	for(int[] i : pairs){
	    System.out.println(Arrays.toString(i));
	}

	List<int[]> pairs1 = numbers1.stream()
	    .flatMap(i -> numbers2.stream().filter(j -> (i+j)%2==0).map(j -> new int[]{i, j}))
	    .collect(Collectors.toList());

	for(int[] i : pairs1){
	    System.out.println(Arrays.toString(i));
	}
    }

    public static void run5(){
	List<Dish> dishs = Menu.menu;
	System.out.println(dishs);
	long num = dishs.stream().count();
	System.out.println(num);

	Map<Dish.Type, List<Dish>> dishs1 = dishs.stream().collect(Collectors.groupingBy(Dish::getType));
	System.out.println(dishs1);

	Optional<Dish> mostCalorieDish = dishs.stream()
	    .collect(Collectors.maxBy(Comparator.comparingDouble(Dish::getCalories)));
	System.out.println(mostCalorieDish);

	Optional<Dish> leastCalorieDish = dishs.stream()
	    .collect(Collectors.minBy(Comparator.comparingDouble(Dish::getCalories)));
	System.out.println(leastCalorieDish);

	double sumCalories = dishs.stream().collect(Collectors.summingDouble(Dish::getCalories));
	System.out.println(sumCalories);

	double averagingCalories = dishs.stream().collect(Collectors.averagingDouble(Dish::getCalories));
	System.out.println(averagingCalories);

	DoubleSummaryStatistics menuStatistics = dishs.stream().collect(Collectors.summarizingDouble(Dish::getCalories));
	System.out.println(menuStatistics);

	String shortMenu = dishs.stream().map(Dish::getName).collect(Collectors.joining(", "));
	System.out.println(shortMenu);

	double sumCalories1 = dishs.stream().collect(Collectors.reducing(0.0, Dish::getCalories, (i, j) -> i+j));
	System.out.println(sumCalories1);

	Optional<Dish> mostCalories1 = dishs.stream()
	    .collect(Collectors.reducing((d1, d2) -> d1.getCalories()>d2.getCalories()?d1:d2));
	System.out.println(mostCalories1);

	double sumCalories2 = dishs.stream().mapToDouble(Dish::getCalories).sum();
	System.out.println(sumCalories2);
    }

    public static void run6(){
	Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel
	    = Menu.menu.stream()
	    .collect(Collectors
		     .groupingBy(Dish::getType, Collectors.groupingBy(dish -> {if(dish.getCalories() <= 400){
				     return CaloricLevel.DIET;
				 }else if(dish.getCalories() <= 700){
				     return CaloricLevel.NORMAL;
				 }else{
				     return CaloricLevel.FAT;
				 }
			     })));
	System.out.println(dishesByTypeCaloricLevel);

	Map<Dish.Type, Long> dishesByTypeNum
	    = Menu.menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
	System.out.println(dishesByTypeNum);

	Map<Dish.Type, Optional<Dish>> mostCaloricByType
	    = Menu.menu.stream()
	    .collect(Collectors.groupingBy(Dish::getType,
					   Collectors.maxBy(Comparator.comparingDouble(Dish::getCalories))));
	System.out.println(mostCaloricByType);

	Map<Dish.Type, Dish> mostCaloricByType1
	    = Menu.menu.stream()
	    .collect(Collectors
		     .groupingBy(Dish::getType,
				 Collectors.collectingAndThen(Collectors
							      .maxBy(Comparator.comparingDouble(Dish::getCalories)),
									Optional::get)));
	System.out.println(mostCaloricByType1);

	Map<Dish.Type, Double> sumCaloricByType
	    = Menu.menu.stream()
	    .collect(Collectors
		     .groupingBy(Dish::getType,
				 Collectors.summingDouble(Dish::getCalories)));
	System.out.println(sumCaloricByType);
    }

    public static void run7(){
	Map<Boolean, List<Dish>> map = Menu.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
	System.out.println(map);

	Map<Boolean, Map<Dish.Type, List<Dish>>> map1 = Menu.menu.stream()
	    .collect(Collectors.partitioningBy(Dish::isVegetarian,
					       Collectors.groupingBy(Dish::getType)));
	System.out.println(map1);

	System.out.println(isPrime(85));

	System.out.println(partitionPrimes(25));
    }

    public static boolean isPrime(int candicate){
	int candicateRoot = (int) Math.sqrt((double)candicate);
	return IntStream.rangeClosed(2, candicateRoot)
	    .noneMatch(i -> candicate%i==0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n){
	return IntStream.rangeClosed(2, n).boxed()
	    .collect(Collectors.partitioningBy(Review::isPrime));
    }

    public static long parallelSum(long n){
	return Stream.iterate(1L, i -> i+1)
	    .limit(n)
	    .parallel()
	    .reduce(0L, Long::sum);
    }

    public static long sequentSum(long n){
	return Stream.iterate(1L, i -> i+1)
	    .limit(n)
	    .reduce(0L, Long::sum);
    }
    
    public static void run8(){
	Long startTime1 = System.nanoTime();
	System.out.println(parallelSum(100));
	System.out.println((System.nanoTime()-startTime1)/1_000 + " ms");

	Long startTime2 = System.nanoTime();
	System.out.println(sequentSum(100));
	System.out.println((System.nanoTime()-startTime2)/1_000 + " ms");
    }
}
