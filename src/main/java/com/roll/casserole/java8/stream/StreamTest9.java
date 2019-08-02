package com.roll.casserole.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author roll
 * created on 2019-08-02 08:20
 */
public class StreamTest9 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 5000000; i++) {
            String uuid = UUID.randomUUID().toString();
            list.add(uuid);
        }
        System.out.println("开始排序");
        long startTime = System.currentTimeMillis();
        list.stream().sorted().count();
        System.out.println("结束排序" + (System.currentTimeMillis() - startTime));

        System.out.println("开始排序");
        startTime = System.currentTimeMillis();
        list.parallelStream().sorted().count();
        System.out.println("结束排序" + (System.currentTimeMillis() - startTime));


    }
}
