package com.roll.casserole.java8.stream;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author roll
 * created on 2019-08-01 22:30
 */
public class StreamTest6 {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.generate(UUID.randomUUID()::toString);
        stringStream.findFirst().ifPresent(System.out::println);

        Stream.iterate(1, item -> item + 2).limit(10).forEach(System.out::println);
    }
}
