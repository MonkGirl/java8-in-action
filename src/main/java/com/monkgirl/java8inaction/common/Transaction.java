package com.monkgirl.java8inaction.common;

import java.util.Currency;
import java.util.Locale;

public class Transaction{
    private  Trader trader;
    private  int year;
    private  int value;
    private  Currency currency;
    
    public Transaction(Trader trader, int year, int value){
	this.trader = trader;
	this.year = year;
	this.value = value;
	this.currency = Currency.getInstance(Locale.CHINA);
    }

    public Transaction(Trader trader, int year, int value, Currency currency){
     	this(trader, year, value);
     	this.currency = currency;
    }

    public Trader getTrader(){
	return trader;
    }

    public int getYear(){
	return year;
    }

    public int getValue(){
	return value;
    }

    public Currency getCurrency(){
	return currency;
    }

    public String toString(){
	return "{" + this.trader +", year: " + this.year+", " + "value: " + this.value + " , currency: "+ this.currency.getDisplayName() +"}";
    }
}
