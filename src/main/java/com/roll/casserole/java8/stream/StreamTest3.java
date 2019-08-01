package com.roll.casserole.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author roll
 * created on 2019-07-31 22:37
 */
public class StreamTest3 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println(list.stream().map(i -> i * 2).reduce(0, Integer::sum));
    }
}
