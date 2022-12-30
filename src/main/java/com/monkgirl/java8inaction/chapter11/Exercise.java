package com.monkgirl.java8inaction.chapter11;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executor;
import java.util.Random;
import java.util.stream.Stream;

public class Exercise{
    private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
					     new Shop("LetsSaveBig"),
					     new Shop("MyFavoriteShop"),
						    new Shop("BuyItAll"),
						    new Shop("ShopEasy"),
						    new Shop("ShopToll"),
						    new Shop("FruitShop"),
						    new Shop("ComputerShop"),
						    new Shop("OverseaShop"));

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
								   new ThreadFactory(){
								       public Thread newThread(Runnable r){
									   Thread t = new Thread(r);
									   t.setDaemon(true);
									   return t;
								       }
								   });
    private static final Random random = new Random();
    
    public static void main(String...args){
	//run1();
	//run2();
	//run3();
	run4();
    }

    public static void randomDelay(){
	int delay = 500 + random.nextInt(2000);
	try{
	    TimeUnit.MICROSECONDS.sleep(delay);
	}catch(InterruptedException ie){
	    throw new RuntimeException(ie);
	}
    }
    
    public static void run1(){
	ExecutorService executor = Executors.newCachedThreadPool();
	Future<Double> future = executor.submit(new Callable<Double>(){
		public Double call(){
		    return doSomeLongComputation();
		}
	    });
	doSomethingElse();

	try{
	    Double result = future.get(5, TimeUnit.SECONDS);
	}catch(ExecutionException ee){
	    
	}catch(InterruptedException ie){

	}catch(TimeoutException te){
	    
	}
    }

    public static Double doSomeLongComputation(){
	System.out.println("doSomeLongComputation...");
	return 2D;
    }
    
    public static void doSomethingElse(){
	System.out.println("doSomethingElse...");
    }

    public static void run2(){
	Shop shop = new Shop("BestShop");
	long start = System.nanoTime();
	Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
	long invocationTime = ((System.nanoTime()-start)/1_000_000);
	System.out.println("Invocation returned after " + invocationTime + " msecs");
	doSomethingElse();

	try{
	    double price = futurePrice.get();
	    System.out.printf("Price is %.2f%n", price);
	}catch(Exception e){
	    throw new RuntimeException(e);
	}

	long retrievalTime = ((System.nanoTime()-start)/1_000_000);
	System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public static List<String> findPrices(String product){
	//return shops.stream().map(shop -> String.format("%s price %.2f", shop.getName(), shop.getPrice(product)))
	//    .collect(Collectors.toList());
	return shops.stream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount)
	    .collect(Collectors.toList());
    }

    public static List<String> findPricesParallel(String product){
	return shops.parallelStream().map(shop -> String.format("%s price %.2f", shop.getName(), shop.getPrice(product)))
	    .collect(Collectors.toList());
    }

    public static List<String> findPricesAsync(String product){
	/*List<CompletableFuture<String>> priceFutures = shops.stream()
	    .map(shop -> CompletableFuture.supplyAsync(()->shop.getName() + " price is " + shop.getPrice(product), executor))
	    .collect(Collectors.toList());

	return priceFutures.stream()
	    .map(CompletableFuture::join)
	    .collect(Collectors.toList());*/
	
	List<CompletableFuture<String>> priceFutures = shops.stream()
	    .map(shop -> CompletableFuture.supplyAsync(()->shop.getPrice(product), executor))
	    .map(future -> future.thenApply(Quote::parse))
	    .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote), executor)))
		 .collect(Collectors.toList());

	return priceFutures.stream()
	    .map(CompletableFuture::join)
	    .collect(Collectors.toList());
    }

    public static void run3(){
	long start = System.nanoTime();
	//System.out.println(findPrices("myPhone27S"));
	//System.out.println(findPricesParallel("myPhone27S"));
	//System.out.println(findPricesAsync("myPhone27S"));
	System.out.println(findPricesStream("myPhonse27s"));
	CompletableFuture[] futures = findPricesStream("myPhone").map(f -> f.thenAccept(System.out::println))
	    .toArray(size -> new CompletableFuture[size]);
	CompletableFuture.anyOf(futures).join();
	long duration = (System.nanoTime()-start)/1_000_000;
	System.out.println("Done in " + duration + " msecs");
    }

    public static Stream<CompletableFuture<String>> findPricesStream(String product){
	return shops.stream()
	    .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
	    .map(future -> future.thenApply(Quote::parse))
	    .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

    public static void run4(){
	long start = System.nanoTime();
	CompletableFuture[] futures = findPricesStream("myPhone27S")
	    .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start)/1_000_000) + " msecs)")))
	    .toArray(size -> new CompletableFuture[size]);

	CompletableFuture.allOf(futures).join();
	System.out.println("All shops have now responded in " + ((System.nanoTime() - start)/1_000_000) + " msecs");
    }
}
