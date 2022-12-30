package com.monkgirl.java8inaction.common;

import java.util.function.Supplier;

public class LazyList<T> implements MyList<T>{
    public final T head;

    public final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail){
	this.head = head;
	this.tail = tail;
    }

    public T head(){
	return head;
    }

    public MyList<T> tail(){
	return tail.get();
    }

    public boolean isEmpty(){
	return false;
    }

    public MyList<T> filter(Predicate<T> p){
	return isEmpty() ? this :
	    p.test(head()) ? new LazyList<>(head(), ()->tail().filter(p)) :
	    tail().filter(p);
    }
}
