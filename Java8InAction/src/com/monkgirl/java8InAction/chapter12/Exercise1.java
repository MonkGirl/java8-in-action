package com.monkgirl.java8InAction.chapter12;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.LocalTime;
import java.time.Month;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Exercise1{
    public static void main(String...args){
	//run1();
	run2();
    }

    public static void run1(){
	//LocalDate date = LocalDate.of(2014,3,18);
	//LocalDate date = LocalDate.now();
	LocalDate date = LocalDate.parse("2018-07-18");
	
	System.out.println(date.getYear());
	System.out.println(date.getMonth());
	System.out.println(date.getDayOfMonth());

	System.out.println(date.getDayOfWeek());
	System.out.println(date.lengthOfMonth());
	System.out.println(date.isLeapYear());

	System.out.println(date.get(ChronoField.YEAR));
	System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
	System.out.println(date.get(ChronoField.DAY_OF_MONTH));

	//LocalTime time = LocalTime.now();
	//LocalTime time = LocalTime.of(23,21,26);
	LocalTime time = LocalTime.parse("23:21:26");

	
	System.out.println(time.getHour());
	System.out.println(time.getMinute());
	System.out.println(time.getSecond());

	System.out.println(time.get(ChronoField.HOUR_OF_DAY));
	System.out.println(time.get(ChronoField.MINUTE_OF_HOUR));
	System.out.println(time.get(ChronoField.SECOND_OF_MINUTE));

	LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
	System.out.println(dt1);
	LocalDateTime dt2 = date.atTime(time);
	System.out.println(dt2);
	LocalDateTime dt3 = time.atDate(date);
	System.out.println(dt3);
	LocalDateTime dt4 = LocalDateTime.of(date, time);
	System.out.println(dt4);

	System.out.println(dt4.toLocalDate());
	System.out.println(dt4.toLocalTime());
    }

    public static void run2(){
	System.out.println(Instant.ofEpochSecond(3));

	LocalTime time1 = LocalTime.parse("08:29:54");
	LocalTime time2 = LocalTime.now();
	System.out.println(time2);
	Duration duration1 = Duration.between(time1, time2);
	System.out.println(duration1.abs().toMinutes());
	System.out.println(duration1.abs().toHours());
	System.out.println(duration1.abs().toDays());

	LocalDate date1 = LocalDate.of(2017, 9, 27);
	LocalDate date2 = LocalDate.now();
	System.out.println(date1);
	System.out.println(date2);
	Period period = Period.between(date1, date2);
	System.out.println(period.getDays());
	System.out.println(period.getMonths());

	Duration tenMinutes = Duration.ofMinutes(10);
	System.out.println(tenMinutes);
	Duration threeHours = Duration.of(3, ChronoUnit.HOURS);
	System.out.println(threeHours);

	Period fiveDays = Period.ofDays(5);
	System.out.println(fiveDays);
	Period oneYearTwoMonthThreeDay = Period.of(1,2,3);
	System.out.println(oneYearTwoMonthThreeDay);
    }
}
