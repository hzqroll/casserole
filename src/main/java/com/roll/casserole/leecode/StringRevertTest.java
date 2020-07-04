package com.roll.casserole.leecode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 字符串反转
 * <p>@author roll
 * <p>created on 2020/7/1 9:53 下午
 */
public class StringRevertTest {

    public static String reversal(String s) {
        char[] chars = s.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            result.append(chars[i]);
        }
        return result.toString();
    }

    public static String reversal1(String s) {
        char[] chars = s.toCharArray();
        char[] result = new char[chars.length];
        for (int i = chars.length - 1; i >= chars.length / 2; i--) {
            result[i] = chars[chars.length - 1 - i];
            result[chars.length - 1 - i] = chars[i];
        }
        return new String(result);
    }

    public static String reversalWithStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = stack.pop();
        }
        return new String(result);
    }

    public static String reversalWithReverse(String s) {
        char[] result = new char[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            result[s.length() - 1 - i] = s.charAt(i);
        }
        return new String(result);
    }

    public static String reversalWithRecursive(String s) {
        if (s == null || s.length() == 0) return s;
        int length = s.length();
        if (length == 1) {
            return s;
        } else {
            return reversalWithRecursive(s.substring(1)) + s.charAt(0);
        }
    }

    public static char[] recursive(String s, int count, char[] result) {
        if (count == s.length()) {
            return result;
        } else {
            result[count] = s.charAt(s.length() - 1 - count);
            count++;
            return recursive(s, count, result);
        }
    }

    public static void main(String[] args) {
        System.out.println(reversal("hahahahaha"));
        System.out.println(reversal1("hahahahaha"));
        System.out.println(reversalWithStack("hahahahaha"));
        System.out.println(reversalWithReverse("hahahahaha"));
        System.out.println(reversalWithRecursive("hahahahaha"));
    }
}
