package com.monkgirl.java8inaction.chapter8;

public class HeaderTextProcessing extends ProcessingObject<String>{
    public String handWork(String text){
	return "From Raoul, Mario and Alan: " + text;
    }
}
