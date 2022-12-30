package com.monkgirl.java8inaction.common;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import com.monkgirl.java8inaction.chapter1.BufferedReaderProcessor;
import java.io.FileReader;

public class LambdaUtils{
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
	List<T> result = new ArrayList<T>();
	for(T t : list){
	    if(p.test(t)){
		result.add(t);
	    }
	}
	return result;
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException{
	BufferedReader br = new BufferedReader(new FileReader("e:/Java8/Java8InAction/data.txt"));
	return p.process(br);
    }
}
