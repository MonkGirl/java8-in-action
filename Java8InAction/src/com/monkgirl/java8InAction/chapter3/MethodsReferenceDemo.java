package com.monkgirl.java8InAction.chapter3;

import java.util.Arrays;
import java.util.List;

public class MethodsReferenceDemo{
    public static void main(String...args){
	List<String> str = Arrays.asList("a","b","A","B");
	//	str.sort((s1, s2)->s1.compareToIgnoreCase(s2));
	System.out.println("Lambda: "+str);
	str.sort(String::compareToIgnoreCase);
	System.out.println("MethodReference: "+str);
    }
}
