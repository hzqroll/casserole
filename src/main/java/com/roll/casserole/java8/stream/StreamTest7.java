package com.roll.casserole.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author roll
 * created on 2019-08-02 07:38
 */
public class StreamTest7 {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("hello", "world", "hello world");

        //stringList.stream().map(item -> item.substring(0, 1).toUpperCase() + item.substring(1)).forEach(System.out::println);

        stringList.stream().map(item -> {
            String result = item.substring(0, 1).toUpperCase() + item.substring(1);
            System.out.println("result");
            return result;
        }).forEach(System.out::println);
    }
}
