package com.monkgirl.java8InAction.chapter8;

public class Guardian implements Observer{
    public void notify(String tweet){
	if(tweet!=null&&tweet.contains("queue")){
	    System.out.println("Yet another news in London..." + tweet);
	}
    }
}
