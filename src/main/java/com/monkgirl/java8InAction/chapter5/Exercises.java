package com.monkgirl.java8InAction.chapter5;

import java.util.Arrays;
import com.monkgirl.java8InAction.common.Dish;
import com.monkgirl.java8InAction.common.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.util.function.IntSupplier;
import static java.util.stream.Collectors.toList;

public class Exercises{
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
	//run1();
	//run2();
	//run3();
	//run4();
	//run5();
	//run6();
	//run7();
	//run8();
	//run9();
	//run10();
	//run11();
	run12();
    }

    public static void run1(){
	List<Dish> vegetarianDishes = menu.parallelStream()
	    .filter(Dish::isVegetarian)
	    .collect(toList());
	System.out.println(vegetarianDishes);
    }

    public static void run2(){
	List<Integer> numbers = Arrays.asList(7,1,2,3,5,8,10,1,7,5);
	numbers.stream()
	    .filter(i->i%2==1)
	    .distinct()
	    //.limit(3)
	    .sorted((a, b)->a.compareTo(b))
	    //.limit(3)
	    .skip(3)
	    .forEach(System.out::println);
    }

    public static void run3(){
	List<String> dishNames = menu.parallelStream()
	    .map(Dish::getName)
	    .collect(toList());
	System.out.println(dishNames);

	List<Integer> wordLengths = dishNames.parallelStream()
	    .map(String::length)
	    .collect(toList());
	System.out.println(wordLengths);

	List<String> words = dishNames.parallelStream()
	    .map(word->word.split(""))
	    .flatMap(Arrays::stream)
	    //.map(Arrays::stream)
	    .distinct()
	    //.filter(str->str.trim().length()>0)
	    .collect(toList());
	System.out.println(words);

	List<Integer> numbers = Arrays.asList(1,2,3,4,5);
	List<Integer> square = numbers.parallelStream()
	    .map(n->n*n)
	    .collect(toList());
	System.out.println(square);

	List<Integer> num1 = Arrays.asList(1,2,3),
	    num2 = Arrays.asList(3,4);
	List<int[]> result = num1.stream()
	    .flatMap(i->num2.stream().filter(j->(i+j)%3==0).map(j->new int[]{i,j}))
	    .collect(toList());
	for(int[] i : result){
	    System.out.print(Arrays.toString(i));
	}
    }

    public static void run4(){
	if(menu.stream().anyMatch(Dish::isVegetarian)){
	    System.out.println("The menu is (somewhat) vegetarian friendly!!");
	}
	if(menu.parallelStream().allMatch(d->d.getCalories()<1000)){
	    System.out.println("Cool!");
	}
	if(menu.parallelStream().noneMatch(d->d.getCalories()>1000)){
	    System.out.println("Very cool!!");
	}

	Optional<Dish> dish = menu.stream()
	    .filter(Dish::isVegetarian)
	    .findAny();
	System.out.println(dish);

	menu.stream()
	    .filter(Dish::isVegetarian)
	    .findAny()
	    .ifPresent(d->System.out.println(d.getName()));

	List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
	Optional<Integer> firstSquareDivisibleByTwo = someNumbers.parallelStream()
	    .map(i->i*i)
	    .filter(i->i%2==0)
	    .findFirst();
	System.out.println(firstSquareDivisibleByTwo);
    }

    public static void run5(){
	List<Integer> numbers = Arrays.asList(2,4,7,1,2,3,-4);
	//int result1 = numbers.parallelStream().reduce(0,(a, b)->a+b);
	int result1 = numbers.parallelStream().reduce(0, Integer::sum);
	System.out.println(result1);

	int result2 = numbers.parallelStream().reduce(1,(a,  b)->a*b);
	System.out.println(result2);

	Optional<Integer> result3 = numbers.parallelStream().reduce(Integer::sum);
	System.out.println(result3.get());

	//Optional<Integer> result4 = numbers.parallelStream().reduce(Integer::max);
	Optional<Integer> result4 = numbers.parallelStream().reduce(Integer::min);
	System.out.println(result4.get());

	int count = menu.parallelStream().map(d->1).reduce(0,Integer::sum);
	System.out.println(count);
    }

    public static void run6(){
	double calories = menu.parallelStream()
	    .mapToDouble(Dish::getCalories)
	    .sum();
	System.out.println(calories);

	OptionalDouble max = menu.parallelStream()
	    .mapToDouble(Dish::getCalories)
	    .max();
	System.out.println(max.getAsDouble());

	DoubleStream doubleStream = menu.parallelStream()
	    .mapToDouble(Dish::getCalories);

	Stream<Double> stream = doubleStream.boxed();

	IntStream evenNumber = IntStream.rangeClosed(1, 100);
	//System.out.println(evenNumber.sum());
	List<Integer> list = evenNumber.boxed().collect(toList());
	System.out.println(list);
    }

    public static void run7(){
	IntStream number = IntStream.rangeClosed(1, 100);
	Stream<int[]> pythagoreanTriples = number.boxed()
	    .flatMap(a->IntStream.rangeClosed(a, 100)
		     .filter(b->Math.sqrt(a*a+b*b)%1==0)
		     .mapToObj(b->new int[]{a, b, (int)Math.sqrt(a*a+b*b)}));
        pythagoreanTriples.forEach(a->System.out.println(a[0]+","+a[1]+","+a[2]));
    }

    public static void run8(){
	Stream<String> stream = Stream.of("Java8", "In", "Action");
	stream.map(String::toUpperCase).forEach(System.out::println);

	int[] numbers = {1,2,3,4,5,6,7};
	int sum = Arrays.stream(numbers).sum();
	System.out.println(sum);

    }

    public static void run9(){
	Path path = Paths.get("E:/Java8/Java8InAction/data.txt");
	long startTime1 = System.nanoTime();
	try{
	    Stream<String> lines = Files.lines(path, Charset.defaultCharset());
	    long uniqueWords = lines.flatMap(line->Arrays.stream(line.split(" "))).distinct().count();
	    System.out.println(uniqueWords);
	}catch(IOException e){

	}
	System.out.println((System.nanoTime()-startTime1)/1000.0);

	long startTime2 = System.nanoTime();
	BufferedReader reader = null;
	try{
	    reader = new BufferedReader(new FileReader(new File("E:/java8/Java8InAction/data.txt")));
	    String str;
	    Set<String> container = new HashSet<>();
	    while((str=reader.readLine())!=null){
		for(String s : str.split(" ")){
		    container.add(s);
		}
	    }
	    System.out.println(container.size());
	}catch(IOException e){
	    
	}finally{
	    if(reader!=null){
		try{
		    reader.close();
		}catch(IOException e){
		}
	    }
	}
	System.out.println((System.nanoTime()-startTime2)/1000.0);
    }

    public static void run10(){
	//iterate
	Stream.iterate(0, n->n+2)
	    .limit(10)
	    .forEach(System.out::println);

	Stream.iterate(new int[]{0,1},t->new int[]{t[1], t[0]+t[1]})
	    .limit(10)
	    .map(t->t[0])
	    .forEach(System.out::println);
    }

    public static void run11(){
	Stream.generate(Math::random)
	    .limit(5)
	    .forEach(System.out::println);

	IntStream.generate(()->1)
	    .limit(5)
	    .forEach(System.out::println);

	IntStream.generate(new IntSupplier(){
		public int getAsInt(){
		    return 3;
		}
	    }).limit(5)
	    .forEach(System.out::println);
    }

    public static void run12(){
	IntSupplier fib = new IntSupplier(){
		private int previous = 0;
		private int current  = 1;
		public int getAsInt(){
		    int oldPrevious = this.previous;
		    int nextValue = this.previous + this.current;
		    this.previous = this.current;
		    this.current  = nextValue;
		    return oldPrevious;
		}
	    };
	IntStream.generate(fib)
	    .limit(5)
	    .forEach(System.out::println);
    }
}
