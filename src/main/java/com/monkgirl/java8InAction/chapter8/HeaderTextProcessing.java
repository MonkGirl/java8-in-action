package com.monkgirl.java8InAction.chapter8;

public class HeaderTextProcessing extends ProcessingObject<String>{
    public String handWork(String text){
	return "From Raoul, Mario and Alan: " + text;
    }
}
