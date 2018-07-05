package com.monkgirl.java8InAction.chapter5;

import java.util.Arrays;
import com.monkgirl.java8InAction.common.Transaction;
import com.monkgirl.java8InAction.common.Trader;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

public class Exercise1{
    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan  = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Cambridge");

    private static List<Transaction> transactions = Arrays.asList(
								  new Transaction(brian, 2011, 500),
								  new Transaction(raoul, 2012, 1000),
								  new Transaction(raoul, 2011, 400),
								  new Transaction(mario, 2012, 710),
								  new Transaction(mario, 2012, 700),
								  new Transaction(alan, 2012, 950));

    public static void main(String...args){
	run1();
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
}
