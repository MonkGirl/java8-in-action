package com.monkgirl.java8inaction.chapter8;

public class LeMonde implements Observer{
    public void notify(String tweet){
	if(tweet!=null&&tweet.contains("wine")){
	    System.out.println("Today cheese, wine and news! " + tweet);
	}
    }
}
