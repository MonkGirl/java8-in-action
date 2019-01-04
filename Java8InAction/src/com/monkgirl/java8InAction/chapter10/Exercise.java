package com.monkgirl.java8InAction.chapter10;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Exercise{
    public static void main(String...args){
	Insurance insurance = null;
	Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
	Optional<String> name = optInsurance.map(Insurance::getName);
	System.out.println(name);
	//System.out.println(optInsurance.get());

	Optional<Person> optPerson = Optional.of(new Person());
	//Optional<String> name = optPerson.map(Person::getCar).map(Car::getInsurance).map(Insurance::getName);
	//String carName = optPerson.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
	//System.out.println("The name of car : " + carName);
	Insurance in = new Insurance("CambridgeInsurance");
	Optional<Insurance> optInsurance1 = Optional.of(in);
	optInsurance1.filter(ins->"CambridgeInsurance".equals(ins.getName())).ifPresent(x -> System.out.println("ok"));

	Map<String, String> map = new HashMap<>();
	Optional<Object> value = Optional.ofNullable(map.get("key"));
	System.out.println(value.isPresent());

	System.out.println(stringToInt("232"));

	Properties props = new Properties();
	props.setProperty("a", "5");
	props.setProperty("b", "true");
	props.setProperty("c", "-3");

	System.out.println(readDuration(props, "b"));
    }

    public static Optional<Integer> stringToInt(String str){
	try{
	    return Optional.of(Integer.valueOf(str));
	}catch(NumberFormatException e){
	    return Optional.empty();
	}
    }

    public static int readDuration(Properties props, String name){
	/*String value = props.getProperty(name);
	if(value != null){
	    try{
		int i = Integer.parseInt(value);
		if(i > 0){
		    return i;
		}
	    }catch(NumberFormatException e){

	    }
	}
	return 0;*/

	return Optional.ofNullable(props.getProperty(name))
	    .flatMap(Exercise::stringToInt)
	    .filter(i->i>0)
	    .orElse(0);
    }
}
