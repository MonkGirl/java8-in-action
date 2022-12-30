package com.monkgirl.java8inaction.common;

public class Dish{
    private static int counter;
    private final int id = ++counter;
    private double calories;
    private String name;
    private Type type;
    private boolean vegetarian;
    
    public Dish(double calories, String name, Type type, boolean vegetarian){
	this.calories = calories;
	this.name = name;
	this.type = type;
	this.vegetarian = vegetarian;
    }

    public String getName(){
	return name;
    }

    public void setName(String name){
	this.name = name;
    }
    
    public double getCalories(){
	return calories;
    }

    public void setCalories(double calories){
	this.calories = calories;
    }

    public Type getType(){
	return type;
    }

    public void setType(Type type){
	this.type = type;
    }

    public boolean isVegetarian(){
	return vegetarian;
    }

    public void setVegetarian(boolean vegetarian){
	this.vegetarian = vegetarian;
    }
    
    public String toString(){
	return "Dish " + id + ": Name " + name + ", Calories " + calories;
    }

    public enum Type{
       MEAT, FISH, OTHER
    }

    public int compareTo(Dish dish){
	return dish.getName().compareTo(this.getName());
    }

    public CaloricLevel getCaloricLevel(){
	if(this.getCalories()<=400){
	    return CaloricLevel.DIET;
	}else if(this.getCalories()<=700){
	    return CaloricLevel.NORMAL;
	}else{
	    return CaloricLevel.FAT;
	}
    }
}
