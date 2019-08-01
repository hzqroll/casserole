package com.roll.casserole.java8.stream;

import java.util.stream.IntStream;

/**
 * @author roll
 * created on 2019-07-31 22:24
 */
public class StreamTest2 {
    public static void main(String[] args) {
        IntStream.of(1, 2, 3, 4).forEach(System.out::println);

        IntStream.range(4, 122).forEach(System.out::println );
    }
}
