package com.monkgirl.java8InAction.common;

import java.util.ArrayList;
/**
 * Demo Class
 */
public class Apple{

    // the color of apple
    private String color;

    // the weight of apple
    private double weight;

    public Apple(){
       this("green", 302);
    }

    public Apple(double weight){
	this("green", weight);
    }
    
    public Apple(String color, double weight){
	this.color = color;
	this.weight = weight;
    }

    public void setWeight(Double weight){
	this.weight = weight;
    }

    public double getWeight(){
	return weight;
    }
    
    public void setColor(String color){
	this.color = color;
    }

    public String getColor(){
	return color;
    }

    public String toString(){
	return "[color : "+color+", weight: " + weight + "g]";
    }
}
