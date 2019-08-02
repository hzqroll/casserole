package com.roll.casserole.java8.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author roll
 * created on 2019-08-02 07:45
 */
public class StreamTest8 {
    public static void main(String[] args) {
        //IntStream.iterate(0,i->(i+1)%2).distinct().limit(6).forEach(System.out::println);

        IntStream.iterate(0,i->(i+1)%2).limit(6).distinct().limit(6).forEach(System.out::println);

    }
}
