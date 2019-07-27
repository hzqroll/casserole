package com.roll.casserole.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * @author roll
 * created on 2019-07-26 08:44
 */
public class Test {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "3");
        map.put("3", "4");

        map.forEach((s, s2) -> System.out.println(s + " " + s2));

        //map.forEach(System.out::println(s,s2));
    }
}
