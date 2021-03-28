package com.roll.casserole.leecode;

/**
 * 跳台阶
 * <p>@author roll
 * <p>created on 2020/10/23 5:14 下午
 */
public class Stage {
    public static void main(String[] args) {
        int n = 100;
        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = 1;
        result[2] = 2;
        result[3] = 4;
        result[4] = 8;

        for (int i = 5; i < n + 1; i++) {
            result[i] = result[i - 1] + result[i - 2] + result[i - 3] + result[i - 4];
        }
        System.out.println(result[n]);
    }
}
