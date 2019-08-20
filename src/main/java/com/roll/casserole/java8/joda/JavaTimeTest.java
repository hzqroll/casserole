package com.roll.casserole.java8.joda;


import net.sf.cglib.core.Local;

import java.time.*;
import java.util.Set;

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

        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);//2019-08-20T08:30:31.566

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);//2019-08-20T08:30:31.566+08:00[Asia/Shanghai]


        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.isLeapYear());

        YearMonth yearMonth1 = YearMonth.of(2019, 4);
        System.out.println(yearMonth1);
        System.out.println(yearMonth1.lengthOfYear());
        System.out.println(yearMonth1.isLeapYear());

        LocalDate localDate3 = LocalDate.now();
        LocalDate localDate4 = LocalDate.of(2019, 11, 25);
        Period period = Period.between(localDate3, localDate4);
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

        System.out.println(Instant.now());
    }
}
