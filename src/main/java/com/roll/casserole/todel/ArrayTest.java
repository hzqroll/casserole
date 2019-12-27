package com.roll.casserole.todel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author roll
 * created on 2019-12-03 16:39
 */
public class ArrayTest {
    public static void main(String[] args) {
        Object[] a = new Long[10];
        //a[1] = "hahah";
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        List<Map<String, String >> list = new ArrayList<>();
        list.add(map);

        list.forEach(stringStringMap -> stringStringMap.put("1","3"));
        System.out.println(list);
    }
}
