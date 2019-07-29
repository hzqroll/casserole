package com.roll.casserole.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author roll
 * created on 2019-07-28 18:05
 */
public class Test3 {
    public static void main(String[] args) {
        TheInterface i1 = () -> {
        };
        System.out.println(i1.getClass().getInterfaces()[0]);

        TheInterface2 i2 = () -> {
        };
        System.out.println(i2.getClass().getInterfaces()[0]);

        new Thread(() -> System.out.println("new runnable")).start();

        List<String> lists = Arrays.asList("hello", "world", "hello  world");
        lists.forEach(item -> System.out.println(item.toUpperCase()));

        List<String> list2 = new ArrayList<>();
        lists.forEach(item -> list2.add(item.toUpperCase()));
        list2.forEach(System.out::println);

        lists.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));

        list2.stream().map(String::toUpperCase).forEach(System.out::println);

        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0] );
    }
}

@FunctionalInterface
interface TheInterface {
    void myMethod();

}

@FunctionalInterface
interface TheInterface2 {
    void myMethod2();

}