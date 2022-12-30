package com.monkgirl.java8inaction.chapter5;

import java.util.Arrays;
import com.monkgirl.java8inaction.common.Transaction;
import com.monkgirl.java8inaction.common.Trader;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

public class Exercise1{
    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan  = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Roma");

    private static List<Transaction> transactions = Arrays.asList(
								  new Transaction(brian, 2011, 500),
								  new Transaction(raoul, 2012, 1000),
								  new Transaction(raoul, 2011, 400),
								  new Transaction(mario, 2012, 710),
								  new Transaction(mario, 2012, 700),
								  new Transaction(alan, 2012, 950));

    public static void main(String...args){
	//run1();
	//run2();
	//run3();
	//run4();
	//run5();
	//run6();
	//run7();
	run8();
    }

    /**
     * 找出2011年发生的所有交易，并按交易额排序（从低到高）
     */
    public static void run1(){
        List<Transaction> trans = transactions
	    .stream()
	    .filter(t->t.getYear()==2011)
	    //.sorted((a, b)->a.getValue()-b.getValue())
	    .sorted(comparing(Transaction::getValue))
	    .collect(toList());
	System.out.println(trans);
    }

    /**
     * 交易员都在哪些不同的地方工作过
     */
    public static void run2(){
	List<String> cities = transactions.parallelStream()
	    .map(t->t.getTrader().getCity())
	    .distinct()
	    .collect(toList());
	System.out.println(cities);
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序
     */
    public static void run3(){
	List<Trader> traders = transactions.parallelStream()
	    .map(Transaction::getTrader)
	    .filter(t->"Cambridge".equals(t.getCity()))
	    .sorted(comparing(Trader::getName))
	    .distinct()
	    .collect(toList());
	System.out.println(traders);
    }

    /**
     * 返回交易员的姓名字符串，按字母顺序排序
     */
    public static void run4(){
	List<String> names = transactions.parallelStream()
	    .map(t->t.getTrader().getName())
	    .distinct()
	    .sorted(String::compareTo)
	    .collect(toList());
	System.out.println(names);
    }

    /**
     * 有没有交易员是在米兰工作的
     */
    public static void run5(){
	List<Trader> traders = transactions.parallelStream()
	    .map(t->t.getTrader())
	    .filter(d->"Milan".equals(d.getCity()))
	    .distinct()
	    .collect(toList());
	System.out.println(traders);
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额
     */
    public static void run6(){
	List<Integer> values = transactions.parallelStream()
	    .filter(t->"Cambridge".equals(t.getTrader().getCity()))
	    .map(t->t.getValue())
	    .collect(toList());
	System.out.println(values);
    }

    /**
     * 所有交易中，最高交易额是多少
     */
    public static void run7(){
	Optional<Integer> max = transactions.parallelStream()
	    .map(t->t.getValue())
	    .reduce(Integer::max);
	System.out.println(max.get());
    }

    /**
     * 找到交易额最小的交易
     */
    public static void run8(){
	Optional<Transaction> trans = transactions.parallelStream()
	    .reduce((t1, t2)->t1.getValue()<t2.getValue()?t1:t2);
	System.out.println(trans.get());
    }
}
