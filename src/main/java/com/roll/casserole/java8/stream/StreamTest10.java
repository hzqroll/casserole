package com.roll.casserole.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author roll
 * created on 2019-08-02 08:27
 */
public class StreamTest10 {
    public static void main(String[] args) {
        // 打印长度为5的第一个单词打印出来
        List<String> stringList = Arrays.asList("hello1", "world", "hello world");


        //stringList.stream().mapToInt(String::length).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
        stringList.stream().mapToInt(item -> {
            int length = item.length();
            // 流存在短路操作，只要找到符合条件的，下面不回去执行
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);


    }
}
