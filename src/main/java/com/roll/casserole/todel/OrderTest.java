package com.roll.casserole.todel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zongqiang.hao
 * created on 2018-12-07 11:37.
 */
public class OrderTest {
    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(1);
        list.add(7);
        list.add(4);
        list.add(2);
        list.add(2);
        list.add(0);
        System.out.println(list.toString());
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        System.out.println(list.toString());
    }
}
