package com.roll.casserole.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author roll
 * created on 2020/2/26 4:05 下午
 */
public class Reverse {
    public static int reverse(int x) {
        List<String> list = new ArrayList<>();
        int flag = 1;
        if (x > 0) {
            while (x >= 10) {
                int a = x / 10 * 10;
                int b = x % a;
                list.add(String.valueOf(b));
                x = x / 10;
            }
            list.add(String.valueOf(x));
        } else {
            flag = -1;
            x = x * (flag);
            while (x >= 10) {
                int a = x / 10 * 10;
                int b = x % a;
                list.add(String.valueOf(b));
                x = x / 10;
            }
            list.add(String.valueOf(x));
        }
        if (list.size() >= 10 && (Integer.parseInt(list.get(0)) > 6)) {
            return 0;
        }
        StringBuilder a = new StringBuilder();
        if (list.get(0).equals("0")) {
            list.subList(1, list.size());
        }
        for (String s : list) {
            a.append(s);
        }
        if (a.toString().equals("")) {
            return 0;
        } else {
            return Integer.parseInt(a.toString()) * flag;
        }
    }

    public static int reverse1(int x) {
        int result = 0;
        while (x != 0) {
            int pop = x % 10;
            if ((result * 10) / 10 != result) {
                result = 0;
                break;
            }
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            result = result * 10 + pop;
            x = x / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        int a = -2147483648;
        System.out.println(reverse1(a));
    }

}
