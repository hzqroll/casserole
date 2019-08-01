package com.roll.casserole.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author roll
 * created on 2019-07-31 22:21
 */
public class StreamTest1 {
    public static void main(String[] args) {
        Stream stream1 = Stream.of("hello", "world");

        String[] array = new String[]{"hello"};
        Stream stream2 = Stream.of(array);

        List<String> strings = Arrays.asList(array);
        Stream stream3 = strings.stream();


    }
}
