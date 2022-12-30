package com.monkgirl.java8inaction.chapter8;

public abstract class ProcessingObject<T>{
    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor){
	this.successor = successor;
    }

    public T handle(T input){
	T r = handWork(input);
	if(successor != null){
	    return successor.handle(r);
	}
	return r;
    }

    abstract protected T handWork(T input);
}
