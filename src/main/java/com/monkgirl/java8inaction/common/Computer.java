package com.monkgirl.java8inaction.common;

public class Computer{
    private String cpu;
    private String disk;
    private String memory;

    public Computer(){

    }

    public Computer(String cpu, String disk, String memory){
	this.cpu = cpu;
	this.disk = disk;
	this.memory = memory;
    }

    public String toString(){
	return "Cpu: " + cpu +" Disk: " + disk + " Memory: "+memory;
    }
}
