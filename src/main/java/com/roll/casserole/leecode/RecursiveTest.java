package com.roll.casserole.leecode;

import java.util.Arrays;

/**
 * <p>@author roll
 * <p>created on 2020/7/2 3:37 下午
 */
public class RecursiveTest {
    public static String reversalWithRecursive(String s) {
        if (s == null || s.length() == 0) return s;
        int length = s.length();
        if (length == 1) {
            return s;
        } else {
            return reversalWithRecursive(s.substring(1)) + s.charAt(0);
        }
    }

    static int count;

    /**
     * 把第n个盘子移动到c，把第n-1个盘子移动到b
     *
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static void hanoi(int n, String a, String b, String c) {
        count++;
        if (n == 1) {
            System.out.println("移动第" + n + "个盘子，从" + a + "移动到" + c + ".");
        } else {
            hanoi(n - 1, a, c, b);
            System.out.println("移动第" + n + "个盘子，从" + a + "移动到" + c + ".");
            hanoi(n - 1, b, a, c);
        }
    }

    /**
     * 二分查找
     * array 是从小到大排序
     */
    public static boolean findIndex(String s, char a) {
        int low = 0;
        int high = s.length();
        while (low <= high) {
            int middle = (low + high) / 2;
            char b = s.charAt(middle);
            if (a == b) {
                return true;
            } else {
                if (a > b) {
                    low = middle + 1;
                } else {
                    high = middle - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        reversalWithRecursive("abc");
        //hanoi(3, "A", "B", "C");
        //System.out.println("移动了" + count + "次.");
        System.out.println(findIndex("abcdefghijklmn", 'a'));
        int[] a = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(Arrays.copyOfRange(a, 1, 2)));
    }
}
