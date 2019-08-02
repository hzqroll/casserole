package com.roll.casserole.java8.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author roll
 * created on 2019-08-01 22:11
 */
public class StreamTest5 {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("hello", "world", "hello world");
        stringList.forEach(String::toUpperCase);
        stringList.forEach(System.out::println);

        stringList.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        Stream<List<Integer>> listStream = Stream.of(Collections.singletonList(1), Collections.singletonList(2), Collections.singletonList(3));
        listStream.flatMap(theList -> theList.stream()).map(item -> item * item).forEach(System.out::println);
    }
}
