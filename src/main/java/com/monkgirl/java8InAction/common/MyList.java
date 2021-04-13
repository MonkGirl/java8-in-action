package com.monkgirl.java8InAction.common;


public interface MyList<T>{
    T head();

    MyList<T> tail();

    default boolean isEmpty(){
	return false;
    }

    default MyList<T> filter(Predicate<T> p){
	return null;
    }
}
