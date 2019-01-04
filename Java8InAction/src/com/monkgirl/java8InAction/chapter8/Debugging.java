package com.monkgirl.java8InAction.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Debugging{
    public static void main(String...args){
	List<Point> points = Arrays.asList(new Point(2, 2), null);
	//points.stream().map(p -> p.getX()).forEach(System.out::println);
	//points.stream().map(Point::getX).forEach(System.out::println);

	List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
	//numbers.stream().map(Debugging::divideByZero).forEach(System.out::println);

	numbers.stream().map(x -> x+17).filter(x -> x%2==0).limit(3).forEach(System.out::println);

	numbers.stream().peek(x -> System.out.println("from Stream : " + x)).map(x -> x+17)
	    .peek(x -> System.out.println("from map : " + x)).filter(x -> x%2==0)
	    .peek(x -> System.out.println("from filter : " + x)).limit(3)
	    .peek(x -> System.out.println("from limit : " + x)).collect(Collectors.toList());
    }

    public static int divideByZero(int i){
	return i/0;
    }
}
