package com.roll.casserole.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author roll
 * created on 2019-08-02 08:36
 */
public class StreamTest11 {
    public static void main(String[] args) {
        // 找到所有的单词去重并输出
        List<String> stringList = Arrays.asList("hello1 hello", "welcome world", "hello world");

        //stringList.stream().map(item -> item.split(" ")).distinct().collect(Collectors.toList()).forEach(item -> Arrays.asList(item).forEach(System.out::println));

        stringList.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println)
        ;


    }
}
