package com.monkgirl.java8inaction.chapter8;

import java.util.List;
import java.util.ArrayList;

public class Feed implements Subject{
    private final List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer){
	observers.add(observer);
    }

    public void notifyObservers(String tweet){
	observers.forEach(o -> o.notify(tweet));
    }
}
