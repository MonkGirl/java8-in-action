package com.monkgirl.java8inaction.chapter7;

import java.util.stream.Stream;
import java.util.function.Function;
import java.math.BigDecimal;
import java.util.stream.LongStream;
import com.monkgirl.java8inaction.common.Accumulator;
import java.util.concurrent.ForkJoinPool;
import com.monkgirl.java8inaction.common.ForkJoinSumCalculator;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import com.monkgirl.java8inaction.common.WordCounter;
import java.util.stream.StreamSupport;
import com.monkgirl.java8inaction.common.WordCounterSpliterator;
import java.util.Spliterator;

public class ParallelStreams{
    private static final String SENTENCE = "Nel mezzo del camin di nostra vita mi ritrovai in una selva oscura "
	+ "che la dritta via era smarrita";
    
    public static void main(String...args){
	//run1();
	run2();
    }

    public static void run1(){
	long i = 10_000_000L;
	System.out.println("Sequential sum done in: "
			   + measureSumPerf(ParallelStreams::sequentialSum, i) + " msecs");
	System.out.println("Parallel sum done in: "
			   + measureSumPerf(ParallelStreams::parallelSum, i) + " msecs");
	System.out.println("Iterative sum done in: "
			   + measureSumPerf(ParallelStreams::iterativeSum, i) + " msecs");
	System.out.println("LongStream sum done in: "
			   + measureSumPerf(ParallelStreams::rangedSum, i) + " msecs");
	System.out.println("ParallelLongStream sum done in: "
			   + measureSumPerf(ParallelStreams::parallelRangedSum, i) + " msecs");
	System.out.println("SideEffectSum sum done in: "
			   + measureSumPerf(ParallelStreams::sideEffectSum, i) + " msecs");
	System.out.println("ParallelSideEffectSum sum done in: "
			   + measureSumPerf(ParallelStreams::parallelSideEffectSum, i) + " msecs");
	System.out.println("ForkJoinCalculator sum done in: "
			   + measureSumPerf(ParallelStreams::forkJoinSum, i) + " msecs");
    }
    
    public static long measureSumPerf(Function<Long, Long> adder, long n){
	long fastest = Long.MAX_VALUE;
	for(int i=0;i<10;i++){
	    long start = System.nanoTime();
	    long sum = adder.apply(n);
	    long duration = (System.nanoTime()-start)/1_000_000;
	    System.out.println("Result : " + sum);
	    if(duration < fastest){
		fastest = duration;
	    }
	}
	return fastest;
    }
    
    public static long sequentialSum(long n){
	return Stream.iterate(1L, i -> i+1).limit(n).reduce(0L, Long::sum);
    }

    public static long parallelSum(long n){
	return Stream.iterate(1L, i -> i+1).limit(n).parallel().reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n){
	BigDecimal result = BigDecimal.valueOf(1L);
	for(long i=0;i<n;i++){
	    result = result.add(BigDecimal.valueOf(i));
	}
	return result.longValue();
    }

    public static long rangedSum(long n){
	return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n){
	return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }

    public static long sideEffectSum(long n){
	Accumulator accumulator = new Accumulator();
	LongStream.rangeClosed(1, n).forEach(accumulator::add);
	return accumulator.total;
    }

    public static long parallelSideEffectSum(long n){
	Accumulator accumulator = new Accumulator();
	LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
	return accumulator.total;
    }

    public static long forkJoinSum(long n){
	long[] numbers = LongStream.rangeClosed(1, n).toArray();
	ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
	return new ForkJoinPool().invoke(task);
    }

    private static int countWordsIteratively(String str){
	int counter = 0;
	boolean lastSpace = true;
	for(char c : str.toCharArray()){
	    if(Character.isWhitespace(c)){
		lastSpace = true;
	    }else{
		if(lastSpace){
		    counter++;
		}
		lastSpace = false;
	    }
	}
	return counter;
    }
    
    public static void run2(){
	System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
	Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
	//System.out.println("Found " + countWords(stream) + " words");
	//System.out.println("Found " + countWords(stream.parallel()) + " words");

	Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
	Stream<Character> stream1 = StreamSupport.stream(spliterator, true);

	System.out.println("Found " + countWords(stream1) + " words");
    }

    private static int countWords(Stream<Character> stream){
	WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
	return wordCounter.getCounter();
    }
}
