package com.monkgirl.java8inaction.chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop{
    private String name;

    public Shop(String name){
	this.name = name;
    }

    public String getName(){
	return name;
    }
    
    public String getPrice(String product){
	///return calculatePrice(product);
	double price = calculatePrice(product);
	Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
	return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product){
	delay();
	//throw new RuntimeException("Not Found");
	return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public void delay(){
	try{
	    Thread.sleep(1000L);
	}catch(InterruptedException ie){
	    throw new RuntimeException(ie);
	}
    }

    public Future<Double> getPriceAsync(String product){
	CompletableFuture<Double> futurePrice = new CompletableFuture<>();
	/*new Thread(()->{
		try{
		    double price = calculatePrice(product);
		    futurePrice.complete(price);
		}catch(Exception e){
		    futurePrice.completeExceptionally(e);
		}
		}).start();
		return futurePrice;*/
	return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

	public void doSomethingElse(){

	}
}
