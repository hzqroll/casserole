package com.roll.casserole.java8.stream.compare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author roll
 * created on 2019-08-08 22:18
 */
public class MyCompareTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "wolcome");
        //Collections.sort(list);

        //Collections.sort(list, (item1, item2) -> item1.length() - item2.length());

        //Collections.sort(list, (item1, item2) -> item2.length() - item1.length());

        //Collections.sort(list, Comparator.comparingInt(String::length).reversed());

        //Collections.sort(list, Comparator.comparingInt((String item)->item.length()).reversed());

        //Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).thenComparing(String.CASE_INSENSITIVE_ORDER));

        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())).
                thenComparing(Comparator.reverseOrder()));
        System.out.println(list);
    }
}
