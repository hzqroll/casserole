package com.roll.casserole.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author roll
 * created on 2019-08-04 14:57
 */
public class StreamTest12 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("hi", "hello", "你好");

        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");


        //List<String> list3 = list1.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
        //list3.forEach(System.out::println);

        try (Stream<String> stringStream = list1.stream()) {
            stringStream.
                    onClose(() -> {
                        System.out.println("onclose 1");
                        throw new RuntimeException();
                    }).
                    onClose(() -> System.out.println("onclose2")).
                    forEach(System.out::println);
        }
    }
}
