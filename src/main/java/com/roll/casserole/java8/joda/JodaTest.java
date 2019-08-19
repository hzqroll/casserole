package com.roll.casserole.java8.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * @author roll
 * created on 2019-08-19 20:43
 */
public class JodaTest {
    public static Date convertUtCToDate(String utcDate) {
        DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        return dateTime.toDate();
    }

    public static String convertDate2UTC(Date javaDate) {
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }

    public static String convertDate2LocalDateTIme(Date javaDate, String dateFormat) {
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);
    }

    public static void main(String[] args) {
        // 标准时间：2015-11-04T09:22:54.876Z
        System.out.println(convertUtCToDate("2015-11-04T09:22:54.876Z"));//Wed Nov 04 17:22:54 CST 2015

        System.out.println(convertDate2UTC(new Date()));//2019-08-19T12:57:47.213Z

        System.out.println(convertDate2LocalDateTIme(new Date(), "yyyy-MM-dd"));//2019-08-19
    }


}
