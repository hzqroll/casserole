package com.roll.casserole.todel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zongqiang.hao
 * created on 2019-06-18 19:57.
 */
public class ExceptionTest {
    public static void main(String[] args) {
        Map<String, String> testMap = new ConcurrentHashMap<>();
        if (testMap.containsKey(null)) {
            System.out.println("not caints");
            testMap.get(null);
        }
    }
}
