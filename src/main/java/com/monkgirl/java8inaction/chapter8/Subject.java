package com.monkgirl.java8inaction.chapter8;

public interface Subject{
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
