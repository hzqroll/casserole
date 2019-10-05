package com.roll.casserole.java8.stream;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author roll
 * created on 2019-09-25 09:59
 */
public class StreamTest13 {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("qw");
        a.add("s");
        a.add("");
        a.add(" ");
        a.add("xcv");
        a.add("fghj");

        List<Integer> b = a.stream().map(member -> {
            int c = 0;
            try {
                if (StringUtils.isNotBlank(member)) {
                    if (member.length() > 2) {
                        c = member.length();
                        return c;
                    }
                }
            } catch (Exception e) {
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        System.out.println(b);

        double e = 12323123123D;
        System.out.println((int)e);
        System.out.println(Integer.MAX_VALUE);
    }
}
