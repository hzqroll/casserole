package com.roll.casserole.java8.joda;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;

/**
 * @author roll
 * created on 2019-08-19 21:02
 */
public class JavaTimeTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear() + "," + localDate.getDayOfMonth());

        LocalDate localDate1 = LocalDate.of(2017, 3, 4);
        System.out.println(localDate1);

        LocalDate localDate2 = LocalDate.of(2010, 3, 25);
        MonthDay monthDay = MonthDay.of(localDate2.getMonth(), localDate2.getDayOfMonth());
        MonthDay monthDay1 = MonthDay.from(LocalDate.of(2011, 3, 25));
        // 只比较月日
        if (monthDay.equals(monthDay1)) {
            System.out.println("同一天");
        } else {
            System.out.println("不是同一天");
        }

        LocalTime localTime = LocalTime.now();
        //21:08:35.391
        System.out.println(localTime);

        LocalTime time = localTime.plusHours(3).plusMinutes(2);
        //00:11:10.584
        System.out.println(time);
    }
}
