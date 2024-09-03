package com.monkgirl.java8inaction.chapter10;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * 练习.
 *
 * @author MissYoung
 * @version 0.1
 * @since 2024-08-30 11:20:55
 */
public final class Exercise {
    private Exercise() {

    }

    /**
     * 主方法.
     *
     * @param args 命令行入参
     */
    public static void main(final String... args) {
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
        optInsurance1.filter(ins -> "CambridgeInsurance".equals(ins.getName())).ifPresent(x -> System.out.println("ok"));

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

    /**
     * 字符串转整型.
     *
     * @param str 字符串
     * @return 整型
     */
    public static Optional<Integer> stringToInt(final String str) {
        try {
            return Optional.of(Integer.valueOf(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * 读数据.
     *
     * @param props 属性
     * @param name  名称
     * @return 数值
     */
    public static int readDuration(final Properties props, final String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(Exercise::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }
}
