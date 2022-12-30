package com.monkgirl.java8inaction.chapter12;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.time.temporal.ChronoField;

import static java.time.temporal.TemporalAdjusters.*;

public class Exercise1{
    public static void main(String...args){
	//run1();
	//run2();
	//run3();
	//run4();
	run5();
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

    public static void run3(){
	LocalDate date1 = LocalDate.of(2014,3,26);
	LocalDate date2 = LocalDate.now();

	Period period = Period.between(date1, date2);
	System.out.println(period.getDays());
	System.out.println(Period.between(date1, date2).getMonths());

	Period days = date1.until(date2);
	System.out.println(days.getDays());
	long allDays = date1.until(date2, ChronoUnit.DAYS);
	System.out.println(allDays);

	Period from = Period.from(period);
	System.out.println(from.getDays());
    }

    public static void run4(){
	LocalDate date = LocalDate.now();
	System.out.println(date.withYear(2011));
	System.out.println(date.withDayOfMonth(30));
	System.out.println(date.with(ChronoField.MONTH_OF_YEAR, 2));

	System.out.println(date.minusYears(7));
	System.out.println(date.plusDays(13));
	System.out.println(date.plus(20, ChronoUnit.MONTHS));

	System.out.println(date.with(nextOrSame(DayOfWeek.SATURDAY)));
	System.out.println(date.with(lastDayOfMonth()));
	System.out.println(date.with(firstDayOfMonth()));
	System.out.println(date.with(dayOfWeekInMonth(5, DayOfWeek.FRIDAY)));

	System.out.println(new NextWorkingDay().adjustInto(date));

	System.out.println(date.with(temporal -> {
		    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		    int dayToAdd = 1;
		    if(dow == DayOfWeek.FRIDAY){
			dayToAdd = 3;
		    }else if(dow == DayOfWeek.SATURDAY){
			dayToAdd = 2;
		    }
		    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		}));

	TemporalAdjuster nextWorkingDay = TemporalAdjusters
	    .ofDateAdjuster(temporal -> {
		    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		    int dayToAdd = 1;
		    if(dow == DayOfWeek.FRIDAY){
			dayToAdd = 3;
		    }else if(dow == DayOfWeek.SATURDAY){
			dayToAdd = 2;
		    }
		    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});
	System.out.println(date.with(nextWorkingDay));
    }

    public static void run5(){
	LocalDate date = LocalDate.of(2017,10,11);
	System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
	System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));

	System.out.println(LocalDate.parse("20150809", DateTimeFormatter.BASIC_ISO_DATE));

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy|MM|dd");
	System.out.println(date.format(formatter));

	ZoneId zoneID = TimeZone.getDefault().toZoneId();
	System.out.println(zoneID);
	System.out.println(date.atStartOfDay(zoneID));

	LocalDateTime time = LocalDateTime.now();
	System.out.println(time.atZone(zoneID));

	Instant instant = Instant.now();
	System.out.println(instant.atZone(zoneID));

	System.out.println(time.toInstant((ZoneOffset) zoneID));
	System.out.println(LocalDateTime.ofInstant(instant, zoneID));
    }
}

class NextWorkingDay implements TemporalAdjuster{
    @Override
    public Temporal adjustInto(Temporal temporal){
	DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
	int dayToAdd = 1;
	if(dow == DayOfWeek.FRIDAY){
	    dayToAdd = 3;
	}else if(dow == DayOfWeek.SATURDAY){
	    dayToAdd = 2;
	}
	return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
